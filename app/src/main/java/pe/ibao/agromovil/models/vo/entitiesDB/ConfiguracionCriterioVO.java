package pe.ibao.agromovil.models.vo.entitiesDB;

public class ConfiguracionCriterioVO {
    private int id;
    private int idCriterio;
    private int idFundoVariedad;

    public ConfiguracionCriterioVO(int id, int idCriterio, int idFundoVariedad) {
        this.id = id;
        this.idCriterio = idCriterio;
        this.idFundoVariedad = idFundoVariedad;
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

    public int getIdFundoVariedad() {
        return idFundoVariedad;
    }

    public void setIdFundoVariedad(int idFundoVariedad) {
        this.idFundoVariedad = idFundoVariedad;
    }
}
