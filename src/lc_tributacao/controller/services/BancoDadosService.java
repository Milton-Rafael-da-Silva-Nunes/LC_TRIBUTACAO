package lc_tributacao.controller.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lc_tributacao.controller.conexao.GenericMysqlDAO;
import lc_tributacao.view.TelaInicial;

/**
 *
 * @author Rafael Nunes
 */
public class BancoDadosService extends GenericMysqlDAO {

    private Connection conn = null;

    public BancoDadosService(Connection conn) {
        this.conn = conn;
    }

    public void criarBancoLcTributacao() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("CREATE DATABASE IF NOT EXISTS lc_tributacao;")) {
            pstm.executeUpdate();
        } catch (SQLException e) {
            TelaInicial.getLog("Erro ao criar banco de dados (banco: lc_tributação): " + e.getMessage());
        }
    }

    public void criarTabelaProduto() throws SQLException {
        deletarTabelaProdutos();
        
        try (PreparedStatement pstm = conn.prepareStatement("CREATE TABLE lc_tributacao.`produtos` (\n"
                + "  `id_produto` INTEGER(11),\n"
                + "  `barras` VARCHAR(30),\n"
                + "  `nome` VARCHAR(180),\n"
                + "  `id_grupotributacao` INTEGER(11),\n"
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
                + "ENGINE = InnoDB;")) {

            pstm.executeUpdate();

        } catch (SQLException e) {
            TelaInicial.getLog("Erro ao criar tabela 'produtos' (banco: lc_tributação): " + e.getMessage());
        } 
    }

    public void criarBackupTabelaProduto() throws IOException {
        String comando = String.format("mysqldump --host=localhost --user=" + usuario + " --password=" + senha + " " + dataBase + " produto > produto.sql");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", comando);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                TelaInicial.getLog("\n**** BACKUP ****\nCaminho: C:\\LC sistemas - Softhouse\\produto.sql");
                System.out.println("Backup criado com sucesso.");
            } else {
                TelaInicial.getLog("**** Erro ao criar o backup ****");
            }
        } catch (IOException | InterruptedException e) {
            TelaInicial.getLog("Erro inesperado! \n\n" + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private void deletarTabelaProdutos() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("DROP TABLE IF EXISTS lc_tributacao.`produtos`;")){
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tabela 'produtos' (banco: lc_tributação): " + e.getMessage());
        } 
    }
}
