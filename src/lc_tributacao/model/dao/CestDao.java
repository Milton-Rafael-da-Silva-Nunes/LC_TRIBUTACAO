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
import lc_tributacao.model.entities.Cest;
import lc_tributacao.model.entities.Produto;

/**
 *
 * @author Rafael Nunes
 */
public class CestDao {

    private Connection conn = null;

    public CestDao(Connection conn) {
        this.conn = conn;
    }

    private TreeMap<String, String> mapCest;

    public List<Cest> obterCestsComBaseNosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        return criarNovosCestsComBaseNaListaDosProdutos(listaDeProdutos);
    }

    private List<Cest> criarNovosCestsComBaseNaListaDosProdutos(List<Produto> listaDeProdutos) throws SQLException {
        List<Cest> listaDeCests = new ArrayList<>();
        Map<Cest, Cest> cestMap = new HashMap<>();

        mapCest = getMapaCest();

        for (Produto produto : listaDeProdutos) {
            Cest cestChave = new Cest();
            cestChave.setCest(produto.getCest());

            if (!cestMap.containsKey(cestChave)) {
                String chave = produto.getCest();

                if (!mapCest.containsKey(chave)) {
                    Cest novoCest = criarNovoCest(produto);
                    listaDeCests.add(novoCest);
                    cestMap.put(cestChave, cestChave);
                }
            }
        }
        return listaDeCests;
    }

    private Cest criarNovoCest(Produto produto) {
        Cest cest = new Cest();
        cest.setCest(produto.getCest());
        return cest;
    }

    private TreeMap<String, String> getMapaCest() throws SQLException {
        TreeMap<String, String> mapaCest = new TreeMap<>();

        try (PreparedStatement pstm = conn.prepareStatement("SELECT cest FROM cest;");
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                String chave = rs.getString("cest");
                if (!mapaCest.containsKey(chave)) {
                    mapaCest.put(chave, chave);
                }
            }
        }
        return mapaCest;
    }
}
