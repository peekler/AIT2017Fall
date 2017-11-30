package hu.ait.android.locationdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AITLocationManager.NewLocationListener {

    private TextView tvLocData;
    private AITLocationManager aitLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLocData = findViewById(R.id.tvLocData);
        aitLocationManager = new AITLocationManager(this, this);

        requestNeededPermission();
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
