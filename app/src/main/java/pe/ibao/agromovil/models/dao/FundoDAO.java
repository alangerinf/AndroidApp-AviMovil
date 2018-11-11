package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_IDEMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_NAME;

public class FundoDAO {


    Context ctx;
    ConexionSQLiteHelper c;
    public FundoDAO(Context ctx) {
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );

        this.ctx=ctx;
    }

    public FundoVO consultarFundoById(int id) {
        SQLiteDatabase db = c.getReadableDatabase();
        FundoVO temp = null;
        try{
            temp = new FundoVO();
            Cursor cursor = db.rawQuery("SELECT F."+ TABLE_FUNDO_COL_ID+", F."+TABLE_FUNDO_COL_NAME+", F."+TABLE_FUNDO_COL_IDEMPRESA+
                                            " FROM "+TABLE_FUNDO+" as F",null);
            cursor.moveToFirst();
            temp.setId(cursor.getInt(0));
            temp.setName(cursor.getString(1));
            temp.setIdEmpresa(cursor.getInt(2));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        return temp;
    }

    public List<FundoVO> listarFundoByIdEmpresa(int idEmpresa){
        SQLiteDatabase db = c.getReadableDatabase();
        List<FundoVO> fundoVOS = new ArrayList<FundoVO>();
        try{
            Cursor cursor = db.rawQuery(
                    " SELECT "+"F."+TABLE_FUNDO_COL_ID+", F."+TABLE_FUNDO_COL_NAME+", F."+TABLE_FUNDO_COL_IDEMPRESA+
                            " FROM "+TABLE_FUNDO+" as F"+
                            " WHERE "+"F."+TABLE_FUNDO_COL_IDEMPRESA+"="+  String.valueOf(idEmpresa),null);
            while (cursor.moveToNext()){
                FundoVO fundoVO = new FundoVO();
                    fundoVO.setId(cursor.getInt(0));
                    fundoVO.setName(cursor.getString(1));
                    fundoVO.setIdEmpresa(cursor.getInt(2));
                fundoVOS.add(fundoVO);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return fundoVOS;
    }


}
