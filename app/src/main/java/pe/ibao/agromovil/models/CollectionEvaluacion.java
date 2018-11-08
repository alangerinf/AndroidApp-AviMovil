package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

public class CollectionEvaluacion {

    private List<Evaluacion> evaluaciones;

    public CollectionEvaluacion(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public CollectionEvaluacion() {
        evaluaciones = new ArrayList<>();
    }

    public void addEvalucion(Evaluacion e){
        evaluaciones.add(e);
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public int searchByName(String name){
        int pos=-1;
        for(int i = 0; i< evaluaciones.size(); i++){
            if(evaluaciones.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< evaluaciones.size(); i++){
            if(evaluaciones.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }


}
