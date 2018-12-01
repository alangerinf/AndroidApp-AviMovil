package pe.ibao.agromovil.models.vo.entitiesDB;

public class RecomendacionVO {

    private int id;
    private String frecuencia;
    private String unidad;
    private String cantidad;
    private String comentario;
    private int idCriterioRecomendacion;
    private int idVisita;

    public RecomendacionVO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdCriterioRecomendacion() {
        return idCriterioRecomendacion;
    }

    public void setIdCriterioRecomendacion(int idCriterioRecomendacion) {
        this.idCriterioRecomendacion = idCriterioRecomendacion;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }
}
