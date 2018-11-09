package pe.ibao.agromovil.entities.entitiesInternal;

public class Muestra {
    private int id;
    private int idCriterio;
    private int idEvalacion;
    private String value;

    public Muestra(int id, int idCriterio, int idEvalacion) {
        this.id = id;
        this.idCriterio = idCriterio;
        this.idEvalacion = idEvalacion;
    }

    public Muestra(int id) {
        this.id = id;
    }

    public Muestra(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public int getIdEvalacion() {
        return idEvalacion;
    }

    public void setIdEvalacion(int idEvalacion) {
        this.idEvalacion = idEvalacion;
    }
}
