package com.hqsoft.esales.doanapptravel.admin.adminHotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.R;

import java.sql.Connection;
import java.sql.Statement;

public class InsertHotel extends AppCompatActivity {

    EditText url,name_hotel,gia_hotel,address_hotel;
    LinearLayout insert;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public InsertHotel (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_hotel);

        url = findViewById(R.id.imginsert);
        name_hotel = findViewById(R.id.name_insert_hotel);
        gia_hotel = findViewById(R.id.price_insert_hotel);
        address_hotel = findViewById(R.id.insert_addr);
        insert = findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertHotel.inserthotel().execute("");
            }

        });

    }
    public class inserthotel extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(InsertHotel.this, "Loading Data.....", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(InsertHotel.this, "Insert Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InsertHotel.this, AdminHotel.class);
            startActivity(intent);
        }

        @Override
        protected String doInBackground(String... strings) {


            try {

                JDBCModel jdbcModel = new JDBCModel();
                connect = jdbcModel.getConnectionOf();


                if (connect == null){
                    Toast.makeText(InsertHotel.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
                }
                if(connect != null){
                    String imgurl = url.getText().toString();
                    String insert_name = name_hotel.getText().toString();
                    String insert_price = gia_hotel.getText().toString().trim();
                    String insert_address = address_hotel.getText().toString().trim();
                    if(imgurl.equals("")||insert_name.equals("")||insert_price.equals("")||insert_address.equals("")){
                        Toast.makeText(InsertHotel.this, "Nhập Đẩy Đủ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String sqlinset ="INSERT INTO Hotel (img,name,gia,addr) VALUES ('" + imgurl + "','" + insert_name + "','" + insert_price + "','" + insert_address + "')";
                        Statement statement = connect.createStatement();
                        statement.executeUpdate(sqlinset);

                    }


                }
                else{
                    Log.d("TAG", "onClick: ");
                }


            } catch (Exception exception) {
                Log.d("error",exception.getMessage() );
            }

            return z;
        }
    }
}