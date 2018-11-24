package pe.ibao.agromovil.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.helpers.DownloaderConfiguracionCriterio;
import pe.ibao.agromovil.helpers.DownloaderCriterio;
import pe.ibao.agromovil.helpers.DownloaderCultivo;
import pe.ibao.agromovil.helpers.DownloaderEmpresa;
import pe.ibao.agromovil.helpers.DownloaderFundo;
import pe.ibao.agromovil.helpers.DownloaderFundoVariedad;
import pe.ibao.agromovil.helpers.DownloaderTipoInspeccion;
import pe.ibao.agromovil.helpers.DownloaderVariedad;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;

public class UploaderDB {

    private Context ctx;

    public UploaderDB(Context ctx) {
        this.ctx = ctx;
        ConexionSQLiteHelper c;
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CULTIVO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDOVARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_TIPOINSPECCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CONFIGURACIONCRITERIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CRITERIO);
        /*
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VISITA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EVALUACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_MUESTRA);
*/
        db.execSQL(Utilities.CREATE_TABLE_USUARIO);
        db.execSQL(Utilities.CREATE_TABLE_EMPRESA);
        db.execSQL(Utilities.CREATE_TABLE_FUNDO);
        db.execSQL(Utilities.CREATE_TABLE_CULTIVO);
        db.execSQL(Utilities.CREATE_TABLE_VARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_FUNDOVARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_TIPOINSPECCION);
        db.execSQL(Utilities.CREATE_TABLE_CONFIGURACIONCRITERIO);
        db.execSQL(Utilities.CREATE_TABLE_CRITERIO);
        db.close();
        c.close();

