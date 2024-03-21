package lc_tributacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lc_tributacao.controller.conexao.exceptions.Exceptions;
import lc_tributacao.model.entities.Cest;
import lc_tributacao.model.entities.GrupoTributacao;
import lc_tributacao.model.entities.Ncm;
import lc_tributacao.model.entities.Produto;
import lc_tributacao.util.DataHora;
import lc_tributacao.view.TelaInicial;

/**
 *
 * @author Rafael Nunes
 */
public class ProdutoDao {

    private Connection conn = null;

    public ProdutoDao(Connection conn) throws SQLException {
        this.conn = conn;
    }

    private final List<Integer> listaGrupoDeTributacao = new ArrayList<>();
    private final List<Integer> listaCest = new ArrayList<>();
    private final List<Integer> listaNcm = new ArrayList<>();

    public void executarAcoesNoBancoPrincipal() throws SQLException {
        TelaInicial.getLog("\n**** RESULTADO ****\n-> Inseridos");
        ncmsInseridos();
        cestsInseridos();
        gruposDeTributacaoInseridos();
        TelaInicial.getLog("-> Atualizados");
        updateProdutosIdNCM();
        updateProdutosIdCEST();
        updateProdutosIdCST();
        updateProdutosIdCFOP();
        updateProdutosOrigem();
        updateProdutosGenero();
        updateProdutosPisEAliquota();
        updateProdutosCofinsEAliquota();
        updateProdutosIpiEAliquota();
        updateProdutosIdGrupoTributacao();
    }

