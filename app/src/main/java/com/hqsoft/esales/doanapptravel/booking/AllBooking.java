package com.hqsoft.esales.doanapptravel.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hqsoft.esales.doanapptravel.R;
import com.hqsoft.esales.doanapptravel.flight.FlightBook;

public class AllBooking extends AppCompatActivity  {

    String nameuser = " ";
    LinearLayout bfl,bht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_booking);

        bfl = findViewById(R.id.bookflighne);
        bht = findViewById(R.id.bookhotel);


        bfl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( AllBooking.this, FlightBook.class);
                startActivity(intent);


            }
        });
        bht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( AllBooking.this, BookHotel.class);
                startActivity(intent);
            }
        });



    }


}