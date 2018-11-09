package pe.ibao.agromovil.entities.entitiesDB;

public class Fundo {

    private int id;
    private String name;
    private int idEmpresa;


    public Fundo(int id, String name, int idEmpresa) {
        this.id = id;
        this.name = name;
        this.idEmpresa = idEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
