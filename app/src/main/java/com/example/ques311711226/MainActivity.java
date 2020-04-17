/*
                                                 CA 3 Assignment

                                                Roll No       =  57
                                                Ques          =  57 % 6 = 3
                                                Name          =  Priyansh Tiwari
                                                Section       =  KM002
                                                Submitted to  =  Amarinder Kaur Mam (21482)



Q3: Create an android app using Place Picker and show the name of user's current place. Avoid directly copying the code from any student/website.. Solution must be unique for each student

SOL : Since Place Picker is deprecated and hence can't be used anymore , I have used the new Google Api called play services location 12.0.0

Here I have used the Play Services Location dependency to create a fused location client which gives the current address,country,state,latitude and longitude.
The app also asks for permission ACCESS_FINE_LOCATION.


                ANY KIND OF LOCATION DOES NOT WORK IN EMULATOR KINDLY USE USB DEBUGGING FOR TESTING THE APP. 100% working.


THANK YOU.
 */





package com.example.ques311711226;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button locbtn;
    TextView tv1,tv2,tv3,tv4,tv5;
    FusedLocationProviderClient flpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locbtn = (Button)findViewById(R.id.locbtn);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);

        flpc = LocationServices.getFusedLocationProviderClient(this);
        locbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    getLocation();

                }else
                {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},44);
                    Toast.makeText(getApplicationContext(),"Click on the Show Location Button Again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void getLocation()
    {
        flpc.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if(location!=null)
                {
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        tv1.setText(Html.fromHtml("<font color = '#be3164'><b>Latitude : </b><br></font>"+addresses.get(0).getLatitude()));
                        tv2.setText(Html.fromHtml("<font color = '#be3164'><b>Longitude : </b><br></font>"+addresses.get(0).getLongitude()));
                        tv3.setText(Html.fromHtml("<font color = '#be3164'><b>Country : </b><br></font>"+addresses.get(0).getCountryName()));
                        tv4.setText(Html.fromHtml("<font color = '#be3164'><b>State : </b><br></font>"+addresses.get(0).getAdminArea()));
                        tv5.setText(Html.fromHtml("<font color = '#be3164'><b>Address : </b><br></font>"+addresses.get(0).getAddressLine(0)));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }
}
