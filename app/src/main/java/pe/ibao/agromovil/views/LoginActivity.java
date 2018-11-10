package pe.ibao.agromovil.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import pe.ibao.agromovil.ConexionSQLiteHelper;
import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.EmpresaDAO;
import pe.ibao.agromovil.models.vo.entitiesDB.EmpresaVO;
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
/*
        new CargaInicial(this);
        EmpresaDAO empresaDAO = new EmpresaDAO(this);
        EmpresaVO vo = empresaDAO.consultarEmpresaByidFundo(3);
        Toast.makeText(this,vo.getName(),Toast.LENGTH_SHORT).show();
        vo = empresaDAO.consultarEmpresaByid(1);
        Toast.makeText(this,vo.getName(),Toast.LENGTH_SHORT).show();
        empresaDAO.EmpresaDAOCloseConection();
*/

        Intent intent = new Intent(this,Update.class);
        startActivity(intent);

    }




}

