package pe.ibao.agromovil.views;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.downloaders.DownloaderConfiguracionCriterio;
import pe.ibao.agromovil.helpers.downloaders.DownloaderConfiguracionRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderContacto;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCriterio;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCriterioRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderCultivo;
import pe.ibao.agromovil.helpers.downloaders.DownloaderEmpresa;
import pe.ibao.agromovil.helpers.downloaders.DownloaderFundo;
import pe.ibao.agromovil.helpers.downloaders.DownloaderFundoVariedad;
import pe.ibao.agromovil.helpers.downloaders.DownloaderTipoInspeccion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderTipoRecomendacion;
import pe.ibao.agromovil.helpers.downloaders.DownloaderVariedad;
import pe.ibao.agromovil.services.UpdateService;
import pe.ibao.agromovil.utilities.UpdateDB;

public class ActivityUpdate extends AppCompatActivity {

    private static TextView porcentaje;
    private static TextView mensaje;

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(UpdateService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            final Bundle bundle = intent.getExtras();
            //Log.d("cargando",""+bundle.getString(UpdateService.RESULT_MENSAJE)+" "+bundle.getInt(UpdateService.RESULT_PORCENT));

            if (bundle != null) {
                final int resultCode = bundle.getInt(UpdateService.RESULT);
                if (resultCode == RESULT_OK) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("cargando",""+bundle.getString(UpdateService.RESULT_MENSAJE)+" "+bundle.getInt(UpdateService.RESULT_PORCENT));
                            String menActual = bundle.getString(UpdateService.RESULT_MENSAJE);
                            String porActual = ""+bundle.getInt(UpdateService.RESULT_PORCENT)+"%";
                            if(!porcentaje.getText().toString().equals(porActual)){
                                porcentaje.setText(porActual);
                                if(porActual.equals("100%")){
                                    openMain();
                                }
                            }
                            if(!mensaje.getText().toString().equals(menActual)){
                                mensaje.setText(menActual);
                            }
                        }
                    });
                } else {

                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("cargando",""+bundle.getString(UpdateService.RESULT_MENSAJE)+" "+bundle.getInt(UpdateService.RESULT_PORCENT));
                            String menActual = bundle.getString(UpdateService.RESULT_MENSAJE);
                            String porActual = ""+bundle.getInt(UpdateService.RESULT_PORCENT)+"%";
                            if(!porcentaje.getText().toString().equals(porActual)){
                                porcentaje.setText(porActual);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if(porActual.equals("-2%")){
                                    openMain();
                                }else {
                                    if(porActual.equals("-1%")){
                                        openMain();
                                    }
                                }
                            }
                            if(!mensaje.getText().toString().equals(menActual)){
                                mensaje.setText(menActual);
                            }
                        }
                    });
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        porcentaje = (TextView) findViewById(R.id.update_tViewPorcentaje);
        mensaje = (TextView) findViewById(R.id.update_tViewMensaje);
        Intent intent = new Intent(this,UpdateService.class);
        startService(intent);
        mensaje.setText("Buscando Actulizacion");



    }

    void openMain(){
        /**/
        Intent intent = new Intent(this,ActivityMain.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }





}


