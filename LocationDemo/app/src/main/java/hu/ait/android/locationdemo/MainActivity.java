package hu.ait.android.locationdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AITLocationManager.NewLocationListener {

    private TextView tvLocData;
    private AITLocationManager aitLocationManager;

    private Location lastLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLocData = findViewById(R.id.tvLocData);
        aitLocationManager = new AITLocationManager(this, this);

        requestNeededPermission();

        tvLocData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastLocation != null) {
                    try {
                        double latitude = lastLocation.getLatitude();
                        double longitude = lastLocation.getLongitude();
                        Geocoder gc = new Geocoder(MainActivity.this,
                                Locale.getDefault());
                        List<Address> addrs = null;
                        addrs = gc.getFromLocation(latitude, longitude, 10);

                        String addr = addrs.get(0).getAddressLine(0) + "\n" +
                                addrs.get(0).getAddressLine(1) + "\n" +
                                addrs.get(0).getAddressLine(2) + "\n" +
                                addrs.get(0).getAddressLine(3);
                        Toast.makeText(MainActivity.this, addr, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Toast...
            }

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        } else {
            // start our job
            aitLocationManager.startLocationMonitoring();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Permission granted, jupeee!", Toast.LENGTH_SHORT).show();

                // start our job
                aitLocationManager.startLocationMonitoring();
            } else {
                Toast.makeText(this, "Permission not granted :(", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onNewLocation(Location location) {
        lastLocation = location;

        StringBuilder sb = new StringBuilder();
        sb.append("Provider: "+location.getProvider()+"\n");
        sb.append("Latitude: "+location.getLatitude()+"\n");
        sb.append("Longitude: "+location.getLongitude()+"\n");
        sb.append("Accuracy: "+location.getAccuracy()+"\n");
        sb.append("Altitude: "+location.getAltitude()+"\n");
        sb.append("Speed: "+location.getSpeed()+"\n");

        tvLocData.setText(sb.toString());
    }
}
