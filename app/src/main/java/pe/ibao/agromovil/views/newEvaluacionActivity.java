package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.AdapterListMuestras;
import pe.ibao.agromovil.models.dao.CriterioDAO;
import pe.ibao.agromovil.models.dao.EvaluacionDAO;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.dao.TipoInspeccionDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioVO;
import pe.ibao.agromovil.models.vo.entitiesDB.TipoInspeccionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;

public class newEvaluacionActivity extends AppCompatActivity {


    static final private int REQUEST_QR_CODE = 1;
    //static final int REQUEST_GPS_LOCATION = 2;
    static final public String QR_RESULT="QR_RESULT";
    private String qrCode = "";

    private static Spinner spnTipoInspeccion;
    private static TextView tViewFechaHora;
    private static EditText eTextQR;
    Button buttonOk;

    static ListView listViewMuestas;
    private static List<CriterioVO> CRITERIOS =new  ArrayList<>();
    private static List<MuestraVO> saveMuestras =new ArrayList<>();

    private static List<TipoInspeccionVO> listTipoInspeccion = new ArrayList<>();
    private static List<String> listNombreTipoInspeccion = new ArrayList<>();


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
                    eTextQR.setText(qrCode);
                    new EvaluacionDAO(getBaseContext()).editarQR(idEvaluacion,qrCode);
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
       // setupActionBar();

/*
        buttonOk=(Button) findViewById(R.id.button_ok);

        Display display = getWindowManager().getDefaultDisplay();
        int anchoTotal = display.getWidth();
        int  altoTotal= display.getHeight();
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.mainLayout);
        cl.setMinWidth(anchoTotal);
        cl.setMinHeight(altoTotal);
*/
        spnTipoInspeccion = (Spinner) findViewById(R.id.spnTipoInspeccion);
        eTextQR = (EditText) findViewById(R.id.eTextQR);
        listViewMuestas = (ListView) findViewById(R.id.list_criterios);
        tViewFechaHora = (TextView) findViewById(R.id.tViewFechaHora);

        Intent intent = getIntent();
        Bundle mybundle = intent.getExtras();
        idEvaluacion = mybundle.getInt("idEvaluacion");
        idFundo = mybundle.getInt("idFundo");
        idVariedad = mybundle.getInt("idVariedad");
        tViewFechaHora.setText(mybundle.getString("fechaHora","<null>"));
        Log.d("locomata","encontrados 1.0 "+idEvaluacion+" "+idFundo);

        CriterioDAO criterioDAO = new CriterioDAO(getBaseContext());

        idTipoInspeccion = mybundle.getInt("idTipoInspeccion",0);
        primeraEdicion = mybundle.getInt("isNewTest");

        eTextQR.setText(new EvaluacionDAO(getBaseContext()).consultarById(idEvaluacion).getQr());


        cargarTipoInspeccion();
        Log.d("eva123","tipotam"+listTipoInspeccion.size());
        Log.d("eva123","tiponomtam"+listNombreTipoInspeccion.size());
        ArrayAdapter<CharSequence> adaptadorInspecciones = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreTipoInspeccion);
        spnTipoInspeccion.setAdapter(adaptadorInspecciones);

        saveMuestras = new MuestrasDAO(this).listarByIdEvaluacion(idEvaluacion);
        AdapterListMuestras adapterListMuestras = new AdapterListMuestras(getBaseContext(), saveMuestras,listViewMuestas, spnTipoInspeccion);
        listViewMuestas.setAdapter(adapterListMuestras);//seteanis ek adaotadir

        setListViewHeightBasedOnChildren(listViewMuestas);//tamañap respecto a hijos
        for(int i=0;i<listTipoInspeccion.size();i++){
            if(listTipoInspeccion.get(i).getId()==idTipoInspeccion){
                spnTipoInspeccion.setSelection(i+1);
                break;
            }
        }
        if(saveMuestras.size()>0){
            //si ya hay muestras bloqueamos el spn
            spnTipoInspeccion.setEnabled(false);
            //fijamos el son donde es

        }else{
            Toast.makeText(this, "Agregue Muestras", Toast.LENGTH_SHORT).show();

        }

        //op el momento desbilitao por   no llega desde  el ctivit  anterior nunc a s einvoca
        if(primeraEdicion>0){
            if(idTipoInspeccion>0){

            }
        }
        spnTipoInspeccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0){
                    idTipoInspeccion = listTipoInspeccion.get(i-1).getId();
                    new EvaluacionDAO(getBaseContext()).editarIdTipoInspeccion(idEvaluacion,idTipoInspeccion);
                }else{
                    Toast.makeText(getBaseContext(),"Seleccione una Inspeccion para ingresar Muestras",Toast.LENGTH_SHORT).show();
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

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
/*
    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        Log.d("hola","holamundobacisc");
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();
        onBackPressed();
        return false;
    }





    public void getQRCode(View view){
        Intent i = new Intent(this, QRScannerActivity.class);
        startActivityForResult(i, 1);
    }

    public void end(View view){
        finish();
    }

    private void cargarTipoInspeccion() {
        TipoInspeccionDAO tipoInspeccionDAO = new TipoInspeccionDAO(getBaseContext());
        listTipoInspeccion = tipoInspeccionDAO.listarByIdFundoAndIdVariedad(idFundo,idVariedad);
        cargarNombreTipoInspeccion();
    }
    private void cargarNombreTipoInspeccion(){
        listNombreTipoInspeccion = new ArrayList<>();
        listNombreTipoInspeccion.add("Seleccione Tipo");
        for(TipoInspeccionVO temp : listTipoInspeccion){
            listNombreTipoInspeccion.add(temp.getName());
        }
    }



    public void showList(View view){

        CRITERIOS = new CriterioDAO(getBaseContext()).listarByIdTipoInspeccionIdFundoIdVariedad(idTipoInspeccion,idFundo,idVariedad);
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
                        AdapterListMuestras adapterListMuestras = new AdapterListMuestras(getBaseContext(), saveMuestras,listViewMuestas,spnTipoInspeccion );
                        listViewMuestas.setAdapter(adapterListMuestras);
                        setListViewHeightBasedOnChildren(listViewMuestas);
                        spnTipoInspeccion.setEnabled(false);
                        final ScrollView scrollView = findViewById(R.id.ScrollEva);
                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        });
                    }
                });
        dialogo.show();



    }





}
