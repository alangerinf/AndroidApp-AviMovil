package pe.ibao.agromovil.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.helpers.MyRecyclerViewAdapter;
import pe.ibao.agromovil.models.dao.FotoDAO;
import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PhotoGallery extends AppCompatActivity {

    private final String CARPETA_RAIZ = "misImagenes/";
    private final String CARPETA_IMAGEN = "misFotos";
    private final String DIRECTORIO_IMAGEN = CARPETA_RAIZ +CARPETA_IMAGEN;
    private String PATH = "";
    ImageView iViewLienzo;
    File fileImagen;
    Bitmap bitmap;

    private static int idMuestra;
    private static String value;
    private static String fechaHora;
    private static List<FotoVO> listFotos;

    private static ListView listViewFotos;
    RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        iViewLienzo = (ImageView) findViewById(R.id.iViewPhoto);

        Bundle mybundle = getIntent().getExtras();

        idMuestra = mybundle.getInt("idMuestra");
        value =  mybundle.getString("value");
        fechaHora =  mybundle.getString("fechaHora");
        listFotos = new FotoDAO(this).listarByIdMuestra(idMuestra);
        //listViewFotos = (ListView) findViewById(R.id.foto_listView);
        Toast.makeText(this,mybundle.toString(),Toast.LENGTH_LONG).show();


        if (validarPermisos()){

        } else{

        }

        //AdapterListPhoto adapterListFotos = new AdapterListPhoto(getBaseContext(), listFotos);
        //listViewFotos.setAdapter(adapterListFotos);//seteanis ek adaotadir

      //  setListViewHeightBasedOnChildren(listViewMuestas);//tamañap respecto a hijos




        recyclerView = findViewById(R.id.foto_listView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(PhotoGallery.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MyRecyclerViewAdapter(this,listFotos,iViewLienzo);
//        adapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);

    }

    private boolean validarPermisos(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)
           &&
           (checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)){
                return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA))
                ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }
        return false;

    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PhotoGallery.this);
        dialog.setTitle("Permisos Desactivados");
        dialog.setMessage("Debe aceptar todos los permisos para poder tomar fotos");
        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                solicitarPermisosManual();
            }
        });
        dialog.show();
    }


    public void TomarFoto(View view){
        fileImagen = new File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN);
        Boolean isCreated  = fileImagen.exists();
        String nombre="";

        if(!isCreated){
            isCreated = fileImagen.mkdirs();
        }

        if(isCreated){
            nombre = System.currentTimeMillis()/100+".jpg";
        }

        PATH=Environment.getExternalStorageDirectory()+File.separator+ DIRECTORIO_IMAGEN +File.separator+nombre;

        File imagen = new File(PATH);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            String autorities = getApplicationContext().getPackageName()+".provider";
            Log.d("locomata",autorities);
            Uri imageUri= FileProvider.getUriForFile(PhotoGallery.this,autorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        }else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagen));
        }
        startActivityForResult(intent,20);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length == 2
                    &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                    &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            } else {
                solicitarPermisosManual();

            }

        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones = {
                "si",
                "no"
        };
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(PhotoGallery.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de Forma Manual?");
        alertOpciones.setItems(
                opciones,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(opciones[i].equals("si")){
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package",getPackageName(),null);
                            intent.setData(uri);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                    }
                });
        alertOpciones.show();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case 20:
                    MediaScannerConnection.scanFile(this,new String[]{PATH}, null ,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("Ruta","path : "+PATH);
                                }
                            });
                    bitmap = BitmapFactory.decodeFile(PATH);
                    double scale = bitmap.getWidth()/(bitmap.getHeight()*1.0);
                    bitmap = Bitmap.createScaledBitmap(bitmap,(int)(iViewLienzo.getHeight()*scale), iViewLienzo.getHeight(), true);
                    iViewLienzo.setImageBitmap(bitmap);

                    new FotoDAO(getBaseContext()).nuevoByIdMuestra(idMuestra,PATH);
                    listFotos = new FotoDAO(this).listarByIdMuestra(idMuestra);
                    /*

                    AdapterListPhoto adapterListFotos = new AdapterListPhoto(getBaseContext(), listFotos);

                    listViewFotos.setAdapter(adapterListFotos);//seteanis ek adaotadir
                    adapterListFotos.notifyDataSetChanged();
                    */

                    recyclerView = findViewById(R.id.foto_listView);
                    LinearLayoutManager horizontalLayoutManager
                            = new LinearLayoutManager(PhotoGallery.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(horizontalLayoutManager);
                    adapter = new MyRecyclerViewAdapter(this,listFotos, iViewLienzo);
//        adapter.setClickListener((MyRecyclerViewAdapter.ItemClickListener) this);
                    recyclerView.setAdapter(adapter);
                    break;
            }
        }
    }

}
