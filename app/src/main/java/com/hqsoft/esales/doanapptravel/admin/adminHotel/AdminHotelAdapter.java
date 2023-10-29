package com.hqsoft.esales.doanapptravel.admin.adminHotel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.R;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class AdminHotelAdapter extends RecyclerView.Adapter<AdminHotelAdapter.AdminHotelAdapterViewHolder>{

    Context context;
    private List<AdminHotelModel> list;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public AdminHotelAdapter (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = "";
    public AdminHotelAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<AdminHotelModel> listt){
        this.list = listt;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminHotelAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adminhotel,parent,false);
        return new AdminHotelAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminHotelAdapterViewHolder holder, int position) {
        AdminHotelModel hotelModel = list.get(position);
        if (hotelModel == null){
            return;
        }
        Picasso.get().load(hotelModel.getImg()).into(holder.imghotel);
        //Glide.with(context).load(hotelModel.getImg()).into(holder.imghotel);
        // holder.imghotel.setImageURI(hotelModel.getImg());
        holder.namehotel.setText(hotelModel.getName_hotel());
        holder.giahotel.setText(hotelModel.getGia_hotel());
        holder.addrhotel.setText(hotelModel.getAddr_hotel());
        holder.idhotel.setText(hotelModel.getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class AdminHotelAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView imghotel;
        private TextView namehotel;
        private TextView idhotel;
        private TextView giahotel;
        private TextView addrhotel;
        private Button chotkhachsan,detelekhachsan,updatekhachsan;
        public AdminHotelAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            imghotel = itemView.findViewById(R.id.img_hotel);
            namehotel = itemView.findViewById(R.id.name_hotel);
            idhotel = itemView.findViewById(R.id.id_hotel);
            giahotel = itemView.findViewById(R.id.gia_hotel);
            addrhotel = itemView.findViewById(R.id.addr_hotel);
            chotkhachsan = itemView.findViewById(R.id.chotkhachsan);
            detelekhachsan = itemView.findViewById(R.id.xoakhachsan);
            updatekhachsan = itemView.findViewById(R.id.updatekhachsan);
            detelekhachsan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String idne = idhotel.getText().toString();
//                        Toast.makeText(context, ""+idne, Toast.LENGTH_SHORT).show();
                        JDBCModel jdbcModel = new JDBCModel();
                        connect = jdbcModel.getConnectionOf();

                        if (connect == null){
                            Toast.makeText(context, "Check Connect Data!", Toast.LENGTH_SHORT).show();
                        }
                        if(connect != null){
//                              Toast.makeText(context, " - " +txtnamehotel + " - " +txtgiahotel + " - " +txtaddr, Toast.LENGTH_LONG).show();

                          String sqlinset ="DELETE FROM Hotel  where id ='" + idne + "'";
                            Statement statement = connect.createStatement();

                            if (statement.executeUpdate(sqlinset)>0){
                                Toast.makeText(context, "Xoá Thành Công ", Toast.LENGTH_SHORT).show();
                                Intent xoaok = new Intent(context, AdminHotel.class);
                                context.startActivity(xoaok);
                            }
                            else {
                                Toast.makeText(context, "Xoá Bị Lỗi .... ", Toast.LENGTH_SHORT).show();
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
            updatekhachsan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idne = idhotel.getText().toString();
                    String nameupdate = namehotel.getText().toString();
                    String addr = addrhotel.getText().toString();
                    String gia  = giahotel.getText().toString();
                    Intent update = new Intent(context, UpdateAdmin.class);
                    update.putExtra("name",idne);
                    update.putExtra("tenhotel",nameupdate);
                    update.putExtra("gia",gia);
                    update.putExtra("addr",addr);

                    context.startActivity(update);
                }
            });
        }
    }
}
