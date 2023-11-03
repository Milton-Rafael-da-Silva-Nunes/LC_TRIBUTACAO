package lc_tributacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lc_tributacao.controller.conexao.exceptions.Exceptions;
import lc_tributacao.model.entities.Ncm;
import lc_tributacao.model.entities.Produto;

/**
 *
 * @author Rafael Nunes
 */
public class NcmDao {

    private Connection conn = null;

    public NcmDao(Connection conn) {
        this.conn = conn;
    }

    private TreeMap<String, String> mapNcm;

    public List<Ncm> obterCestsComBaseNosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        return criarNovosNcmsComBaseNaListaDosProdutos(listaDeProdutos);
    }

    private List<Ncm> criarNovosNcmsComBaseNaListaDosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        List<Ncm> listaDeNcms = new ArrayList<>();
        Map<Ncm, Ncm> ncmMap = new HashMap<>();

        mapNcm = getMapaNcm();

        for (Produto produto : listaDeProdutos) {
            Ncm ncmChave = new Ncm();
            ncmChave.setCodigo(produto.getNcm());

            if (!ncmMap.containsKey(ncmChave)) {
                String chave = produto.getNcm();

                if (!mapNcm.containsKey(chave)) {
                    Ncm novoNcm = criarNovoNcm(produto);
                    listaDeNcms.add(novoNcm);
                    ncmMap.put(novoNcm, novoNcm);
                }
            }
        }

        return listaDeNcms;
    }

    private Ncm criarNovoNcm(Produto produto) {
        Ncm ncm = new Ncm();
        ncm.setCodigo(produto.getNcm());
        return ncm;
    }

    private TreeMap<String, String> getMapaNcm() throws SQLException {
        TreeMap<String, String> mapaNcm = new TreeMap<>();

        try (PreparedStatement pstm = conn.prepareStatement("SELECT codigo FROM ncm;");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                String chave = rs.getString("codigo");
                if (!mapaNcm.containsKey(chave)) {
                    mapaNcm.put(chave, chave);
                }
            }
        } catch (SQLException e) {
            throw new Exceptions("Erro getMapaNcm: " + e.getMessage());
        }
        return mapaNcm;
    }
}
