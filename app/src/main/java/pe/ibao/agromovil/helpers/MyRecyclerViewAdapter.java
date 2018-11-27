package pe.ibao.agromovil.helpers;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.ibao.agromovil.DataUserHandler;
import pe.ibao.agromovil.R;
import pe.ibao.agromovil.models.vo.entitiesInternal.FotoVO;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<FotoVO> listFotos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context ctx;
    private ImageView lienzo;
    private Boolean isEditable;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<FotoVO> listFotos,ImageView lienzo,Boolean isEditable) {
        this.mInflater = LayoutInflater.from(context);
        this.listFotos = listFotos;
        this.ctx=context;
        this.lienzo = lienzo;
        this.isEditable = isEditable;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.foto_itemeditor_list_view, parent, false);
        return new ViewHolder(view);


    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                FotoVO fotoVO = listFotos.get(position);
                Bitmap bitmap = BitmapFactory.decodeFile(listFotos.get(position).getPath());
                bitmap = Bitmap.createScaledBitmap(bitmap, 150, 200, true);
                holder.iViewFoto.setImageBitmap(bitmap);
            }
        }, 100);

        holder.iViewFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"seleccionado"+position,Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeFile(listFotos.get(position).getPath());
                double scale = bitmap.getWidth()/(bitmap.getHeight()*1.0);
                bitmap = Bitmap.createScaledBitmap(bitmap,(int)(lienzo.getHeight()*scale), lienzo.getHeight(), true);
                lienzo.setImageBitmap(bitmap);
            }
        });




   //     holder.tViewFechaHora.setText(listFotos.get(position).getFechaHora());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return listFotos.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iViewFoto;
        TextView tViewFechaHora;

        ViewHolder(View itemView) {
            super(itemView);
            iViewFoto = (ImageView) itemView.findViewById(R.id.foto_imagen);
          //  tViewFechaHora = itemView.findViewById(R.id.foto_fechahora);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    public FotoVO getItem(int position) {
        return listFotos.get(position);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}