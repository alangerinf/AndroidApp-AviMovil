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
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;
import pe.ibao.agromovil.utilities.Utilities;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_MAGNITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_TIPO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_IDCRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_IDEVALUACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_VALUE;

public class MuestrasDAO {

    Context ctx;

    public MuestrasDAO(Context ctx){
        this.ctx = ctx;
    }


    public List<MuestraVO> listarByIdEvaluacion(int idEvaluacion){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<MuestraVO> muestraVOList =  new ArrayList<>();
        Cursor cursor = db.rawQuery(
                "SELECT " +
                        "M."+TABLE_MUESTRA_COL_ID+", " +
                        "M."+TABLE_MUESTRA_COL_VALUE+", " +
                        "M."+TABLE_MUESTRA_COL_IDCRITERIO+", "+
                        "M."+TABLE_MUESTRA_COL_IDEVALUACION+", "+
                        "C."+TABLE_CRITERIO_COL_TIPO+", "+
                        "C."+TABLE_CRITERIO_COL_MAGNITUD+", "+
                        "C."+TABLE_CRITERIO_COL_NAME+
                    " FROM "+
                        TABLE_MUESTRA+" as M, "+
                        TABLE_CRITERIO+" as C"+
                    " WHERE "+
                        "M."+TABLE_MUESTRA_COL_IDEVALUACION+"="+String.valueOf(idEvaluacion)+
                        " AND "+
                        "M."+TABLE_MUESTRA_COL_IDCRITERIO+"="+"C."+TABLE_CRITERIO_COL_ID
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            MuestraVO temp= new MuestraVO();

        }
        cursor.close();
        c.close();
        return muestraVOList;

    }

    public MuestraVO nuevoByIdEvaluacionIdCriterio(int idEvaluacion, int idCriterio) {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        Log.d("locomata","datos recibidos e funcion : "+idEvaluacion+" "+idCriterio);

        SQLiteDatabase db = conn.getWritableDatabase();
        MuestraVO res = null;
        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_MUESTRA_COL_VALUE,"");
            values.put(Utilities.TABLE_MUESTRA_COL_IDCRITERIO,String.valueOf(idCriterio));
            values.put(Utilities.TABLE_MUESTRA_COL_IDEVALUACION,String.valueOf(idEvaluacion));
        Long id = db.insert(Utilities.TABLE_MUESTRA,Utilities.TABLE_MUESTRA_COL_ID,values);

        Log.d("locomata","id insertado : "+id);

        //obteniendo datos extra de tablas alternas
        Cursor cursor = db.rawQuery(
                "SELECT " +
                    "M."+TABLE_MUESTRA_COL_ID+", " +
                    "M."+TABLE_MUESTRA_COL_VALUE+", " +
                    "M."+TABLE_MUESTRA_COL_IDCRITERIO+", "+
                    "M."+TABLE_MUESTRA_COL_IDEVALUACION+
                " FROM "+
                TABLE_MUESTRA+" as M"+
                " WHERE "+
                "M."+TABLE_MUESTRA_COL_ID+"="+String.valueOf(id)
                , null);
        Log.d("locomata","cantidad isnercio"+cursor.getCount());
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            res = new MuestraVO();
            res.setId(cursor.getInt(0));
            res.setValue(cursor.getString(1));
            res.setIdCriterio(cursor.getInt(2));
            res.setIdEvaluacion(cursor.getInt(3));
            CriterioDAO criterioDAO = new CriterioDAO(ctx);
            CriterioVO temp = criterioDAO.consultarById(res.getIdCriterio());
            if(temp!=null){
                Log.d("locomata","temp is null");
                res.setIdTipoInspseccion(temp.getIdTipoInspseccion());
                res.setName(temp.getName());
                res.setType(temp.getType());
                res.setMagnitud(temp.getMagnitud());
            }else{
                Toast.makeText(ctx,"Error de data interna",Toast.LENGTH_LONG);
            }

            Log.d("locomata","datos encontrados"+
                    res.getId()+" "+
                    res.getIdCriterio()+" "+
            res.getIdEvaluacion()+" "+
            res.getMagnitud()+" "+
            res.getIdCriterio()+" "+
            res.getValue()+" "+
            res.getName()+" "+
            res.getType());
        }
        cursor.close();
        db.close();
        return res;
    }
}
