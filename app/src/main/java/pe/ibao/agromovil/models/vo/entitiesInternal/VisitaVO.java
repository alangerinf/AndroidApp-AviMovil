package pe.ibao.agromovil.models.vo.entitiesInternal;


import java.util.ArrayList;
import java.util.List;

public class VisitaVO {
    private int id;
    private String fechaHoraIni;
    private String fechaHoraFin;
    private double lat;
    private double lon;
    private int idEmpresa;
    private String nameEmpresa;
    private int idFundo;
    private String nameFundo;
    private int idCultivo;
    private String nameCultivo;
    private int idVariedad;
    private String nameVariedad;
    private int idContacto;
    private String nameContacto;
    private boolean editing;
    private boolean statusContactoPersonalizado;
    private String contactoPersonalizado;
    private String areaFundoVariedad;
    private String sistemaRiego;

    public String getSistemaRiego() {
        return sistemaRiego;
    }

    public void setSistemaRiego(String sistemaRiego) {
        this.sistemaRiego = sistemaRiego;
    }

    public String getAreaFundoVariedad() {
        return areaFundoVariedad;
    }

    public void setAreaFundoVariedad(String areaFundoVariedad) {
        this.areaFundoVariedad = areaFundoVariedad;
    }

    public VisitaVO() {

    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean isStatusContactoPersonalizado() {
        return statusContactoPersonalizado;
    }

    public void setStatusContactoPersonalizado(boolean statusContactoPersonalizado) {
        this.statusContactoPersonalizado = statusContactoPersonalizado;
    }

    public String getContactoPersonalizado() {
        return contactoPersonalizado;
    }

    public void setContactoPersonalizado(String contactoPersonalizado) {
        this.contactoPersonalizado = contactoPersonalizado;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public String getNameContacto() {
        return nameContacto;
    }

    public void setNameContacto(String nameContacto) {
        this.nameContacto = nameContacto;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }



    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public void setNameEmpresa(String nameEmpresa) {
        this.nameEmpresa = nameEmpresa;
    }

    public String getNameFundo() {
        return nameFundo;
    }

    public void setNameFundo(String nameFundo) {
        this.nameFundo = nameFundo;
    }

    public String getNameVariedad() {
        return nameVariedad;
    }

    public void setNameVariedad(String nameVariedad) {
        this.nameVariedad = nameVariedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaHoraIni() {
        return fechaHoraIni;
    }

    public void setFechaHoraIni(String fechaHora) {
        this.fechaHoraIni = fechaHora;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
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

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public int getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(int idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getNameCultivo() {
        return nameCultivo;
    }

    public void setNameCultivo(String nameCultivo) {
        this.nameCultivo = nameCultivo;
    }
}