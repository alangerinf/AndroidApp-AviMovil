package pe.ibao.agromovil.models;

import java.util.ArrayList;
import java.util.List;

public class CollectionFundos {

    private List<Fundo> fundos;


    public CollectionFundos(List<Fundo> fundos) {
        this.fundos = fundos;
    }

    public CollectionFundos() {
        fundos = new ArrayList<>();
    }

    public void addFundo(Fundo f){
        fundos.add(f);
    }

    public List<Fundo> getFundos() {
        return fundos;
    }

    public int searchByName(String name){
        int pos=-1;
        for(int i = 0; i< fundos.size(); i++){
            if(fundos.get(i).getName().equals(name)){
                pos = i;
            }
        }
        return pos;
    }
    public int searchById(int id){
        int pos=-1;
        for(int i = 0; i< fundos.size(); i++){
            if(fundos.get(i).getId()==(id)){
                pos = i;
            }
        }
        return pos;
    }


}
