package pe.ibao.agromovil;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pe.ibao.agromovil.controllers.AppController;

import static android.content.Context.MODE_PRIVATE;

public class DataUserHandler {

    //no cammbiar nunca en la vida

    private final String path="apps.ibao.pe/request/autentificar.php";

    private final String SESSION = "logon";
        //estructura del login y last login
        private final String USER = "user";
        private final String PASSWORD = "password";
        private final String STATUS_LOG = "status";

    //no tocar son nombres de cabeceras
    private SharedPreferences pref;

    public DataUserHandler(Context ctx){
        pref = ctx.getSharedPreferences(SESSION, MODE_PRIVATE);
    }

    /*
    0 - otro ya esta logueado
    1 - logro loguearse
    */

    public  boolean login(final String user, final String password){
        boolean res;
        /*
        StringRequest sr = new StringRequest(Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject data = new JSONObject(response);
                            int success = data.getInt("success");
                            if(success==1){

                                int estado = data.getInt("estado");
                                if(estado==1){
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putInt("id_usuario", data.getInt("id_usuario"));
                                    editor.putInt("id_pedido", 0);
                                    editor.putString("usuario", data.getString("usuario"));
                                    editor.putBoolean("disponible", false);
                                    editor.putString("tipo", data.getString("tipo"));
                                    editor.putInt("estado", estado);
                                    editor.putString("factura", data.getString("factura"));
                                    editor.putInt("conectado",1);
                                    editor.commit();



                                }

                            }else{
                                global.ShowToast(ctx,"DNI o contraseña incorrecto");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put(USER, user);
                params.put(PASSWORD, password);

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
*/

        if(pref.contains(STATUS_LOG)) {//si alguien ya se logueo antes
            if (pref.getBoolean(STATUS_LOG, false)){
                //agregando nuevo usuario
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(USER,user);
                editor.putString(PASSWORD,password);
                editor.putBoolean(STATUS_LOG,true);
                editor.commit();
                res=true;
            }else{
                res = false; //alguien ya esta logueado
            }

        }else{
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(USER,user);
            editor.putString(PASSWORD,password);
            editor.putBoolean(STATUS_LOG,true);
            editor.commit();
            res=true;
        }

        return res;
    }


    public String getUser(){
        String resp="VOID";
        if(pref.contains(STATUS_LOG)){
            if(pref.getBoolean(STATUS_LOG,false) && pref.contains(USER)){
                resp=pref.getString(USER,"VOID");
            }
        }
        return  resp;
    }
    public Boolean isLogin(){
        Boolean res = false;
        if(pref.contains(STATUS_LOG)){
            res= pref.getBoolean(STATUS_LOG,false);
        }
        return res;
    }



    public void logout(){
        if(pref.contains(STATUS_LOG)){
            SharedPreferences.Editor  editor = pref.edit();
                editor.putBoolean(STATUS_LOG,false);
                editor.commit();
        }
    }




}
