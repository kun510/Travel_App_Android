package com.hqsoft.esales.doanapptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateAdmin extends AppCompatActivity {
    EditText url_update,name_update_hotel,gia_update_hotel,address_update_hotel;
    TextView id;
    LinearLayout update;
    String idtxt ="";
    String giatxt ="";
    String nametxt ="";
    String addrtxt ="";
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public UpdateAdmin (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);
        url_update = findViewById(R.id.imgupdate);
        name_update_hotel = findViewById(R.id.name_update_hotel);
        gia_update_hotel = findViewById(R.id.price_update_hotel);
        address_update_hotel = findViewById(R.id.update_addr);
        update = findViewById(R.id.update);
        id = findViewById(R.id.idupdate);

        idtxt =  getIntent().getStringExtra("name");
        nametxt =  getIntent().getStringExtra("tenhotel");
        giatxt =  getIntent().getStringExtra("gia");
        addrtxt =  getIntent().getStringExtra("addr");
        id.setText(idtxt);
        name_update_hotel.setText(nametxt);
        gia_update_hotel.setText(giatxt);
        address_update_hotel.setText(addrtxt);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateAdmin.Udatehotel().execute("");

            }
        });
    }
    public class Udatehotel extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(UpdateAdmin.this, "Loading Data.....", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(UpdateAdmin.this, "UPDATE Hotel Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateAdmin.this,AdminHotel.class);
            startActivity(intent);
        }

        @Override
        protected String doInBackground(String... strings) {


            try {

                JDBCModel jdbcModel = new JDBCModel();
                connect = jdbcModel.getConnectionOf();


                if (connect == null){
                    Toast.makeText(UpdateAdmin.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
                }
                if(connect != null){
                    String idupdate = id.getText().toString();
                    String imgurl_update = url_update.getText().toString();
                    String update_name = name_update_hotel.getText().toString();
                    String update_price = gia_update_hotel.getText().toString().trim();
                    String update_address = address_update_hotel.getText().toString().trim();
                    if(imgurl_update.equals("")||update_name.equals("")||update_price.equals("")||update_address.equals("")){
                        Toast.makeText(UpdateAdmin.this, "Nhập Đẩy Đủ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String sqlinset ="UPDATE Hotel  SET img ='" + imgurl_update + "', name ='" + update_name + "',gia ='" + update_price + "',addr ='" + update_address + "' where id ='" +  idupdate + "' ";
                        Statement statement = connect.createStatement();
                      //  ResultSet resultSet = statement.executeQuery(sqlinset);
                        statement.executeUpdate(sqlinset);
                        Toast.makeText(UpdateAdmin.this, "UPDATE Hotel Success", Toast.LENGTH_SHORT).show();
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