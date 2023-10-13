package lc_tributacao.model.entities;

/**
 *
 * @author MIGRAÇÃO
 */
public class Produtos {

    private Integer idProduto;
    private String barras;
    private String nome;
    private String cst;
    private String cfop;
    private String ncm;
    private String cest;
    private String pis;
    private String cofins;
    private String ipi;
    private String origem;
    private String genero;
    private Double pisAliq;
    private Double cofinsAliq;
    private Double ipiAliq;
    private Double icmsAliq;
    private Double icmsAliqRedBc;

    public Produtos() {
    }

    public Produtos(Integer idProduto, String barras, String nome, String cst, String cfop, String ncm, String cest, String pis, String cofins, String ipi, String origem, String genero, Double pisAliq, Double cofinsAliq, Double ipiAliq, Double icmsAliq, Double icmsAliqRedBc) {
        this.idProduto = idProduto;
        this.barras = barras;
        this.nome = nome;
        this.cst = cst;
        this.cfop = cfop;
        this.ncm = ncm;
        this.cest = cest;
        this.pis = pis;
        this.cofins = cofins;
        this.ipi = ipi;
        this.origem = origem;
        this.genero = genero;
        this.pisAliq = pisAliq;
        this.cofinsAliq = cofinsAliq;
        this.ipiAliq = ipiAliq;
        this.icmsAliq = icmsAliq;
        this.icmsAliqRedBc = icmsAliqRedBc;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getCofins() {
        return cofins;
    }

    public void setCofins(String cofins) {
        this.cofins = cofins;
    }

    public String getIpi() {
        return ipi;
    }

    public void setIpi(String ipi) {
        this.ipi = ipi;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPisAliq() {
        return pisAliq;
    }

    public void setPisAliq(Double pisAliq) {
        this.pisAliq = pisAliq;
    }

    public Double getCofinsAliq() {
        return cofinsAliq;
    }

    public void setCofinsAliq(Double cofinsAliq) {
        this.cofinsAliq = cofinsAliq;
    }

    public Double getIpiAliq() {
        return ipiAliq;
    }

    public void setIpiAliq(Double ipiAliq) {
        this.ipiAliq = ipiAliq;
    }

    public Double getIcmsAliq() {
        return icmsAliq;
    }

    public void setIcmsAliq(Double icmsAliq) {
        this.icmsAliq = icmsAliq;
    }

    public Double getIcmsAliqRedBc() {
        return icmsAliqRedBc;
    }

    public void setIcmsAliqRedBc(Double icmsAliqRedBc) {
        this.icmsAliqRedBc = icmsAliqRedBc;
    }

    @Override
    public String toString() {
        return idProduto + " " + barras + " " + nome + " " + cst + " " + cfop + " " + ncm + " " + cest + " " + pis + " " + cofins + " " + origem + " " + genero;
    }
}
