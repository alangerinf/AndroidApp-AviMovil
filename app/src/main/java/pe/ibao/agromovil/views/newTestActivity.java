package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.CollectionCriterios;
import pe.ibao.agromovil.models.Criterio;

public class newTestActivity extends AppCompatActivity {


    static final private int REQUEST_QR_CODE = 1;
    //static final int REQUEST_GPS_LOCATION = 2;
    static final public String QR_RESULT="QR_RESULT";
    private String qrCode = "";
    EditText eTxtPosition = null;

    private CollectionCriterios CRITERIOS;
    private int lastItemSelected;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode){
            case REQUEST_QR_CODE:
                if(resultCode == Activity.RESULT_OK){
                    String qrCode=data.getStringExtra(QR_RESULT);
                    eTxtPosition.setText(qrCode);
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
                break;

        }

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String qrCode=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        setupActionBar();
        eTxtPosition = (EditText) findViewById(R.id.eTxtPosition);

        CRITERIOS=new CollectionCriterios();
        lastItemSelected =0;

        Criterio temp =new Criterio(0,"Botritis","boolean", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(1,"Lasiodiplodia","int", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(2,"Phytophthora","float", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(3,"Oídium","", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(4,"Antracnosis","boolean", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(5,"Fumagina","boolean", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(6,"Sunblotch","boolean", "");
        CRITERIOS.addCriterio(temp);

    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    public void getQRCode(View view){
        Intent i = new Intent(this, QRScannerActivity.class);
        startActivityForResult(i, 1);
    }

    public void end(View view){
        finish();
    }



    public void showList(View view){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        final CharSequence[] items = new CharSequence[ CRITERIOS.getCriterios().size()];


        for(int i=0; i< CRITERIOS.getCriterios().size();i++){
            items[i]=CRITERIOS.getCriterios().get(i).getName();
        }


        dialogo.setTitle("Criterios")
                .setSingleChoiceItems(items, lastItemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        lastItemSelected=which;
                        Toast.makeText(
                                getBaseContext(),
                                "Seleccionaste: " + CRITERIOS.getCriterios().get(which).getName(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        dialogo.show();


    }





}
