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
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;
import pe.ibao.agromovil.utilities.Utilities;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_IDTIPOINSPECCION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_IDVISITA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_LATITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_LONGITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_QR;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_TIMEFIN;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EVALUACION_COL_TIMEINI;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_TIPOINSPECCION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_TIPOINSPECCION_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_TIPOINSPECCION_COL_NAME;

public class EvaluacionDAO {
    private Context ctx;

    public EvaluacionDAO(Context ctx) {
        this.ctx = ctx;
    }

    public EvaluacionVO consultarById(int  id) {
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        EvaluacionVO temp = null;
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "E."+TABLE_EVALUACION_COL_ID+", " +
                            "E."+TABLE_EVALUACION_COL_TIMEINI+", " +
                            "E."+TABLE_EVALUACION_COL_TIMEFIN+", "+
                            "E."+TABLE_EVALUACION_COL_QR+", "+
                            "E."+TABLE_EVALUACION_COL_LATITUD+", "+
                            "E."+TABLE_EVALUACION_COL_LONGITUD+", "+
                            "E."+TABLE_EVALUACION_COL_IDTIPOINSPECCION+", "+
                            "E."+TABLE_EVALUACION_COL_IDVISITA+
                            "TI."+TABLE_TIPOINSPECCION_COL_NAME+
                    " FROM "+
                            TABLE_EVALUACION+" as E, " +
                            TABLE_TIPOINSPECCION+" AS TI"+
                    " WHERE "+
                            "E."+TABLE_EVALUACION_COL_ID+" = "+String.valueOf(id)+" AND "+
                            "TI."+TABLE_TIPOINSPECCION_COL_ID+" = "+"E."+TABLE_EVALUACION_COL_IDTIPOINSPECCION
                    ,null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                temp = new EvaluacionVO();
                temp.setId(cursor.getInt(0));
                temp.setTimeIni(cursor.getString(1));
                temp.setTimeFin(cursor.getString(2));
                temp.setQr(cursor.getString(3));
                temp.setLat(cursor.getDouble(4));
                temp.setLon(cursor.getDouble(5));
                temp.setIdTipoInspeccion(cursor.getInt(6));
                temp.setIdVisita(cursor.getInt(7));
                temp.setNameInspeccion(cursor.getString(8));
            }
            cursor.close();

        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        c.close();
        return temp;
    }

    public EvaluacionVO nuevoByIdVisita(Double lat, Double lon, Integer idVisita){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_EVALUACION_COL_LATITUD,lat);
            values.put(Utilities.TABLE_EVALUACION_COL_LONGITUD,lon);
            values.put(Utilities.TABLE_EVALUACION_COL_IDVISITA,idVisita);
        int temp = (int) db.insert(TABLE_EVALUACION,TABLE_EVALUACION_COL_ID,values);
        consultarById(temp);
        db.close();
        return consultarById(temp);
    }

    public int borrarById(int id){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        int res =db.delete(Utilities.TABLE_EVALUACION,TABLE_EVALUACION_COL_ID+"=?",parametros);
        Log.d("borrando",String.valueOf(res));

        return res;
    }


    public List<EvaluacionVO> listarByIdVisita(int idVisita){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<EvaluacionVO> evaluacionVOS = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "E."+TABLE_EVALUACION_COL_ID+", " +
                            "E."+TABLE_EVALUACION_COL_TIMEINI+", " +
                            "E."+TABLE_EVALUACION_COL_TIMEFIN+", "+
                            "E."+TABLE_EVALUACION_COL_QR+", "+
                            "E."+TABLE_EVALUACION_COL_LATITUD+", "+
                            "E."+TABLE_EVALUACION_COL_LONGITUD+", "+
                            "E."+TABLE_EVALUACION_COL_IDTIPOINSPECCION+", "+
                            "E."+TABLE_EVALUACION_COL_IDVISITA+
                            " FROM "+
                            TABLE_EVALUACION+" as E "+
                            " WHERE "+
                            "E."+TABLE_EVALUACION_COL_IDVISITA+" = "+String.valueOf(idVisita)
                    ,null);
            while (cursor.moveToNext() && cursor.getCount()>0){
                EvaluacionVO temp = new EvaluacionVO();
                temp = new EvaluacionVO();
                temp.setId(cursor.getInt(0));
                temp.setTimeIni(cursor.getString(1));
                temp.setTimeFin(cursor.getString(2));
                temp.setQr(cursor.getString(3));
                temp.setLat(cursor.getDouble(4));
                temp.setLon(cursor.getDouble(5));
                temp.setIdTipoInspeccion(cursor.getInt(6));
                temp.setIdVisita(cursor.getInt(7));
                evaluacionVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return evaluacionVOS;
    }
}