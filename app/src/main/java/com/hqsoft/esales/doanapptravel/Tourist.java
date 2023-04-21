package com.hqsoft.esales.doanapptravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Tourist extends AppCompatActivity {
    CardView BaNa, Caurong, cvca;
    ImageView imageView,back;
    TextView textView, textView2, textView3, textView4, textView5;


    Animation anim_from_button, anim_from_top, anim_from_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist);
        BaNa = findViewById(R.id.cardView);
        Caurong = findViewById(R.id.cardView2);
        cvca = findViewById(R.id.cardView3);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.firstText);
        textView2 = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView4);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tourist.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //Load Animations
        anim_from_button = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);
        anim_from_top = AnimationUtils.loadAnimation(this, R.anim.anim_from_top);
        anim_from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);

        //Set Animations
        BaNa.setAnimation(anim_from_button);
        Caurong.setAnimation(anim_from_button);
        cvca.setAnimation(anim_from_button);
        imageView.setAnimation(anim_from_top);
        textView.setAnimation(anim_from_top);
        textView2.setAnimation(anim_from_top);
        textView3.setAnimation(anim_from_top);
        textView4.setAnimation(anim_from_top);
        textView5.setAnimation(anim_from_top);

        BaNa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondActivity = new Intent(Tourist.this, TouristBaNa.class);
                startActivity(secondActivity);
            }
        });
        Caurong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent caurong = new Intent(Tourist.this, TouristCauRong.class);
                startActivity(caurong);
            }
        });
        cvca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cvca = new Intent(Tourist.this, TouristCVCA.class);
                startActivity(cvca);
            }
        });

        //Hide status bar and navigation bar at the bottom
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        );
    }
}