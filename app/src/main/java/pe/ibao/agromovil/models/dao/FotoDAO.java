package pe.ibao.agromovil.models.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FOTO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FOTO_COL_HORAFECHA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FOTO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FOTO_COL_IDMUESTRA;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_FOTO_COL_PATH;

public class FotoDAO {

    Context ctx;

    public FotoDAO(Context ctx) {


        this.ctx=ctx;
    }


    public FotoVO nuevoByIdMuestra(int idMuestra,String path) {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        Log.d("locomata","datos recibidos e funcion : "+idMuestra);

        SQLiteDatabase db = conn.getWritableDatabase();
        FotoVO res = null;
        ContentValues values = new ContentValues();
        values.put(TABLE_FOTO_COL_PATH,path);
        values.put(TABLE_FOTO_COL_IDMUESTRA,String.valueOf(idMuestra));
        Long id = db.insert(TABLE_FOTO,TABLE_FOTO_COL_ID,values);

        Log.d("locomata","id FOTO insertado : "+id);

        //obteniendo datos extra de tablas alternas
        Cursor cursor = db.rawQuery(
                "SELECT " +
                        "P."+TABLE_FOTO_COL_ID+", " +
                        "P."+TABLE_FOTO_COL_HORAFECHA+", " +
                        "P."+TABLE_FOTO_COL_PATH+", "+
                        "P."+TABLE_FOTO_COL_IDMUESTRA+
                        " FROM "+
                        TABLE_FOTO+" as P"+
                        " WHERE "+
                        "P."+TABLE_FOTO_COL_ID+"="+String.valueOf(id)
                , null);
        Log.d("locomata","cantidad isnercio"+cursor.getCount());
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            res = new FotoVO();
            res.setId(cursor.getInt(0));
            res.setFechaHora(cursor.getString(1));
            res.setPath(cursor.getString(2));
            res.setIdMuestra(cursor.getInt(3));

        }
        cursor.close();
        db.close();
        conn.close();
        return res;
    }

    public FotoVO consultarById(int id) {
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        FotoVO temp = null;
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "P."+TABLE_FOTO_COL_ID+", " +
                            "P."+TABLE_FOTO_COL_HORAFECHA+", " +
                            "P."+TABLE_FOTO_COL_PATH+", "+
                            "P."+TABLE_FOTO_COL_IDMUESTRA+
                            " FROM "+TABLE_FOTO+" as P"+
                            " WHERE "+
                            "P."+TABLE_FOTO_COL_ID+"="+String.valueOf(id)
                    ,null);
            if(cursor.getCount()>0){
                Log.d("locomata","photo encontrado");
                temp = new FotoVO();
                cursor.moveToFirst();
                temp.setId(cursor.getInt(0));
                temp.setFechaHora(cursor.getString(1));
                temp.setPath(cursor.getString(2));
                temp.setIdMuestra(cursor.getInt(3));

            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT);
        }
        c.close();
        return temp;
    }



    public List<FotoVO> listarByIdMuestra(int idMuestra){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();
        List<FotoVO> fotoVOS = new ArrayList<FotoVO>();
        try{
            Cursor cursor = db.rawQuery(
                    " SELECT "+
                            "P."+TABLE_FOTO_COL_ID+", " +
                            "P."+TABLE_FOTO_COL_HORAFECHA+", " +
                            "P."+TABLE_FOTO_COL_PATH+", "+
                            "P."+TABLE_FOTO_COL_IDMUESTRA+
                            " FROM "+
                            TABLE_FOTO+" AS P"+
                            " WHERE "+
                            "P."+TABLE_FOTO_COL_IDMUESTRA+"="+String.valueOf(idMuestra)
                    ,null);
            while (cursor.moveToNext()){
                FotoVO temp = new FotoVO();
                temp.setId(cursor.getInt(0));
                temp.setFechaHora(cursor.getString(1));
                temp.setPath(cursor.getString(2));
                temp.setIdMuestra(cursor.getInt(3));

                fotoVOS.add(temp);
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(ctx,e.toString(),Toast.LENGTH_SHORT).show();
        }
        c.close();
        return fotoVOS;
    }


    public boolean borrarById(int id){
        boolean flag = false;
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros =
                {
                        String.valueOf(id),
                };

        int res = db.delete(TABLE_FOTO,TABLE_FOTO_COL_ID+"=?",parametros);
        if(res>0){
            flag=true;
        }
        db.close();
        conn.close();
        return flag;
    }

    public boolean borrarValorByIdMuestra(int idMuestra){
        boolean flag = false;
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros =
                {
                        String.valueOf(idMuestra),
                };

        int res = db.delete(TABLE_FOTO,TABLE_FOTO_COL_IDMUESTRA+"=?",parametros);
        if(res>0){
            flag=true;
        }
        db.close();
        conn.close();
        return flag;
    }



}
