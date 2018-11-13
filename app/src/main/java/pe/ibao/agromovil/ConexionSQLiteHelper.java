package pe.ibao.agromovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.ibao.agromovil.utilities.Utilities;

public class ConexionSQLiteHelper extends SQLiteOpenHelper{



    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREATE_TABLE_USUARIO);
        db.execSQL(Utilities.CREATE_TABLE_EMPRESA);
        db.execSQL(Utilities.CREATE_TABLE_FUNDO);
        db.execSQL(Utilities.CREATE_TABLE_CULTIVO);
        db.execSQL(Utilities.CREATE_TABLE_VARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_FUNDOVARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_CONFIGURACIONCRITERIO);
        db.execSQL(Utilities.CREATE_TABLE_CRITERIO);
        db.execSQL(Utilities.CREATE_TABLE_TIPOINSPECCION);
        db.execSQL(Utilities.CREATE_TABLE_VISITA);
        db.execSQL(Utilities.CREATE_TABLE_EVALUACION);
        db.execSQL(Utilities.CREATE_TABLE_MUESTRA);
        db.execSQL(Utilities.CREATE_TABLE_FOTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CULTIVO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDOVARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CONFIGURACIONCRITERIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CRITERIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_TIPOINSPECCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VISITA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EVALUACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_MUESTRA);
        onCreate(db);
    }
}
