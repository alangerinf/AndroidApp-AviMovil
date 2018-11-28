package pe.ibao.agromovil.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.ibao.agromovil.app.AppController;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.dao.EvaluacionDAO;
import pe.ibao.agromovil.models.dao.FotoDAO;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.dao.UsuarioDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.UsuarioVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.VisitaVO;

import static pe.ibao.agromovil.utilities.Utilities.URL_UPLOAD_MASTER;

public class UploadMaster {

    Context ctx;
    ProgressDialog progress;
    public UploadMaster(Context ctx){
        this.ctx = ctx;
    }

    public void Upload(){
        progress = new ProgressDialog(ctx);
        progress.setCancelable(false);
        progress.setMessage("Intentando descargar Configuracion Criterio");
        progress.show();
        StringRequest sr = new StringRequest(Request.Method.POST,
                URL_UPLOAD_MASTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        try {

                            JSONArray main = new JSONArray(response);
                            for(int i=0;i<main.length();i++){
                                JSONObject data = new JSONObject(main.get(i).toString());

                            }

                        } catch (JSONException e) {
                            //Log.d("CONFCRITERIODOWN ",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.dismiss();
                       // Toast.makeText(ctx,"Error conectando con el servidor",Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();

                //informacion del usuario
                Gson gson = new Gson();
                String usuarioJson = gson.toJson(new UsuarioDAO(ctx).verficarLogueo());
                params.put("usuario",usuarioJson);

                //visitas
                gson = new Gson();
                List<VisitaVO> visitas = new VisitaDAO(ctx).listarNoEditable();
                String visitasJson = gson.toJson(
                        visitas,
                        new TypeToken<ArrayList<VisitaVO>>() {}.getType());
                params.put("visitas",visitasJson);

                //Evaluaciones
                gson = new Gson();
                List<EvaluacionVO> evaluaciones = new EvaluacionDAO(ctx).listarAll();
                String evaluacionesJson = gson.toJson(
                        evaluaciones,
                        new TypeToken<ArrayList<EvaluacionVO>>() {}.getType());
                params.put("evaluaciones",evaluacionesJson);

                //muestas
                gson = new Gson();
                List<MuestraVO> muestas = new MuestrasDAO(ctx).listarAll();
                String muestasJson = gson.toJson(
                        muestas,
                        new TypeToken<ArrayList<MuestraVO>>() {}.getType());
                params.put("muestras",muestasJson);
                //fotos
                gson = new Gson();
                List<FotoVO> fotos = new FotoDAO(ctx).listarAll();
                String fotosJson = gson.toJson(
                        fotos,
                        new TypeToken<ArrayList<FotoVO>>() {}.getType());
                params.put("fotos",fotosJson);

                Log.d("usuarioJson",usuarioJson);
                Log.d("visitasJson",visitasJson);
                Log.d("evaluacionesJson",evaluacionesJson);
                Log.d("muestasJson",muestasJson);
                Log.d("fotosJson",fotosJson);

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