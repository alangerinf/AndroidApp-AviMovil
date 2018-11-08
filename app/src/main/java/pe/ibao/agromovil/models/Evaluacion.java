package pe.ibao.agromovil.models;

public class Evaluacion {
    private int id;
    private String name;
    private int idInspeccion;

    public Evaluacion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Evaluacion(int id, String name, int idInspeccion) {
        this.id = id;
        this.name = name;
        this.idInspeccion = idInspeccion;
    }

    public int getIdInspeccion() {
        return idInspeccion;
    }

    public void setIdInspeccion(int idInspeccion) {
        this.idInspeccion = idInspeccion;
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