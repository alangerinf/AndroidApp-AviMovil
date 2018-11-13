package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.List;

public class CollectionVisitaVO implements Collection<VisitaVO> {

    List<VisitaVO> visitaVOList;

    CollectionVisitaVO(){
        visitaVOList = new ArrayList<>();
    }

    @Override
    public void agregar(VisitaVO element) {
        visitaVOList.add(element);
    }

    @Override
    public void agregar(List<VisitaVO> elements) {
        visitaVOList.addAll(elements);
    }

    @Override
    public void deleteAll() {
        visitaVOList.clear();
    }

    @Override
    public boolean deleteOfListById(int id) {
        boolean res=false;
        int pos=positionById(id);
        if(pos!= -1){
            visitaVOList.remove(pos);
            res=true;
        }
        return  res;
    }

    @Override
    public VisitaVO buscarnById(int id) {
        VisitaVO res=null;
        int pos=positionById(id);
        if(pos!= -1){
            res=visitaVOList.get(pos);
        }
        return res;
    }

    @Override
    public int positionById(int id) {
        int pos = -1;
        for(int i=0;i<visitaVOList.size();i++){
            pos=((visitaVOList.get(i).getId() == id)?i:-1);
        }
        return pos;
    }
}
