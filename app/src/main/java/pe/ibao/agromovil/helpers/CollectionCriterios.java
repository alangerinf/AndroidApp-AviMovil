package pe.ibao.agromovil.helpers;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesDB.CriterioVO;

public class CollectionCriterios {

    private List<CriterioVO> criterios;


    public CollectionCriterios(List<CriterioVO> criterios) {
        this.criterios = criterios;
    }

    public CollectionCriterios() {
        criterios = new ArrayList<>();
    }

    public void addCriterio(CriterioVO c){
        criterios.add(c);
    }

    public List<CriterioVO> getCriterios() {
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
