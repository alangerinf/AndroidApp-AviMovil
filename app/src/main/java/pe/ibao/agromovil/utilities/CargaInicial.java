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
        cargarCriterio();
        cargarConfiguracionCriterio();

    }


    public void cargarEmpresas(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"1");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"EMPRESA1");
        Long temp = db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);

        values = new ContentValues();
        values.put(Utilities.TABLE_EMPRESA_COL_ID,"2");
        values.put(Utilities.TABLE_EMPRESA_COL_NAME,"EMPRESA2");
        temp =db.insert(Utilities.TABLE_EMPRESA,Utilities.TABLE_EMPRESA_COL_ID,values);

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


        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"2");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"FUNDO21");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"1");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        Toast.makeText(ctx,"ingreso:"+temp,Toast.LENGTH_SHORT);

        values = new ContentValues();
            values.put(Utilities.TABLE_FUNDO_COL_ID,"3");
            values.put(Utilities.TABLE_FUNDO_COL_NAME,"FUNDO32");
            values.put(Utilities.TABLE_FUNDO_COL_IDEMPRESA,"2");
        temp = db.insert(Utilities.TABLE_FUNDO,Utilities.TABLE_FUNDO_COL_ID,values);
        Toast.makeText(ctx,"ingreso:"+temp,Toast.LENGTH_SHORT);
        db.close();
    }

    public void cargarCultivos(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"1");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"CULTIVO1");
        Long temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CULTIVO_COL_ID,"2");
            values.put(Utilities.TABLE_CULTIVO_COL_NAME,"CULTIVO2");
        temp = db.insert(Utilities.TABLE_CULTIVO,Utilities.TABLE_CULTIVO_COL_ID,values);


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

        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"2");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"VARIEDAD21");
            values.put(Utilities.TABLE_VARIEDAD_COL_IDCULTIVO,"1");
        temp = db.insert(Utilities.TABLE_VARIEDAD,Utilities.TABLE_VARIEDAD_COL_ID,values);



        values = new ContentValues();
            values.put(Utilities.TABLE_VARIEDAD_COL_ID,"3");
            values.put(Utilities.TABLE_VARIEDAD_COL_NAME,"VARIEDAD32");
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
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion1");
        Long temp = db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"2");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion2");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_ID,"3");
            values.put(Utilities.TABLE_TIPOINSPECCION_COL_NAME,"TipoInspeccion3");
        temp =db.insert(Utilities.TABLE_TIPOINSPECCION,Utilities.TABLE_TIPOINSPECCION_COL_ID,values);

        db.close();

    }

    public void cargarCriterio(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"1");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO11");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"int");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"°C");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        Long temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"2");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO21");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"float");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"ml");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"3");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO31");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"string");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);


        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"4");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO41");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"list");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"poco-medio-alto");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);

        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"5");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO51");
            values.put(Utilities.TABLE_CRITERIO_COL_TIPO,"boolean");
            values.put(Utilities.TABLE_CRITERIO_COL_MAGNITUD,"");
            values.put(Utilities.TABLE_CRITERIO_COL_IDTIPOINSPECCION,"1");
        temp = db.insert(Utilities.TABLE_CRITERIO,Utilities.TABLE_CRITERIO_COL_ID,values);


        values = new ContentValues();
            values.put(Utilities.TABLE_CRITERIO_COL_ID,"6");
            values.put(Utilities.TABLE_CRITERIO_COL_NAME,"CRITERIO62");
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

}
