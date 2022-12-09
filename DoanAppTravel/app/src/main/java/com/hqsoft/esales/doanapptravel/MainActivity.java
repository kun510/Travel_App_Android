package com.hqsoft.esales.doanapptravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    EditText findtxt;
    String nameuser = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findtxt = findViewById(R.id.findtxt);

        findtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent flight = new Intent(MainActivity.this, SearchTravel.class);
                startActivity(flight);
            }
        });

        BottomNavigationView navigationView = findViewById(R.id.BottomMenu);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.Home:
                        Intent home = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.Tour:
                        Intent hotel = new Intent(MainActivity.this, Tourist.class);
                        startActivity(hotel);
                        break;
                    case R.id.Hotel:
                        Intent nexthotel = new Intent(MainActivity.this, Hotel.class);
                        startActivity(nexthotel);
                        break;
                    case R.id.Flight:
                        Intent flight = new Intent(MainActivity.this, Flight.class);
                        Bundle bundleflight = new Bundle();
                        bundleflight.putString("user", nameuser);
                        flight.putExtras(bundleflight);
                        startActivity(flight);
                        break;
                    case R.id.Account:
                        Intent account = new Intent(MainActivity.this, Account.class);
                        startActivity(account);

                        break;
                }

                return true;

            }
        });


        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Tỉnh Ðà Nẵng","Việt Nam","From $200",R.drawable.danang));
        recentsDataList.add(new RecentsData("Thành phố Hồ Chí Minh","Việt Nam","From $300",R.drawable.hcm));
        recentsDataList.add(new RecentsData("Thủ Ðô Hà Nội","Việt Nam","From $200",R.drawable.hanoi));
        recentsDataList.add(new RecentsData("Tỉnh Quảng Nam","Việt Nam","From $300",R.drawable.quangnam));
        recentsDataList.add(new RecentsData("Tỉnh Quảng Trị","Việt Nam","From $200",R.drawable.quangtri));
        recentsDataList.add(new RecentsData("Tỉnh Bắc Giang","Việt Nam","From $300",R.drawable.bacgiangne));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Hội An ","Ðà Nẵng","$200 - $500",R.drawable.hoian));
        topPlacesDataList.add(new TopPlacesData("Cầu Hiền Lương","Quảng Trị","$200 - $500",R.drawable.hienluong));
        topPlacesDataList.add(new TopPlacesData("Hồ Gươm ","Hà Nội","$200 - $500",R.drawable.hoguom));
        topPlacesDataList.add(new TopPlacesData("Bà Nà Hill","Đà Nẵng","$200 - $500",R.drawable.bana));
        topPlacesDataList.add(new TopPlacesData("Đảo Ngọc","Phú Quốc","$200 - $500",R.drawable.daongoc));
        topPlacesDataList.add(new TopPlacesData("Phong Nha","Quảng Bình","$200 - $500",R.drawable.phongnha));
        topPlacesDataList.add(new TopPlacesData("Vĩnh Hạ Long","Quảng Ninh","$200 - $500",R.drawable.vihalong));
        topPlacesDataList.add(new TopPlacesData("Thác Nước Đà Lạt","Đà Lạt","$200 - $500",R.drawable.thacnuocdalat));
        topPlacesDataList.add(new TopPlacesData("Núi Sương Mù ở Đà Lạt","Đà Lạt","$200 - $500",R.drawable.suongmudalat));
        topPlacesDataList.add(new TopPlacesData("Cố Đô Huế","Huế","$200 - $500",R.drawable.codohue));
        topPlacesDataList.add(new TopPlacesData("Phú Quốc","Kiên Giang","$200 - $500",R.drawable.phuquoc));

        setTopPlacesRecycler(topPlacesDataList);
    }
    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }



}