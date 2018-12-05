package pe.ibao.agromovil.models.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioRecomendacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CONFIGURACIONRECOMENDACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDCRITERIORECOMENDACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDVARIEDAD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIORECOMENDACION_COL_NAME;

public class CriterioRecomendacionDAO {

    private Context ctx;

    public CriterioRecomendacionDAO(Context ctx){
        this.ctx = ctx;
    }
    public CriterioRecomendacionVO consultarById(int  id) {
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        CriterioRecomendacionVO temp = null;
        try{
            String consulta = "SELECT " +
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_ID+", "+
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_NAME+", "+
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES+", "+
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS+", "+
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION+
                    " FROM "+
                    TABLE_CRITERIORECOMENDACION+" as CR "+
                    " WHERE "+
                    "CR."+TABLE_CRITERIORECOMENDACION_COL_ID+"="+String.valueOf(id);
            Cursor cursor = db.rawQuery(consulta,null);
            if(cursor.getCount()>=0) {
                cursor.moveToFirst();
                temp = new CriterioRecomendacionVO();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setListUnidades(cursor.getString(2));
                temp.setListFrecuancias(cursor.getString(3));
                temp.setIdTipoRecomendacion(cursor.getInt(4));
            }
            cursor.close();

        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        c.close();
        return temp;
    }


    public CriterioRecomendacionVO insertar(int id, String name, String listUnidades,String listFrecuencias,int idTipoRecomendacion){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_CRITERIORECOMENDACION_COL_ID,id);
        values.put(TABLE_CRITERIORECOMENDACION_COL_NAME,name);
        values.put(TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES,listUnidades);
        values.put(TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS,listFrecuencias);
        values.put(TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION,idTipoRecomendacion);
        int temp = (int) db.insert(TABLE_CRITERIORECOMENDACION,TABLE_CRITERIORECOMENDACION_COL_ID,values);
        Log.d("consultasuccess CR","idEva->"+temp);
        CriterioRecomendacionVO ev  = consultarById(temp);
        Log.d("consultasuccess CR","idEva-->"+ev.getId());
        db.close();
        return ev;
    }

    public List<CriterioRecomendacionVO> listarByIdTipoRecomendacionIdVariedad(int idTipoRecomendacion,int idVariedad){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<CriterioRecomendacionVO> criterioRecomendacionVOS = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_ID+", " +//0
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_NAME+", " +//1
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES+", "+//2
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS+", "+//3
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION+//4
                            " FROM "+
                            TABLE_CRITERIORECOMENDACION+" as CR ," +
                            TABLE_CONFIGURACIONRECOMENDACION+" as COR"+
                            " WHERE "+
                            "CR."+TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION+"="+String.valueOf(idTipoRecomendacion)+
                            " AND "+
                            "COR."+TABLE_CONFIGURACIONRECOMENDACION_COL_IDCRITERIORECOMENDACION+"="+"CR."+TABLE_CRITERIORECOMENDACION_COL_ID+
                            " AND "+
                            "COR."+TABLE_CONFIGURACIONRECOMENDACION_COL_IDVARIEDAD+"="+String.valueOf(idVariedad)
                    ,null);
            while (cursor.moveToNext() && cursor.getCount()>0){
                CriterioRecomendacionVO temp;
                temp = new CriterioRecomendacionVO();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setListUnidades(cursor.getString(2));
                temp.setListFrecuancias(cursor.getString(3));
                temp.setIdTipoRecomendacion(cursor.getInt(4));
                criterioRecomendacionVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return criterioRecomendacionVOS;
    }


}
