package pe.ibao.agromovil.helpers;

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
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.dao.FundoDAO;

import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_EMPRESA;
import static pe.ibao.agromovil.utilities.Utilities.URL_DOWNLOAD_TABLE_FUNDO;

public class DownloaderFundo {

    Context ctx;
    ProgressDialog progress;
    public DownloaderFundo(Context ctx){
        this.ctx = ctx;
    }

    public void download(){
        progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Fundos");
        progress.show();
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_FUNDO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = String.valueOf(id)+"-"+data.getString("nombre");
                                int idEmpresa = data.getInt("idEmpresa");
                                Log.d("FUNDODOWN","fila "+i+" : "+id+" "+nombre+" "+idEmpresa);
                                if(new FundoDAO(ctx).insertarFundo(id,nombre,idEmpresa)){
                                    Log.d("FUNDODOWN","logro insertar");
                                }
                            }

                        } catch (JSONException e) {
                            Log.d("FUNDODOWN ",e.toString());
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

    public void download(final TextView porcentaje, TextView mensaje, final int ini,final int tam) {
     /*   progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Fundos");
        progress.show();
       */
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_DOWNLOAD_TABLE_FUNDO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progress.dismiss();
                        try {
                            JSONArray main = new JSONArray(response);
                            final int length = main.length();
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());
                                int id = data.getInt("id");
                                String nombre = String.valueOf(id)+"-"+data.getString("nombre");
                                int idEmpresa = data.getInt("idEmpresa");
                                Log.d("FUNDODOWN","fila "+i+" : "+id+" "+nombre+" "+idEmpresa);
                                if(new FundoDAO(ctx).insertarFundo(id,nombre,idEmpresa)){
                                    Log.d("FUNDODOWN","logro insertar");
                                    android.os.Handler handler = new android.os.Handler();
                                    final int finalI = i;
                                    handler.post(new Runnable() {
                                        public void run() {

                                            porcentaje.setText("" + (ini + ((finalI * tam) / length)) + "%");
                                        }
                                    });
                                }
                            }
                         //   porcentaje.setText(String.valueOf(tam));

                        } catch (JSONException e) {
                            Log.d("FUNDODOWN ",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
         //               progress.dismiss();
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
