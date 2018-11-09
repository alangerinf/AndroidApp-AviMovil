package pe.ibao.agromovil.entities.entitiesInternal;

public class FotoMuestra {
    private int id;
    private String fecha;
    private String hora;
    private String uri;

    public FotoMuestra(int id, String fecha, String hora, String uri) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.uri = uri;
    }

    public FotoMuestra() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
