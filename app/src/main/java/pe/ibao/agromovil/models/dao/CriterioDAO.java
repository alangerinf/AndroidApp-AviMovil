package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioVO;
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_MAGNITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_TIPO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_IDEMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_NAME;

public class CriterioDAO {

    Context ctx;

    public CriterioDAO(Context ctx) {

        this.ctx=ctx;
    }

    public CriterioVO consultarById(int id) {
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        CriterioVO temp = null;
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "C."+TABLE_CRITERIO_COL_ID+", " +
                            "C."+TABLE_CRITERIO_COL_NAME+", " +
                            "C."+TABLE_CRITERIO_COL_TIPO+", "+
                            "C."+TABLE_CRITERIO_COL_MAGNITUD+", "+
                            "C."+TABLE_CRITERIO_COL_IDTIPOINSPECCION+
                    " FROM "+TABLE_CRITERIO+" as C"+
                    " WHERE "+
                            "C."+TABLE_CRITERIO_COL_ID+"="+String.valueOf(id)
                    ,null);
            if(cursor.getCount()>0){
                Log.d("locomata","criterio encontrado");
                temp = new CriterioVO();
                cursor.moveToFirst();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setType(cursor.getString(2));
                temp.setMagnitud(cursor.getString(3));
                temp.setIdTipoInspseccion(cursor.getInt(4));

            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }

        c.close();
        return temp;
    }

    public List<CriterioVO> listarByIdTipoInspeccion(int idTipoInspeccion){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<CriterioVO> criterioVOS = new ArrayList<CriterioVO>();
        try{
            Cursor cursor = db.rawQuery(
                    " SELECT "+
                            "C."+TABLE_CRITERIO_COL_ID+", " +
                            "C."+TABLE_CRITERIO_COL_NAME+", " +
                            "C."+TABLE_CRITERIO_COL_TIPO+", "+
                            "C."+TABLE_CRITERIO_COL_MAGNITUD+", "+
                            "C."+TABLE_CRITERIO_COL_IDTIPOINSPECCION+
                        " FROM "+
                            TABLE_CRITERIO+" as C"+
                        " WHERE "+
                            "c."+TABLE_CRITERIO_COL_IDTIPOINSPECCION+"="+  String.valueOf(idTipoInspeccion)
                    ,null);
            while (cursor.moveToNext()){
                CriterioVO temp = new CriterioVO();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setType(cursor.getString(2));
                temp.setMagnitud(cursor.getString(3));
                temp.setIdTipoInspseccion(cursor.getInt(4));
                criterioVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        c.close();
        return criterioVOS;
    }

}