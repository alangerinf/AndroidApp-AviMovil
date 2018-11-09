package pe.ibao.agromovil.entities.entitiesDB;

public class FundoVariedad {

    private int id;
    private int idFundo;
    private int idVariedad;

    public FundoVariedad(int id, int idFundo, int idVariedad) {
        this.id = id;
        this.idFundo = idFundo;
        this.idVariedad = idVariedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
