package com.hqsoft.esales.LoginJDBC.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import com.hqsoft.esales.LoginJDBC.Model.JDBCModel;
import com.hqsoft.esales.doanapptravel.admin.adminHotel.AdminHotel;
import com.hqsoft.esales.doanapptravel.MainActivity;
import com.hqsoft.esales.doanapptravel.R;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    ImageView fb,gg;
    TextView nextRegister,txthide;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    LinearLayout linear;
    EditText username , password;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public Login (){
        connection = jdbcController.ConnnectionData(); // Tạo kết nối tới database
    }
    Connection connect;
    SharedPreferences sharedPreferences;

    String user="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nextRegister = findViewById(R.id.nextRegister);
        fb = findViewById(R.id.fb);
        linear = findViewById(R.id.linear);
        username = findViewById(R.id.user_Login);
        password = findViewById(R.id.pass_Login);
        txthide = findViewById(R.id.txthide);
        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        txthide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = txthide.getText().toString();
                if (a.equals("Hide")){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    txthide.setText("Show");
                }
                else  if (a.equals("Show")){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    txthide.setText("Hide");
                }
            }
        });
        gg = findViewById(R.id.gg);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            navigateToSecondActivity();
        }
        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getuser = username.getText().toString();
                String getpass = password.getText().toString();
                if (getuser.isEmpty()||getpass.isEmpty()){
                    Toast.makeText(Login.this, "Điền Đầy Đủ", Toast.LENGTH_SHORT).show();
                }
                else if (getuser.equals("dreamteam")&&getpass.equals("1234567")){
                    Intent nextAdmin = new Intent(Login.this,AdminHotel.class);
                    startActivity(nextAdmin);
                }
                else {
                    new Login.checkLogin().execute("");
                }

            }
        });
        nextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(fb, "background_image_transition");
                Intent Register = new Intent(Login.this, com.hqsoft.esales.LoginJDBC.Login.Register.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(Register, options.toBundle());

            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public class checkLogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected String doInBackground(String... strings) {

            JDBCModel jdbcModel = new JDBCModel();
            connect = jdbcModel.getConnectionOf();

            if(connect == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                try {
                    user = username.getText().toString();
                    String pass = password.getText().toString();
                    String sql = "Select * from logintravel where username ='" + user + "' and password ='" + pass + "'";
                    Statement stmt = connect.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(Login.this, "Login Success " + user, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("user", user);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                writeFile();
                                finish();
                            }
                        });
                        z = "Success";


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Check email or password", Toast.LENGTH_LONG).show();
                            }
                        });

                        username.setText("");
                        password.setText("");
                    }
                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
            }
            return z;
        }
    }
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        }

    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }

    String fileName = "ghiFile.txt";

    public void writeFile(){
        try {
            FileOutputStream fileOutputStream = openFileOutput (fileName ,MODE_PRIVATE) ;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream) ;
            outputStreamWriter.write(user);
            outputStreamWriter.close();
        }
        catch (Exception ex){
            Log.e( "Error:", ex.toString());

        }
    }


}