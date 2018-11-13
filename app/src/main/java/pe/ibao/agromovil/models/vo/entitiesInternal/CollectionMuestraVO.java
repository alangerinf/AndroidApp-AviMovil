package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.List;

public class CollectionMuestraVO implements Collection<MuestraVO> {

    private List<MuestraVO> muestraVOList;

    CollectionMuestraVO(){
        muestraVOList= new ArrayList<>();
    }


    @Override
    public void agregar(MuestraVO element) {
     muestraVOList.add(element);
    }

    @Override
    public void agregar(List<MuestraVO> elements) {
        muestraVOList.addAll(elements);
    }

    @Override
    public void deleteAll() {
        muestraVOList.clear();
    }

    @Override
    public boolean deleteOfListById(int id) {
        boolean res=false;
        int pos=positionById(id);
        if(pos!= -1){
            muestraVOList.remove(pos);
            res=true;
        }
        return  res;
    }

    @Override
    public MuestraVO buscarnById(int id) {
        MuestraVO res=null;
        int pos=positionById(id);
        if(pos!= -1){
            res=muestraVOList.get(pos);
        }
        return res;
    }

    @Override
    public int positionById(int id) {
        int pos = -1;
        for(int i=0;i<muestraVOList.size();i++){
            pos=((muestraVOList.get(i).getId() == id)?i:-1);
        }
        return pos;
    }
}
