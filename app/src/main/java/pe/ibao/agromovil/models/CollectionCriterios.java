package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.entities.entitiesDB.Criterio;

public class CollectionCriterios {

    private List<Criterio> criterios;


    public CollectionCriterios(List<Criterio> criterios) {
        this.criterios = criterios;
    }

    public CollectionCriterios() {
        criterios = new ArrayList<>();
    }

    public void addCriterio(Criterio c){
        criterios.add(c);
    }

    public List<Criterio> getCriterios() {
        return criterios;
    }

    public int searchByName(String name){
        int pos=-1;
        for(int i=0;i<criterios.size();i++){
            if(criterios.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< criterios.size(); i++){
            if(criterios.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }


}
