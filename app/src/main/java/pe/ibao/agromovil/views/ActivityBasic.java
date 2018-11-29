package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import pe.ibao.agromovil.models.dao.ContactoDAO;
import pe.ibao.agromovil.models.dao.CultivoDAO;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.dao.FundoDAO;
import pe.ibao.agromovil.models.dao.VariedadDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.ContactoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.CultivoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesDB.FundoVO;
import pe.ibao.agromovil.models.vo.entitiesDB.VariedadVO;

public class ActivityBasic extends AppCompatActivity {

    private static List<EmpresaVO> listEmpresas;
    private static List<String> listNombreEmpresas;

    private static List<FundoVO> listFundos;
    private static List<String> listNombreFundos;

    private static List<CultivoVO> listCultivos;
    private static List<String> listNombreCultivos;

    private static List<VariedadVO> listVariedades;
    private static List<String> listNombreVariedades;

    private static List<ContactoVO> listContactos;
    private static List<String> listNombreContactos;

    String TAG = "1199232";
    private static Spinner spnEmpresa;
    private static Spinner spnFundo;
    private static Spinner spnVariedad;
    private static Spinner spnCultivo;
    private static Spinner spnContacto;
    private static EditText eTextContacto;

    private static int idEmpresa;
    private static int idFundo;
    private static int idCultivo;
    private static int idVariedad;
    private static int idVisita;
    private static int idContacto;

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
        spnContacto = (Spinner) findViewById(R.id.spnContacto);

        //cargar los nombre a ram
        cargarEmpresas();
        cargarCultivos();

        //inicializando adaptadpres de spn iniciales
        ArrayAdapter<CharSequence> adaptadorEmpresas = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreEmpresas);
        ArrayAdapter<CharSequence> adaptadorCultivos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreCultivos);
        //seteando esos adaptadores
        spnCultivo.setAdapter(adaptadorCultivos);
        spnEmpresa.setAdapter(adaptadorEmpresas);

        //reciviendo parametros de la actividad anterior
        recibidos = getIntent().getExtras();
        if(recibidos!=null){//si se recibe datos
            //cargamos a ram el id de visita
            idVisita=recibidos.getInt("idVisita");
            Log.d(TAG, "SEND IS NOT NULL");

            //si no es la primera ves q se accede a editar
            if(recibidos.getInt("isFirst")==0){//si no es la primera vez q ingresan
                idContacto= recibidos.getInt("idContacto");
                //eTextContacto.setText(contacto);
                idEmpresa=recibidos.getInt("idEmpresa");
                idFundo =recibidos.getInt("idFundo");
                idCultivo=recibidos.getInt("idCultivo");
                idVariedad=recibidos.getInt("idVariedad");

                Log.d(TAG,""+idEmpresa+" "+idFundo+" "+idCultivo+" "+idVariedad+" "+idVisita+" "+idContacto);

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
                                            spnFundo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()  {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    if(position !=0){
                                                        cargarContactos();
                                                        spnContacto.setEnabled(true);
                                                        ArrayAdapter<CharSequence> adaptadorContactos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreContactos);
                                                        spnContacto.setAdapter(adaptadorContactos);
                                                        for(int k =0 ; k< listContactos.size();k++){
                                                            if(listContactos.get(k).getId()==idContacto){
                                                                spnContacto.setSelection(k+1);
                                                            }
                                                        }
                                                    }else{
                                                        spnContacto.setEnabled(false);
                                                    }
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });


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
                            spnFundo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position != 0){
                                        cargarContactos();
                                        spnContacto.setEnabled(true);
                                        ArrayAdapter<CharSequence> adaptadorContactos = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item,listNombreContactos);
                                        spnContacto.setAdapter(adaptadorContactos);
                                    }else{
                                        spnContacto.setEnabled(false);
                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
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
    private void cargarContactos() {
        ContactoDAO contactoDAO = new ContactoDAO(getBaseContext());
        int posEmpresa = spnFundo.getSelectedItemPosition();
        listContactos = contactoDAO.listarByIdFundo(listFundos.get(posEmpresa-1).getId());
        cargarNombreContactos();
    }
    private void cargarNombreContactos() {
        listNombreContactos = new ArrayList<String>();
        listNombreContactos.add("Seleccione Contactos");
        for(ContactoVO co : listContactos){
            listNombreContactos.add(co.getName());
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
                && spnContacto.getSelectedItemPosition()!=0
            ){
            idFundo = listFundos.get(spnFundo.getSelectedItemPosition()-1).getId();
            idVariedad = listVariedades.get(spnVariedad.getSelectedItemPosition()-1).getId();
            idContacto = listContactos.get(spnContacto.getSelectedItemPosition()-1).getId();
            returnIntent.putExtra(ActivityVisita.REQUEST_EMPRESA,spnEmpresa.getSelectedItem().toString());
            returnIntent.putExtra(ActivityVisita.REQUEST_FUNDO,spnFundo.getSelectedItem().toString());
            returnIntent.putExtra(ActivityVisita.REQUEST_CULTIVO,spnCultivo.getSelectedItem().toString());
            returnIntent.putExtra(ActivityVisita.REQUEST_VARIEDAD,spnVariedad.getSelectedItem().toString());
            returnIntent.putExtra(ActivityVisita.REQUEST_CONTACTO,spnContacto.getSelectedItem().toString());

            VisitaDAO visitaDAO = new VisitaDAO(getBaseContext());
            boolean res =  visitaDAO.cambiarIdFundoIdVariedadIdContacto(idVisita,idFundo,idVariedad,idContacto);
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
