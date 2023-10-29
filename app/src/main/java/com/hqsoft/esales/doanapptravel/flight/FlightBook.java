package com.hqsoft.esales.doanapptravel.flight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.R;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightBook extends AppCompatActivity {
    RecyclerView flightt;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public FlightBook (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_book);
        flightt = findViewById(R.id.flight_fragment);
        FlightAdapter flightAdapter = new FlightAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        flightt.setLayoutManager(linearLayoutManager);

        flightAdapter.setData(getList());
        flightt.setAdapter(flightAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nameuser = bundle.getString("user", "");
        }
        Toast.makeText(this, ""+ nameuser, Toast.LENGTH_SHORT).show();

    }
    private List<FlightModel> getList(){
        List<FlightModel> list = new ArrayList<>();
        try {

            JDBCModel jdbcModel = new JDBCModel();
            connect = jdbcModel.getConnectionOf();
           // Toast.makeText(this, ""+ connect, Toast.LENGTH_SHORT).show();
            if (connect == null){
                Toast.makeText(this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
            }
            if(connect != null){
                nameuser = readFile();
                String sql ="Select * from Flight where username ='" + nameuser + "' ";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);


                if (rs != null){
                    while ((rs.next())){
                        String diemkhoihanh = rs.getString(2);
                        String diemden = rs.getString(3);
                        String ngaydi = rs.getString(4);
                        String sohanhkhach = rs.getString(5);
                        String loaighe = rs.getString(6);
                        int gia = rs.getInt(7);
                        String giastring = String.valueOf(gia);
                        // Toast.makeText(getActivity(), " - " +diemkhoihanh + " - " + diemden + " - " +ngaydi + " - " + sohanhkhach +" - " + loaighe + " - " + gia  , Toast.LENGTH_SHORT).show();

                        list.add(new FlightModel(diemkhoihanh,diemden,ngaydi,sohanhkhach,loaighe,giastring));
                    }

                }
                else {
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
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
    public String readFile(){
        String fileName = "ghiFile.txt";
        String str = "";
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            char[] inputFile = new char[100];
            int charRead ;
            while ((charRead = inputStreamReader.read(inputFile)) > 0){
                String readText = String.valueOf(inputFile, 0, charRead);
                str += readText;
            }
        }
        catch (Exception e){
            Log.e( "Error:", e.toString());

        }
        return str;
    }
}