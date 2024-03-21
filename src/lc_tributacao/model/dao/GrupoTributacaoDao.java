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
import lc_tributacao.model.entities.Empresa;
import lc_tributacao.model.entities.GrupoTributacao;
import lc_tributacao.model.entities.Produto;

/**
 *
 * @author Rafael Nunes
 */
public class GrupoTributacaoDao {

    private Connection conn = null;

    private TreeMap<String, Integer> mapaCst;
    private TreeMap<String, Integer> mapaCfop;
    private TreeMap<String, String> mapaGrupoTributacao;

    public GrupoTributacaoDao(Connection conn) {
        this.conn = conn;
    }

    public List<GrupoTributacao> obterGruposTributacaoComBaseNaLocalidadeDaEmpresa(List<Produto> listasDeProdutos, Empresa empresa) throws SQLException {
        return criarGruposTributacaoPorLocalidadeEmpresa(listasDeProdutos, empresa);
    }

    private List<GrupoTributacao> criarGruposTributacaoPorLocalidadeEmpresa(List<Produto> listaDeProdutos, Empresa empresa) throws SQLException {
        List<GrupoTributacao> listaGruposTributacao = new ArrayList<>();
        Map<GrupoTributacao, GrupoTributacao> grupoMap = new HashMap<>();

        mapaCst = getMapaCst();
        mapaCfop = getMapaCfop();
        mapaGrupoTributacao = getMapaGrupoTributacao();

        for (Produto produto : listaDeProdutos) {
            GrupoTributacao grupoChave = new GrupoTributacao();
            grupoChave.setIdCst(mapaCst.get(produto.getCst()));
            grupoChave.setIdCfop(mapaCfop.get(produto.getCfop()));
            grupoChave.setPisSaida(produto.getPis());
            grupoChave.setCofinsSaida(produto.getCofins());
            grupoChave.setOrigem(produto.getOrigem());

            // Aqui é usado o Equal e HashCode da classe modelo com base no que foi definido lá.
            if (!grupoMap.containsKey(grupoChave)) {

                // Chave criada para verificar se grupo de tributacao ja existe no banco.
                String chave = mapaCst.get(produto.getCst()).toString() + mapaCfop.get(produto.getCfop()).toString() + produto.getPis() + produto.getCofins() + produto.getOrigem();

                // Mapa para verificar se o grupo no banco existe.
                if (!mapaGrupoTributacao.containsKey(chave)) {
                    GrupoTributacao novoGrupo = criarNovoGrupoTributacaoPorLocalidadeEmpresa(produto, empresa);
                    listaGruposTributacao.add(novoGrupo);
                    grupoMap.put(grupoChave, novoGrupo);
                }
            }
        }

        return listaGruposTributacao;
    }

    private GrupoTributacao criarNovoGrupoTributacaoPorLocalidadeEmpresa(Produto produto, Empresa empresa) throws SQLException {
        GrupoTributacao grupo = new GrupoTributacao();

        mapaCst = getMapaCst();
        mapaCfop = getMapaCfop();

        grupo.setNome("| CST " + produto.getCst() + "  /  CFOP " + produto.getCfop() + "  /  PIS e COFINS " + produto.getPis() + " * " + produto.getCofins() + "  /  ORIGEM " + produto.getOrigem());
        grupo.setUf(empresa.getEstado());
        grupo.setIdEstado(empresa.getIdEstado());
        grupo.setIdCst(mapaCst.get(produto.getCst()));
        grupo.setIdCfop(mapaCfop.get(produto.getCfop()));
        grupo.setOrigem(produto.getOrigem());
        grupo.setPisSaida(produto.getPis());
        grupo.setPisSaidaAliquota(produto.getPisAliq());
        grupo.setCofinsSaida(produto.getCofins());
        grupo.setCofinsSaidaAliquota(produto.getCofinsAliq());
        grupo.setIpiCst(produto.getIpi());
        grupo.setIpiAliquota(produto.getIpiAliq());
        grupo.setIcmsSaidaAliquota(produto.getIcmsAliq());
        grupo.setIcmsSaidaAliquotaRedBaseCalc(produto.getIcmsAliqRedBc());

        return grupo;
    }

    private TreeMap<String, Integer> getMapaCst() throws SQLException {
        TreeMap<String, Integer> map = new TreeMap();

        try (PreparedStatement pstm = conn.prepareStatement("select id, codigotributario from cst");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("codigotributario"), rs.getInt("id"));
            }
        }
        return map;
    }

    private TreeMap<String, Integer> getMapaCfop() throws SQLException {
        TreeMap<String, Integer> map = new TreeMap();

        try (PreparedStatement pstm = conn.prepareStatement("select id, codigocfop from cfop");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("codigocfop"), rs.getInt("id"));
            }
        }
        return map;
    }

    private TreeMap<String, String> getMapaGrupoTributacao() throws SQLException {
        TreeMap<String, String> mapaGrupo = new TreeMap<>();

        try (PreparedStatement pstm = conn.prepareStatement("SELECT id_cst, id_cfop, pis_saida, cofins_saida, origem FROM grupotributacao WHERE id > 1;");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                String chave = rs.getString("id_cst") + rs.getString("id_cfop") + rs.getString("pis_saida") + rs.getString("cofins_saida") + rs.getString("origem");

                if (!mapaGrupo.containsKey(chave)) {
                    mapaGrupo.put(chave, chave);
                }
            }
        }
        return mapaGrupo;
    }
}
