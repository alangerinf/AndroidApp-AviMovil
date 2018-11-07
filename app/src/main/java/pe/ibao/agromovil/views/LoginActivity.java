package pe.ibao.agromovil.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.views.Update;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    ImageButton imgBtnEnter= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        imgBtnEnter = findViewById(R.id.imgbtn_enter);
        imgBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eventPressEnter();

            }
        });
    }

    void eventPressEnter(){
        Intent intent = new Intent(this,Update.class);
        startActivity(intent);

    }




}

