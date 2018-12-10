package pe.ibao.agromovil.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.UploadMaster;
import pe.ibao.agromovil.models.dao.EvaluacionDAO;
import pe.ibao.agromovil.models.dao.FotoDAO;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.dao.RecomendacionDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;

public class ActivityUpload extends AppCompatActivity {

    private static TextView porcentaje;
    private static TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        porcentaje = (TextView) findViewById(R.id.update_tViewPorcentaje);
        mensaje = (TextView) findViewById(R.id.update_tViewMensaje);


        Toast.makeText(getBaseContext(),"SUBIENDO",
                Toast.LENGTH_SHORT).show();
        UploadMaster.status=0;

        new UploadMaster(getBaseContext()).Upload(
                new VisitaDAO(getBaseContext()).listarNoEditable()
                ,new EvaluacionDAO(getBaseContext()).listarAll()
                ,new MuestrasDAO(getBaseContext()).listarAll()
                ,new FotoDAO(getBaseContext()).listarAll()
                ,new RecomendacionDAO(getBaseContext()).listarAll()
        );
        r.run();

    }


    int i=0;

    Runnable r = new Runnable() {
        @Override
        public void run() {
        i++;
        Log.d("statusUpload",""+UploadMaster.status+" "+i);
                if(UploadMaster.status==1){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            mensaje.setText("Subiendo Fotos");
                            porcentaje.setText("50%");
                            Log.d("porcentaje ",porcentaje.getText().toString());
                            r.run();
                        }
                    },1);

                    Log.d("qmierda",""+i);
                }else {
                    if (UploadMaster.status == 2) {
                        openMain();
                    }else{
                        if(UploadMaster.status == 0){
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    mensaje.setText("Subiendo Archivos");
                                    porcentaje.setText("20%");
                                    Log.d("porcentaje ",porcentaje.getText().toString());
                                    r.run();
                                }
                            },500);
                        }
                        Log.d("tamano",""+i);

                    }
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
        onBackPressed();
    }


}