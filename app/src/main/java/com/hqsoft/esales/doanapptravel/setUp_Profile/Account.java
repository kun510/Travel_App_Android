package com.hqsoft.esales.doanapptravel.setUp_Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hqsoft.esales.LoginJDBC.Controller.JDBCController;
import  com.hqsoft.esales.LoginJDBC.Login.Login;
import com.hqsoft.esales.doanapptravel.booking.AllBooking;
import com.hqsoft.esales.doanapptravel.MainActivity;
import com.hqsoft.esales.doanapptravel.R;
import com.hqsoft.esales.doanapptravel.placesTravel.Terms;

import java.sql.Connection;
import java.util.Locale;

public class Account extends AppCompatActivity {

    EditText username_profile,pass_profile,email_profile;
    Button logout,back;
    private Connection connection;
    private JDBCController jdbcController = new JDBCController();
    public Account (){
        connection = jdbcController.ConnnectionData();
    }

    LinearLayout language, changePasswordAll, rules,booking;
  
    ImageView avt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Anhxa();

        booking.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent book = new Intent(Account.this, AllBooking.class);
               startActivity(book);
           }
       });
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, Terms.class);
                startActivity(intent);
            }
        });
        changePasswordAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(Account.this);
                LayoutInflater inflater = Account.this.getLayoutInflater();
                dialog.setContentView(inflater.inflate(R.layout.selectlanguage,null));
                LinearLayout vietnam , tienganh , nhatban , trungquoc;
                vietnam = dialog.findViewById(R.id.vietnam);
                tienganh = dialog.findViewById(R.id.tienganh);
                nhatban = dialog.findViewById(R.id.nhatban);
                trungquoc = dialog.findViewById(R.id.trungquoc);
                vietnam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("vi");
                    }
                });
                tienganh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("en");
                    }
                });
                nhatban.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("ja");
                    }
                });
                trungquoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLanguage("zh");
                    }
                });
                dialog.show();

            }
        });
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent logout = new Intent(Account.this,Login.class);
               startActivity(logout);
           }
       });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Account.this, MainActivity.class);
               startActivity(intent);
           }
       });


    }
    public void setLanguage(String language){
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration(); // cấu tạo hệ thống android để chuyển ngôn ngữ
        configuration.locale = locale; // cấu hình ngôn ngữ đc chọn
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics()); //cập nhật String ngôn ngữ
        Intent intent = new Intent(Account.this,Account.class);
        startActivity(intent);

    }
    public void Dialog(){
        Dialog dialog = new Dialog(this);
        LayoutInflater inflater = Account.this.getLayoutInflater();
        dialog.setContentView(inflater.inflate(R.layout.activity_account, null));

    }
    public  void Anhxa(){
        username_profile = findViewById(R.id.txtusername_profile);
        pass_profile = findViewById(R.id.txtpass_profile);
        email_profile = findViewById(R.id.txtemail_profile);
        logout = findViewById(R.id.btn_logout_profile);
        back = findViewById(R.id.btn_back_profile);
        language = findViewById(R.id.chontieng);
        changePasswordAll = findViewById(R.id.changepassall);
        rules = findViewById(R.id.dieukhoan);
        booking = findViewById(R.id.booking);
        avt = findViewById(R.id.avt);
    }
}