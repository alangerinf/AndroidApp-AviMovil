package pe.ibao.agromovil.services;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class GPSService extends IntentService implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
        ,LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;

    private static String TAG ="GPS TAG";

    public GPSService(String name) {
        super(name);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create ()
                .setPriority (LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval (2 * 1000) // 10 segundos, en milisegundos
                .setFastestInterval (1 * 1000); // 1 segundo, en milisegundos

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Log.i(TAG, "Location services connected.");

            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                Log.i(TAG, "locacion nula al conectar");
                LocationServices.FusedLocationApi.requestLocationUpdates (mGoogleApiClient, mLocationRequest, this);
            }
            else {
                handleNewLocation (location);
            }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution ()) {
            /*
            try {
                // Inicie una actividad que intente resolver el error
                connectionResult.startResolutionForResult (this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace ();
            }
            */
        } else{

            Log.i (TAG, "La conexión de los servicios de ubicación falló con el código" + connectionResult.getErrorCode ());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation (location);
    }

    double currentLatitude=0.0d;
    double currentLongitude=0.0d;

    private void handleNewLocation(Location location) {
        Log.d(TAG,"hola " + location.toString());
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);
        Log.d(TAG,latLng.toString());
    }
}