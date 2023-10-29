package com.hqsoft.esales.doanapptravel.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class BookHotel extends AppCompatActivity {

    RecyclerView recyclerView;

    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public BookHotel (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

        recyclerView = findViewById(R.id.ryrcHotelbook);
        BookHotelAdapter bookHotelAdapter = new BookHotelAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        bookHotelAdapter.setData(getList());
        recyclerView.setAdapter(bookHotelAdapter);

    }
    private  List<BookHotelModel> getList(){
        List<BookHotelModel> list = new ArrayList<>();
        try {

            JDBCModel jdbcModel = new JDBCModel();
            connect = jdbcModel.getConnectionOf();

            if (connect == null){
                Toast.makeText(BookHotel.this, "Check Connect Data!", Toast.LENGTH_SHORT).show();
            }
            if(connect != null){
                nameuser = readFile();
                Toast.makeText(this, ""+nameuser, Toast.LENGTH_SHORT).show();
                String sql ="Select * from hoteladd where username ='" + nameuser + "'";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (rs != null){
                    while ((rs.next())){
                        String namebookhotel = rs.getString(2);
                        String giabookhotel = rs.getString(3);
                        String addrbookhotel = rs.getString(4);
                        list.add(new BookHotelModel(""+namebookhotel,""+giabookhotel,""+addrbookhotel));

                    }

                }
                else {
                    Toast.makeText(BookHotel.this, "Fail", Toast.LENGTH_SHORT).show();
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