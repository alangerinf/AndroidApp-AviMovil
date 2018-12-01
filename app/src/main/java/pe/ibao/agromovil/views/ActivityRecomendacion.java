package pe.ibao.agromovil.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.CriterioRecomendacionDAO;
import pe.ibao.agromovil.models.dao.TipoRecomendacionDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.CriterioRecomendacionVO;
import pe.ibao.agromovil.models.vo.entitiesDB.TipoRecomendacionVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.VisitaVO;

public class ActivityRecomendacion extends Activity {

    private static List<TipoRecomendacionVO> listTipoRecomendaciones;
    private static List<CriterioRecomendacionVO> listCriterioRecomendaciones;

    private static boolean isEditable;
    private static VisitaVO visita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        isEditable= b.getBoolean("isEditable",false);
        int idTemp =b.getInt("idVisita");

        VisitaDAO visitaDAO = new VisitaDAO(this);
        visita = visitaDAO.buscarById((long)idTemp);


    }

    public void end(View view) {
       onBackPressed();
    }


    public void showListTipoRecomendacion(View view){
        if(isEditable){
            listTipoRecomendaciones = new TipoRecomendacionDAO(getBaseContext()).listarByIdVariedad(visita.getIdVariedad());
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

            final CharSequence[] items = new CharSequence[ listTipoRecomendaciones.size()];

            for(int i = 0; i< listTipoRecomendaciones.size(); i++){
                items[i]= listTipoRecomendaciones.get(i).getName();
            }


            dialogo.setTitle("Recomendaciones")
                    .setSingleChoiceItems(items, ActivityVisita.lastTipoRecomendacionSelected, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityVisita.lastTipoRecomendacionSelected = which;
                            Toast.makeText(
                                    getBaseContext(),
                                    "Seleccionaste: " + listTipoRecomendaciones.get(which).getName(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                            TipoRecomendacionVO temp = listTipoRecomendaciones.get(which);
                            Intent i = new Intent(getBaseContext(), ActivityRecomendacion.class);
                            i.putExtra("idVisita",visita.getId());
                            i.putExtra("idVariedad",visita.getIdVariedad());
                            i.putExtra("idTipoRecomendacion",temp.getId());
                            i.putExtra("isEditable",isEditable);
                            startActivityForResult(i, ActivityVisita.REQUEST_RECOMENDACION);//cambie  aqui de 1
                            overridePendingTransition(R.anim.bot_in, R.anim.fade_out);
                        }
                    });
            dialogo.show();
        }

    }

    public void showListRecomendacion(View view){
        if(isEditable){
            listCriterioRecomendaciones = new CriterioRecomendacionDAO(getBaseContext());
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            final CharSequence[] items = new CharSequence[ listTipoRecomendaciones.size()];
            for(int i = 0; i< listTipoRecomendaciones.size(); i++){
                items[i]= listTipoRecomendaciones.get(i).getName();
            }

            dialogo.setTitle("Recomendaciones")
                    .setSingleChoiceItems(items, ActivityVisita.lastTipoRecomendacionSelected, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityVisita.lastTipoRecomendacionSelected = which;
                            Toast.makeText(
                                    getBaseContext(),
                                    "Seleccionaste: " + listTipoRecomendaciones.get(which).getName(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                            TipoRecomendacionVO temp = listTipoRecomendaciones.get(which);
                            Intent i = new Intent(getBaseContext(), ActivityRecomendacion.class);
                            i.putExtra("idVisita",visita.getId());
                            i.putExtra("idVariedad",visita.getIdVariedad());
                            i.putExtra("idTipoRecomendacion",temp.getId());
                            i.putExtra("isEditable",isEditable);
                            startActivityForResult(i, ActivityVisita.REQUEST_RECOMENDACION);//cambie  aqui de 1
                            overridePendingTransition(R.anim.bot_in, R.anim.fade_out);


                        }
                    });
            dialogo.show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(),ActivityVisita.class);
        i.putExtra("isEditable",isEditable);
        i.putExtra("idVisita",visita.getId());
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.top_out);
        finish();

    }
}
