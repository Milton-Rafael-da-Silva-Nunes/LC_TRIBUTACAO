package lc_tributacao.controller.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lc_tributacao.model.entities.GrupoTributacao;
import lc_tributacao.model.entities.Produtos;

/**
 *
 * @author Rafael Nunes
 */
public class GrupoTributacaoService {

    private Connection conn = null;

    private TreeMap<String, Integer> mapaCst;
    private TreeMap<String, Integer> mapaCfop;

    public GrupoTributacaoService(Connection conn) {
        this.conn = conn;
    }

    public List<GrupoTributacao> getListaGruposTributacao(List<Produtos> listasDeProdutos) throws SQLException {
        return criarGruposTributacao(listasDeProdutos);
    }

    private List<GrupoTributacao> criarGruposTributacao(List<Produtos> listaDeProdutos) {
        List<GrupoTributacao> listaGruposTributacao = new ArrayList<>();
        Map<GrupoTributacao, GrupoTributacao> grupoMap = new HashMap<>();

        mapaCst = getMapaCst();
        mapaCfop = getMapaCfop();
        
        for (Produtos produto : listaDeProdutos) {
            GrupoTributacao grupoChave = new GrupoTributacao();
            grupoChave.setIdCst(mapaCst.get(produto.getCst()));
            grupoChave.setIdCfop(mapaCfop.get(produto.getCfop()));
            grupoChave.setOrigem(produto.getOrigem());
            grupoChave.setPisSaida(produto.getPis());
            grupoChave.setCofinsSaida(produto.getCofins());

            if (!grupoMap.containsKey(grupoChave)) {
                GrupoTributacao novoGrupo = criarNovoGrupoTributacao(produto);
                listaGruposTributacao.add(novoGrupo);
                grupoMap.put(grupoChave, novoGrupo);
            }
        }

        return listaGruposTributacao;
    }

    private GrupoTributacao encontrarGrupoCorrespondente(List<GrupoTributacao> lista, Produtos produto) {
        for (GrupoTributacao grupo : lista) {

            if (grupo.getIdCst() == mapaCst.get(produto.getCst())
                    && grupo.getIdCfop() == mapaCfop.get(produto.getCfop())
                    && grupo.getOrigem().equals(produto.getOrigem())
                    && grupo.getPisSaida().equals(produto.getPis())
                    && grupo.getCofinsSaida().equals(produto.getCofins())) {
                return grupo;
            }
        }

        return null;
    }

    private GrupoTributacao criarNovoGrupoTributacao(Produtos produto) {
        GrupoTributacao grupo = new GrupoTributacao();

        mapaCst = getMapaCst();
        mapaCfop = getMapaCfop();

        grupo.setNome("| CST " + produto.getCst() + "  /  CFOP " + produto.getCfop() + "  /  PIS e COFINS " + produto.getPis() + " * " + produto.getCofins() + "  /  ORIGEM " + produto.getOrigem());
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

    private TreeMap<String, Integer> getMapaCst() {
        TreeMap<String, Integer> map = new TreeMap();
        try (PreparedStatement pstm = conn.prepareStatement("select id, codigotributario from cst");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("codigotributario"), rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("Erro em getMapCst: " + e.getMessage());
        }

        return map;
    }

    private TreeMap<String, Integer> getMapaCfop() {
        TreeMap<String, Integer> map = new TreeMap();
        try (PreparedStatement pstm = conn.prepareStatement("select id, codigocfop from cfop");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("codigocfop"), rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println("Erro em getMapaCfop: " + e.getMessage());
        }

        return map;
    }
}
