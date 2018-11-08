package pe.ibao.agromovil.models;

public class FotoCriterio {

    private int id;
    private int idCriterio;
    private int dir;

    public FotoCriterio(int id, int idCriterio, int dir) {
        this.id = id;
        this.idCriterio = idCriterio;
        this.dir = dir;
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
