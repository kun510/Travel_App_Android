package com.hqsoft.esales.LoginJDBC.Login;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hqsoft.esales.doanapptravel.R;
import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import  com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.hqsoft.esales.LoginJDBC.Model.User;

public class Register extends AppCompatActivity {
    TextView nextlogin;
    EditText email,user,pass,configpass;
    LinearLayout linear;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public Register (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Anhxa();

        nextlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String config = configpass.getText().toString();
                String emailtxt = email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (username.isEmpty()||password.isEmpty()||config.isEmpty()||emailtxt.isEmpty()){
                    Toast.makeText(Register.this, "Nhap day du", Toast.LENGTH_SHORT).show();

                }
               else if (!emailtxt.matches(emailPattern)){
                    Toast.makeText(Register.this, "Email Fail", Toast.LENGTH_SHORT).show();
                }
               else   if (!password.equals(config)){
                    Toast.makeText(Register.this, "Password không giống nhau", Toast.LENGTH_SHORT).show();
                }
               else  if(password.length()<6){
                    Toast.makeText(Register.this, "Password phải 6 ký tự", Toast.LENGTH_SHORT).show();
                }

               else {

                    new Register.registeruser().execute("");
                }

            }
        });

    }


    public void Anhxa(){
       email = findViewById(R.id.email);
       user = findViewById(R.id.user);
       pass = findViewById(R.id.pass);
       configpass = findViewById(R.id.confirm_pass);
       linear = findViewById(R.id.linear);
        nextlogin = findViewById(R.id.nextlogin);

    }
    public class registeruser extends AsyncTask<String, String , String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(Register.this, "Loading Data.....", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this,Login.class);
            startActivity(intent);
        }

        @Override
        protected String doInBackground(String... strings) {


            try{
                JDBCModel jdbcModel = new JDBCModel();
                connect = jdbcModel.getConnectionOf();
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String emailtxt = email.getText().toString().trim();

                if(connect == null){
                    z = "Check Your Internet Connection";
                }
                else{
                    String sql ="INSERT INTO logintravel (email,username,password) VALUES ('" + emailtxt + "','" + username + "','" + password + "')";
                    Statement stmt = connect.createStatement();
                    stmt.executeUpdate(sql);
                    Toast.makeText(Register.this, "Register Success", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }
    }
}