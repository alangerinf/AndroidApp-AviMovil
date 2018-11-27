package pe.ibao.agromovil.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.utilities.UploaderDB;

public class ActivityUpdate extends AppCompatActivity {

    private static TextView porcentaje;
    private static TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        porcentaje = (TextView) findViewById(R.id.update_tViewPorcentaje);
        mensaje = (TextView) findViewById(R.id.update_tViewMensaje);
        r.run();
        new UploaderDB(this,porcentaje,mensaje);
        /*

*/
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(porcentaje.getText().toString().equals("100%")){
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    public void run() {
                        openMain();
                    }
                });
            }else{
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Log.d("porcentaje ",porcentaje.getText().toString());
                        r.run();
                    }
                },500);
            }
        }
    };

    void openMain(){
        /*try {
            Thread.sleep(323);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Intent intent = new Intent(this,ActivityMain.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);
        }
    }


}
