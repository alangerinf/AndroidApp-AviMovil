package pe.ibao.agromovil.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.helpers.downloaders.DownloaderConfiguracionCriterio;
import pe.ibao.agromovil.helpers.downloaders.DownloaderConfiguracionRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderContacto;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCriterio;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCriterioRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCultivo;
import pe.ibao.agromovil.helpers.downloaders.DownloaderEmpresa;
import pe.ibao.agromovil.helpers.downloaders.DownloaderFundo;
import pe.ibao.agromovil.helpers.downloaders.DownloaderFundoVariedad;
import pe.ibao.agromovil.helpers.downloaders.DownloaderTipoInspeccion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderTipoRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderVariedad;

import static pe.ibao.agromovil.utilities.Utilities.DATABASE_NAME;

public class UploaderDB {

    private Context ctx;
    TextView porcentaje;
    TextView mensaje;

    public UploaderDB(Context ctx, TextView porcentaje, TextView mensaje){
        this.ctx = ctx;
        this.porcentaje = porcentaje;
        this.mensaje = mensaje;
        ConexionSQLiteHelper c;
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CULTIVO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_FUNDOVARIEDAD);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_TIPOINSPECCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CONFIGURACIONCRITERIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CRITERIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CONTACTO);

        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_TIPORECOMENDACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CRITERIORECOMENDACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_CONFIGURACIONRECOMENDACION);

        /*
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_VISITA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_EVALUACION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLE_MUESTRA);
*/
        db.execSQL(Utilities.CREATE_TABLE_EMPRESA);
        db.execSQL(Utilities.CREATE_TABLE_FUNDO);
        db.execSQL(Utilities.CREATE_TABLE_CULTIVO);
        db.execSQL(Utilities.CREATE_TABLE_VARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_FUNDOVARIEDAD);
        db.execSQL(Utilities.CREATE_TABLE_TIPOINSPECCION);
        db.execSQL(Utilities.CREATE_TABLE_CONFIGURACIONCRITERIO);
        db.execSQL(Utilities.CREATE_TABLE_CRITERIO);
        db.execSQL(Utilities.CREATE_TABLE_CONTACTO);

        db.execSQL(Utilities.CREATE_TABLE_TIPORECOMENDACION);
        db.execSQL(Utilities.CREATE_TABLE_CRITERIORECOMENDACION);
        db.execSQL(Utilities.CREATE_TABLE_CONFIGURACIONRECOMENDACION);
        db.close();
        c.close();

        DownloaderContacto cont = new DownloaderContacto(ctx);
        cont.download(porcentaje,mensaje,0,10);
        DownloaderEmpresa emp = new DownloaderEmpresa(ctx);
        emp.download(porcentaje,mensaje,0,10);
        DownloaderFundo fun = new DownloaderFundo(ctx);
        fun.download(porcentaje,mensaje,10,20);
        DownloaderCultivo cul = new DownloaderCultivo(ctx);
        cul.download(porcentaje,mensaje,30,20);
        DownloaderVariedad var = new DownloaderVariedad(ctx);
        var.download(porcentaje,mensaje,50,10);
        DownloaderFundoVariedad fva = new DownloaderFundoVariedad(ctx);
        fva.download(porcentaje,mensaje,60,10);
        DownloaderTipoInspeccion ti = new DownloaderTipoInspeccion(ctx);
        ti.download(porcentaje,mensaje,70,10);
        DownloaderConfiguracionCriterio cc = new DownloaderConfiguracionCriterio(ctx);
        cc.download(porcentaje,mensaje,80,10);
        DownloaderCriterio cri = new DownloaderCriterio(ctx);
        cri.download(porcentaje,mensaje,81,10);
        DownloaderTipoRecomendacion tre = new DownloaderTipoRecomendacion(ctx);
        tre.download(porcentaje,mensaje,81,10);
        DownloaderConfiguracionRecomendacion core= new DownloaderConfiguracionRecomendacion(ctx);
        core.download(porcentaje,mensaje,81,10);
        DownloaderCriterioRecomendacion cre = new DownloaderCriterioRecomendacion(ctx);
        cre.download(porcentaje,mensaje,91,10);

        //cargarContactos();
        // cargarTipoRecomendaciones();
        //cargarCriterioRecomendaciones();
       // cargarConfiguracionRecomendacion();
    }

    public UploaderDB(Context ctx) {
        this.ctx = ctx;
        ConexionSQLiteHelper c;
        c = new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,1 );
        SQLiteDatabase db = c.getWritableDatabase();
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




    public void cargarContactos(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        Long temp;
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_CONTACTO_COL_ID,"1");
        values.put(Utilities.TABLE_CONTACTO_COL_NAME,"Josue1");
        values.put(Utilities.TABLE_CONTACTO_COL_IDFUNDO,"1");
        temp = db.insert(Utilities.TABLE_CONTACTO,Utilities.TABLE_CONTACTO_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_CONTACTO_COL_ID,"2");
        values.put(Utilities.TABLE_CONTACTO_COL_NAME,"Alan2");
        values.put(Utilities.TABLE_CONTACTO_COL_IDFUNDO,"1");
        temp =db.insert(Utilities.TABLE_CONTACTO,Utilities.TABLE_CONTACTO_COL_ID,values);
        db.close();
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
        values.put(Utilities.TABLE_VISITA_COL_IDCONTACTO,"1");
        Long temp = db.insert(Utilities.TABLE_VISITA,Utilities.TABLE_VISITA_COL_ID,values);
        Log.d("cargarVisita",String.valueOf(temp));
        values = new ContentValues();
        values.put(Utilities.TABLE_VISITA_COL_ID,2);
        values.put(Utilities.TABLE_VISITA_COL_EDITING,true);
        values.put(Utilities.TABLE_VISITA_COL_IDVARIEDAD,"1");
        values.put(Utilities.TABLE_VISITA_COL_IDFUNDO,"1");
        values.put(Utilities.TABLE_VISITA_COL_IDCONTACTO,"1");
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
/*
    public void cargarTipoRecomendaciones(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_TIPORECOMENDACION_COL_ID,1);
        values.put(Utilities.TABLE_TIPORECOMENDACION_COL_NAME,"FERTILIZACION");
        Long temp = db.insert(Utilities.TABLE_TIPORECOMENDACION,Utilities.TABLE_TIPORECOMENDACION_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_TIPORECOMENDACION_COL_ID,2);
        values.put(Utilities.TABLE_TIPORECOMENDACION_COL_NAME,"PURGA");
        temp = db.insert(Utilities.TABLE_TIPORECOMENDACION,Utilities.TABLE_TIPORECOMENDACION_COL_ID,values);

        db.close();
    }
*/
    public void cargarCriterioRecomendaciones(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,1);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION,1);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_NAME,"Sulfato de Cobre");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS,"1vez/dia-2/veces/dia-3/veces/dia");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES,"mlts-cm²");
        Long temp = db.insert(Utilities.TABLE_CRITERIORECOMENDACION,Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,2);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION,1);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_NAME,"Sulfato de Azufre");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS,"1vez/dia-2/veces/dia-3/veces/dia");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES,"mlts-cm²");
        temp = db.insert(Utilities.TABLE_CRITERIORECOMENDACION,Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,values);


        values = new ContentValues();
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,3);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_IDTIPORECOMENDACION,2);
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_NAME,"Sulfato de Sultato");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTFRECUENCIAS,"1vez/dia-2/veces/dia-3/veces/dia");
        values.put(Utilities.TABLE_CRITERIORECOMENDACION_COL_LISTUNIDADES,"mlts-cm²");
        temp = db.insert(Utilities.TABLE_CRITERIORECOMENDACION,Utilities.TABLE_CRITERIORECOMENDACION_COL_ID,values);

        db.close();
    }

    public void cargarConfiguracionRecomendacion(){
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,1);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDCRITERIORECOMENDACION,1);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDFUNDOVARIEDAD,1);
        Long temp = db.insert(Utilities.TABLE_CONFIGURACIONRECOMENDACION,Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,2);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDCRITERIORECOMENDACION,2);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDFUNDOVARIEDAD,1);
        temp = db.insert(Utilities.TABLE_CONFIGURACIONRECOMENDACION,Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,3);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDCRITERIORECOMENDACION,3);
        values.put(Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_IDFUNDOVARIEDAD,1);
        temp = db.insert(Utilities.TABLE_CONFIGURACIONRECOMENDACION,Utilities.TABLE_CONFIGURACIONRECOMENDACION_COL_ID,values);

        db.close();
    }

}
