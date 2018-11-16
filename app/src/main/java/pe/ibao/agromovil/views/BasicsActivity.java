package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.CultivoDAO;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.dao.FundoDAO;
import pe.ibao.agromovil.models.dao.VariedadDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.CultivoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.VariedadVO;

public class BasicsActivity extends AppCompatActivity {

    private List<EmpresaVO> listEmpresas;
    private List<String> listNombreEmpresas;

    private List<FundoVO> listFundos;
    private List<String> listNombreFundos;

    private List<CultivoVO> listCultivos;
    private List<String> listNombreCultivos;

    private List<VariedadVO> listVariedades;
    private List<String> listNombreVariedades;

    String TAG = "1199232";
    private Spinner spnEmpresa;
    private Spinner spnFundo;
    private Spinner spnVariedad;
    private Spinner spnCultivo;
    private EditText eTextContacto;

    private static int idEmpresa;
    private static int idFundo;
    private static int idCultivo;
    private static int idVariedad;
    private static int idVisita;
    private static String contacto;

    private static Bundle recibidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        //setupActionBar();

        eTextContacto= (EditText) findViewById(R.id.eTextContacto);
        spnEmpresa = (Spinner) findViewById(R.id.spnEmpresa);
        spnFundo = (Spinner) findViewById(R.id.spnFundo);
        spnCultivo = (Spinner) findViewById(R.id.spnCultivo);
        spnVariedad = (Spinner) findViewById(R.id.spnVariedad);

