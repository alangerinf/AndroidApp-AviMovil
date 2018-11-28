package pe.ibao.agromovil.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import pe.ibao.agromovil.R;
import java.util.List;

import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;

public class AdapterListPhoto extends BaseAdapter {

    Context ctx;
    private List<FotoVO> listFotos;
    private ImageView lienzo;
    public AdapterListPhoto(Context ctx, List<FotoVO> listFotos, ImageView iView)
    {
        this.ctx = ctx;
        this.listFotos =listFotos;
        this.lienzo = iView;
    }

    public AdapterListPhoto(Context ctx, List<FotoVO> listFotos)
    {
        this.ctx = ctx;
        this.listFotos =listFotos;
    }

    @Override
    public int getCount() {
        return listFotos.size();
    }

    @Override
    public FotoVO getItem(int position) {
        return listFotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listFotos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ctx = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(ctx);
        v = inflater.inflate(R.layout.foto_itemeditor_list_view,null);
    //    TextView tViewFechaHora= (TextView) v.findViewById(R.id.foto_fechahora);
        ImageView iViewFoto = (ImageView) v.findViewById(R.id.foto_imagen);
        Bitmap bitmap = BitmapFactory.decodeFile(listFotos.get(position).getPath());
        if (bitmap.getWidth() > bitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap , 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        iViewFoto.setImageBitmap(bitmap);
  //      tViewFechaHora.setText(getItem(position).getFechaHora());


        return v;
    }
}
