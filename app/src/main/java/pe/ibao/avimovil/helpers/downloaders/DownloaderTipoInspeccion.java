package pe.ibao.avimovil.helpers.downloaders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pe.ibao.avimovil.ConexionSQLiteHelper;
import pe.ibao.avimovil.app.AppController;
import pe.ibao.avimovil.helpers.LoginHelper;
import pe.ibao.avimovil.models.dao.TipoInspeccionDAO;
import pe.ibao.avimovil.models.vo.entitiesInternal.UsuarioVO;

import static pe.ibao.avimovil.ConexionSQLiteHelper.VERSION_DB;
import static pe.ibao.avimovil.utilities.Utilities.DATABASE_NAME;
import static pe.ibao.avimovil.utilities.Utilities.TABLE_TIPOINSPECCION;
import static pe.ibao.avimovil.utilities.Utilities.TABLE_TIPOINSPECCION_COL_ID;
import static pe.ibao.avimovil.utilities.Utilities.TABLE_TIPOINSPECCION_COL_NAME;
import static pe.ibao.avimovil.utilities.Utilities.URL_DOWNLOAD_TABLE_TIPOINSPECCION;

public class DownloaderTipoInspeccion {

    Context ctx;
  //  ProgressDialog progress;
    public static int status;
    public DownloaderTipoInspeccion(Context ctx){
        status=0;
        this.ctx = ctx;
    }

    public void download(){
        status=1;
    //    progress = new ProgressDialog(ctx);
    //    progress.setCancelable(false);
    //    progress.setMessage("Intentando descargar Tipo Inpeccion");
    //    progress.show();
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_TIPOINSPECCION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
        //                progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            if(main.length()>0){
                                new TipoInspeccionDAO(ctx).clearTableUpload();
                                status=2;
                            }
                            String insert = "INSERT INTO " +
                                    TABLE_TIPOINSPECCION+
                                    "("+
                                    TABLE_TIPOINSPECCION_COL_ID+","+
                                    TABLE_TIPOINSPECCION_COL_NAME+
                                    ")"+
                                    "VALUES ";


                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = data.getString("nombre");
                                Log.d("TIPOINSPECCIONDOWN","fila "+i+" : "+id+" "+nombre);
                                /*
                                if(new TipoInspeccionDAO(ctx).insertarTipoInspeccion(id,nombre)){
                                    Log.d("TIPOINSPECCIONDOWN","logro insertar");
                                }
                                */
                                insert=insert+"("+id+",\""+nombre+"\")";
                                if(i%1000==0&& i>0){
                                    try{
                                        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,VERSION_DB);
                                        SQLiteDatabase db = conn.getWritableDatabase();
                                        db.execSQL(insert);
                                        db.close();
                                        conn.close();
                                        insert = "INSERT INTO " +
                                                TABLE_TIPOINSPECCION+
                                                "("+
                                                TABLE_TIPOINSPECCION_COL_ID+","+
                                                TABLE_TIPOINSPECCION_COL_NAME+
                                                ")"+
                                                "VALUES ";
                                    }catch (Exception e){
                                        Log.d("errorCR",e.toString());
                                    }
                                }else {
                                    if(main.length()-1!=i ){
                                        insert=insert+",";
                                    }
                                }
                            }
                            try{
                                ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, DATABASE_NAME,null,VERSION_DB );
                                SQLiteDatabase db = conn.getWritableDatabase();
                                db.execSQL(insert);
                                db.close();
                                conn.close();
                            }catch (Exception e){
                                Log.d("errorCR",e.toString());
                            }
                            status=3;
                        } catch (JSONException e) {
                            Log.d("TIPOINSPECCIONDOWN ",e.toString());
                            status=-1;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
         //               progress.dismiss();
                        Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();
                        status=-2;
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
               /* params.put(POST_USER, user);
                params.put(POST_PASSWORD, pass);
                */

                UsuarioVO temp = new LoginHelper(ctx).verificarLogueo();
                params.put("id",String.valueOf(temp.getId()));
                params.put("idInspector",String.valueOf(temp.getCodigo()));

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(sr);
    }

    public void download(final TextView porcentaje, final TextView mensaje, final int ini, final int tam) {
        /*progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Tipo Inpeccion");
        progress.show();
        */
        status=1;
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_TIPOINSPECCION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mensaje.setText("Descargando Tipos de Inspeccion");

//                        progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            final int length = main.length();
                            if(main.length()>0){
                                new TipoInspeccionDAO(ctx).clearTableUpload();
                                status=2;
                            }
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = data.getString("nombre");
                                Log.d("TIPOINSPECCIONDOWN","fila "+i+" : "+id+" "+nombre);
                                if(new TipoInspeccionDAO(ctx).insertarTipoInspeccion(id,nombre)){
                                    Log.d("TIPOINSPECCIONDOWN","logro insertar");
                                    android.os.Handler handler = new android.os.Handler();
                                    final int finalI = i;
                                    handler.post(new Runnable() {
                                        public void run() {

                                            porcentaje.setText("" + (ini + ((finalI * tam) / length)) + "%");
                                        }
                                    });
                                }

                            }
               //             porcentaje.setText(String.valueOf(tam));
                        status=3;
                        } catch (JSONException e) {
                            Log.d("TIPOINSPECCIONDOWN ",e.toString());
                            status=-1;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  progress.dismiss();
                        Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();
                        status=-2;
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
               /* params.put(POST_USER, user);
                params.put(POST_PASSWORD, pass);
                */

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(sr);
    }
}
