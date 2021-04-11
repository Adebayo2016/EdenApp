package com.eden;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.eden.SelectionAlgo.Result;
import com.eden.WeatherUpdate.WeatherService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class HomePage extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    private static final int REQUEST_LOCATION = 1;
    TextView user_text, day_time, temperature, humidity, pressure, windspeed;

    LocationManager locationManager;

    public static String BaseUrl2 = "https://api.openweathermap.org/data/2.5/weather?q=Nigeria&APPID=79493d475e03c13e2699b093e7345847";
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "79493d475e03c13e2699b093e7345847";
    public double latitude;
    public double longitude;
    public int roundedValue;
    public String windS;
    public String hum;
    public String temp_final;
    public String press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        temperature = findViewById(R.id.temperature_edit);
        humidity = findViewById(R.id.humidity_edit);
        pressure = findViewById(R.id.rainfall_edit);
        windspeed = findViewById(R.id.windSpeed_edit);
        user_text = findViewById(R.id.welcome_text);
        day_time = findViewById(R.id.day_time);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //OnGPS();
        } else {
            getCurrentLocation();
        }


        getCurrentLocation();

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getCurrentUserName();


    }

    private void getCurrentUserName() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name = snapshot.child("full_name").getValue().toString();
                if (name != null) {
                    user_text.setText("Hello " + name);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


    }

    //to be used in next Update

   /* private void OnGPS() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    } */


    private void getCurrentLocation() {

        //to be used for next Update

        if (ActivityCompat.checkSelfPermission(
                HomePage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                HomePage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String flat = Double.toString(lat);
                String lng = Double.toString(longi);
                Toast.makeText(this, lng, Toast.LENGTH_SHORT).show();

                // showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

// perform weather update

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(BaseUrl2).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String myResponse = response.body().string();

                HomePage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(myResponse);

                            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
                            roundedValue = (int) Math.rint(tempResult);
                            windS = jsonObject.getJSONObject("wind").getString("speed");
                            hum = jsonObject.getJSONObject("main").getString("humidity");
                            temp_final = Integer.toString(roundedValue);
                            press = jsonObject.getJSONObject("main").getString("pressure");
                            UpdateScreen();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }

    //update Weather result to Screen

    public void UpdateScreen() {
        temperature.setText(temp_final + "°C");
        windspeed.setText(windS + " m/s");
        humidity.setText(hum + " g/m\u00B2");
        pressure.setText(press + " N/m");


    }


    public void BestTree(View view) {

        Intent TreeIntent = new Intent(this,StateActivity.class);
        startActivity(TreeIntent);
    }


    //gets the value of our File
    public void PolyView(View view) {

        Intent intent2 = new Intent(this, OrnamentalCropView.class);
        intent2.putExtra("send", "polyalthia.pdf");
        startActivityForResult(intent2, 0);
    }

    public void CassiaView(View view) {

        Intent intent2 = new Intent(this, OrnamentalCropView.class);
        intent2.putExtra("send", "cassia.pdf");
        startActivityForResult(intent2, 0);
    }

    public void TreeDetail(View view) {
        Toast.makeText(this, "Detail Coming Soon", Toast.LENGTH_SHORT).show();
    }

    public void GotoAll(View view) {
        Intent intentAll = new Intent(this, OrnamentalTreeView.class);
        startActivity(intentAll);

    }

    public void GotoForest(View view) {
        Intent intentAll = new Intent(this, ForestTreeView.class);
        startActivity(intentAll);
    }
}



