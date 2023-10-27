package lc_tributacao.model.entities;

import java.util.Objects;

/**
 *
 * @author Rafael Nunes
 */
public class Ncm implements Comparable<Ncm>{
    
    private Integer id;
    private String codigo;
    private final String ex;
    private final String descricao;
    private final Double aliquotaNacional;
    private final Double aliquotaInternacional;
    private final Double aliquotaEstadual;
    private final Double aliquotaMunicipal;
    private final String vigenciaInicio;
    private final String vigenciaFim;
    private final String chave;
    private final String versao;
    private final Integer ativo;

    public Ncm() {
        ex = "";
        descricao = "";
        aliquotaNacional = 0.0;
        aliquotaInternacional = 0.0;
        aliquotaEstadual = 0.0;
        aliquotaMunicipal = 0.0;
        vigenciaInicio = null;
        vigenciaFim = null;
        chave = "";
        versao = "";
        ativo = 1;
    }

    public Ncm(Integer id, String codigo, String ex, String descricao, Double aliquotaNacional, Double aliquotaInternacional, Double aliquotaEstadual, Double aliquotaMunicipal, String vigenciaInicio, String vigenciaFim, String chave, String versao, Integer ativo) {
        this.id = id;
        this.codigo = codigo;
        this.ex = ex;
        this.descricao = descricao;
        this.aliquotaNacional = aliquotaNacional;
        this.aliquotaInternacional = aliquotaInternacional;
        this.aliquotaEstadual = aliquotaEstadual;
        this.aliquotaMunicipal = aliquotaMunicipal;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFim = vigenciaFim;
        this.chave = chave;
        this.versao = versao;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEx() {
        return ex;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getAliquotaNacional() {
        return aliquotaNacional;
    }

    public Double getAliquotaInternacional() {
        return aliquotaInternacional;
    }

    public Double getAliquotaEstadual() {
        return aliquotaEstadual;
    }

    public Double getAliquotaMunicipal() {
        return aliquotaMunicipal;
    }

    public String getVigenciaInicio() {
        return vigenciaInicio;
    }

    public String getVigenciaFim() {
        return vigenciaFim;
    }

    public String getChave() {
        return chave;
    }

    public String getVersao() {
        return versao;
    }

    public Integer getAtivo() {
        return ativo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ncm other = (Ncm) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ncm outroNcm) {
        return codigo.compareTo(outroNcm.getCodigo());
    }

    @Override
    public String toString() {
        return id + " " + codigo;
    }
}
