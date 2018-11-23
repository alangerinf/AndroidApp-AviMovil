package pe.ibao.agromovil.views;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.LoginHelper;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
import pe.ibao.agromovil.models.vo.entitiesInternal.UsuarioVO;
import pe.ibao.agromovil.utilities.CargaInicial;
import pe.ibao.agromovil.utilities.Utilities;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    ImageButton imgBtnEnter= null;


    EditText eTextUser;
    EditText eTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        eTextUser = (EditText) findViewById(R.id.eTextUser);
        eTextPassword = (EditText) findViewById(R.id.eTextPassword);
        imgBtnEnter = findViewById(R.id.imgbtn_enter);
        imgBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eventPressEnter();

            }
        });
    }

    void eventPressEnter(){
/*
        new CargaInicial(this);
        EmpresaDAO empresaDAO = new EmpresaDAO(this);
        EmpresaVO vo = empresaDAO.consultarEmpresaByidFundo(3);
        Toast.makeText(this,vo.getName(),Toast.LENGTH_SHORT).show();
        vo = empresaDAO.consultarEmpresaByid(1);
        Toast.makeText(this,vo.getName(),Toast.LENGTH_SHORT).show();
        empresaDAO.EmpresaDAOCloseConection();

*/

        Log.d("autentification","intentando");

        LoginHelper loginHelper = new LoginHelper(LoginActivity.this);
        loginHelper.intentoLogueo(eTextUser.getText().toString(),eTextPassword.getText().toString());


        //comprar en el servidor



/*
        Intent intent = new Intent(this,Update.class);
        startActivity(intent);
*/
    }

    @Override
    public void onBackPressed() {

            //super.onBackPressed();
            moveTaskToBack(true);

    }



}

