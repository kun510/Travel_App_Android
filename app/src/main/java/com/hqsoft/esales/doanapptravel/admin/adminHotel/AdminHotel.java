package com.hqsoft.esales.doanapptravel.admin.adminHotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminHotel extends AppCompatActivity {
    RecyclerView recyclerView;
    AdminHotelAdapter hotelAdapter;
    ImageView add;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public AdminHotel (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel);

        recyclerView = findViewById(R.id.ryrcHotel);
        hotelAdapter = new AdminHotelAdapter(this);
        add = findViewById(R.id.add);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        hotelAdapter.setData(getList());
        recyclerView.setAdapter(hotelAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextInsert = new Intent(AdminHotel.this, InsertHotel.class);
                startActivity(NextInsert);
            }
        });

    }
    private List<AdminHotelModel> getList(){
        List<AdminHotelModel> listt = new ArrayList<>();
        try {

            JDBCModel jdbcModel = new JDBCModel();
            connect = jdbcModel.getConnectionOf();

            if (connect == null){
                Toast.makeText(AdminHotel.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
            }
            if(connect != null){
                String sql ="Select * from Hotel ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null){
                    while ((rs.next())){
                        String id = rs.getString(1);
                        String img = rs.getString(2);
                        String name = rs.getString(3);
                        String gia = rs.getString(4);
                        String addr = rs.getString(5);
                        listt.add(new AdminHotelModel(""+id,img,""+name,""+gia,""+addr));
                    }

                }
                else {
                    Toast.makeText(AdminHotel.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            else{
                Log.d("TAG", "onClick: ");
            }


        } catch (Exception e) {
            Log.d("error",e.getMessage() );
        }
        return listt;
    }
}