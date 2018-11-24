package pe.ibao.agromovil.models.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.models.vo.entitiesInternal.UsuarioVO;
import pe.ibao.agromovil.utilities.Utilities;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO_COL_ID;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO_COL_LASTNAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO_COL_NAME;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO_COL_PASSWORD;
import static pe.ibao.agromovil.utilities.Utilities.TABLE_USUARIO_COL_USER;

public class UsuarioDAO {

    Context ctx;
    String TAG = "autentification";

    public UsuarioDAO(Context ctx) {

        this.ctx=ctx;
    }


    public int borrarTable(){
        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME, null, 1);
        SQLiteDatabase db = c.getWritableDatabase();
        int res =db.delete(TABLE_USUARIO,null,null);
        c.close();
        return res;

    }


    public int guardarUsuarioNuevo(UsuarioVO u){
        Log.d("autentification",u.toString());

        ConexionSQLiteHelper c = new ConexionSQLiteHelper(ctx, DATABASE_NAME, null, 1);
        SQLiteDatabase db = c.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(TABLE_USUARIO_COL_USER,u.getUser());
            values.put(TABLE_USUARIO_COL_PASSWORD, u.getPassword());
            values.put(TABLE_USUARIO_COL_NAME,u.getName());
            values.put(TABLE_USUARIO_COL_LASTNAME,u.getLastName());
        int id = (int)db.insert(TABLE_USUARIO, Utilities.TABLE_USUARIO_COL_ID, values);
        c.close();

        return id;
    }


    public UsuarioVO verficarLogueo(){
        ConexionSQLiteHelper c= new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getReadableDatabase();

        UsuarioVO temp = null;
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " +
                            "U."+TABLE_USUARIO_COL_ID        +", " +//0
                            "U."+TABLE_USUARIO_COL_USER +", " +//1
                            "U."+TABLE_USUARIO_COL_PASSWORD   +", " +//2
                            "U."+TABLE_USUARIO_COL_NAME   +", " +//3
                            "U."+TABLE_USUARIO_COL_LASTNAME+//4
                            " FROM "+
                            TABLE_USUARIO+" as U "
                    ,null);
            if(cursor.getCount()>0){
                cursor.moveToFirst() ;
                Log.d(TAG,"hay primero");
                temp = new UsuarioVO();
                Log.d(TAG,"0");
                temp.setId(cursor.getInt(0));
                Log.d(TAG,"1");
                temp.setUser(cursor.getString(1));
                Log.d(TAG,"2");
                temp.setPassword(cursor.getString(2));
                Log.d(TAG,"3");
                temp.setName(cursor.getString(3));
                Log.d(TAG,"4");
                temp.setLastName(cursor.getString(4));

            }
            cursor.close();

        }catch (Exception e){
            Log.d(TAG,"getEditing "+e.toString());
            Toast.makeText(ctx,"getEditing "+e.toString(),Toast.LENGTH_SHORT).show();
        }
        return temp;
    }

}
