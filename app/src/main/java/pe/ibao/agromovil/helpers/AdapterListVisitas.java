package pe.ibao.agromovil.helpers;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.dao.FundoDAO;
import pe.ibao.agromovil.models.dao.VisitaDAO;
import pe.ibao.agromovil.models.vo.entitiesInternal.VisitaVO;
import pe.ibao.agromovil.views.ActivityVisita;

public class AdapterListVisitas extends BaseAdapter{

    private Context ctx;
    private List<VisitaVO> listVisitas;
    private ListView listView;

    public AdapterListVisitas(Context ctx, List<VisitaVO> listVisitas, ListView listView){
        this.ctx = ctx;
        this.listVisitas =listVisitas;
        this.listView = listView;
       // setListViewHeightBasedOnChildren(listView);
    }

    @Override
    public int getCount() {
        return listVisitas.size();
    }

    @Override
    public Object getItem(int position) {
        return listVisitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listVisitas.get(position).getId();
    }

    private void verVisita(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ctx.startActivity(i);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        v = inflater.inflate(R.layout.visita_itemeditor_list_view,null);

        final TextView tViewFechaHora = (TextView) v.findViewById(R.id.visita_tViewFechaHora);
        final TextView tViewNameFundo    = (TextView) v.findViewById(R.id.visita_tViewNameFundo);
        final TextView tViewNameCultivo  = (TextView) v.findViewById(R.id.visita_tViewNameCultivo);
        final TextView tViewNameVariedad = (TextView) v.findViewById(R.id.visita_tViewNameVariedad);
        final TextView tViewNameContacto = (TextView) v.findViewById(R.id.visita_tViewNameContacto);

        final ImageView iViewBtnView = (ImageView) v.findViewById(R.id.visita_iViewBtnView);
        final ImageView iViewBtnDelete = (ImageView) v.findViewById(R.id.visita_iViewBtnDelete);

        final VisitaVO visitaVO = listVisitas.get(position);

        tViewFechaHora.setText(visitaVO.getFechaHora());
        tViewNameFundo.setText( new FundoDAO(ctx).consultarById(visitaVO.getIdFundo()).getName());
        tViewNameCultivo.setText( visitaVO.getNameCultivo());
        tViewNameVariedad.setText( visitaVO.getNameVariedad());
        tViewNameContacto.setText( visitaVO.getContacto());

        iViewBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ActivityVisita.class);
                i.putExtra("isEditable",false);
                i.putExtra("idVisita",visitaVO.getId());
                ctx.startActivity(i);
            }
        });

        iViewBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listVisitas.remove(position);
                AdapterListVisitas.super.notifyDataSetChanged();
                new VisitaDAO(ctx).borrarById(visitaVO.getId());
            }
        });

        return v;
    }






}
