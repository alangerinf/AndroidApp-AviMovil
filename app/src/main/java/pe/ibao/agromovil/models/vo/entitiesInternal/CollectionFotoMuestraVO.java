package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.List;

public class CollectionFotoMuestraVO implements Collection<FotoMuestraVO> {

    private List<FotoMuestraVO> fotoMuestraVOList;

    CollectionFotoMuestraVO(){
        fotoMuestraVOList = new ArrayList<>();
    }

    @Override
    public void agregar(FotoMuestraVO element) {
        fotoMuestraVOList.add(element);
    }

    @Override
    public void agregar(List<FotoMuestraVO> elements) {
        fotoMuestraVOList.addAll(elements);
    }

    @Override
    public void deleteAll() {
        fotoMuestraVOList.clear();
    }

    @Override
    public boolean deleteOfListById(int id) {
        boolean res=false;
        int pos=positionById(id);
        if(pos!= -1){
            fotoMuestraVOList.remove(pos);
            res=true;
        }
        return  res;
    }

    @Override
    public FotoMuestraVO buscarnById(int id) {
        FotoMuestraVO res=null;
        int pos=positionById(id);
        if(pos!= -1){
            res=fotoMuestraVOList.get(pos);
        }
        return res;
    }

    @Override
    public int positionById(int id) {
        int pos = -1;
        for(int i=0;i<fotoMuestraVOList.size();i++){
            pos=((fotoMuestraVOList.get(i).getId() == id)?i:-1);
        }
        return pos;
    }
}
