package edu.osu.recipe_app.ui.FindStore;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Toast;

import edu.osu.recipe_app.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.widget.Toast.LENGTH_LONG;

public class FindStoreActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private boolean mPermissionDenied = false;
    private FusedLocationProviderClient mFusedLocationClient;

    private double lat = 0.0;
    private double lng = 0.0;
    private LatLng latLng = new LatLng(lat,lng);
    private LatLng latLng2 = new LatLng(lat,lng);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_store);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        latLng2 = new LatLng(location.getLatitude(),location.getLongitude());
                        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
                        CameraPosition cp = new CameraPosition(latLng2,10.0f,0f,0f);
                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });

        if(!mPermissionDenied && lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    findStore(null);
                }
            }, 4000);   //5 seconds
        }else{
            Toast.makeText(this, "Please Enable Location Services!\n" + "If it is enabled, please check app permissions." , LENGTH_LONG).show();

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        latLng = new LatLng(lat,lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    //On click of button in corner of map, get user's current location and pan camera to location
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, LENGTH_LONG).show();
        lat = location.getLatitude();
        lng = location.getLongitude();
        latLng = new LatLng(lat,lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    public void findStore(View v){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        latLng2 = new LatLng(location.getLatitude(),location.getLongitude());

                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
        //Stringbuilder builds the custom URL based on location and search query information
        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        //append user current location from above
        stringBuilder.append("location="+latLng2.latitude + "," + latLng2.longitude);
        //area to search around user
        stringBuilder.append("&radius="+"5000");
        //google place search key words
        stringBuilder.append("&value="+"supermarket");
        stringBuilder.append("&keyword="+"store");
        //key is google places api key
        stringBuilder.append("&key="+getResources().getString(R.string.google_places_key));
        //added 11/20

//        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 5);
//        mMap.moveCamera(yourLocation);
        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[2];
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;
        //retrieve the locations nearby to user location based on above query
        GetNearbyPlace getNearbyPlace = new GetNearbyPlace();
        getNearbyPlace.execute(dataTransfer);

    }

    //request permission to use google maps and retrieve user locations
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    //Below two methods handle a user denying access to the location services.
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }


}
