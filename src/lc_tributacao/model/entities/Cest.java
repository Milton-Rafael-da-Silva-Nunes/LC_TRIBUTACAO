
package lc_tributacao.model.entities;

import java.util.Objects;

/**
 *
 * @author Rafael Nunes
 */
public class Cest implements Comparable<Cest>{
    
    private Integer id;
    private String cest;
    private final String ncm;
    private final String descricao;

    public Cest() {
        ncm = "00000000";
        descricao = "";
    }

    public Cest(Integer id, String cest, String ncm, String descricao) {
        this.id = id;
        this.cest = cest;
        this.ncm = ncm;
        this.descricao = descricao;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
    }

    public String getNcm() {
        return ncm;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cest);
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
        final Cest other = (Cest) obj;
        if (!Objects.equals(this.cest, other.cest)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Cest outroCest) {
        return cest.compareTo(outroCest.cest);
    }

    @Override
    public String toString() {
        return id + " " + cest;
    }
}
