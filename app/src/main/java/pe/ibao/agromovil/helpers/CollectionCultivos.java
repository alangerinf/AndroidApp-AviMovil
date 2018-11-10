package pe.ibao.agromovil.helpers;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesDB.CultivoVO;

public class CollectionCultivos {

    List<CultivoVO> cultivos;


    public CollectionCultivos(List<CultivoVO> cultivos) {
        this.cultivos = cultivos;
    }

    public CollectionCultivos() {
        cultivos = new ArrayList<>();
    }

    public void addCultivo(CultivoVO c){
        cultivos.add(c);
    }

    public List<CultivoVO> getCriterios() {
        return cultivos;
    }

    public int searchByName(String name){
        int pos=-1;
        for(int i=0;i<cultivos.size();i++){
            if(cultivos.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< cultivos.size(); i++){
            if(cultivos.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }


}
