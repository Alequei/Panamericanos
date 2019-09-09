package lourdes.pampa.panamericanos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorPanamericano extends  RecyclerView.Adapter<AdaptadorPanamericano.ViewHolder>{
    private List<Panamericanos> panamericanos;
    public AdaptadorPanamericano(){
        this.panamericanos=new ArrayList<>();
    }

    public void setPanamericanos(List<Panamericanos> panamericanos){
        this.panamericanos=panamericanos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulotxt;
        public ViewHolder(View itemView) {
            super(itemView);
            titulotxt = itemView.findViewById(R.id.titulo);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,final int position) {
       final Panamericanos panamericanos= this.panamericanos.get(position);
        viewHolder.titulotxt.setText(panamericanos.getNombredeport());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("ID", panamericanos.getId());
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.panamericanos.size();
    }
}
