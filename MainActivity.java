ACtivity_maps.xml
 
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android" 
android:layout_width="match_parent"
 android:orientation="vertical" android:layout_height="400dp">
 <LinearLayout
 android:layout_width="wrap_content"

 android:layout_height="wrap_content"
 android:orientation="horizontal"
 android:id="@+id/ll1">
 <EditText
 android:id="@+id/et1"
 android:layout_width="196dp"
 android:layout_height="wrap_content" />
 <Button
 android:id="@+id/searchbut"
 android:layout_width="98dp"
 android:layout_height="wrap_content"
 android:onClick="onSearch"
 android:text="Search" />
 <Button
 android:id="@+id/typebut"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:onClick="onType"
 android:text="Type" />
 </LinearLayout>
 <LinearLayout
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:orientation="horizontal"
 android:layout_below="@id/ll1"
 android:id="@+id/linearLayout"
 android:layout_alignParentBottom="true">
 <fragment xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:tools="http://schemas.android.com/tools"
 android:id="@+id/map"
 android:layout_below="@id/ll1"
 android:name="com.google.android.gms.maps.SupportMapFragment"
 android:layout_width="343dp"
 android:layout_height="match_parent"
 tools:context="com.example.mohan.demomaps.MapsActivity" />
 <LinearLayout
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:orientation="vertical">
 <Button
 android:id="@+id/zoomin"
 android:layout_width="wrap_content"
 android:layout_height="69dp"
 android:onClick="onZoom"
 android:text="+" />
 <Button
 android:id="@+id/zoomout"
 android:layout_width="wrap_content"
 android:layout_height="68dp"
 android:onClick="onZoom"
 android:text="-" />
 </LinearLayout>
 </LinearLayout>
</RelativeLayout>


 
package com.example.program8;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.program8.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
 private GoogleMap mMap;
 private TextView tv;
 private ActivityMapsBinding binding;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 
 binding = ActivityMapsBinding.inflate(getLayoutInflater());
 setContentView(binding.getRoot());
 

 SupportMapFragment mapFragment = (SupportMapFragment) 
getSupportFragmentManager().findFragmentById(R.id.map);
 mapFragment.getMapAsync(this);
 }


 @Override
 public void onMapReady(GoogleMap googleMap) {
 mMap = googleMap;

 LatLng Bengaluru = new LatLng(13, 78);
 mMap.addMarker(new MarkerOptions().position(Bengaluru).title("Marker in Bengaluru"));
 mMap.moveCamera(CameraUpdateFactory.newLatLng(Bengaluru));
 }
 
 public void setmMap(GoogleMap mMap) {
 this.mMap = mMap;
 }
 
 public void onSearch(View view) {
 List<Address> addressList = null;
 EditText et_location = (EditText) findViewById(R.id.et1);
 String location = et_location.getText().toString();
 if (location != null || location.equals("")) {
 Geocoder geocoder = new Geocoder(this);
 
 try {
 addressList = geocoder.getFromLocationName(location, 1);
 } catch (IOException e) {
 e.printStackTrace();
 }
 
 Address address = addressList.get(0);
 LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
 mMap.addMarker(new MarkerOptions().position(latLng).title(location));
 mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
 }
 }
 
 public void onType(View view) {
 if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
 mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
 } else {
 mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
 }
 }
 
 public void onZoom(View view) {
 if (view.getId() == R.id.zoomin) {
 mMap.animateCamera(CameraUpdateFactory.zoomIn());
 }
 if (view.getId() == R.id.zoomout) {
 mMap.animateCamera(CameraUpdateFactory.zoomOut());
 }
 }
}


