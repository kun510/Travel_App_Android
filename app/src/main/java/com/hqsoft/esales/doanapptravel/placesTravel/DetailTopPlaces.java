package com.hqsoft.esales.doanapptravel.placesTravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqsoft.esales.doanapptravel.R;

public class DetailTopPlaces extends AppCompatActivity {

ImageView img;
TextView name,chitiet,giave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_top_places);
        String nametxt = getIntent().getStringExtra("name");
        String diadiem = getIntent().getStringExtra("diadiem");
        String gia = getIntent().getStringExtra("gia");
        int anh = getIntent().getIntExtra("img",0);


        img = findViewById(R.id.imgtopplaces);
        name = findViewById(R.id.name);
        chitiet = findViewById(R.id.chitiet);
        giave = findViewById(R.id.giave);

        name.setText(nametxt);
        img.setImageResource(anh);
        chitiet.setText(diadiem);
        giave.setText(gia);

    }
}