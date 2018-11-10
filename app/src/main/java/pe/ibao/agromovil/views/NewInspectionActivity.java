package pe.ibao.agromovil.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pe.ibao.agromovil.R;

public class NewInspectionActivity extends AppCompatActivity {



    static final public int REQUEST_BASICS_DATA = 1;  // The request code
    static final public String REQUEST_FUNDO = "fundo_request";
    static final public String REQUEST_CULTIVO = "cultivo_request";
    static final public String REQUEST_VARIEDAD = "variedad_request";
    static final public String REQUEST_CONTACTO = "contacto_request";

    private TextView tViewContacto;
    private TextView tViewFundo;
    private TextView tViewCultivo;
    private TextView tViewVariedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inspection);
        setupActionBar();
        tViewContacto = (TextView) findViewById(R.id.tViewContacto);
        tViewFundo = (TextView) findViewById(R.id.tViewFundo);
        tViewCultivo = (TextView) findViewById(R.id.tViewCultivo);
        tViewVariedad = (TextView) findViewById(R.id.tViewVariedad);

    }

    public void openTest(View view){
        Intent intent = new Intent(this,newTestActivity.class);
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

    public void openbasics(View view){
        Intent intent = new Intent(this,BasicsActivity.class);
        startActivityForResult(intent, REQUEST_BASICS_DATA);
    }

    public void end(View view){
        finish();
    }

}
