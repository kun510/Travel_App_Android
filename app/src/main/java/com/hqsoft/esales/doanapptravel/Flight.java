package com.hqsoft.esales.doanapptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Login.Login;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class Flight extends AppCompatActivity {
    TextView price_flight;
    Spinner spinnerkhoihanh, spinner_diemden, spinner_sohanhkhach, spinner_loaighe;
    String diemkhoihanh[]={"HCM", "Ha Noi", "Da Nang", "Hue","Quang Tri","Quang Nam"};

    String diemden[]={ "Ha Noi","HCM",  "Da Nang", "Hue","Quang Tri","Quang Nam"};

    String sohanhkhach[]={"1", "2", "3", "4","5","6","7","8","9"};

    String loaighe[]={"Business Class Seats","Economy Class Seat"};
    Calendar myCalendar = Calendar.getInstance();
    DatePicker ngaydi;

    Button back_flight, chon_flight;

    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public Flight (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        price_flight = findViewById(R.id.price_flight);
        chon_flight = findViewById(R.id.chon_flight);
        back_flight = findViewById(R.id.back_flight);
        back_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Flight.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        ngay di
        ngaydi = findViewById(R.id.ngaydi);
        Calendar today = Calendar.getInstance();
        long now = today.getTimeInMillis();
        ngaydi.setMinDate(now);
        DatePickerDialog.OnDateSetListener getday = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH ,dayOfMonth);

            }
        };
        ngaydi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Flight.this,getday,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

//        spinner
        spinnerkhoihanh = findViewById(R.id.spinner_diemkkhoihanh);
        spinner_diemden = findViewById(R.id.spinner_diemden);
        spinner_sohanhkhach = findViewById(R.id.spinner_sohanhkhach);
        spinner_loaighe = findViewById(R.id.spinner_loaighe);

        ArrayAdapter<String> adapterdiemkhoihanh=new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, diemkhoihanh
        );
        ArrayAdapter<String> adapterdiemden=new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, diemden
        );
        ArrayAdapter<String> adapterhanhkhach=new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, sohanhkhach
        );
        ArrayAdapter<String> adapterloaighe=new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, loaighe
        );

        adapterdiemkhoihanh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterdiemden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterhanhkhach.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterloaighe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerkhoihanh.setAdapter(adapterdiemkhoihanh);
        spinnerkhoihanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Flight.this, ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_diemden.setAdapter(adapterdiemden);
        spinner_diemden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_sohanhkhach.setAdapter(adapterhanhkhach);
        spinner_sohanhkhach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_loaighe.setAdapter(adapterloaighe);

        spinner_loaighe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1){
                    price_flight.setText("2000000");
                }
                else {
                    price_flight.setText("5000000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        chon_flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    JDBCModel jdbcModel = new JDBCModel();
                    connect = jdbcModel.getConnectionOf();

                    if (connect == null){
                        Toast.makeText(Flight.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
                    }
                    if(connect != null){
                        String txtkhoihanh = spinnerkhoihanh.getSelectedItem().toString();
                        String txtdiemden = spinner_diemden.getSelectedItem().toString();
                        String  txtngaydi = ngaydi.getDayOfMonth()+"/"+ (ngaydi.getMonth() + 1)+ "/"+ ngaydi.getYear();
                        String txtsohanhkhach = spinner_sohanhkhach.getSelectedItem().toString();
                        String txtloaighe = spinner_loaighe.getSelectedItem().toString();
                        String txtgia =  price_flight.getText().toString();
                        String username = readFile();
//
                        Toast.makeText(Flight.this, " - " +txtkhoihanh + " - " +txtdiemden + " - " +txtngaydi + " - "  +txtsohanhkhach + " - "  +txtloaighe + " - "  +txtgia, Toast.LENGTH_LONG).show();

                        String sqlinset ="INSERT INTO Flight (diemkhoihanh,diemden,ngaydi,sohanhkhach,hangghe,price,username) VALUES ('" + txtkhoihanh + "','" + txtdiemden + "','" + txtngaydi + "','" + txtsohanhkhach + "','" + txtloaighe + "','" + txtgia +  "','" + username + "')";
                        Statement statement = connect.createStatement();

                        if (statement.executeUpdate(sqlinset)>0){
                            Toast.makeText(Flight.this, "tao duoc roi .... ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Flight.this, "tao khong duoc .... ", Toast.LENGTH_SHORT).show();
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