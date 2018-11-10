package pe.ibao.agromovil.helpers;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;

public class CollectionEmpresas {

    List<EmpresaVO> empresas;

    public CollectionEmpresas(List<EmpresaVO> empresas) {
        this.empresas = empresas;
    }

    public CollectionEmpresas() {
        empresas = new ArrayList<>();
    }

    public void addCriterio(EmpresaVO e){
        empresas.add(e);
    }

    public List<EmpresaVO> getEmpresas() {
        return empresas;
    }


    public int searchByName(String name){
        int pos=-1;
        for(int i = 0; i< empresas.size(); i++){
            if(empresas.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< empresas.size(); i++){
            if(empresas.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }

}
