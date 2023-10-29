package com.hqsoft.esales.doanapptravel.api.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hqsoft.esales.doanapptravel.MainActivity;
import com.hqsoft.esales.doanapptravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Weather extends AppCompatActivity {
    TextView tencity,timehientai,tinhtrang,txtnhietdo,binhminh,phantrammay,gio,txtapsuat,txtdoam;
    LinearLayout cacngaykhac;
    Button backweather;
    ImageView iconthoitiet;
    String nametxt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        nametxt = getIntent().getStringExtra("name");
        Anhxa();


        GetCurrenWeatherData(nametxt);


        backweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Nextt = new Intent(Weather.this, MainActivity.class);
                startActivity(Nextt);
            }
        });
    }

    public void GetCurrenWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(Weather.this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=4fdcdf31ade846ac58b0d8eb20e7b8e6";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                         //   Log.d("ketqua", response);
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");


                            if (nametxt.equals("Tỉnh Ðà Nẵng")){
                                name = "Thành Phố Ðà Nẵng";

                            }

                            if (nametxt.equals("Thủ Ðô Hà Nội")){
                                name = "Thủ Ðô Hà Nội";
                            }

                            if (nametxt.equals("Tỉnh Quảng Trị")){
                                name = "Tỉnh Quảng Trị";
                            }

                            if (nametxt.equals("Tỉnh Bắc Giang")){
                                name = "Tỉnh Bắc Giang";
                            }

                            if (nametxt.equals("Tỉnh Quảng Nam")){
                                name = "Tỉnh Quảng Nam";
                            }

                            if (nametxt.equals("Thành phố Hồ Chí Minh")){
                                name = "TP Hồ Chí Minh";
                            }
                            tencity.setText(name);
                            //chuyen daytime

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L); // chuyển về dạng mili giây
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy ");
                            String Dayformat = simpleDateFormat.format(date);

                            timehientai.setText(Dayformat);

                            //thoi tiet

                            JSONArray jsonArrayweather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectweather = jsonArrayweather.getJSONObject(0);

                            String status  = jsonObjectweather.getString("main");
                            String iconweather = jsonObjectweather.getString("icon");
                            //Set Icon Weather
                           // Picasso.get().load("http://openweathermap.org/img/wn/"+iconweather+".png").into(iconthoitiet);
                            tinhtrang.setText(status);

                            //tong quan thoi tiet
                            JSONObject jsonObjectmain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectmain.getString("temp");
                            String doam = jsonObjectmain.getString("humidity");
                            String apsuat = jsonObjectmain.getString("pressure");

                            Double nhietdodouble = Double.valueOf(nhietdo);
                            String NhietDoChinh = String.valueOf(nhietdodouble.intValue());

                            txtnhietdo.setText(NhietDoChinh+"°C");
                            txtdoam.setText(doam+"%");
                            txtapsuat.setText(apsuat+"%");

                            //hien thi gió
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String hienthigio = jsonObjectWind.getString("speed");
                            gio.setText(hienthigio+"m/s");

                            //hien thi may
                            JSONObject jsonObjectcloud = jsonObject.getJSONObject("clouds");
                            String hienthimay = jsonObjectcloud.getString("all");
                            phantrammay.setText(hienthimay+"%");

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Weather.this, "akaaa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }

    private void Anhxa(){
        tencity = findViewById(R.id.tencity);
        timehientai = findViewById(R.id.timehientai);
        tinhtrang = findViewById(R.id.tinhtrang);
        txtnhietdo = findViewById(R.id.nhietdo);
        binhminh = findViewById(R.id.binhminh);
        phantrammay = findViewById(R.id.phantrammay);
        gio = findViewById(R.id.gio);
        txtapsuat = findViewById(R.id.apsuat);
        txtdoam = findViewById(R.id.doam);
        cacngaykhac = findViewById(R.id.cacngaykhac);
        backweather = findViewById(R.id.backweather);
        iconthoitiet = findViewById(R.id.iconthoitiet);
    }
}