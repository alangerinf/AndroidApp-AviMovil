package pe.ibao.agromovil.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.MuestrasDAO;
import pe.ibao.agromovil.models.vo.entitiesInternal.MuestraVO;
import pe.ibao.agromovil.views.PhotoGallery;

public class AdapterListMuestras extends BaseAdapter{

    private Context ctx;
    private List<MuestraVO> listMuestas;
    private ListView list;
    private Spinner spnEva;
    public AdapterListMuestras(Context ctx, List<MuestraVO> listMuestas, ListView lv, Spinner spnEva){
        this.ctx = ctx;
        this.listMuestas =listMuestas;
        this.list = lv;
        this.spnEva = spnEva;
    }

    @Override
    public int getCount() {
        return listMuestas.size();
    }

    @Override
    public Object getItem(int position) {
        return listMuestas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listMuestas.get(position).getId();
    }




    private void tomarFoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ctx.startActivity(i);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        v = inflater.inflate(R.layout.muestra_itemeditor_list_view,null);

        final TextView nameitem = (TextView) v.findViewById(R.id.name_criterio);
        final EditText _int =(EditText) v.findViewById(R.id._int);
        final EditText _float = (EditText) v.findViewById(R.id._float);
        final EditText _string = (EditText) v.findViewById(R.id._string);
        final Spinner _list = (Spinner) v.findViewById(R.id._list);
        final Switch _boolean = (Switch) v.findViewById(R.id._boolean);
        final ImageView btnDelete = (ImageView) v.findViewById(R.id.muestra_delete);
        ImageView btnCam = (ImageView) v.findViewById(R.id.btn_cam);
        TextView tViewTime = (TextView) v.findViewById(R.id.tViewHoraMuestra);
        final EditText eTextComent = (EditText) v.findViewById(R.id.eTextComentario);

        tViewTime.setText(listMuestas.get(position).getTime());

        eTextComent.setText(listMuestas.get(position).getComent());

        btnCam.setClickable(true);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tomarFoto();
                //Toast.makeText(ctx,"Tomar Fotografia",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ctx,PhotoGallery.class);
                intent.putExtra("idMuestra",listMuestas.get(position).getId());
                intent.putExtra("value",listMuestas.get(position).getValue());
                intent.putExtra("name",listMuestas.get(position).getName());
                intent.putExtra("fechaHora",listMuestas.get(position).getTime());

                ((Activity) ctx).startActivityForResult(intent, 123);
            }
        });

        final int _id = listMuestas.get(position).getId();
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x=new MuestrasDAO(ctx).borrarMuestraById(listMuestas.get(position).getId());
                if(!x){
                    Toast.makeText(ctx,"Error al eliminar",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ctx,"Eliminado",Toast.LENGTH_SHORT).show();
                }
                listMuestas.remove(position);
                AdapterListMuestras.super.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(list);
                if(listMuestas.size()==0){
                    spnEva.setEnabled(true);
                }
            }
        });


       // Log.d("xdxdxd",position+"    "+listCriterios.get(position).getName());
        nameitem.setText(listMuestas.get(position).getName()+" "+listMuestas.get(position).getMagnitud());
        _string.setHeight(0);
        /*
        _float.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    nameitem.setText("123");
                }else{
                    nameitem.setText("321");
                }
            }
        });
*/
        eTextComent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listMuestas.get(position).setComent(eTextComent.getText().toString());
                new MuestrasDAO(ctx).editarComentById(_id,listMuestas.get(position).getComent());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        switch (listMuestas.get(position).getType()){
            case "boolean":

                _boolean.setVisibility(View.VISIBLE);
                nameitem.setText(listMuestas.get(position).getName());

                _boolean.setChecked(Boolean.valueOf(listMuestas.get(position).getValue()));

                _boolean.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        listMuestas.get(position).setValue(String.valueOf(isChecked));

                        new MuestrasDAO(ctx).editarValorById(_id,listMuestas.get(position).getValue());
                        //AdapterListMuestras.super.notifyDataSetChanged();

                    }
                });

                break;
            case "int":
                _int.setVisibility(View.VISIBLE);
                _int.setText(listMuestas.get(position).getValue());
                // _int.setText(Integer.valueOf(listMuestas.get(position).getValue()));
                nameitem.setText(listMuestas.get(position).getName()+" "+listMuestas.get(position).getMagnitud());


                _int.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        listMuestas.get(position).setValue(_int.getText().toString());

                        new MuestrasDAO(ctx).editarValorById(_id,listMuestas.get(position).getValue());
                        /*AdapterListMuestras.super.notifyDataSetChanged();
                        */
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                break;
            case "float":
                _float.setVisibility(View.VISIBLE);

                _float.setText(listMuestas.get(position).getValue());
                nameitem.setText(listMuestas.get(position).getName()+" "+listMuestas.get(position).getMagnitud());
                _float.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        listMuestas.get(position).setValue(_float.getText().toString());

                        new MuestrasDAO(ctx).editarValorById(_id,listMuestas.get(position).getValue());
                        //AdapterListMuestras.super.notifyDataSetChanged();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            case "list":
                nameitem.setText(listMuestas.get(position).getName());
                String tempList = listMuestas.get(position).getMagnitud();

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
                if(listMuestas.get(position).getValue().equals("")){
                    listMuestas.get(position).setValue("0");
                    new MuestrasDAO(ctx).editarValorById(_id,"0");
                }

                _list.setSelection(Integer.valueOf(listMuestas.get(position).getValue()));

                _list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        listMuestas.get(position).setValue(String.valueOf(i));
                        /**falta cambiar en la base de  datos
                         */
                        new MuestrasDAO(ctx).editarValorById(_id,String.valueOf(i));


                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
                break;
            case "string":
                Log.d("tttttt", "getView: ");
                _string.setHeight(150);
                _string.setVisibility(View.VISIBLE);
                _string.setText(listMuestas.get(position).getValue());
                _string.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        listMuestas.get(position).setValue(String.valueOf(s));
                        new MuestrasDAO(ctx).editarValorById(_id,listMuestas.get(position).getValue());
                        /**
                         * falta cambir valor  en db
                         */
                        /*
                        new MuestrasDAO(ctx).editarValorById(_id,listMuestas.get(position).getValue());
                        AdapterListMuestras.super.notifyDataSetChanged();
*/
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            default:
                Log.d("tamano","error no encontrado el tipo");
                break;

        }

        return v;
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




}
