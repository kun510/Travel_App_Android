package com.hqsoft.esales.doanapptravel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHoder> {
    private static final  int VIEW_TYPE_DATA = 0;
    private static final  int VIEW_TYPE_LOADING= 1;
    Context context;
    private List<HotelModel> list;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public HotelAdapter (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }

    Connection connect;
    String nameuser = "";
    public HotelAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<HotelModel> listt){
        this.list = listt;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public HotelAdapter.HotelViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel,parent,false);
            return new HotelViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHoder holder, int position) {
        HotelModel hotelModel = list.get(position);
        if (hotelModel == null){
            return;
        }
        Picasso.get().load(hotelModel.getImg()).into(holder.imghotel);
        //Glide.with(context).load(hotelModel.getImg()).into(holder.imghotel);
       // holder.imghotel.setImageURI(hotelModel.getImg());
        holder.namehotel.setText(hotelModel.getName_hotel());
        holder.giahotel.setText(hotelModel.getGia_hotel());
        holder.addrhotel.setText(hotelModel.getAddr_hotel());

    }
    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null? VIEW_TYPE_LOADING:VIEW_TYPE_DATA; //nếu ma position bằng null thì hiện thị thanh load, còn không thì view data
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class HotelViewHoder extends RecyclerView.ViewHolder {
        private ImageView imghotel;
        private TextView namehotel;
        private TextView giahotel;
        private TextView addrhotel;
        private Button chotkhachsan;
        public HotelViewHoder(@NonNull View itemView) {
            super(itemView);

            imghotel = itemView.findViewById(R.id.img_hotel);
            namehotel = itemView.findViewById(R.id.name_hotel);
            giahotel = itemView.findViewById(R.id.gia_hotel);
            addrhotel = itemView.findViewById(R.id.addr_hotel);
            chotkhachsan = itemView.findViewById(R.id.chotkhachsan);
            chotkhachsan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        JDBCModel jdbcModel = new JDBCModel();
                        connect = jdbcModel.getConnectionOf();

                        if (connect == null){
                            Toast.makeText(context, "Check Connect Data!", Toast.LENGTH_SHORT).show();
                        }
                        if(connect != null){
                            String txtnamehotel = namehotel.getText().toString();
                            String  txtgiahotel = giahotel.getText().toString();
                            String txtaddr = addrhotel.getText().toString();
                            String username = "lamcuong";
                            String sqlinset ="INSERT INTO hoteladd (namehotel,giahotel,addr,username) VALUES ('" + txtnamehotel + "','" + txtgiahotel + "','" + txtaddr +  "','" + username + "')";
                            Statement statement = connect.createStatement();

                            if (statement.executeUpdate(sqlinset)>0){
                                Context context = itemView.getContext();
                                Intent intent = new Intent(context,AllBooking.class);
                                context.startActivity(intent);
                                Toast.makeText(context, "tao duoc roi .... ", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "tao khong duoc .... ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Log.d("TAG", "onClick: ");
                        }


                    } catch (Exception exception) {
                        Log.d("error",exception.getMessage() );
                    }
                }
            });

        }
    }


}
