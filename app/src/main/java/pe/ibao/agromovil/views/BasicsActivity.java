package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.CultivoDAO;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.dao.FundoDAO;
import pe.ibao.agromovil.models.dao.VariedadDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.CultivoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.VariedadVO;

public class BasicsActivity extends AppCompatActivity {

    List<EmpresaVO> listEmpresas;
    List<String> listNombreEmpresas;

    List<FundoVO> listFundos;
    List<String> listNombreFundos;

    List<CultivoVO> listCultivos;
    List<String> listNombreCultivos;

    List<VariedadVO> listVariedades;
    List<String> listNombreVariedades;


    Spinner spnEmpresa;
    Spinner spnFundo;
    Spinner spnVariedad;
    Spinner spnCultivo;
    EditText eTextContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        setupActionBar();

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
        listFundos = fundoDAO.listarFundoByIdEmpresa(listEmpresas.get(posEmpresa-1).getId());
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


    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }


    public void pressListo(View view){

        Intent returnIntent = new Intent();
        returnIntent.putExtra(NewVisitActivity.REQUEST_FUNDO,spnFundo.getSelectedItem().toString());
        returnIntent.putExtra(NewVisitActivity.REQUEST_CULTIVO,spnCultivo.getSelectedItem().toString());
        returnIntent.putExtra(NewVisitActivity.REQUEST_VARIEDAD,spnVariedad.getSelectedItem().toString());
        returnIntent.putExtra(NewVisitActivity.REQUEST_CONTACTO,eTextContacto.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }








}
