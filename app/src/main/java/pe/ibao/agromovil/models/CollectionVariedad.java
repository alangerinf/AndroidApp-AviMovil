package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.entities.entitiesDB.Variedad;

public class CollectionVariedad {

    List<Variedad> variedades;

    public CollectionVariedad(List<Variedad> variedades) {
        this.variedades = variedades;
    }

    public CollectionVariedad() {
        variedades = new ArrayList<>();
    }

    public void addVariedad(Variedad v){
        variedades.add(v);
    }

    public List<Variedad> getVariedades() {
        return variedades;
    }

    public int searchByName(String name){
        int pos=-1;
        for(int i=0;i<variedades.size();i++){
            if(variedades.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< variedades.size(); i++){
            if(variedades.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }


}


