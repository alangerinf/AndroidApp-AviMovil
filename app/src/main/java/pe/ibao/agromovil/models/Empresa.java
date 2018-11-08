package pe.ibao.agromovil.models;

import java.util.List;

public class Empresa {

    private int id;
    private String name;
    private int idUsuario;

    public Empresa(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Empresa(int id, String name, int idUsuario) {
        this.id = id;
        this.name = name;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
