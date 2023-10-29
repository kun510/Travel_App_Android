package com.hqsoft.esales.doanapptravel.hotel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;

import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.R;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends AppCompatActivity {

    RecyclerView recyclerView;
    HotelAdapter hotelAdapter;
    private  Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public Hotel (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("HOTEL");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF018786")));
        recyclerView = findViewById(R.id.ryrcHotel);
        hotelAdapter = new HotelAdapter(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            nameuser = bundle.getString("user", "");
        }
        Toast.makeText(this, ""+nameuser, Toast.LENGTH_SHORT).show();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


       hotelAdapter.setData(getList());
        recyclerView.setAdapter(hotelAdapter);


    }
    private List<HotelModel> getList(){
        List<HotelModel> list = new ArrayList<>();
        try {

            JDBCModel jdbcModel = new JDBCModel();
            connect = jdbcModel.getConnectionOf();

            if (connect == null){
                Toast.makeText(Hotel.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
            }
            if(connect != null){
                String sql ="Select * from Hotel ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);


                if (rs != null){
                    while ((rs.next())){
                        String img = rs.getString(2);
                        String name = rs.getString(3);
                        String gia = rs.getString(4);
                        String addr = rs.getString(5);
                        list.add(new HotelModel(img,""+name,""+gia,""+addr));
                    }

                }
                else {
                    Toast.makeText(Hotel.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            else{
                Log.d("TAG", "onClick: ");
            }


        } catch (Exception e) {
            Log.d("error",e.getMessage() );
        }
        return list;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}