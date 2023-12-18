package com.egeetouch.egeetouch_manager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes.dex */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener {
    double latitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    double longitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private GoogleMap map;
    MapView mapView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_map);
        getWindow().setFlags(1024, 1024);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.latitude = extras.getDouble("latitude");
            this.longitude = extras.getDouble("longitude");
        }
        System.out.println("CMDCMD map longitude: " + this.longitude + " latitude: " + this.latitude);
        ((MapFragment) getFragmentManager().findFragmentById(R.id.mapview)).getMapAsync(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(MainActivity.context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(MainActivity.context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.map.setMyLocationEnabled(true);
            try {
                MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, new OnMapsSdkInitializedCallback() { // from class: com.egeetouch.egeetouch_manager.MapActivity.1
                    @Override // com.google.android.gms.maps.OnMapsSdkInitializedCallback
                    public void onMapsSdkInitialized(MapsInitializer.Renderer renderer) {
                        MapActivity.this.print("CMDCMD Map Initialized");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            LatLng latLng = new LatLng(this.latitude, this.longitude);
            this.map.addMarker(new MarkerOptions().position(latLng).title("Access Location"));
            this.map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            this.map.setOnCameraMoveListener(this);
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
    public void onCameraMove() {
        System.out.println("CMDCMD MAP MOVIN");
    }

    public void print(String str) {
        System.out.println(str);
    }
}
