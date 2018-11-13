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
import pe.ibao.agromovil.models.vo.entitiesInternal.CollectionEvaluationVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.VisitaVO;
import pe.ibao.agromovil.utilities.Utilities;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_EMPRESA_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_EDITING;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_FECHAHORA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_IDFUNDO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_IDVARIEDAD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_LATITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VISITA_COL_LONGITUD;

public class VisitaDAO {

    String TAG ="VisitaDAO";
    Context ctx;

    public VisitaDAO(Context ctx){
        this.ctx = ctx;
    }

    public VisitaVO getEditing(){
        ConexionSQLiteHelper c= new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();

        VisitaVO temp = null;
        try{
            String[] campos = {
                    TABLE_VISITA_COL_ID,
                    TABLE_VISITA_COL_FECHAHORA,
                    TABLE_VISITA_COL_EDITING,
                    TABLE_VISITA_COL_LATITUD,
                    TABLE_VISITA_COL_LONGITUD,
                    TABLE_VISITA_COL_IDFUNDO,
                    TABLE_VISITA_COL_IDVARIEDAD};
            String[]clausulas={
                    "1"
            };
            Cursor cursor= db.query(
                    TABLE_VISITA,
                    campos,
                    TABLE_VISITA_COL_EDITING+"=1",
                    null,
                    null,
                    null,
                    null);
                    if(cursor.getCount()>0){
                        cursor.moveToFirst() ;
                        Log.d(TAG,"hay primero");
                        temp = new VisitaVO();
                        Log.d(TAG,"0");
                        temp.setId(cursor.getInt(0));
                        Log.d(TAG,"1");
                        temp.setFechaHora(cursor.getString(1));
                        Log.d(TAG,"2");
                        temp.setEditing(cursor.getInt(2)>0);
                        Log.d(TAG,"3");
                        temp.setLat(cursor.getDouble(3));
                        Log.d(TAG,"4");
                        temp.setLon(cursor.getDouble(4));
                        Log.d(TAG,"5");
                        temp.setIdFundo(cursor.getInt(5));
                        Log.d(TAG,"6");
                        temp.setIdVariedad(cursor.getInt(6));
                        Log.d(TAG,"7");
                    }
                cursor.close();

        }catch (Exception e){
            Log.d(TAG,"getEditing "+e.toString());
            Toast.makeText(ctx,"getEditing "+e.toString(),Toast.LENGTH_SHORT).show();
        }
        return temp;
    }


    public VisitaVO intentarNuevo(){
        //obtener el q  se esta editando
        VisitaVO resVisita = getEditing();
        Long id = null;
        if(resVisita==null){//si retorno vacio osea q no hay editando creamos uno nuevo
            try {
                ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME, null, 1);
                SQLiteDatabase db = c.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Utilities.TABLE_VISITA_COL_EDITING, true);
                values.put(Utilities.TABLE_VISITA_COL_IDFUNDO, "1");
                values.put(Utilities.TABLE_VISITA_COL_IDVARIEDAD, "1");
                id = db.insert(Utilities.TABLE_VISITA, Utilities.TABLE_VISITA_COL_ID, values);
                c.close();
                resVisita = buscarById(id);
                Log.d(TAG,"--->"+resVisita.getFechaHora());
                Toast.makeText(ctx, "Nueva Visita Registrada:" + id, Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Log.d(TAG,"Intentar nuevo"+e.toString());
            }
        } else{
            Toast.makeText(ctx,"Ya se esta editando una:"+resVisita.getId(),Toast.LENGTH_SHORT).show();
        }
        return resVisita;
    }

    public  VisitaVO buscarById(Long id){
        ConexionSQLiteHelper c= new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        VisitaVO temp = null;
        try{

            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "V."+TABLE_VISITA_COL_ID+", " +
                            "V."+TABLE_VISITA_COL_EDITING+", "+
                            "V."+TABLE_VISITA_COL_FECHAHORA+", "+
                            "V."+TABLE_VISITA_COL_LATITUD+", "+
                            "V."+TABLE_VISITA_COL_LONGITUD+", "+
                            "V."+TABLE_VISITA_COL_IDFUNDO+", "+
                            "V."+TABLE_VISITA_COL_IDVARIEDAD+
                        " FROM "+
                            TABLE_VISITA+" as V"+
                        " WHERE "+
                            "V."+TABLE_VISITA_COL_ID+"="+String.valueOf(id)
                    ,null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                temp = new VisitaVO();
                temp.setId(cursor.getInt(0));
                temp.setEditing(cursor.getInt(1) > 0);
                temp.setFechaHora(cursor.getString(2));
                temp.setLat(cursor.getDouble(3));
                temp.setLon(cursor.getDouble(4));
                temp.setIdFundo(cursor.getInt(5));
                temp.setIdVariedad(cursor.getInt(6));
                Toast.makeText(ctx, "**"+temp.getFechaHora().toString(), Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            c.close();
        }catch (Exception e){
            Log.e(TAG,"buscarbyid"+e.toString());
            Toast.makeText(ctx,"buscarbyId"+e.toString(),Toast.LENGTH_SHORT).show();
        }
        return temp;
    }


}
