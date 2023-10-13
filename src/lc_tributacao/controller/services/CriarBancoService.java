package lc_tributacao.controller.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lc_tributacao.controller.conexao.GenericMysqlDAO;
import lc_tributacao.view.TelaInicial;

/**
 *
 * @author MIGRAÇÃO
 */
public class CriarBancoService extends GenericMysqlDAO {

    private Connection conn = null;

    public CriarBancoService(Connection conn) {
        this.conn = conn;
    }

    public void criarBanco() throws SQLException {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS lc_tributacao;");
            pstm.executeUpdate();
        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao criar banco de dados (banco: lc_tributação): " + e.getMessage());
        } finally {
            GenericMysqlDAO.closeStatement(pstm);
        }
    }

    public void criarTabelaProdutos() throws SQLException {
        PreparedStatement pstm = null;
        deletarTabelaProdutos();
        try {
            pstm = conn.prepareStatement("CREATE TABLE lc_tributacao.`produtos` (\n"
                    + "  `id_produto` INTEGER(11),\n"
                    + "  `barras` VARCHAR(30),\n"
                    + "  `nome` VARCHAR(180),\n"
                    + "  `cst` VARCHAR(5),\n"
                    + "  `cfop` VARCHAR(6),\n"
                    + "  `ncm` VARCHAR(12),\n"
                    + "  `cest` VARCHAR(10),\n"
                    + "  `pis` VARCHAR(3),\n"
                    + "  `cofins` VARCHAR(3),\n"
                    + "  `ipi` VARCHAR(3),\n"
                    + "  `origem` VARCHAR(5),\n"
                    + "  `genero` VARCHAR(5),\n"
                    + "  `pis_aliq` DOUBLE(12,3),\n"
                    + "  `cofins_aliq` DOUBLE(12,3),\n"
                    + "  `ipi_aliq` DOUBLE(12,3),\n"
                    + "  `icms_aliq` DOUBLE(12,3),\n"
                    + "  `icms_aliq_red_bc` DOUBLE(12,3),\n"
                    + "  `data_hora` DATETIME,\n"
                    + "   PRIMARY KEY (`id_produto`)"
                    + ")\n"
                    + "ENGINE = InnoDB;");

            pstm.executeUpdate();

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao criar tabela 'produtos' (banco: lc_tributação): " + e.getMessage());
        } finally {
            GenericMysqlDAO.closeStatement(pstm);
        }
    }

    private void deletarTabelaProdutos() throws SQLException {
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement("DROP TABLE IF EXISTS lc_tributacao.`produtos`;");
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tabela 'produtos' (banco: lc_tributação): " + e.getMessage());
        } finally {
            GenericMysqlDAO.closeStatement(pstm);
        }
    }
}
