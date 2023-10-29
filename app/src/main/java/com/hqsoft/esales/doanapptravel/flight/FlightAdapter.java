package com.hqsoft.esales.doanapptravel.flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.doanapptravel.R;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    Context context;
    private List<FlightModel> list;
    String nameuser = " ";
    public FlightAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<FlightModel> listt){
        this.list = listt;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight,parent,false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        FlightModel  fightModel = list.get(position);
        holder.dd.setText(fightModel.getDiemkhoihanh());
        holder.nd.setText(fightModel.getDiemden());
        holder.ngd.setText(fightModel.getNgaydi());
        holder.shk.setText(fightModel.getSohanhkhach());
        holder.hg.setText(fightModel.getLoaighe());
        holder.giane.setText(fightModel.getGia());

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder {

        TextView dd,nd,ngd,shk,hg,giane,nguoidat;
        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            dd = itemView.findViewById(R.id.diemkhoihanh_item);
            nd = itemView.findViewById(R.id.diemden_item);
            ngd = itemView.findViewById(R.id.ngaydi_item);
            shk = itemView.findViewById(R.id.sohanhkhach_item);
            hg = itemView.findViewById(R.id.loaighe_item);
            giane = itemView.findViewById(R.id.gia_item);

        }
    }
}
