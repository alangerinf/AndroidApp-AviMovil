package pe.ibao.agromovil.models.vo.entitiesDB;

public class MuestraVO extends CriterioVO {
    private int ids;

    private int idEvaluacion;
    private int idCriterio;
    private String value;//parsearlo a int float o boolean segun se crea conveniente
    public MuestraVO(){
        super();
    }


    public String getValue() {
        return value;

    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return ids;
    }


    public void setId(int id) {
        this.ids = id;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }
}
