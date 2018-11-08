package pe.ibao.agromovil.models;

import java.util.List;

public class Criterio {

    private int id;
    private String name;
    private String type;
    private String magnitud;
    private String value;

    List<String> fotos;

    public Criterio(int id, String name, String type,String magnitud, String value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.magnitud = magnitud;
    }

    public Criterio(int id, String name, String type, String magnitud, String value, List<String> fotos) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.magnitud = magnitud;
        this.value = value;
        this.fotos = fotos;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }
}
