package pe.ibao.agromovil.models.vo.entitiesInternal;

public class EvaluacionVO {
    private int id;
    private int idTipoInspeccion;
    private int idFundo;
    private int idVariedad;
    private double lon;
    private double lat;
    private String qr;

    public EvaluacionVO(int id, int idTipoInspeccion, int idFundo, int idVariedad) {
        this.id = id;
        this.idTipoInspeccion = idTipoInspeccion;
        this.idFundo = idFundo;
        this.idVariedad = idVariedad;
    }

    public EvaluacionVO() {
    }

    public EvaluacionVO(int id, int idTipoInspeccion, int idFundo, int idVariedad, double lon, double lat, String qr) {
        this.id = id;
        this.idTipoInspeccion = idTipoInspeccion;
        this.idFundo = idFundo;
        this.idVariedad = idVariedad;
        this.lon = lon;
        this.lat = lat;
        this.qr = qr;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoInspeccion() {
        return idTipoInspeccion;
    }

    public void setIdTipoInspeccion(int idTipoInspeccion) {
        this.idTipoInspeccion = idTipoInspeccion;
    }

    public int getIdFundo() {
        return idFundo;
    }

    public void setIdFundo(int idFundo) {
        this.idFundo = idFundo;
    }

    public int getIdVariedad() {
        return idVariedad;
    }

    public void setIdVariedad(int idVariedad) {
        this.idVariedad = idVariedad;
    }
}
