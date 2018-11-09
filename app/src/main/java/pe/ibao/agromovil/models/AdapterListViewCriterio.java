package pe.ibao.agromovil.models;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.entities.entitiesDB.Criterio;

public class AdapterListViewCriterio extends BaseAdapter{

    Context ctx;
    List<Criterio> listCriterios;

    public AdapterListViewCriterio(Context ctx, List<Criterio> listCriterios){
        this.ctx = ctx;
        this.listCriterios =listCriterios;
    }

    @Override
    public int getCount() {
        return listCriterios.size();
    }

    @Override
    public Object getItem(int position) {
        return listCriterios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listCriterios.get(position).getId();
    }




    private void tomarFoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ctx.startActivity(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(ctx);
        v = inflater.inflate(R.layout.criterio_itemeditor_list_view,null);

        TextView nameitem = (TextView) v.findViewById(R.id.name_criterio);
        EditText _int =(EditText) v.findViewById(R.id._int);
        EditText _float = (EditText) v.findViewById(R.id._float);
        EditText _string = (EditText) v.findViewById(R.id._string);
        Spinner _list = (Spinner) v.findViewById(R.id._list);
        Switch _boolean = (Switch) v.findViewById(R.id._boolean);

        ImageView btnCam = (ImageView) v.findViewById(R.id.btn_cam);

        btnCam.setClickable(true);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tomarFoto();
                Toast.makeText(ctx,"Tomar Fotografia",Toast.LENGTH_LONG);
            }
        });

        nameitem.setText(listCriterios.get(position).getName()+" "+listCriterios.get(position).getMagnitud());



        switch (listCriterios.get(position).getType()){
            case "boolean":
                _boolean.setVisibility(View.VISIBLE);
                nameitem.setText(listCriterios.get(position).getName());
                break;
            case "int":
                _int.setVisibility(View.VISIBLE);

                nameitem.setText(listCriterios.get(position).getName()+" "+listCriterios.get(position).getMagnitud());
                break;
            case "float":
                _float.setVisibility(View.VISIBLE);

                nameitem.setText(listCriterios.get(position).getName()+" "+listCriterios.get(position).getMagnitud());
                break;
            case "list":

                nameitem.setText(listCriterios.get(position).getName());
                String tempList = listCriterios.get(position).getMagnitud();

                String[] parts = tempList.split("-");

                List<String> list = new ArrayList<String>();

                for(int i=0;i<parts.length;i++){
                    list.add(parts[i]);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ctx,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                _list.setAdapter(dataAdapter);

                _list.setVisibility(View.VISIBLE);
                break;
            case "string":

                _string.setHeight(80);
                _string.setVisibility(View.VISIBLE);
                break;

        }

        return v;
    }





}
