package pe.ibao.agromovil.models;

import java.util.List;

public class Criterio {

    private int id;
    private String name;
    private String type;
    private String magnitud;
    private String value;


    public Criterio(int id, String name, String type,String magnitud, String valueDefault) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = valueDefault;
        this.magnitud = magnitud;
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
