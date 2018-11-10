package pe.ibao.agromovil.helpers;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesDB.VariedadVO;

public class CollectionVariedad {

    List<VariedadVO> variedades;

    public CollectionVariedad(List<VariedadVO> variedades) {
        this.variedades = variedades;
    }

    public CollectionVariedad() {
        variedades = new ArrayList<>();
    }

    public void addVariedad(VariedadVO v){
        variedades.add(v);
    }

    public List<VariedadVO> getVariedades() {
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


