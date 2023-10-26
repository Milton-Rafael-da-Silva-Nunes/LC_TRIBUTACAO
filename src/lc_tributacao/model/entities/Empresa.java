package lc_tributacao.model.entities;

/**
 *
 * @author Rafael Nunes
 */
public class Empresa {
    
    private Integer id;
    private String estado;
    private Integer idEstado;

    public Empresa() {
    }

    public Empresa(Integer id, String estado, Integer idEstado) {
        this.id = id;
        this.estado = estado;
        this.idEstado = idEstado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return id + " " + estado + " " + idEstado;
    }
}
