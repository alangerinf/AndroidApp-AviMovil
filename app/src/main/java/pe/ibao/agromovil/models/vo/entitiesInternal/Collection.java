package pe.ibao.agromovil.models.vo.entitiesInternal;

import java.util.ArrayList;
import java.util.List;

public interface Collection<T> {

    public void agregar(T element);

    public void agregar(List<T> elements);

    public void deleteAll();

    public boolean deleteOfListById(int id);

    public T buscarnById(int id);

    public int positionById(int id);


}
