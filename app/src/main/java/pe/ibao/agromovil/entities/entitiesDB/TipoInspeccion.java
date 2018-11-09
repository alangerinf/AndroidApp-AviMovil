package pe.ibao.agromovil.entities.entitiesDB;

public class TipoInspeccion {
    private int id;
    private String name;

    public TipoInspeccion(int id, String name) {
        this.id = id;
        this.name = name;
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