        cargarEmpresas();
        cargarCultivos();
        ArrayAdapter<CharSequence> adaptadorEmpresas = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreEmpresas);
        ArrayAdapter<CharSequence> adaptadorCultivos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreCultivos);
        spnCultivo.setAdapter(adaptadorCultivos);
        spnEmpresa.setAdapter(adaptadorEmpresas);

        recibidos = getIntent().getExtras();

        if(recibidos!=null){

            idVisita=recibidos.getInt("idVisita");

            Log.d(TAG, "SEND IS NOT NULL");
            if(recibidos.getInt("isFirst")==0){//si no es la primera vez q ingresan
                contacto= recibidos.getString("contacto");
                eTextContacto.setText(contacto);
                idEmpresa=recibidos.getInt("idEmpresa");
                idFundo =recibidos.getInt("idFundo");
                idCultivo=recibidos.getInt("idCultivo");
                idVariedad=recibidos.getInt("idVariedad");

                Log.d(TAG,""+idEmpresa+" "+idFundo+" "+idCultivo+" "+idVariedad+" "+idVisita);

                for(int i=0;i<listEmpresas.size();i++){
                    if(listEmpresas.get(i).getId()==idEmpresa){
                        Log.d(TAG,"EMPRESA ENCONTRADA POS = "+i+1);
                        spnEmpresa.setSelection(i+1);

                        spnEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(i != 0){
                                    cargarFundos();
                                    spnFundo.setEnabled(true);
                                    ArrayAdapter<CharSequence> adaptadorFundos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreFundos);
                                    spnFundo.setAdapter(adaptadorFundos);
                                    for(int j = 0 ; j<listFundos.size();j++){
                                        if(listFundos.get(j).getId()==idFundo){
                                            spnFundo.setSelection(j+1);
                                        }
                                    }

                                }else{
                                    spnFundo.setEnabled(false);
                                }
                            }
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                return;
                            }
                        });
                        break;
                    }
                }

                for(int i=0;i<listCultivos.size();i++) {
                    if (listCultivos.get(i).getId() == idCultivo) {
                        Log.d(TAG, "CULTIVO ENCONTRADA POS = " + i + 1);
                        spnCultivo.setSelection(i + 1);
                        spnCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(i != 0){
                                    cargarVariedades();
                                    spnVariedad.setEnabled(true);
                                    ArrayAdapter<CharSequence> adaptadorFundos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreVariedades);
                                    spnVariedad.setAdapter(adaptadorFundos);
                                    for(int j = 0 ; j<listVariedades.size();j++){
                                        if(listVariedades.get(j).getId()==idVariedad){
                                            spnVariedad.setSelection(j+1);
                                        }
                                    }
                                }else{
                                    spnVariedad.setEnabled(false);
                                }
                            }
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                return;
                            }
                        });

                    }
                }



            }else{//si es la primera vez q ingresan
                Log.d(TAG,"es primera edicion");

                spnEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i != 0){
                            cargarFundos();
                            spnFundo.setEnabled(true);
                            ArrayAdapter<CharSequence> adaptadorFundos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreFundos);
                            spnFundo.setAdapter(adaptadorFundos);
                        }else{
                            spnFundo.setEnabled(false);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });

                spnCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i != 0){
                            cargarVariedades();
                            spnVariedad.setEnabled(true);
                            ArrayAdapter<CharSequence> adaptadorFundos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreVariedades);
                            spnVariedad.setAdapter(adaptadorFundos);
                        }else{
                            spnVariedad.setEnabled(false);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
            }
        }



    }

    private void cargarVariedades() {
        VariedadDAO variedadDAO = new VariedadDAO(getBaseContext());
        int posCultivo = spnCultivo.getSelectedItemPosition();
        listVariedades = variedadDAO.listarVariedadByIdCultivo(listCultivos.get(posCultivo-1).getId());
        cargarNombreVariedades();
    }

    private void cargarNombreVariedades() {
        listNombreVariedades = new ArrayList<String>();
        listNombreVariedades.add("Seleccione Variedad");
        for(VariedadVO var : listVariedades){
            listNombreVariedades.add(var.getName());
        }
    }


    private void cargarCultivos(){
        CultivoDAO cultivoDAO = new CultivoDAO(getBaseContext());
        listCultivos = cultivoDAO.listCultivos();
        cargarNombreCultivos();
    }

    private void cargarNombreCultivos() {
        listNombreCultivos = new ArrayList<String>();
        listNombreCultivos.add("Seleccione Cultivos");
        for(CultivoVO cul : listCultivos){
            listNombreCultivos.add(cul.getName());
        }
    }

    private void cargarFundos() {
        FundoDAO fundoDAO = new FundoDAO(getBaseContext());
        int posEmpresa = spnEmpresa.getSelectedItemPosition();
        listFundos = fundoDAO.listarByIdEmpresa(listEmpresas.get(posEmpresa-1).getId());
        cargarNombreFundos();
    }

    private void cargarNombreFundos(){
        listNombreFundos = new ArrayList<String>();
        listNombreFundos.add("Seleccione Fundo");
        for(FundoVO fun : listFundos){
            listNombreFundos.add(fun.getName());
        }
    }

    private void cargarEmpresas() {
        EmpresaDAO empresaDAO = new EmpresaDAO(getBaseContext());
        listEmpresas = empresaDAO.listEmpresas();
        cargarNombreEmpresas();
    }
    private void cargarNombreEmpresas(){
        listNombreEmpresas = new ArrayList<String>();
        listNombreEmpresas.add("Seleccione Empresa");
        for(EmpresaVO emp : listEmpresas){
            listNombreEmpresas.add(emp.getName());
        }
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

    public void pressListo(View view){

        Intent returnIntent = new Intent();
        if(spnEmpresa.getSelectedItemPosition()!=0
            && spnCultivo.getSelectedItemPosition()!=0
            && spnFundo.getSelectedItemPosition()!=0
            && spnVariedad.getSelectedItemPosition()!=0
            ){
            idFundo = listFundos.get(spnFundo.getSelectedItemPosition()-1).getId();
            idVariedad = listVariedades.get(spnVariedad.getSelectedItemPosition()-1).getId();
            contacto = eTextContacto.getText().toString();
            returnIntent.putExtra(NewVisitActivity.REQUEST_EMPRESA,spnEmpresa.getSelectedItem().toString());
            returnIntent.putExtra(NewVisitActivity.REQUEST_FUNDO,spnFundo.getSelectedItem().toString());
            returnIntent.putExtra(NewVisitActivity.REQUEST_CULTIVO,spnCultivo.getSelectedItem().toString());
            returnIntent.putExtra(NewVisitActivity.REQUEST_VARIEDAD,spnVariedad.getSelectedItem().toString());
            returnIntent.putExtra(NewVisitActivity.REQUEST_CONTACTO,eTextContacto.getText().toString());
            returnIntent.putExtra(NewVisitActivity.REQUEST_CONTACTO,eTextContacto.getText().toString());

            VisitaDAO visitaDAO = new VisitaDAO(getBaseContext());
            boolean res =  visitaDAO.cambiarIdFundoIdVariedadContacto(idVisita,idFundo,idVariedad,contacto);
            if(!res){
                Toast.makeText(getBaseContext(),"Error al intententar Modificar",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(),"Informacion Actualizada",Toast.LENGTH_SHORT).show();
            }

            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }else{
            Toast.makeText(getBaseContext(),"Seleccione todos los campos",Toast.LENGTH_SHORT).show();
        }


    }








}
