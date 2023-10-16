package lc_tributacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static lc_tributacao.controller.conexao.GenericMysqlDAO.database;
import lc_tributacao.model.entities.Produtos;
import lc_tributacao.view.TelaInicial;

/**
 *
 * @author MIGRAÇÃO
 */
public class ProdutosDAO {

    final String dataBase = database; // Nome do BANCO DE DADOS do rede.txt
    private Connection conn = null;
    private final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Date dataHoraAtual = new Date();

    public ProdutosDAO(Connection conn) throws Exception {
        this.conn = conn;
    }

    public void executarUpdates() throws Exception {
        TelaInicial.getLogError("**** TRIBUTACAO ****");
        inserirNovosNCM();
        inserirNovosCEST();
        updateNCM();
        updateCEST();
        updateCST();
        updatePisCofinsIpiOrigemAliquotas();
    }

    public void InserirProdutosNoBanco(List<Produtos> listaProdutos) throws Exception {

        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO lc_tributacao.produtos(id_produto, barras, nome, cst, cfop, ncm, cest, pis, cofins, ipi, origem, genero, pis_aliq, cofins_aliq, ipi_aliq, icms_aliq, icms_aliq_red_bc, data_hora) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            for (Produtos produto : listaProdutos) {
                pstm.setInt(1, produto.getIdProduto());
                pstm.setString(2, produto.getBarras());
                pstm.setString(3, produto.getNome());
                pstm.setString(4, produto.getCst());
                pstm.setString(5, produto.getCfop());
                pstm.setString(6, produto.getNcm());
                pstm.setString(7, produto.getCest());
                pstm.setString(8, produto.getPis());
                pstm.setString(9, produto.getCofins());
                pstm.setString(10, produto.getIpi());
                pstm.setString(11, produto.getOrigem());
                pstm.setString(12, produto.getGenero());
                pstm.setDouble(13, produto.getPisAliq());
                pstm.setDouble(14, produto.getCofinsAliq());
                pstm.setDouble(15, produto.getIpiAliq());
                pstm.setDouble(16, produto.getIcmsAliq());
                pstm.setDouble(17, produto.getIcmsAliqRedBc());
                pstm.setString(18, sdf1.format(dataHoraAtual));
                pstm.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            TelaInicial.getLogError("Erro ao inserir produto: " + e.getMessage());
        }
    }

    private void inserirNovosNCM() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO " + dataBase + ".ncm(codigo, ex, descricao, aliquota_nacional, aliquota_internacional, aliquota_estadual, aliquota_municipal, vigenciainicio, vigenciafim, chave, versao, ativo) "
                + "SELECT ncm, '', '', 0.000, 0.000, 0.000, 0.000, null, null, '', '', 1 FROM lc_tributacao.produtos "
                + "WHERE length(ncm) = 8 "
                + "AND ncm NOT IN(SELECT codigo FROM lc_sistemas.ncm)GROUP BY ncm;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("NCMs novos: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao inserir novos NCMs -> " + e.getMessage());
        }
    }

    private void inserirNovosCEST() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO " + dataBase + ".cest(cest, ncm, descricao) "
                + "SELECT cest, '00000000', '' FROM lc_tributacao.produtos "
                + "WHERE length(cest)=7 "
                + "AND cest NOT IN(SELECT cest FROM " + dataBase + ".cest)GROUP BY cest;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("CESTs novos: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao inserir novos CESTs -> " + e.getMessage());
        }
    }

    private void updateCST() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE " + dataBase + ".produto p "
                + "INNER JOIN lc_tributacao.produtos t ON t.id_produto = p.id "
                + "INNER JOIN " + dataBase + ".cst c ON c.codigotributario = t.cst "
                + "SET p.id_cst = c.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("CSTs Alterados: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao executar Update CST -> " + e.getMessage());
        }
        // Executar CFOP ao final do CST (depende dele)
        updateCFOP();
    }

    private void updateCFOP() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("UPDATE " + dataBase + ".produto p "
                + "INNER JOIN lc_tributacao.produtos pp ON pp.id_produto = p.id "
                + "INNER JOIN " + dataBase + ".cfop c ON  c.codigocfop = pp.cfop "
                + "SET p.id_cfop = c.id;")) {

            pstm.executeUpdate();

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("CFOPs Alterados: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao executar Update CFOP -> " + e.getMessage());
        }
    }

    private void updatePisCofinsIpiOrigemAliquotas() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("UPDATE " + dataBase + ".produto p "
                + "INNER JOIN lc_tributacao.produtos t ON t.id_produto = p.id "
                + "SET p.trib_pissaida= t.pis,"
                + "p.trib_cofinssaida = t.cofins,"
                + "p.trib_ipisaida = t.ipi,"
                + "p.origem_produto = t.origem,"
                + "p.trib_genero = t.genero,"
                + "p.trib_pisaliqsaida = t.pis_aliq,"
                + "p.trib_cofinsaliqsaida = t.cofins_aliq,"
                + "p.trib_ipialiqsaida = t.ipi_aliq,"
                + "p.trib_icmsaliqsaida = t.icms_aliq, "
                + "p.trib_icmsaliqredbasecalcsaida = t.icms_aliq_red_bc;")) {

            pstm.executeUpdate();

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("PIS/COFINS/ALIQUOTAS Alterados: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao executar Update PIS/COFINS/ALIQUOTAS -> " + e.getMessage());
        }
    }

    private void updateNCM() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("UPDATE " + dataBase + ".produto p "
                + "INNER JOIN lc_tributacao.produtos pp ON pp.id_produto = p.id "
                + "INNER JOIN " + dataBase + ".ncm n ON n.codigo = pp.ncm "
                + "SET p.id_ncm = n.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("NCMs Alterados: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao executar Update NCM -> " + e.getMessage());
        }
    }

    private void updateCEST() throws Exception {
        try (PreparedStatement pstm = conn.prepareStatement("UPDATE " + dataBase + ".produto p "
                + "INNER JOIN lc_tributacao.produtos pp ON pp.id_produto = p.id "
                + "INNER JOIN " + dataBase + ".cest c ON c.cest = pp.cest "
                + "SET p.id_cest = c.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLogError("CESTs Alterados: " + resultado);

        } catch (SQLException e) {
            TelaInicial.getLogError("Erro ao executar Update CEST -> " + e.getMessage());
        }
    }
}
