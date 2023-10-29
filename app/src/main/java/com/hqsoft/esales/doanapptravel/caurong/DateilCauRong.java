package com.hqsoft.esales.doanapptravel.caurong;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.hqsoft.esales.doanapptravel.R;
import com.hqsoft.esales.doanapptravel.api.apiYoutube.APIYoutubeCaurong;

public class DateilCauRong extends AppCompatActivity {

    ImageView down_arrow;

    ScrollView third_scrollview;
    Button reviewcaurong_button;
    Animation from_bottom;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateil_cau_rong);

        down_arrow = findViewById(R.id.down_arrow);
        third_scrollview = findViewById(R.id.third_scrillview);

        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);

        reviewcaurong_button = findViewById(R.id.reviewcaurong_button);
        reviewcaurong_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateilCauRong.this, APIYoutubeCaurong.class);
                startActivity(intent);
            }
        });

        down_arrow.setAnimation(from_bottom);
        third_scrollview.setAnimation(from_bottom);

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
        down_arrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateilCauRong.this, TouristCauRong.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(down_arrow, "background_image_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DateilCauRong.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}