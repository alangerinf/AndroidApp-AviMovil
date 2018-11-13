package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_MAGNITUD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CRITERIO_COL_TIPO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FUNDO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_IDCRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_IDEVALUACION;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_MUESTRA_COL_VALUE;

public class MuestrasDAO {

    Context ctx;

    MuestrasDAO(Context ctx){
        this.ctx = ctx;
    }


    public List<MuestraVO> consultarByIdEvaluacion(int idEvaluacion){
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
}
