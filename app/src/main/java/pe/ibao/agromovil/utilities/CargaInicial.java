package pe.ibao.agromovil.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import pe.ibao.agromovil.ConexionSQLiteHelper;

public class CargaInicial {

    private Context ctx;

    public CargaInicial(Context ctx) {
        this.ctx = ctx;

        cargarEmpresas();
        cargarFundos();
        cargarCultivos();
        cargarVariedad();
        cargarFundoVariedad();
        cargarTipoInspeccion();
    }

    public void cargarEmpresas(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"1");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"EMPRESA1");
        Long temp = db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);
        Toast.makeText(ctx,"idEmpresa: "+temp,Toast.LENGTH_SHORT).show();
        values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"2");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"EMPRESA2");
        temp =db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);
        Toast.makeText(ctx,"idEmpresa: "+temp,Toast.LENGTH_SHORT).show();
        db.close();

    }
    public void cargarFundos(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"1");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"FUNDO11");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"1");
        Long temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        Toast.makeText(ctx,"idFundo: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"2");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"FUNDO21");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"1");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        Toast.makeText(ctx,"idFundo: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"3");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"FUNDO32");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"2");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        Toast.makeText(ctx,"idFundo: "+temp,Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void cargarCultivos(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"1");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"CULTIVO1");
        Long temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);
        Toast.makeText(ctx,"idCultivo: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"2");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"CULTIVO2");
        temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);
        Toast.makeText(ctx,"idCultivo: "+temp,Toast.LENGTH_SHORT).show();

        db.close();
    }
    public void cargarVariedad(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"1");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"VARIEDAD11");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"1");
        Long temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"2");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"VARIEDAD21");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"1");
        temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"3");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"VARIEDAD32");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"2");
        temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idVariedad: "+temp,Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void cargarFundoVariedad(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"1");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"1");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"1");
        Long temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"1");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"2");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"4");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"1");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"5");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"2");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"6");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"3");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);
        Toast.makeText(ctx,"idFundoVariedad: "+temp,Toast.LENGTH_SHORT).show();


        db.close();
    }

    public void cargarTipoInspeccion(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"1");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion1");
        Long temp = db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);
        Toast.makeText(ctx,"idTipoInspeccion: "+temp,Toast.LENGTH_SHORT).show();
        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"2");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion2");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);
        Toast.makeText(ctx,"idTipoInspeccion: "+temp,Toast.LENGTH_SHORT).show();
        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"3");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion3");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);
        Toast.makeText(ctx,"idTipoInspeccion: "+temp,Toast.LENGTH_SHORT).show();

        db.close();

    }


}
