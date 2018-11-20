package pe.ibao.agromovil.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

public class GPSService extends Service  implements LocationListener{

    private Context ctx;
    private Double latitud;
    private Double longitud;
    Location location;
    boolean status;
    TextView text;

    public GPSService(){
        super();
        this.ctx=this.getApplicationContext();
    }

    public GPSService(Context ctx) {
        super();
        this.ctx=ctx;
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {

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
