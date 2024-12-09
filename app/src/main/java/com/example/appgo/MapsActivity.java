package com.example.appgo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvLocalizacion;
    private Button btnVerUbicaciones;
    private static final int PERMISSION_REQUEST_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Inicializar vistas
        tvLocalizacion = findViewById(R.id.tvLocalizacion);
        btnVerUbicaciones = findViewById(R.id.bLocalizacion);

        // Configurar el botón de ver ubicaciones
        btnVerUbicaciones.setOnClickListener(view -> {
            // Aquí puedes agregar el código para manejar el clic
            // Ejemplo: Abrir nueva actividad que muestre ubicaciones guardadas
            Toast.makeText(MapsActivity.this, "Ver Ubicaciones", Toast.LENGTH_SHORT).show();
        });

        // Configurar el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Configurar el tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Configurar los controles de la cámara
        UiSettings settings = mMap.getUiSettings();
        settings.setCompassEnabled(true);
        settings.setZoomControlsEnabled(true);

        // Obtener ubicación actual y mover la cámara
        LatLng currentLocation = new LatLng(19.4326, -99.1332); // Ejemplo: Ciudad de México
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

        // Agregar marcador en la ubicación actual
        Marker marker = mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicación Actual"));

        // Si deseas mostrar la ubicación en el TextView
        tvLocalizacion.setText("Lat: " + currentLocation.latitude + ", Long: " + currentLocation.longitude);
    }

    // Verificar permisos para la ubicación
    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        } else {
            // Si ya tenemos permisos, obtener la ubicación
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationPermission();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Prueba, Justin", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Prueba, Ari", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Prueba, yisus", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
