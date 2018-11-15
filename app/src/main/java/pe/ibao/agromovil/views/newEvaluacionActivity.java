package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.AdapterListMuestras;
import pe.ibao.agromovil.models.dao.CriterioDAO;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;

public class newEvaluacionActivity extends AppCompatActivity {


    static final private int REQUEST_QR_CODE = 1;
    //static final int REQUEST_GPS_LOCATION = 2;
    static final public String QR_RESULT="QR_RESULT";
    private String qrCode = "";
    EditText eTxtPosition = null;


    Button buttonOk;
    static ListView listViewCriterios;

    private static List<CriterioVO> CRITERIOS =new  ArrayList<>();

    private static List<MuestraVO> saveMuestras =new ArrayList<>();

    private static int lastItemSelected=0;
    private static int primeraEdicion;
    private static int idEvaluacion;
    private static int idTipoInspeccion;
    private static int idFundo;
    private static int idVariedad;


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
        setContentView(R.layout.activity_new_evaluacion);
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
        listViewCriterios = (ListView) findViewById(R.id.list_criterios);

        Intent i = getIntent();
        Bundle mybundle = i.getExtras();
        idEvaluacion = mybundle.getInt("idEvaluacion");
        idFundo = mybundle.getInt("idFundo");
        idVariedad = mybundle.getInt("idVariedad");
        Log.d("locomata","encontrados 1.0 "+idEvaluacion+" "+idFundo);

        CriterioDAO criterioDAO = new CriterioDAO(getBaseContext());
        /**camvbiar  por tipoInspeccion**/
        CRITERIOS = criterioDAO.listarByIdTipoInspeccion(1);
        if(mybundle.getInt("isNewTest")>0){ //si no es la primera vez q  ingresa
            Log.d("locomata","no es primera vez");
            primeraEdicion=1;
            idTipoInspeccion = mybundle.getInt("idTipoInspeccion",0);
            //cargar filtros por tipo inspeccion

        }else{
            Log.d("locomata","es primera vez");
            primeraEdicion=0;


        }

        AdapterListMuestras adapterListMuestras = new AdapterListMuestras(getBaseContext(), saveMuestras);
        listViewCriterios.setAdapter(adapterListMuestras);
        setListViewHeightBasedOnChildren(listViewCriterios);

        if(saveMuestras.size()==0){
            Log.d("locomata","pila vacia");
            Toast.makeText(
                    getBaseContext(),
                    "vacio",
                    Toast.LENGTH_SHORT)
                    .show();

        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        Log.d("tamano",""+listAdapter.getCount());
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        //listView.setDividerHeight(params.height);
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

        final CharSequence[] items = new CharSequence[ CRITERIOS.size()];


        for(int i = 0; i< CRITERIOS.size(); i++){
            items[i]= CRITERIOS.get(i).getName();
        }


        dialogo.setTitle("Criterios")
                .setSingleChoiceItems(items, lastItemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        lastItemSelected=which;
                        Toast.makeText(
                                getBaseContext(),
                                "Seleccionaste: " + CRITERIOS.get(which).getName(),
                                Toast.LENGTH_SHORT)
                                .show();
                        CriterioVO temp = CRITERIOS.get(which);
                        Log.d("locomata","antes de mandar "+idEvaluacion+" "+temp.getId());
                        MuestraVO temp2 = new MuestrasDAO(getBaseContext()).nuevoByIdEvaluacionIdCriterio(idEvaluacion,temp.getId());

                        saveMuestras.add(temp2);

                        AdapterListMuestras adapterListMuestras = new AdapterListMuestras(getBaseContext(), saveMuestras);
                        listViewCriterios.setAdapter(adapterListMuestras);
                        setListViewHeightBasedOnChildren(listViewCriterios);

                    }
                });
        dialogo.show();



    }





}
