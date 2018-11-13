package pe.ibao.agromovil.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.AdapterListCriterio;
import pe.ibao.agromovil.helpers.AdapterListEvaluacion;
import pe.ibao.agromovil.models.dao.EvaluacionDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesInternal.EvaluacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.VisitaVO;

public class NewVisitActivity extends AppCompatActivity {



    static final public int REQUEST_BASICS_DATA = 1;  // The request code
    static final public String REQUEST_FUNDO = "fundo_request";
    static final public String REQUEST_CULTIVO = "cultivo_request";
    static final public String REQUEST_VARIEDAD = "variedad_request";
    static final public String REQUEST_CONTACTO = "contacto_request";

    private TextView tViewContacto;
    private TextView tViewFundo;
    private TextView tViewCultivo;
    private TextView tViewVariedad;
    private  ListView listViewEvaluaciones;
    VisitaDAO visitaDAO;
    private VisitaVO visita;
    BaseAdapter baseAdapter;
    private  List<EvaluacionVO> evaluacionVOList = new ArrayList<>();

    private String TAG="newvisit";

    public static Context ctx;
    static Bundle xx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        xx= savedInstanceState;
        setContentView(R.layout.activity_new_visita);
        setupActionBar();
        ctx=this;
        tViewContacto = (TextView) findViewById(R.id.tViewContacto);
        tViewFundo = (TextView) findViewById(R.id.tViewFundo);
        tViewCultivo = (TextView) findViewById(R.id.tViewCultivo);
        tViewVariedad = (TextView) findViewById(R.id.tViewVariedad);

        listViewEvaluaciones = (ListView) findViewById(R.id.list_evaluaciones);

        visitaDAO = new VisitaDAO(this);
        visita = visitaDAO.intentarNuevo();
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO(this);
        evaluacionVOList.addAll(evaluacionDAO.listarByIdVisita(visita.getId()));

        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return evaluacionVOList.size();
            }

            @Override
            public Object getItem(int position) {
                return evaluacionVOList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return evaluacionVOList.get(position).getId();
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View v = convertView;
                final LayoutInflater inflater;
                inflater = LayoutInflater.from(getBaseContext());
                v = inflater.inflate(R.layout.evaluacion_itemeditor_list_view,null);
                TextView tViewTipoInspeccion= (TextView) v.findViewById(R.id.tipo_inspeccion);
                TextView tViewFechaHora     = (TextView) v.findViewById(R.id.fecha_hora);
                TextView tViewPorcerntaje   = (TextView) v.findViewById(R.id.porcentaje);
                ImageView btnDelete         = (ImageView)v.findViewById(R.id.deleter);
                btnDelete.setClickable(true);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //tomarFoto();
                        //Toast.makeText(this,"Borrando"+position,Toast.LENGTH_LONG).show();
                        try{
                            Toast.makeText(getBaseContext(),"borrando->"+position,Toast.LENGTH_LONG).show();

                            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(getBaseContext());
                            evaluacionDAO.borrarById(evaluacionVOList.get(position).getId());
                            evaluacionVOList.remove(position);
                            baseAdapter.notifyDataSetChanged();
                            //autoAdapter();
                        }catch (Exception e){
                            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
                            Log.d("qwerty",e.toString());
                        }
                    }

                });

                //    tViewTipoInspeccion.setText(listEvaluacion.get(position).getNameInspeccion());
                //      tViewFechaHora.setText(listEvaluacion.get(position).getTimeIni());
//        tViewPorcerntaje.setText(listEvaluacion.get(position).getPorcentaje());
                return v;
            }

        };

        if(evaluacionVOList.size()!=0){
            listViewEvaluaciones.setAdapter(baseAdapter);
            setListViewHeightBasedOnChildren(listViewEvaluaciones);
        }else{
            Toast.makeText(
                    this,
                    "Agrege Evaluaciones",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void openTest(View view){
        Intent intent = new Intent(this,newEvaluacionActivity.class);
        startActivity(intent);
    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        switch (requestCode){
            case REQUEST_BASICS_DATA :
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {

                    String temp = data.getStringExtra(REQUEST_FUNDO);
                    tViewFundo.setText(temp);
                    temp=data.getStringExtra(REQUEST_CULTIVO);
                    tViewCultivo.setText(temp);
                    temp=data.getStringExtra(REQUEST_VARIEDAD);
                    tViewVariedad.setText(temp);
                    temp=data.getStringExtra(REQUEST_CONTACTO);
                    tViewContacto.setText(temp);

                }

            break;
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
    public void openbasics(View view){
        Intent intent = new Intent(this,BasicsActivity.class);
        startActivityForResult(intent, REQUEST_BASICS_DATA);
    }

    public void end(View view){
        finish();
    }

}