        DownloaderEmpresa emp = new DownloaderEmpresa(ctx);
        emp.download();
        DownloaderFundo fun = new DownloaderFundo(ctx);
        fun.download();
        DownloaderCultivo cul = new DownloaderCultivo(ctx);
        cul.download();
        DownloaderVariedad var = new DownloaderVariedad(ctx);
        var.download();
        DownloaderFundoVariedad fva = new DownloaderFundoVariedad(ctx);
        fva.download();
        DownloaderTipoInspeccion ti = new DownloaderTipoInspeccion(ctx);
        ti.download();
        DownloaderConfiguracionCriterio cc = new DownloaderConfiguracionCriterio(ctx);
        cc.download();
        DownloaderCriterio cri = new DownloaderCriterio(ctx);
        cri.download();

/*
        cargarEmpresas();
        cargarFundos();
        cargarCultivos();
        cargarVariedad();
        cargarFundoVariedad();
        cargarTipoInspeccion();
        cargarCriterio();
        cargarConfiguracionCriterio();
        //cargarVisitas();
        //cargarEvaluaciones();
        //cargarMuestras();
        */
    }









    public void cargarEmpresas(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"1");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"AgroValle");
        Long temp = db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"2");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"AgroPeru");
        temp =db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);
        db.close();

    }
    public void cargarFundos(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"1");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"Santo Domingo");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"1");
        Long temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"2");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"San Juan");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"1");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);

                values = new ContentValues();
        values.put(Utilities.TABLE_FUNDO_COL_ID,"3");
        values.put(Utilities.TABLE_FUNDO_COL_NAME,"Santa Clara");
        values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"2");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_FUNDO_COL_ID,"4");
        values.put(Utilities.TABLE_FUNDO_COL_NAME,"Santa Isabel");
        values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"2");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        db.close();
    }

    public void cargarCultivos(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"1");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"Arándano");
        Long temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"2");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"Palto");
        temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);


        db.close();
    }
    public void cargarVariedad(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"1");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"Biloxi");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"1");
        Long temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"2");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"Hass");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"2");
        temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"3");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"Fuerte");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"2");
        temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);
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

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"1");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"2");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"2");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"4");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"1");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"5");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"2");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_ID,"6");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDFUNDO,"3");
            values.put(Utilities.TABLE_FUNDOVARIEDAD_COL_IDVARIEDAD,"3");
        temp = db.insert(Utilities.TABLE_FUNDOVARIEDAD,Utilities.TABLE_FUNDOVARIEDAD_COL_ID,values);

        db.close();
    }

    public void cargarTipoInspeccion(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"1");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"Estado Fenológico");
        Long temp = db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"2");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"Problemas Fisiológicos");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"3");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"Estado Fenológico");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        db.close();

    }

    public void cargarCriterio(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"1");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Prefloración");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"float");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"°C");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        Long temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"2");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Floración");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"int");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"ml");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"3");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Cuajado");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);


        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"4");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Brote vegetativo de primavera");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"list");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"poco-medio-alto");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"5");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Brote vegetativo de verano");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);


        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"6");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"Crecimiento de fruto");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"2");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"7");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO72");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"2");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"8");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO82");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"float");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"°K");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"2");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"9");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO93");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"float");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"°K");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"3");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"10");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO10.3");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"3");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        db.close();
    }



    public void cargarConfiguracionCriterio(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        int k=0;
        for(int i=1;i<=6;i++){
            for(int j=1;j<=10;j++){
                k++;
                ContentValues values = new ContentValues();
                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,k);
                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO,i);
                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD,j);
                Long temp = db.insert(Utilities.TABLE_CONFIGURACIONCRITERIO,Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,values);
            }
        }
        db.close();
    }


    public void cargarVisitas(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        Log.d("cargarVisita","asdasdsad");
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_VISITA_COL_ID,1);
        values.put(Utilities.TABLE_VISITA_COL_EDITING,false);
        values.put(Utilities.TABLE_VISITA_COL_IDVARIEDAD,"1");
        values.put(Utilities.TABLE_VISITA_COL_IDFUNDO,"1");
        Long temp = db.insert(Utilities.TABLE_VISITA,Utilities.TABLE_VISITA_COL_ID,values);
        Log.d("cargarVisita",String.valueOf(temp));
        values = new ContentValues();
        values.put(Utilities.TABLE_VISITA_COL_ID,2);
        values.put(Utilities.TABLE_VISITA_COL_EDITING,true);
        values.put(Utilities.TABLE_VISITA_COL_IDVARIEDAD,"1");
        values.put(Utilities.TABLE_VISITA_COL_IDFUNDO,"1");
        values.put(Utilities.TABLE_VISITA_COL_CONTACTO,"Alan Geronimo");
        temp = db.insert(Utilities.TABLE_VISITA,Utilities.TABLE_VISITA_COL_ID,values);
        Log.d("cargarVisita",String.valueOf(temp));
        db.close();
    }

    public void cargarEvaluaciones(){
        Log.d("cargar","hola00");
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_EVALUACION_COL_ID,"1");
        values.put(Utilities.TABLE_EVALUACION_COL_QR,"QR_EXAMPLE");
        values.put(Utilities.TABLE_EVALUACION_COL_LATITUD,1231.123);
        values.put(Utilities.TABLE_EVALUACION_COL_LONGITUD,-12312.555999);
        values.put(Utilities.TABLE_EVALUACION_COL_IDVISITA,"2");
        values.put(Utilities.TABLE_EVALUACION_COL_IDTIPOINSPECCION,1);
        Long temp = db.insert(Utilities.TABLE_EVALUACION,Utilities.TABLE_EVALUACION_COL_ID,values);

        values = new ContentValues();

        values.put(Utilities.TABLE_EVALUACION_COL_ID,"2");
        values.put(Utilities.TABLE_EVALUACION_COL_QR,"QR_EXAMPLE");
        values.put(Utilities.TABLE_EVALUACION_COL_LATITUD,1231.123);
        values.put(Utilities.TABLE_EVALUACION_COL_LONGITUD,-12312.555999);
        values.put(Utilities.TABLE_EVALUACION_COL_IDVISITA,"2");
        values.put(Utilities.TABLE_EVALUACION_COL_IDTIPOINSPECCION,1);
        temp = db.insert(Utilities.TABLE_EVALUACION,Utilities.TABLE_EVALUACION_COL_ID,values);

        values = new ContentValues();

        values.put(Utilities.TABLE_EVALUACION_COL_ID,"3");
        values.put(Utilities.TABLE_EVALUACION_COL_QR,"QR_EXAMPLE");
        values.put(Utilities.TABLE_EVALUACION_COL_LATITUD,1231.123);
        values.put(Utilities.TABLE_EVALUACION_COL_LONGITUD,-12312.555999);
        values.put(Utilities.TABLE_EVALUACION_COL_IDVISITA,"2");
        values.put(Utilities.TABLE_EVALUACION_COL_IDTIPOINSPECCION,1);
        temp = db.insert(Utilities.TABLE_EVALUACION,Utilities.TABLE_EVALUACION_COL_ID,values);

        db.close();
    }

    public void cargarMuestras(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_MUESTRA_COL_ID,1);
        values.put(Utilities.TABLE_MUESTRA_COL_VALUE,"");
        values.put(Utilities.TABLE_MUESTRA_COL_IDCRITERIO,1);
        values.put(Utilities.TABLE_MUESTRA_COL_IDEVALUACION,1);
        Long temp = db.insert(Utilities.TABLE_MUESTRA,Utilities.TABLE_MUESTRA_COL_ID,values);
        db.close();
    }

}
