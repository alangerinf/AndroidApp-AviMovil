package pe.ibao.agromovil.helpers;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;

public class CollectionFundos {

    private List<FundoVO> fundos;

    public CollectionFundos(List<FundoVO> fundos) {
        this.fundos = fundos;
    }

    public CollectionFundos() {
        fundos = new ArrayList<>();
    }

    public void addFundo(FundoVO f){
        fundos.add(f);
    }

    public List<FundoVO> getFundos() {
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
