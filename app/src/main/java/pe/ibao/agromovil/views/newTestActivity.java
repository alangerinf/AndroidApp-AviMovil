package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.AdapterListViewCriterio;
import pe.ibao.agromovil.models.CollectionCriterios;
import pe.ibao.agromovil.entities.entitiesDB.Criterio;

public class newTestActivity extends AppCompatActivity {


    static final private int REQUEST_QR_CODE = 1;
    //static final int REQUEST_GPS_LOCATION = 2;
    static final public String QR_RESULT="QR_RESULT";
    private String qrCode = "";
    EditText eTxtPosition = null;


    Button buttonOk;
    ListView listViewCriterios;

    private static CollectionCriterios CRITERIOS=new CollectionCriterios();;

    private static CollectionCriterios saveCriterios=new CollectionCriterios();;

    private int lastItemSelected=0;




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

/*
        buttonOk=(Button) findViewById(R.id.button_ok);

        Display display = getWindowManager().getDefaultDisplay();
        int anchoTotal = display.getWidth();
        int  altoTotal= display.getHeight();
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.mainLayout);
        cl.setMinWidth(anchoTotal);
        cl.setMinHeight(altoTotal);
*/
        eTxtPosition = (EditText) findViewById(R.id.eTxtPosition);

        Criterio temp =new Criterio(0,"Precipitacion","float","mm", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(1,"Temperatura Maxima","float","C°", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(2,"Temperatura Minima","float","C°", "");
        CRITERIOS.addCriterio(temp);
        temp =new Criterio(3,"Evapotranspiracion","float","mm", "");
        CRITERIOS.addCriterio(temp);
        /*temp =new Criterio(4,"Antracnosis","list","bajo-medio-critico", "");
        CRITERIOS.addCriterio(temp);
        */

        listViewCriterios = (ListView) findViewById(R.id.list_criterios);
        AdapterListViewCriterio adapterListViewCriterio = new AdapterListViewCriterio(getBaseContext(),saveCriterios.getCriterios());

        if(saveCriterios.getCriterios().size()!=0){

            listViewCriterios.setAdapter(adapterListViewCriterio);

        }else{
            Toast.makeText(
                    getBaseContext(),
                    "bsidasdsad",
                    Toast.LENGTH_SHORT)
                    .show();
        }
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
                        Criterio temp = CRITERIOS.getCriterios().get(which);
                        saveCriterios.addCriterio(temp);
                        if(saveCriterios.getCriterios().size()!=0){

                            AdapterListViewCriterio adapterListViewCriterio = new AdapterListViewCriterio(getBaseContext(),saveCriterios.getCriterios());
                            listViewCriterios.setAdapter(adapterListViewCriterio);

                        }
                    }
                });
        dialogo.show();


    }





}
