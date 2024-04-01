package lc_tributacao.controller.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.dataBase;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.senha;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.usuario;
import lc_tributacao.view.TelaInicial;

/**
 *
 * @author Rafael Nunes
 */
public class BancoDadosService {

    private Connection conn = null;

    public BancoDadosService(Connection conn, boolean podeDeletarTabelaTemp) throws SQLException, IOException, InterruptedException {
        this.conn = conn;
        if (podeDeletarTabelaTemp) {
            deletarTabelaTributacaoTemp();
            criarTabelaTributacaoTemp();
            backupTabelasProdutosEGrupoTributacaoBancoPrincipal();
        } 
    }

    private void criarTabelaTributacaoTemp() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("CREATE TABLE `tributacaoTemp` (\n"
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
                + "ENGINE = InnoDB;")) {

            pstm.executeUpdate();
        }
    }

    private void backupTabelasProdutosEGrupoTributacaoBancoPrincipal() throws IOException, InterruptedException {
        String comando = String.format("mysqldump --host=localhost --user=" + usuario + " --password=" + senha + " " + dataBase + " produto grupotributacao > produtoEgrupotributacao.sql");

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", comando);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        int exitCode = process.waitFor();

        if (exitCode == 0) {
            TelaInicial.getLog("\n**** BACKUP ****\nCaminho: C:\\LC sistemas - Softhouse\\produtoEgrupotributacao.sql");
            System.out.println("Backup criado com sucesso.");
        } else {
            TelaInicial.getLog("**** Erro ao criar o backup ****");
        }
    }

    private void deletarTabelaTributacaoTemp() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("DROP TABLE IF EXISTS `tributacaoTemp`;")) {
            pstm.executeUpdate();
        }
    }
}
