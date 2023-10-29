package com.hqsoft.esales.doanapptravel.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.doanapptravel.R;

import java.util.List;

public class BookHotelAdapter extends RecyclerView.Adapter<BookHotelAdapter.HotelBookViewHolder>{

    Context context;
    private List<BookHotelModel> list;
    String nameuser = " ";
    public BookHotelAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<BookHotelModel> listt){
        this.list = listt;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HotelBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotelbook,parent,false);
        return new HotelBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelBookViewHolder holder, int position) {
        BookHotelModel bookHotelModel =  list.get(position);
        holder.txtnamehotel.setText(bookHotelModel.getName_hotel());
        holder.txtdiachihotel.setText(bookHotelModel.getAddr_hotel());
        holder.txtgiahotel.setText(bookHotelModel.getGia_hotel());

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class HotelBookViewHolder extends RecyclerView.ViewHolder {
        TextView txtnamehotel,txtdiachihotel,txtgiahotel;
        public HotelBookViewHolder(@NonNull View itemView) {
            super(itemView);

            txtnamehotel = itemView.findViewById(R.id.tenhotel_item);
            txtdiachihotel = itemView.findViewById(R.id.addr_item);
            txtgiahotel = itemView.findViewById(R.id.giahotel_item);
        }
    }
}
