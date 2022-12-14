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
        recentsDataList.add(new RecentsData("T???nh ???? N???ng","Vi???t Nam","From $200",R.drawable.danang));
        recentsDataList.add(new RecentsData("Th??nh ph??? H??? Ch?? Minh","Vi???t Nam","From $300",R.drawable.hcm));
        recentsDataList.add(new RecentsData("Th??? ???? H?? N???i","Vi???t Nam","From $200",R.drawable.hanoi));
        recentsDataList.add(new RecentsData("T???nh Qu???ng Nam","Vi???t Nam","From $300",R.drawable.quangnam));
        recentsDataList.add(new RecentsData("T???nh Qu???ng Tr???","Vi???t Nam","From $200",R.drawable.quangtri));
        recentsDataList.add(new RecentsData("T???nh B???c Giang","Vi???t Nam","From $300",R.drawable.bacgiangne));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("H???i An ","???? N???ng","$200 - $500",R.drawable.hoian));
        topPlacesDataList.add(new TopPlacesData("C???u Hi???n L????ng","Qu???ng Tr???","$200 - $500",R.drawable.hienluong));
        topPlacesDataList.add(new TopPlacesData("H??? G????m ","H?? N???i","$200 - $500",R.drawable.hoguom));
        topPlacesDataList.add(new TopPlacesData("B?? N?? Hill","???? N???ng","$200 - $500",R.drawable.bana));
        topPlacesDataList.add(new TopPlacesData("?????o Ng???c","Ph?? Qu???c","$200 - $500",R.drawable.daongoc));
        topPlacesDataList.add(new TopPlacesData("Phong Nha","Qu???ng B??nh","$200 - $500",R.drawable.phongnha));
        topPlacesDataList.add(new TopPlacesData("V??nh H??? Long","Qu???ng Ninh","$200 - $500",R.drawable.vihalong));
        topPlacesDataList.add(new TopPlacesData("Th??c N?????c ???? L???t","???? L???t","$200 - $500",R.drawable.thacnuocdalat));
        topPlacesDataList.add(new TopPlacesData("N??i S????ng M?? ??? ???? L???t","???? L???t","$200 - $500",R.drawable.suongmudalat));
        topPlacesDataList.add(new TopPlacesData("C??? ???? Hu???","Hu???","$200 - $500",R.drawable.codohue));
        topPlacesDataList.add(new TopPlacesData("Ph?? Qu???c","Ki??n Giang","$200 - $500",R.drawable.phuquoc));

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