package pe.ibao.agromovil.views;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import pe.ibao.agromovil.DataUserHandler;
import pe.ibao.agromovil.R;

public class Preloader extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);

        //delay de 1 segundo
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                DataUserHandler duh = new DataUserHandler(getBaseContext());
                //verificamos si tenemos internet
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // Si hay conexión a Internet en este momento
                    Toast.makeText(getBaseContext(),"Online Mode On!",
                            Toast.LENGTH_SHORT).show();


                    if(duh.isLogin()){
                        if(true){//si tu contraseña sigue siendo la misma
                            verifyUpdate();//verificar actualizacion
                        }else{//si estas logueado y tu contraseña no es la misma
                            Toast.makeText(getBaseContext(),"Contraseña no es la misma",
                                    Toast.LENGTH_SHORT).show();
                            login();//mostrar activity de de login
                        }

                    }else{
                        //si tienes internet y no estas logueado
                        login();

                    }


                } else {
                    Toast.makeText(getBaseContext(),"Offline Mode On!",
                            Toast.LENGTH_SHORT).show();
                    //varieble para comprovar si esas logueado

                    //si no hay internet pero estas logueado
                    if(duh.isLogin()){
                        ingresar();
                    }else{//si no estas logueado ni tienes conexion a internet por las puras entrarias
                        Toast.makeText(getBaseContext(),"Tampoco estas logueado, conectate a internet, gracias!",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        }, 500);


    }

    void  login(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    void  ingresar(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
    void verifyUpdate(){
        Intent intent = new Intent(this,Update.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }



}
