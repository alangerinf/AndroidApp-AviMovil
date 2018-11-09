package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.entities.entitiesDB.Empresa;

public class CollectionEmpresas {

    List<Empresa> empresas;

    public CollectionEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public CollectionEmpresas() {
        empresas = new ArrayList<>();
    }

    public void addCriterio(Empresa e){
        empresas.add(e);
    }

    public List<Empresa> getEmpresas() {
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
