package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

public class CollectionCriterios {

    private List<Criterio> criterios;


    public CollectionCriterios(List<Criterio> criterios) {
        criterios = criterios;
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

    public int searchCriterioByName(String name){
        int pos=-1;
        for(int i=0;i<criterios.size();i++){
            if(criterios.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }


}
