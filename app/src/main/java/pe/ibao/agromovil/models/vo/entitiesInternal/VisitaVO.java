package pe.ibao.agromovil.models.vo.entitiesInternal;

public class VisitaVO {
    private int id;
    private String fecha;
    private double lat;
    private long lon;
    private String hora;

    public VisitaVO(int id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public VisitaVO() {
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}