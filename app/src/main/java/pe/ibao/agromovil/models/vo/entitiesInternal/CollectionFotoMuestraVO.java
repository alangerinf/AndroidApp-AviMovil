package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.List;

public class CollectionFotoMuestraVO implements Collection<FotoVO> {

    private List<FotoVO> fotoMuestraVOList;

    CollectionFotoMuestraVO(){
        fotoMuestraVOList = new ArrayList<>();
    }

    @Override
    public void agregar(FotoVO element) {
        fotoMuestraVOList.add(element);
    }

    @Override
    public void agregar(List<FotoVO> elements) {
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
    public FotoVO buscarnById(int id) {
        FotoVO res=null;
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
