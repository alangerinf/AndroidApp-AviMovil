package pe.ibao.agromovil.models;

public class Evaluacion {
    private int id;
    private String name;

    CollectionCriterios collectionCriterios;

    public Evaluacion(int id, String name, CollectionCriterios collectionCriterios) {
        this.id = id;
        this.name = name;
        this.collectionCriterios = collectionCriterios;
    }

    public Evaluacion(int id, String name) {
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

    public CollectionCriterios getCollectionCriterios() {
        return collectionCriterios;
    }

    public void setCollectionCriterios(CollectionCriterios collectionCriterios) {
        this.collectionCriterios = collectionCriterios;
    }
}