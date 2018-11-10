package pe.ibao.agromovil.models.vo.entitiesDB;

public class FundoVariedadVO {

    private int id;
    private int idFundo;
    private int idVariedad;

    public FundoVariedadVO(int id, int idFundo, int idVariedad) {
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
