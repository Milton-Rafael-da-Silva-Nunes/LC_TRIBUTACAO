package lc_tributacao.model.entities;

import lc_tributacao.util.DataHora;

/**
 *
 * @author Rafael Nunes
 */
public class GrupoTributacao {

    private Integer id;
    private String nome;
    private String uf;
    private Integer idEstado;
    private Integer idNcm;
    private Integer idCest;
    private Integer idCst;
    private Integer idCfop;
    private Integer idCfopBonificacao;
    private Integer idCfopDevolucao;
    private Integer idCfopTransferencia;
    private String ncm;
    private String origem;
    private String genero;
    private Double icmsSaidaAliquota;
    private Double icmsSaidaAliquotaRedBaseCalc;
    private Double icmsFcpAliquota;
    private String icmsObservacaoFiscal;
    private Double icmsDifererimentoAliquota;
    private Double icmsDesoneradoAliquota;
    private Double icmsStAliquota;
    private Double icmsStRedBaseCalcAliquota;
    private Double icmsIsencaoAliquota;
    private Double icmsIva;
    private String icmsCodigoBeneficioFiscal;
    private String pisNri;
    private String cofinsNri;
    private String ipiCst;
    private String ipiEx;
    private Double ipiAliquota;
    private String ipiCodigoEnquadramento;
    private String imendesCodigoGrupo;
    private String imendesCodigoRegra;
    private String imendesDatahoraAlteracao;
    private String datahoraAlteracao;
    private String pisSaida;
    private Double pisSaidaAliquota;
    private String cofinsSaida;
    private Double cofinsSaidaAliquota;
    private Double precoCmv;
    private Integer ativo;

    public GrupoTributacao() {
        idNcm = 1;
        idCest = 0;
        idCfopBonificacao = 414;
        idCfopDevolucao = 23;
        idCfopTransferencia = 311;
        genero = "";
        ncm = "00000000";
        icmsFcpAliquota = 0.0;
        icmsObservacaoFiscal = "CRIADO POR AJUSTE xls";
        icmsDifererimentoAliquota = 0.0;
        icmsDesoneradoAliquota = 0.0;
        icmsStAliquota = 0.0;
        icmsStRedBaseCalcAliquota = 0.0;
        icmsIsencaoAliquota = 0.0;
        icmsIva = 0.0;
        icmsCodigoBeneficioFiscal = "";
        pisNri = "";
        cofinsNri = "";
        ipiEx = "";
        ipiCodigoEnquadramento = "";
        imendesCodigoGrupo = "";
        imendesCodigoRegra = "";
        imendesDatahoraAlteracao = null;
        datahoraAlteracao = DataHora.getDataHoraAtual();
        precoCmv = 0.0;
        ativo = 1;

    }

    public GrupoTributacao(Integer id, String nome, String uf, Integer idEstado, Integer idNcm, Integer idCest, Integer idCst, Integer idCfop, Integer idCfopBonificacao, Integer idCfopDevolucao, Integer idCfopTransferencia, String ncm, String origem, String genero, Double icmsSaidaAliquota, Double icmsSaidaAliquotaRedBaseCalc, Double icmsFcpAliquota, String icmsObservacaoFiscal, Double icmsDifererimentoAliquota, Double icmsDesoneradoAliquota, Double icmsStAliquota, Double icmsStRedBaseCalcAliquota, Double icmsIsencaoAliquota, Double icmsIva, String icmsCodigoBeneficioFiscal, String pisNri, String cofinsNri, String ipiCst, String ipiEx, Double ipiAliquota, String ipiCodigoEnquadramento, String imendesCodigoGrupo, String imendesCodigoRegra, String imendesDatahoraAlteracao, String datahoraAlteracao, String pisSaida, Double pisSaidaAliquota, String cofinsSaida, Double cofinsSaidaAliquota, Double precoCmv, Integer ativo) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.idEstado = idEstado;
        this.idNcm = idNcm;
        this.idCest = idCest;
        this.idCst = idCst;
        this.idCfop = idCfop;
        this.idCfopBonificacao = idCfopBonificacao;
        this.idCfopDevolucao = idCfopDevolucao;
        this.idCfopTransferencia = idCfopTransferencia;
        this.ncm = ncm;
        this.origem = origem;
        this.genero = genero;
        this.icmsSaidaAliquota = icmsSaidaAliquota;
        this.icmsSaidaAliquotaRedBaseCalc = icmsSaidaAliquotaRedBaseCalc;
        this.icmsFcpAliquota = icmsFcpAliquota;
        this.icmsObservacaoFiscal = icmsObservacaoFiscal;
        this.icmsDifererimentoAliquota = icmsDifererimentoAliquota;
        this.icmsDesoneradoAliquota = icmsDesoneradoAliquota;
        this.icmsStAliquota = icmsStAliquota;
        this.icmsStRedBaseCalcAliquota = icmsStRedBaseCalcAliquota;
        this.icmsIsencaoAliquota = icmsIsencaoAliquota;
        this.icmsIva = icmsIva;
        this.icmsCodigoBeneficioFiscal = icmsCodigoBeneficioFiscal;
        this.pisNri = pisNri;
        this.cofinsNri = cofinsNri;
        this.ipiCst = ipiCst;
        this.ipiEx = ipiEx;
        this.ipiAliquota = ipiAliquota;
        this.ipiCodigoEnquadramento = ipiCodigoEnquadramento;
        this.imendesCodigoGrupo = imendesCodigoGrupo;
        this.imendesCodigoRegra = imendesCodigoRegra;
        this.imendesDatahoraAlteracao = imendesDatahoraAlteracao;
        this.datahoraAlteracao = datahoraAlteracao;
        this.pisSaida = pisSaida;
        this.pisSaidaAliquota = pisSaidaAliquota;
        this.cofinsSaida = cofinsSaida;
        this.cofinsSaidaAliquota = cofinsSaidaAliquota;
        this.precoCmv = precoCmv;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdNcm() {
        return idNcm;
    }

