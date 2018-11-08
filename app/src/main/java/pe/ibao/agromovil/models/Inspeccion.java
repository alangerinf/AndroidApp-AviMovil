package pe.ibao.agromovil.models;

public class Inspeccion {
    private int id;
    private String name;
    private int idFundo;
    private int idVariedad;
    private int idCultivo;
    private int idUsuario;

    public Inspeccion(int id, String name) {
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
