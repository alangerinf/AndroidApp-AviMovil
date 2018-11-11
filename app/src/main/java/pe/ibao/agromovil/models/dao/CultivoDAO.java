package pe.ibao.agromovil.models.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesDB.CultivoVO;
import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CULTIVO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CULTIVO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_CULTIVO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_VARIEDAD_COL_IDCULTIVO;

public class CultivoDAO {

    Context ctx;
    ConexionSQLiteHelper c;
    public CultivoDAO(Context ctx) {
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        this.ctx=ctx;
    }

    public CultivoVO consultarCultivoByid(int id){
        SQLiteDatabase db = c.getReadableDatabase();
        CultivoVO temp = null;
        try{
            temp = new CultivoVO();
            Cursor cursor = db.rawQuery("SELECT C."+TABLE_CULTIVO_COL_ID+", C."+TABLE_CULTIVO_COL_NAME+" FROM "+TABLE_CULTIVO+" as C",null);
            cursor.moveToFirst();
            temp.setId(cursor.getInt(0));
            temp.setName(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        return temp;
    }

    public CultivoVO consultarCultivoByIdVariedad(int idVariedad){

        SQLiteDatabase db = c.getReadableDatabase();
        CultivoVO cultivoVO = null;
        try{
            cultivoVO = new CultivoVO();
            Cursor cursor = db.rawQuery(
                    " SELECT "+"C."+TABLE_CULTIVO_COL_ID+", "+"C."+TABLE_CULTIVO_COL_NAME+
                            " FROM "+TABLE_CULTIVO+" as C, "+TABLE_VARIEDAD+" as V"+
                            " WHERE "+"V."+TABLE_VARIEDAD_COL_ID+"="+  String.valueOf(idVariedad)+
                            " AND "+"V."+TABLE_VARIEDAD_COL_IDCULTIVO+" = "+"C."+TABLE_CULTIVO_COL_ID, null);
            cursor.moveToFirst();
            cultivoVO.setId(cursor.getInt(0));
            cultivoVO.setName(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return cultivoVO;
    }
    public List<CultivoVO> listCultivos(){
        SQLiteDatabase db = c.getReadableDatabase();
        List<CultivoVO> cultivos = new ArrayList<CultivoVO>();
        try{
            String[] campos = {TABLE_CULTIVO_COL_ID,TABLE_CULTIVO_COL_NAME};
            Cursor cursor= db.query(TABLE_CULTIVO,campos,null,null,null,null,null);
            while(cursor.moveToNext()){
                CultivoVO temp = new CultivoVO();
                temp.setId(cursor.getInt(0));
                temp.setName(cursor.getString(1));
                cultivos.add(temp);
                Toast.makeText(ctx,temp.getName(),Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        return cultivos;
    }
    public void cultivoDAOCloseConection() {
        c.close();
    }

}
