package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionEvaluationVO implements Collection<EvaluacionVO> {

    private List<EvaluacionVO> evaluacionVOList;

    public CollectionEvaluationVO(){
        evaluacionVOList = new ArrayList<>();
    }


    @Override
    public void agregar(EvaluacionVO element) {
        evaluacionVOList.add(element);
    }

    public void setEvaluacionVOList(List<EvaluacionVO> evaluacionVOList) {
        this.evaluacionVOList = evaluacionVOList;
    }

    public List<EvaluacionVO> getEvaluacionVOList() {
        return evaluacionVOList;
    }

    @Override
    public void agregar(List<EvaluacionVO> elements) {
        evaluacionVOList.addAll(elements);
    }

    @Override
    public void deleteAll() {
        evaluacionVOList.clear();
    }

    @Override
    public boolean deleteOfListById(int id) {
        boolean res=false;
        int pos=positionById(id);
        if(pos!= -1){
            evaluacionVOList.remove(pos);
            res=true;
        }
        return  res;
    }

    @Override
    public EvaluacionVO buscarnById(int id) {
        EvaluacionVO res=null;
        int pos=positionById(id);
        if(pos!= -1){
            res=evaluacionVOList.get(pos);
        }
        return res;
    }

    @Override
    public int positionById(int id) {
        int pos = -1;
        for(int i=0;i<evaluacionVOList.size();i++){
            pos=((evaluacionVOList.get(i).getId() == id)?i:-1);
        }
        return pos;
    }
}


