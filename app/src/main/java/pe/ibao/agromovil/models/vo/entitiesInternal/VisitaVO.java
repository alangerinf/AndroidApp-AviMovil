package pe.ibao.agromovil.models.vo.entitiesInternal;


import java.util.ArrayList;
import java.util.List;

public class VisitaVO {
    private int id;
    private String fechaHora;
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
    private String contacto;
    private boolean editing;
    private CollectionEvaluationVO collectionEvaluationVO;

    public VisitaVO() {
        collectionEvaluationVO = new CollectionEvaluationVO();
    }

    public CollectionEvaluationVO getCollectionEvaluationVO() {
        return collectionEvaluationVO;
    }

    public void setCollectionEvaluationVO(CollectionEvaluationVO collectionEvaluationVO) {
        this.collectionEvaluationVO = collectionEvaluationVO;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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