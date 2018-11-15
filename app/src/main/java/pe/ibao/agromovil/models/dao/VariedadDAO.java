package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.VariedadVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD_COL_IDCULTIVO;

public class VariedadDAO {

    Context ctx;
    ConexionSQLiteHelper c;
    public VariedadDAO(Context ctx) {
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        this.ctx=ctx;
    }

    public VariedadVO consultarVariedadById(int id) {
        SQLiteDatabase db = c.getReadableDatabase();
        VariedadVO temp = null;
        try{
            temp = new VariedadVO();
            Cursor cursor = db.rawQuery("SELECT V."+ TABLE_VARIEDAD_COL_ID+", V."+TABLE_VARIEDAD_COL_NAME+", V."+TABLE_VARIEDAD_COL_IDCULTIVO+
                    " FROM "+TABLE_VARIEDAD+" as V",null);
            cursor.moveToFirst();
            temp.setId(cursor.getInt(0));
            temp.setName(cursor.getString(1));
            temp.setIdCultivo(cursor.getInt(2));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        return temp;
    }

    public List<VariedadVO> listarVariedadByIdCultivo(int idCultivo){
        SQLiteDatabase db = c.getReadableDatabase();
        List<VariedadVO> variedadVOS = new ArrayList<VariedadVO>();
        try{
            Cursor cursor = db.rawQuery(
                    " SELECT "+
                            "F."+TABLE_VARIEDAD_COL_ID+", " +
                            "F."+TABLE_VARIEDAD_COL_NAME+", " +
                            "F."+TABLE_VARIEDAD_COL_IDCULTIVO+
                        " FROM "+
                            TABLE_VARIEDAD+" as F"+
                        " WHERE "+
                            "F."+TABLE_VARIEDAD_COL_IDCULTIVO+"="+  String.valueOf(idCultivo)
                    ,null);
            while (cursor.moveToNext()){
                VariedadVO temp = new VariedadVO();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                temp.setIdCultivo(cursor.getInt(2));
                variedadVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return variedadVOS;
    }


}
