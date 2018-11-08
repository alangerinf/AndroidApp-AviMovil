package pe.ibao.agromovil.models;

import java.util.List;

public class Empresa {

    private int id;
    private String name;

    private CollectionFundos collectionFundos;

    public Empresa(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Empresa(int id, String name, CollectionFundos collectionFundos) {
        this.id = id;
        this.name = name;
        this.collectionFundos = collectionFundos;
    }

    public CollectionFundos getCollectionFundos() {
        return collectionFundos;
    }

    public void setCollectionFundos(CollectionFundos collectionFundos) {
        this.collectionFundos = collectionFundos;
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
