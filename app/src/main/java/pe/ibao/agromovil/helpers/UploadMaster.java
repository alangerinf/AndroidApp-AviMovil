package pe.ibao.agromovil.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.File;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.nearby.connection.Payload;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.ibao.agromovil.app.AppController;
import pe.ibao.agromovil.models.dao.EvaluacionDAO;
import pe.ibao.agromovil.models.dao.FotoDAO;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.dao.RecomendacionDAO;
import pe.ibao.agromovil.models.dao.UsuarioDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.RecomendacionVO;
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
                      //  progress.dismiss();
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

                //recomendaciones
                gson = new Gson();
                List<RecomendacionVO> recomendaciones = new RecomendacionDAO(ctx).listarAll();
                String recomendacionesJson = gson.toJson(
                        recomendaciones,
                        new TypeToken<ArrayList<RecomendacionVO>>() {}.getType());
                params.put("recomendaciones",recomendacionesJson);

                Log.d("usuarioJson",usuarioJson);
                Log.d("visitasJson",visitasJson);
                Log.d("evaluacionesJson",evaluacionesJson);
                Log.d("muestasJson",muestasJson);
                Log.d("fotosJson",fotosJson);
                Log.d("RecomendacionesJson",recomendacionesJson);
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


    private class UploadFileAsync extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                String sourceFileUri = "/mnt/sdcard/abc.png";

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(sourceFileUri);

                if (sourceFile.isFile()) {

                    try {
                        String upLoadServerUri = "http://website.com/abc.php?";

                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(
                                sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP connection to the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE",
                                "multipart/form-data");
                        conn.setRequestProperty("Content-Type",
                                "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("bill", sourceFileUri);

                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"bill\";filename=\""
                                + sourceFileUri + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);

                        // create a buffer of maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math
                                    .min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0,
                                    bufferSize);

                        }

                        // send multipart form data necesssary after file
                        // data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens
                                + lineEnd);

                        // Responses from the server (code and message)
                        int serverResponseCode = conn.getResponseCode();
                        String serverResponseMessage = conn
                                .getResponseMessage();

                        if (serverResponseCode == 200) {

                            // messageText.setText(msg);
                            //Toast.makeText(ctx, "File Upload Complete.",
                            //      Toast.LENGTH_SHORT).show();

                            // recursiveDelete(mDirectory1);

                        }

                        // close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();

                    } catch (Exception e) {

                        // dialog.dismiss();
                        e.printStackTrace();

                    }
                    // dialog.dismiss();

                } // End else block


            } catch (Exception ex) {
                // dialog.dismiss();

                ex.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}