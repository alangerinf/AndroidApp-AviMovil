package pe.ibao.agromovil.models;

public class Inspeccion {
    private int id;
    private String name;

    CollectionEvaluacion collectionEvaluaciones;

    public Inspeccion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Inspeccion(int id, String name, CollectionEvaluacion collectionEvaluaciones) {
        this.id = id;
        this.name = name;
        this.collectionEvaluaciones = collectionEvaluaciones;
    }

    public CollectionEvaluacion getCollectionEvaluaciones() {
        return collectionEvaluaciones;
    }

    public void setCollectionEvaluaciones(CollectionEvaluacion collectionEvaluaciones) {
        this.collectionEvaluaciones = collectionEvaluaciones;
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
