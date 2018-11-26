package pe.ibao.agromovil.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.utilities.UploaderDB;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentMain.OnFragmentInteractionListener, ListInspectionsFragment.OnFragmentInteractionListener{


    private Fragment myFragment= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFragment = new FragmentMain();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();

        //verificar y estan actualizar de inmediato


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean isFrame = false;
        Intent i = null;

        boolean isUpload=false;
        boolean isUpgrade=false;

        if (id == R.id.nav_new_inspection) {
            isFrame=false;
            i = new Intent(this, ActivityVisita.class);
            myFragment= new FragmentMain();
        } else if (id == R.id.nav_recent_visits){
            Toast.makeText(getBaseContext(),"recent visit!",
                    Toast.LENGTH_SHORT).show();
            isFrame=true;
            myFragment = new ListInspectionsFragment();
            i = new Intent(this, ListInspectionsFragment.class);
        }else if(id == R.id.upload){
            //comprobar internet
            isUpload=true;
        }else if(id == R.id.upgrade){
            isUpgrade=true;
        }

        if(!(isUpload || isUpgrade)){
            if(isFrame){
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,myFragment).commit();

            }else if(!isFrame){
                startActivity(i);
            }
        }else {
            isConnectedToInternetToUpdate(isUpgrade?"up":"down");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void isConnectedToInternetToUpdate(final String caso){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //verificamos si tenemos internet
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // Si hay conexión a Internet en este momento
                    Toast.makeText(getBaseContext(),"Conectando...",
                            Toast.LENGTH_SHORT).show();
                    switch (caso){
                        case "up":
                            Toast.makeText(getBaseContext(),"SUBIENDO",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case "down":
                            startActivity(
                                    new Intent(getBaseContext(), ActivityUpdate.class)
                            );
                            finish();
                            break;
                    }

                } else {
                    Toast.makeText(getBaseContext(),"No hay internet!",
                            Toast.LENGTH_SHORT).show();

                }

            }
        }, 500);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}