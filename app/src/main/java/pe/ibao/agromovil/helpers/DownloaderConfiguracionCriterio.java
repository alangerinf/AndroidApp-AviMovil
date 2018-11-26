package pe.ibao.agromovil.helpers;

import android.app.ProgressDialog;
import android.content.ContentValues;
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

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.app.AppController;
import pe.ibao.agromovil.utilities.Utilities;

import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_CONFIGURACIONCRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_FUNDOVARIEDAD;

public class DownloaderConfiguracionCriterio {

    Context ctx;
    ProgressDialog progress;
    public DownloaderConfiguracionCriterio(Context ctx){
        this.ctx = ctx;
    }

    public void download(){
        progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Configuracion Criterio");
        progress.show();
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_CONFIGURACIONCRITERIO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);

                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                int idFundoVariedad = data.getInt("idFundoVariedad");
                                int idCriterio = data.getInt("idCriterioInspeccion");
                                Log.d("CONFCRITERIODOWN","fila "+i+" : "+id+" "+idFundoVariedad+" "+idCriterio);

                                ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
                                SQLiteDatabase db = conn.getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,id);
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD,idFundoVariedad);
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO,idCriterio);
                                Long temp = db.insert(Utilities.TABLE_CONFIGURACIONCRITERIO,Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,values);

                                if(temp>0){
                                    Log.d("CONFCRITERIODOWN","logro insertar");
                                }

                                db.close();
                                conn.close();
                            }

                        } catch (JSONException e) {
                            Log.d("CONFCRITERIODOWN ",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();

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

    public void download(final TextView porcentaje, TextView mensaje,final int ini, final int tam) {
        /*progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Configuracion Criterio");
        progress.show();
        */StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_CONFIGURACIONCRITERIO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            final int length = main.length();
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                int idFundoVariedad = data.getInt("idFundoVariedad");
                                int idCriterio = data.getInt("idCriterioInspeccion");
                                Log.d("CONFCRITERIODOWN","fila "+i+" : "+id+" "+idFundoVariedad+" "+idCriterio);

                                ConexionSQLiteHelper conn=new ConexionSQLiteHelper(ctx, Utilities.DATABASE_NAME,null,1 );
                                SQLiteDatabase db = conn.getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,id);
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDFUNDOVARIEDAD,idFundoVariedad);
                                values.put(Utilities.TABLE_CONFIGURACIONCRITERIO_COL_IDCRITERIO,idCriterio);
                                Long temp = db.insert(Utilities.TABLE_CONFIGURACIONCRITERIO,Utilities.TABLE_CONFIGURACIONCRITERIO_COL_ID,values);

                                if(temp>0){
                                    Log.d("CONFCRITERIODOWN","logro insertar");
                                    android.os.Handler handler = new android.os.Handler();
                                    final int finalI = i;
                                    handler.post(new Runnable() {
                                        public void run() {

                                            porcentaje.setText("" + (ini + ((finalI * tam) / length)) + "%");
                                        }
                                    });
                                }

                                db.close();
                                conn.close();
                            }
                           // porcentaje.setText(String.valueOf(tam));

                        } catch (JSONException e) {
                            Log.d("CONFCRITERIODOWN ",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                        Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();

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
