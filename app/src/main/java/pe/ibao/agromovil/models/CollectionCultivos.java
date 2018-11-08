package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

public class CollectionCultivos {

    List<Cultivo> cultivos;


    public CollectionCultivos(List<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }

    public CollectionCultivos() {
        cultivos = new ArrayList<>();
    }

    public void addCultivo(Cultivo c){
        cultivos.add(c);
    }

    public List<Cultivo> getCriterios() {
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
