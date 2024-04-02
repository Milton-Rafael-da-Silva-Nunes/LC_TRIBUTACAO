package lc_tributacao.controller.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.dataBase;
import lc_tributacao.model.entities.Produto;
import static lc_tributacao.util.Versao.getVersaoPrograma;
import static lc_tributacao.view.TelaInicial.getLog;
import static lc_tributacao.view.TelaInicial.progressBarValor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Rafael Nunes
 */
public class ProdutoExportExcelService {

    private Connection conn = null;
    private List<Produto> listaProdutos = new ArrayList<>();

    public ProdutoExportExcelService(Connection conn) throws SQLException, IOException {
        this.conn = conn;
        getListaDeProdutos();
    }

    public Boolean gerarProdutosXls(String filePath) throws IOException, SQLException {
        if (listaProdutos.size() > 0) {
            progresso(filePath);
            return true;
        } else {
            getLog("**** ATENÇÃO ****\nNenhum produto encontrado na base: '" + dataBase + "'");
            return false;
        }
    }

    private void criarProdutosXls(String filePath) throws IOException, SQLException {
        Workbook workbook = null;
        FileOutputStream fileOut = null;

        try {
            workbook = new HSSFWorkbook();
            fileOut = new FileOutputStream(filePath);
            Sheet sheet = workbook.createSheet("Produtos");

            int linhas = listaProdutos.size();
            progressBarValor.setMaximum(linhas);

            // Crie o cabeçalho da planilha
            Row cabecalho = sheet.createRow(0);
            cabecalho.createCell(0).setCellValue("ID_PRODUTO");
            cabecalho.createCell(1).setCellValue("BARRAS");
            cabecalho.createCell(2).setCellValue("NOME");
            cabecalho.createCell(3).setCellValue("CST");
            cabecalho.createCell(4).setCellValue("CFOP");
            cabecalho.createCell(5).setCellValue("NCM");
            cabecalho.createCell(6).setCellValue("CEST");
            cabecalho.createCell(7).setCellValue("PIS");
            cabecalho.createCell(8).setCellValue("COFINS");
            cabecalho.createCell(9).setCellValue("IPI");
            cabecalho.createCell(10).setCellValue("ORIGEM");
            cabecalho.createCell(11).setCellValue("ALIQ_PIS");
            cabecalho.createCell(12).setCellValue("ALIQ_COFINS");
            cabecalho.createCell(13).setCellValue("ALIQ_IPI");
            cabecalho.createCell(14).setCellValue("ICMS_ALIQ");

            for (int i = 0; i < linhas; i++) {
                final int progressoAtual = i + 1;
                SwingUtilities.invokeLater(() -> progressBarValor.setValue(progressoAtual));

                Produto produto = listaProdutos.get(i);
                Row linha = sheet.createRow(i + 1);
                linha.createCell(0).setCellValue(produto.getIdProduto());
                linha.createCell(1).setCellValue(produto.getBarras());
                linha.createCell(2).setCellValue(produto.getNome());
                linha.createCell(3).setCellValue(produto.getCst());
                linha.createCell(4).setCellValue(produto.getCfop());
                linha.createCell(5).setCellValue(produto.getNcm());
                linha.createCell(6).setCellValue(produto.getCest());
                linha.createCell(7).setCellValue(produto.getPis());
                linha.createCell(8).setCellValue(produto.getCofins());
                linha.createCell(9).setCellValue(produto.getIpi());
                linha.createCell(10).setCellValue(produto.getOrigem());
                linha.createCell(11).setCellValue(produto.getPisAliq());
                linha.createCell(12).setCellValue(produto.getCofinsAliq());
                linha.createCell(13).setCellValue(produto.getIpiAliq());
                linha.createCell(14).setCellValue(produto.getIcmsAliq());
                linha.createCell(15).setCellValue(produto.getIcmsAliqRedBc());
                System.out.println("Produtos escritos --> " + produto);
            }

            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Tabela exportada com sucesso! \n\n<html><b>Caminho:</b> " + filePath + "\\CLASSIFICAO DE TRIBUTOS.xls</html>", getVersaoPrograma(), JOptionPane.INFORMATION_MESSAGE);
            getLog("\n**** RESULTADO EXPORTAÇÃO ****\nQuantidade de produtos: " + listaProdutos.size());

        } finally {
            fileOut.close();
            workbook.close();
        }
    }

    private List<Produto> getListaDeProdutos() throws SQLException {
        PreparedStatement pstm = conn.prepareStatement("SELECT\n"
                + "P.id as ID_PRODUTO,\n"
                + "P.codigo_barras as BARRAS,\n"
                + "P.NOME,\n"
                + "c.codigotributario as CST,\n"
                + "cf.codigocfop as CFOP,\n"
                + "N.CODIGO AS NCM,\n"
                + "CEST.CEST AS CEST,\n"
                + "TRIB_PISSAIDA AS PIS,\n"
                + "TRIB_COFINSSAIDA AS COFINS,\n"
                + "TRIB_IPISAIDA AS IPI,\n"
                + "p.origem_produto as ORIGEM,\n"
                + "p.trib_genero AS GENERO,\n"
                + "TRIB_PISALIQSAIDA AS ALIQ_PIS,\n"
                + "TRIB_COFINSALIQSAIDA AS ALIQ_COFINS,\n"
                + "TRIB_IPIALIQSAIDA AS ALIQ_IPI,\n"
                + "TRIB_ICMSALIQSAIDA AS ICMS_ALIQ,\n"
                + "trib_icmsaliqredbasecalcsaida AS ICMS_RED_BASE_CALC\n"
                + "FROM PRODUTO P\n"
                + "INNER JOIN CST C ON P.ID_CST = C.ID\n"
                + "INNER JOIN CFOP CF ON P.ID_CFOP = CF.ID\n"
                + "INNER JOIN NCM N ON P.ID_NCM = N.ID\n"
                + "INNER JOIN CEST CEST ON CEST.ID = P.ID_CEST\n"
                + "ORDER BY p.id;");
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Produto prod = new Produto();
            prod.setIdProduto(rs.getInt("ID_PRODUTO"));
            prod.setBarras(rs.getString("BARRAS"));
            prod.setNome(rs.getString("NOME"));
            prod.setCst(rs.getString("CST"));
            prod.setCfop(rs.getString("CFOP"));
            prod.setNcm(rs.getString("NCM"));
            prod.setCest(rs.getString("CEST"));
            prod.setPis(rs.getString("PIS"));
            prod.setCofins(rs.getString("COFINS"));
            prod.setIpi(rs.getString("IPI"));
            prod.setOrigem(rs.getString("ORIGEM"));
            prod.setGenero(rs.getString("GENERO"));
            prod.setPisAliq(rs.getDouble("ALIQ_PIS"));
            prod.setCofinsAliq(rs.getDouble("ALIQ_COFINS"));
            prod.setIpiAliq(rs.getDouble("ALIQ_IPI"));
            prod.setIcmsAliq(rs.getDouble("ICMS_ALIQ"));
            prod.setIcmsAliqRedBc(rs.getDouble("ICMS_RED_BASE_CALC"));
            listaProdutos.add(prod);
        }
        
        return listaProdutos;
    }

    public void progresso(String filePath) {
        ExecutorService d = Executors.newFixedThreadPool(1);
        d.execute(new Thread() {

            @Override
            public void run() {
                try {
                    criarProdutosXls(filePath + "\\CLASSIFICAO DE TRIBUTOS.xls");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        });
        d.shutdown();
    }
}
