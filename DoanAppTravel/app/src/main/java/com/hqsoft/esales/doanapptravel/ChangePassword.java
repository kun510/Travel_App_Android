package com.hqsoft.esales.doanapptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Login.Login;
import com.hqsoft.esales.LoginJDBC.Login.Register;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class ChangePassword extends AppCompatActivity {
    EditText pas_change,configpass_change;
    TextView email_change;
    Button back,change;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public ChangePassword (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    String nameuser = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        email_change = findViewById(R.id.txtemail_change);
        pas_change = findViewById(R.id.txtpass_change);
        configpass_change = findViewById(R.id.txtConfigPassword_change);
        back = findViewById(R.id.btn_back_change);
        change = findViewById(R.id.btn_change_change);

        nameuser = readFile();
        email_change.setText(nameuser);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this,Account.class);
                startActivity(intent);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getpass = pas_change.getText().toString();
                String getcfpass = configpass_change.getText().toString();

                if (getcfpass.isEmpty()||getpass.isEmpty()){
                    Toast.makeText(ChangePassword.this, "Điền Đầy Đủ", Toast.LENGTH_SHORT).show();
                }
                else   if (!getpass.equals(getcfpass)){
                    Toast.makeText(ChangePassword.this, "Password không giống nhau", Toast.LENGTH_SHORT).show();
                }
                else  if(getpass.length()<6){
                    Toast.makeText(ChangePassword.this, "Password phải 6 ký tự", Toast.LENGTH_SHORT).show();
                }
                else {
                    new ChangePassword.UdatepassUser().execute("");
                }

            }
        });
    }
    public class UdatepassUser extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(ChangePassword.this, "Loading Data.....", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ChangePassword.this, "UPDATE Password Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ChangePassword.this,Account.class);
            startActivity(intent);
        }

        @Override
        protected String doInBackground(String... strings) {


            try{
                JDBCModel jdbcModel = new JDBCModel();
                connect = jdbcModel.getConnectionOf();

                String user = email_change.getText().toString();
                String getpass = pas_change.getText().toString();


                if(connect == null){
                    z = "Check Your Internet Connection";
                }
                else{
                    String sql ="UPDATE logintravel set password ='" + getpass + "' WHERE username ='" +  user + "' ";
                    Statement stmt = connect.createStatement();
                    stmt.executeUpdate(sql);
                    Toast.makeText(ChangePassword.this, "UPDATE Password Success", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }
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