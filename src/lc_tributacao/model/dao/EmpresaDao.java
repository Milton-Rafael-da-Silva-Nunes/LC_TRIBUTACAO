package lc_tributacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lc_tributacao.model.entities.Empresa;

/**
 *
 * @author Rafael Nunes
 */
public class EmpresaDao {

    private Connection conn = null;

    public EmpresaDao(Connection conn) {
        this.conn = conn;
    }

    public Empresa getEmpresa(int idEmpresa) throws SQLException {
        return getDadosEmpresaSelecionadaNoRedeTxt(idEmpresa);
    }

    private Empresa getDadosEmpresaSelecionadaNoRedeTxt(int idEmpresa) throws SQLException {
        ResultSet rs;
        Empresa empresa = null;

        try (PreparedStatement pstm = conn.prepareStatement(""
                + "SELECT id, id_estados, (SELECT uf FROM estados WHERE id = id_estados)estado FROM empresa WHERE id = ?;")) {

            pstm.setInt(1, idEmpresa);
            rs = pstm.executeQuery();

            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setEstado(rs.getString("estado"));
                empresa.setIdEstado(rs.getInt("id_estados"));
                System.out.println("DADOS EMPRESA: " + empresa);
            }

            rs.close();
        }
        return empresa;
    }
}
