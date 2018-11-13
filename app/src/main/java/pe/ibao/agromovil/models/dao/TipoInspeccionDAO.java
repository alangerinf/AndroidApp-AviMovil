package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesDB.TipoInspeccionVO;

import static pe.ibao.agromovil.utilities.Utilities.*;

public class TipoInspeccionDAO {

    Context ctx;


    public TipoInspeccionDAO(Context ctx) {

        this.ctx=ctx;
    }

    public TipoInspeccionVO consultarByid(int id){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        TipoInspeccionVO temp = null;
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "TI."+TABLE_TIPOINSPECCION_COL_ID+", " +
                            "TI."+TABLE_TIPOINSPECCION_COL_NAME+
                        " FROM "+
                            TABLE_TIPOINSPECCION+" as TI "+
                        " WHERE "+
                            "TI."+TABLE_TIPOINSPECCION_COL_ID+"="+String.valueOf(id)
                    ,null);

            if(cursor.getCount()>0){
                temp = new TipoInspeccionVO();
                cursor.moveToFirst();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
            }
            cursor.close();

        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        c.close();
        return temp;
    }



    public List<TipoInspeccionVO> consultarListByIdFundoAndIdVariedad(int idFundo, int idVariedad){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<TipoInspeccionVO> tipoInspeccionVOS = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(
                    " SELECT "+
                            "TI."+TABLE_TIPOINSPECCION_COL_ID+", "+
                            "TI."+TABLE_TIPOINSPECCION_COL_NAME+
                            " FROM "+
                            TABLE_VARIEDAD+" as V, "+
                            TABLE_FUNDO+" as F,"+
                            TABLE_FUNDOVARIEDAD+" as FV,"+
                            TABLE_TIPOINSPECCION+" as TI,"+
                            TABLE_CRITERIO+" as C,"+
                            TABLE_CONFIGURACIONCRITERIO+" as CC"+
                            " WHERE "+
                            "F."+TABLE_FUNDO_COL_ID+"="+  String.valueOf(idFundo)+" AND "+
                            "V."+TABLE_VARIEDAD_COL_ID+"="+String.valueOf(idVariedad)+" AND "+
                            "FV."+TABLE_FUNDOVARIEDAD_COL_IDFUNDO+"="+"F."+TABLE_FUNDO_COL_ID+" AND "+
                            "FV."+TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD+"="+"V."+TABLE_VARIEDAD_COL_ID+" AND "+
                            "FV."+TABLE_FUNDOVARIEDAD_COL_ID+"="+"CC."+TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD+" AND "+
                            "CC."+TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO+"="+"C."+TABLE_CRITERIO_COL_ID+" AND "+
                            "C."+TABLE_CRITERIO_COL_IDTIPOINSPECCION+"="+"TI."+TABLE_TIPOINSPECCION_COL_ID+
                            " GROUP BY "+
                            "TI."+TABLE_TIPOINSPECCION_COL_ID
                    , null);
            while (cursor.moveToNext()){
                TipoInspeccionVO temp = new TipoInspeccionVO();
                    temp.setId(cursor.getInt(0));
                    temp.setName(cursor.getString(1));
                tipoInspeccionVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
            Log.d("tipoinspeccionDAO",e.toString());
        }
        c.close();
        return tipoInspeccionVOS;
    }
}