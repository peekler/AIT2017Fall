package hu.ait.android.locationdemo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Peter on 2017. 11. 30..
 */

public class AITLocationManager implements LocationListener {

    public interface NewLocationListener {
        public void onNewLocation(Location location);
    }

    private Context context;
    private NewLocationListener newLocationListener;
    private LocationManager locationManager;

    public AITLocationManager(Context context, NewLocationListener newLocationListener)
    {
        this.context = context;
        this.newLocationListener = newLocationListener;
    }


    public void startLocationMonitoring() throws SecurityException {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, this);

        // DO NOT USE IT ON EMULATOR
        //locationManager.requestLocationUpdates(
        //        LocationManager.NETWORK_PROVIDER, 0, 0, this);

    }

    public void stopLocationMonitoring() {
        locationManager.removeUpdates(this);
    }



    @Override
    public void onLocationChanged(Location location) {
        newLocationListener.onNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