    public void setIdNcm(Integer idNcm) {
        this.idNcm = idNcm;
    }

    public Integer getIdCest() {
        return idCest;
    }

    public void setIdCest(Integer idCest) {
        this.idCest = idCest;
    }

    public Integer getIdCst() {
        return idCst;
    }

    public void setIdCst(Integer idCst) {
        this.idCst = idCst;
    }

    public Integer getIdCfop() {
        return idCfop;
    }

    public void setIdCfop(Integer idCfop) {
        this.idCfop = idCfop;
    }

    public Integer getIdCfopBonificacao() {
        return idCfopBonificacao;
    }

    public void setIdCfopBonificacao(Integer idCfopBonificacao) {
        this.idCfopBonificacao = idCfopBonificacao;
    }

    public Integer getIdCfopDevolucao() {
        return idCfopDevolucao;
    }

    public void setIdCfopDevolucao(Integer idCfopDevolucao) {
        this.idCfopDevolucao = idCfopDevolucao;
    }

    public Integer getIdCfopTransferencia() {
        return idCfopTransferencia;
    }

    public void setIdCfopTransferencia(Integer idCfopTransferencia) {
        this.idCfopTransferencia = idCfopTransferencia;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
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

    public Double getIcmsSaidaAliquota() {
        return icmsSaidaAliquota;
    }

    public void setIcmsSaidaAliquota(Double icmsSaidaAliquota) {
        this.icmsSaidaAliquota = icmsSaidaAliquota;
    }

    public Double getIcmsSaidaAliquotaRedBaseCalc() {
        return icmsSaidaAliquotaRedBaseCalc;
    }

    public void setIcmsSaidaAliquotaRedBaseCalc(Double icmsSaidaAliquotaRedBaseCalc) {
        this.icmsSaidaAliquotaRedBaseCalc = icmsSaidaAliquotaRedBaseCalc;
    }

    public Double getIcmsFcpAliquota() {
        return icmsFcpAliquota;
    }

    public void setIcmsFcpAliquota(Double icmsFcpAliquota) {
        this.icmsFcpAliquota = icmsFcpAliquota;
    }

    public String getIcmsObservacaoFiscal() {
        return icmsObservacaoFiscal;
    }

    public void setIcmsObservacaoFiscal(String icmsObservacaoFiscal) {
        this.icmsObservacaoFiscal = icmsObservacaoFiscal;
    }

    public Double getIcmsDifererimentoAliquota() {
        return icmsDifererimentoAliquota;
    }

    public void setIcmsDifererimentoAliquota(Double icmsDifererimentoAliquota) {
        this.icmsDifererimentoAliquota = icmsDifererimentoAliquota;
    }

    public Double getIcmsDesoneradoAliquota() {
        return icmsDesoneradoAliquota;
    }

    public void setIcmsDesoneradoAliquota(Double icmsDesoneradoAliquota) {
        this.icmsDesoneradoAliquota = icmsDesoneradoAliquota;
    }

    public Double getIcmsStAliquota() {
        return icmsStAliquota;
    }

    public void setIcmsStAliquota(Double icmsStAliquota) {
        this.icmsStAliquota = icmsStAliquota;
    }

    public Double getIcmsStRedBaseCalcAliquota() {
        return icmsStRedBaseCalcAliquota;
    }

    public void setIcmsStRedBaseCalcAliquota(Double icmsStRedBaseCalcAliquota) {
        this.icmsStRedBaseCalcAliquota = icmsStRedBaseCalcAliquota;
    }

    public Double getIcmsIsencaoAliquota() {
        return icmsIsencaoAliquota;
    }

    public void setIcmsIsencaoAliquota(Double icmsIsencaoAliquota) {
        this.icmsIsencaoAliquota = icmsIsencaoAliquota;
    }

    public Double getIcmsIva() {
        return icmsIva;
    }

    public void setIcmsIva(Double icmsIva) {
        this.icmsIva = icmsIva;
    }

    public String getIcmsCodigoBeneficioFiscal() {
        return icmsCodigoBeneficioFiscal;
    }

    public void setIcmsCodigoBeneficioFiscal(String icmsCodigoBeneficioFiscal) {
        this.icmsCodigoBeneficioFiscal = icmsCodigoBeneficioFiscal;
    }

    public String getPisNri() {
        return pisNri;
    }

    public void setPisNri(String pisNri) {
        this.pisNri = pisNri;
    }

    public String getCofinsNri() {
        return cofinsNri;
    }

    public void setCofinsNri(String cofinsNri) {
        this.cofinsNri = cofinsNri;
    }

    public String getIpiCst() {
        return ipiCst;
    }

    public void setIpiCst(String ipiCst) {
        this.ipiCst = ipiCst;
    }

    public String getIpiEx() {
        return ipiEx;
    }

    public void setIpiEx(String ipiEx) {
        this.ipiEx = ipiEx;
    }

    public Double getIpiAliquota() {
        return ipiAliquota;
    }

    public void setIpiAliquota(Double ipiAliquota) {
        this.ipiAliquota = ipiAliquota;
    }

    public String getIpiCodigoEnquadramento() {
        return ipiCodigoEnquadramento;
    }

    public void setIpiCodigoEnquadramento(String ipiCodigoEnquadramento) {
        this.ipiCodigoEnquadramento = ipiCodigoEnquadramento;
    }

    public String getImendesCodigoGrupo() {
        return imendesCodigoGrupo;
    }

    public void setImendesCodigoGrupo(String imendesCodigoGrupo) {
        this.imendesCodigoGrupo = imendesCodigoGrupo;
    }

    public String getImendesCodigoRegra() {
        return imendesCodigoRegra;
    }

    public void setImendesCodigoRegra(String imendesCodigoRegra) {
        this.imendesCodigoRegra = imendesCodigoRegra;
    }

    public String getImendesDatahoraAlteracao() {
        return imendesDatahoraAlteracao;
    }

    public void setImendesDatahoraAlteracao(String imendesDatahoraAlteracao) {
        this.imendesDatahoraAlteracao = imendesDatahoraAlteracao;
    }

    public String getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    public void setDatahoraAlteracao(String datahoraAlteracao) {
        this.datahoraAlteracao = datahoraAlteracao;
    }

    public String getPisSaida() {
        return pisSaida;
    }

    public void setPisSaida(String pisSaida) {
        this.pisSaida = pisSaida;
    }

    public Double getPisSaidaAliquota() {
        return pisSaidaAliquota;
    }

    public void setPisSaidaAliquota(Double pisSaidaAliquota) {
        this.pisSaidaAliquota = pisSaidaAliquota;
    }

    public String getCofinsSaida() {
        return cofinsSaida;
    }

    public void setCofinsSaida(String cofinsSaida) {
        this.cofinsSaida = cofinsSaida;
    }

    public Double getCofinsSaidaAliquota() {
        return cofinsSaidaAliquota;
    }

    public void setCofinsSaidaAliquota(Double cofinsSaidaAliquota) {
        this.cofinsSaidaAliquota = cofinsSaidaAliquota;
    }

    public Double getPrecoCmv() {
        return precoCmv;
    }

    public void setPrecoCmv(Double precoCmv) {
        this.precoCmv = precoCmv;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return id + " " + nome;
    }
}
