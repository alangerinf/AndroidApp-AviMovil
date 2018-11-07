package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import pe.ibao.agromovil.R;

public class BasicsActivity extends AppCompatActivity {


    Spinner spnVariedad;
    Spinner spnFundo;
    Spinner spnCultivo;
    EditText eTextContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        setupActionBar();

        eTextContacto= (EditText) findViewById(R.id.eTextContacto);
        spnFundo = (Spinner) findViewById(R.id.spnFundo);
        spnCultivo = (Spinner) findViewById(R.id.spnCultivo);
        spnVariedad = (Spinner) findViewById(R.id.spnVariedad);


    }

    private void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }


    public void pressListo(View view){

        Intent returnIntent = new Intent();
        returnIntent.putExtra(NewInspectionActivity.REQUEST_FUNDO,spnFundo.getSelectedItem().toString());
        returnIntent.putExtra(NewInspectionActivity.REQUEST_CULTIVO,spnCultivo.getSelectedItem().toString());
        returnIntent.putExtra(NewInspectionActivity.REQUEST_VARIEDAD,spnVariedad.getSelectedItem().toString());
        returnIntent.putExtra(NewInspectionActivity.REQUEST_CONTACTO,eTextContacto.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }








}