    public void InserirProdutosNaTabelaTemp(List<Produto> listaProdutos) throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO tributacaotemp(id_produto, barras, nome, cst, cfop, ncm, cest, pis, cofins, ipi, origem, genero, pis_aliq, cofins_aliq, ipi_aliq, icms_aliq, icms_aliq_red_bc, data_hora) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            for (Produto produto : listaProdutos) {
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
                pstm.setString(18, DataHora.getDataHoraAtual());
                pstm.executeUpdate();
            }
        }
    }

    public void inserirNovosGruposDeTributacaoBancoPrincipal(List<GrupoTributacao> listaGrupos) throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO grupotributacao(nome, uf, id_estado, id_ncm, id_cest, id_cst, id_cfop, id_cfop_bonificacao, id_cfop_devolucao, id_cfop_transferencia, ncm, origem, genero, icms_saida_aliquota, icms_saida_aliquota_red_base_calc, icms_fcp_aliquota, icms_observacao_fiscal, icms_difererimento_aliquota, icms_desonerado_aliquota, icms_st_aliquota, icms_st_red_base_calc_aliquota, icms_isencao_aliquota, icms_iva, icms_codigo_beneficio_fiscal, pis_saida, pis_saida_aliquota, pis_nri, cofins_saida, cofins_saida_aliquota, cofins_nri, ipi_cst, ipi_ex, ipi_aliquota, ipi_codigo_enquadramento, preco_cmv, imendes_codigo_grupo, imendes_codigo_regra, imendes_datahora_alteracao, datahora_alteracao, ativo) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            for (GrupoTributacao grupo : listaGrupos) {
                pstm.setString(1, grupo.getNome());
                pstm.setString(2, grupo.getUf());
                pstm.setInt(3, grupo.getIdEstado());
                pstm.setInt(4, grupo.getIdNcm());
                pstm.setInt(5, grupo.getIdCest());
                pstm.setInt(6, grupo.getIdCst());
                pstm.setInt(7, grupo.getIdCfop());
                pstm.setInt(8, grupo.getIdCfopBonificacao());
                pstm.setInt(9, grupo.getIdCfopDevolucao());
                pstm.setInt(10, grupo.getIdCfopTransferencia());
                pstm.setString(11, grupo.getNcm());
                pstm.setString(12, grupo.getOrigem());
                pstm.setString(13, grupo.getGenero());
                pstm.setDouble(14, grupo.getIcmsSaidaAliquota());
                pstm.setDouble(15, grupo.getIcmsSaidaAliquotaRedBaseCalc());
                pstm.setDouble(16, grupo.getIcmsFcpAliquota());
                pstm.setString(17, grupo.getIcmsObservacaoFiscal());
                pstm.setDouble(18, grupo.getIcmsDifererimentoAliquota());
                pstm.setDouble(19, grupo.getIcmsDesoneradoAliquota());
                pstm.setDouble(20, grupo.getIcmsStAliquota());
                pstm.setDouble(21, grupo.getIcmsStRedBaseCalcAliquota());
                pstm.setDouble(22, grupo.getIcmsIsencaoAliquota());
                pstm.setDouble(23, grupo.getIcmsIva());
                pstm.setString(24, grupo.getIcmsCodigoBeneficioFiscal());
                pstm.setString(25, grupo.getPisSaida());
                pstm.setDouble(26, grupo.getPisSaidaAliquota());
                pstm.setString(27, grupo.getPisNri());
                pstm.setString(28, grupo.getCofinsSaida());
                pstm.setDouble(29, grupo.getCofinsSaidaAliquota());
                pstm.setString(30, grupo.getCofinsNri());
                pstm.setString(31, grupo.getIpiCst());
                pstm.setString(32, grupo.getIpiEx());
                pstm.setDouble(33, grupo.getIpiAliquota());
                pstm.setString(34, grupo.getIpiCodigoEnquadramento());
                pstm.setDouble(35, grupo.getPrecoCmv());
                pstm.setString(36, grupo.getImendesCodigoGrupo());
                pstm.setString(37, grupo.getImendesCodigoRegra());
                pstm.setString(38, grupo.getImendesDatahoraAlteracao());
                pstm.setString(39, grupo.getDatahoraAlteracao());
                pstm.setInt(40, grupo.getAtivo());
                int linhas = pstm.executeUpdate();

                // Logica para setar a ID do retorno do grupo de tributacao inserido.
                if (linhas > 0) {
                    ResultSet rs = pstm.getGeneratedKeys();
                    if (rs.next()) {
                        grupo.setId(rs.getInt(1));
                    }
                    rs.close();
                } else {
                    throw new Exceptions("Erro inesperado! \n\n\nNenhuma linha efetada!");
                }

                listaGrupoDeTributacao.add(1);
                System.out.println("NOVO GRUPO: " + grupo);
            }
        }
    }

    public void inserirNovosNCMs(List<Ncm> listaDeNcms) throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO ncm(codigo, ex, descricao, aliquota_nacional, aliquota_internacional, aliquota_estadual, aliquota_municipal, vigenciainicio, vigenciafim, chave, versao, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            int linhas = 0;

            for (Ncm ncm : listaDeNcms) {
                pstm.setString(1, ncm.getCodigo());
                pstm.setString(2, ncm.getEx());
                pstm.setString(3, ncm.getDescricao());
                pstm.setDouble(4, ncm.getAliquotaNacional());
                pstm.setDouble(5, ncm.getAliquotaInternacional());
                pstm.setDouble(6, ncm.getAliquotaEstadual());
                pstm.setDouble(7, ncm.getAliquotaMunicipal());
                pstm.setString(8, ncm.getVigenciaInicio());
                pstm.setString(9, ncm.getVigenciaFim());
                pstm.setString(10, ncm.getChave());
                pstm.setString(11, ncm.getVersao());
                pstm.setInt(12, ncm.getAtivo());
                linhas = pstm.executeUpdate();

                if (linhas > 0) {
                    ResultSet rs = pstm.getGeneratedKeys();
                    if (rs.next()) {
                        ncm.setId(rs.getInt(1));
                    }
                    rs.close();
                } else {
                    throw new Exceptions("Erro inesperado! \n\n\nNenhuma linha efetada!");
                }

                listaNcm.add(1);
                System.out.println("NOVO NCM: " + ncm);
            }
        }
    }

    public void inserirNovosCESTs(List<Cest> listaCests) throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO cest(cest, ncm, descricao) "
                + "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            int linhas = 0;

            for (Cest cest : listaCests) {
                pstm.setString(1, cest.getCest());
                pstm.setString(2, cest.getNcm());
                pstm.setString(3, cest.getDescricao());
                linhas = pstm.executeUpdate();

                if (linhas > 0) {
                    ResultSet rs = pstm.getGeneratedKeys();
                    if (rs.next()) {
                        cest.setId(rs.getInt(1));
                    }
                    rs.close();
                } else {
                    throw new Exceptions("Erro inesperado! \n\n\nNenhuma linha efetada!");
                }

                listaCest.add(1);
                System.out.println("NOVO CEST: " + cest);
            }
        }
    }

    private void updateProdutosIdNCM() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp pp ON pp.id_produto = p.id "
                + "INNER JOIN ncm n ON n.codigo = pp.ncm "
                + "SET p.id_ncm = n.id "
                + "WHERE p.id_ncm != n.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("NCM   : " + resultado);

        }
    }

    private void updateProdutosIdCEST() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp pp ON pp.id_produto = p.id "
                + "INNER JOIN cest c ON c.cest = pp.cest "
                + "SET p.id_cest = c.id "
                + "WHERE p.id_cest != c.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("CEST  : " + resultado);

        }
    }

    private void updateProdutosIdCST() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "INNER JOIN cst c ON c.codigotributario = t.cst "
                + "SET p.id_cst = c.id "
                + "WHERE p.id_cst != c.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("CST   : " + resultado);

        }
    }

    private void updateProdutosIdCFOP() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp pp ON pp.id_produto = p.id "
                + "INNER JOIN cfop c ON c.codigocfop = pp.cfop "
                + "SET p.id_cfop = c.id "
                + "WHERE p.id_cfop != c.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("CFOP  : " + resultado);

        }
    }

    private void updateProdutosPisEAliquota() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "SET p.trib_pissaida = t.pis,"
                + "p.trib_pisaliqsaida = t.pis_aliq "
                + "WHERE p.trib_pissaida != t.pis;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("PIS/ALIQUOTA   : " + resultado);

        }
    }

    private void updateProdutosCofinsEAliquota() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "SET p.trib_cofinssaida = t.cofins,"
                + "p.trib_cofinsaliqsaida = t.cofins_aliq "
                + "WHERE p.trib_cofinssaida != t.cofins;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("COFINS/ALIQUOTA: " + resultado);

        }
    }

    private void updateProdutosIpiEAliquota() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "SET p.trib_ipisaida = t.ipi,"
                + "p.trib_ipialiqsaida = t.ipi_aliq "
                + "WHERE p.trib_ipisaida != t.ipi;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("IPI/ALIQUOTA   : " + resultado);

        }
    }

    private void updateProdutosOrigem() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "SET p.origem_produto = t.origem "
                + "WHERE p.origem_produto != t.origem;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("ORIGEM: " + resultado);

        }
    }

    private void updateProdutosGenero() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN tributacaotemp t ON t.id_produto = p.id "
                + "SET p.trib_genero = t.genero "
                + "WHERE p.trib_genero != t.genero;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("GENERO: " + resultado);

        }
    }

    private void updateProdutosIdGrupoTributacao() throws SQLException {
        try (PreparedStatement pstm = conn.prepareStatement(
                "UPDATE produto p "
                + "INNER JOIN grupotributacao g on g.id_cst = p.id_cst "
                + "AND p.id_cfop = g.id_cfop "
                + "AND p.trib_pissaida = g.pis_saida "
                + "AND p.trib_cofinssaida = g.cofins_saida "
                + "AND p.origem_produto = g.origem "
                + "SET p.id_grupotributacao = g.id "
                + "WHERE g.id > 1 "
                + "AND p.id_grupotributacao != g.id;")) {

            int resultado = pstm.executeUpdate();
            TelaInicial.getLog("GRUPO TRIBUTACAO: " + resultado);

        }
    }

    private void gruposDeTributacaoInseridos() {
        TelaInicial.getLog("GRUPO TRIB: " + listaGrupoDeTributacao.size() + "\n");
    }

    private void cestsInseridos() {
        TelaInicial.getLog("CEST : " + listaCest.size());
    }

    private void ncmsInseridos() {
        TelaInicial.getLog("NCM : " + listaNcm.size());
    }
}
