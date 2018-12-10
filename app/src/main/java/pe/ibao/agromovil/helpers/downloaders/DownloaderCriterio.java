package pe.ibao.agromovil.helpers.downloaders;

import android.app.ProgressDialog;
import android.content.Context;
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

import pe.ibao.agromovil.app.AppController;
import pe.ibao.agromovil.models.dao.CriterioDAO;
import pe.ibao.agromovil.models.dao.VariedadDAO;

import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_CRITERIO;
import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_VARIEDAD;

public class DownloaderCriterio {
    public static int status;
    Context ctx;
   // ProgressDialog progress;
    public DownloaderCriterio(Context ctx){
        this.ctx = ctx;
        status = 0;
    }

    public void download(){
        status = 1;
     //   progress = new ProgressDialog(ctx);
     //   progress.setCancelable(false);
     //   progress.setMessage("Intentando descargar Criterios");
     //   progress.show();
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_CRITERIO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
       //                 progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            if(main.length()>0){
                                new CriterioDAO(ctx).clearTableUpload();
                                status=2;
                            }
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = data.getString("nombre");
                                String tipo;
                                switch (data.getString("tipodato")){
                                    case "CHECK":
                                        tipo = "boolean";
                                        break;
                                    case "DECIMAL":
                                        tipo = "float";
                                        break;
                                    case "ENTERO":
                                        tipo = "int";
                                        break;
                                    case "LISTA":
                                        tipo = "list";
                                        break;
                                    case "TEXTO":
                                        tipo = "string";
                                        break;
                                    default:
                                        tipo ="string";
                                        break;
                                }
                                String magnitud = data.getString("magnitud");

                                int idTipoInspeccion = data.getInt("idTipoInspeccion");

                                Log.d("CRITERIOSDDOWN","fila "+i+" : "+id+" "+nombre+" "+tipo+" "+magnitud+" "+idTipoInspeccion);
                                if(new CriterioDAO(ctx).insertarCriterio(id,nombre,tipo,magnitud,idTipoInspeccion)){
                                    Log.d("CRITERIOSDDOWN","logro insertar");
                                }
                            }
                            status =3;
                        } catch (JSONException e) {
                            Log.d("CRITERIOSDDOWN ",e.toString());
                            status = -1;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
         //               progress.dismiss();
                        Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();
                        status = -2;
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

    public void download(final TextView porcentaje, final TextView mensaje, final int ini, final int tam) {
   /*     progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Criterios");
        progress.show();
     */
        status=1;
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_CRITERIO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
      //                  progress.dismiss();
                        try {
                            mensaje.setText("Descargando Criterios de Inspeccion");
                            JSONArray main = new JSONArray(response);
                            if(main.length()>0){
                                new CriterioDAO(ctx).clearTableUpload();
                                status=2;
                            }
                            final int length = main.length();
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = data.getString("nombre");
                                String tipo;
                                switch (data.getString("tipodato")){
                                    case "CHECK":
                                        tipo = "boolean";
                                        break;
                                    case "DECIMAL":
                                        tipo = "float";
                                        break;
                                    case "ENTERO":
                                        tipo = "int";
                                        break;
                                    case "LISTA":
                                        tipo = "list";
                                        break;
                                    case "TEXTO":
                                        tipo = "string";
                                        break;
                                    default:
                                        tipo ="string";
                                        break;
                                }
                                String magnitud = data.getString("magnitud");

                                int idTipoInspeccion = data.getInt("idTipoInspeccion");

                                Log.d("CRITERIOSDDOWN","fila "+i+" : "+id+" "+nombre+" "+tipo+" "+magnitud+" "+idTipoInspeccion);
                                if(new CriterioDAO(ctx).insertarCriterio(id,nombre,tipo,magnitud,idTipoInspeccion)){
                                    Log.d("CRITERIOSDDOWN","logro insertar");
                                    android.os.Handler handler = new android.os.Handler();
                                    final int finalI = i;
                                    handler.post(new Runnable() {
                                        public void run() {
                                            porcentaje.setText("" + (ini + ((finalI * tam) / length)) + "%");
                                        }
                                    });
                                }
                            }
                            status=3;
                 //           porcentaje.setText(String.valueOf(tam));

                        } catch (JSONException e) {
                            Log.d("CRITERIOSDDOWN ",e.toString());
                            status=-1;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
           //             progress.dismiss();
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
