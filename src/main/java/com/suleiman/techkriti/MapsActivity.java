package com.suleiman.techkriti;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.suleiman.techkriti.activities.ConnectionDetector;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    Spinner staticSpinner;
    MarkerOptions markerOptions;
    LatLng latLng;
    String item="";
    String items="";
    Context context=this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc
        );
       /* LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.abc,null);*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        staticSpinner = (Spinner) findViewById(R.id.spinner);




        String[] itemL = new String[] { "IIT Kanpur","VISITORS HOSTEL", "NEW SAC", "LHC" ,"HALL 3","OUTREACH AUDI","AIR STRIP","HALL 5","HALL 2","HALL 4","GH","MAIN AUDI","HALL 1","OAT","CCD","FOOTBALL GROUND","FESTIVAL GROUND"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,itemL);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(dataAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();


                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

                Boolean isInternetPresent = cd.isConnectingToInternet();

                if ((isInternetPresent == false) || (item.compareTo("MAIN AUDI") == 0) || (item.compareTo("HALL 3") == 0) || (item.compareTo("HALL 2") == 0) || (item.compareTo("HALL 4") == 0) || (item.compareTo("HALL 5") == 0) || (item.compareTo("HALL 1") == 0) || (item.compareTo("HALL 10") ==0)||(item.compareTo("CCD") ==0)||(item.compareTo("OAT") ==0)||(item.compareTo("FOOTBALL GROUND") ==0)||(item.compareTo("FESTIVAL GROUND") ==0)) {
                    me(item);
                } else {
                    if (item.compareTo("IIT Kanpur") != 0) {
                        items = item + " IIT Kanpur";
                    } else
                        items = "IIT Kanpur";
                    new GeocoderTask().execute(items);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mapFragment.getMapAsync(this);
       /* staticSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();

                new GeocoderTask().execute(item);
            }
        });*/
    }
    public void me(String item)
    {double lat=0;double lon=0;
        if(item.compareTo("IIT Kanpur")==0)
        {
            lat=26.512333;lon=80.232667;
        }


            else if (item.compareTo("VISITORS HOSTEL")==0)
        {
            lat=26.507401;lon=80.234493;

        }
        else if (item.compareTo("MAIN AUDI")==0)
        {
            lat=26.513115;lon=80.235905;

        }
        else if (item.compareTo("HALL 5")==0)
        {
            lat=26.509640;lon=80.227968;

        }
        else if (item.compareTo("NEW SAC")==0){
            lat=26.505219;lon=80.229454;

        }
        else if (item.compareTo("CCD")==0){
            lat=26.511944;lon=80.234291;

        }
        else if (item.compareTo("OAT")==0){
            lat=26.505219;lon=80.229454;

        }

        else if (item.compareTo("HALL 3")==0){
lat=26.508784;lon=80.229762;
        }
        else if (item.compareTo("HALL 4")==0){
            lat=26.507102;lon=80.232357;

        }
        else if (item.compareTo("HALL 2")==0){
            lat=26.510821;lon=80.229900;

        }
        else if (item.compareTo("FOOTBALL GROUND")==0){
            lat=26.505959;lon=80.229474 ;

        }
        else if (item.compareTo("HALL 1")==0){
            lat=26.509552;lon=80.231697;

        }
        else if (item.compareTo("AIR STRIP")==0){
lat=26.520274;lon=80.232537;
        }
        else if (item.compareTo("LHC")==0){
lat=26.511431;lon=80.233280;
        }
        else if (item.compareTo("OUTREACH AUDI")==0){
            lat=26.509291;lon=80.234607;

        }
        else if (item.compareTo("GH")==0){
            lat=26.505925;lon=80.233907;

        }
        else if (item.compareTo("FESTIVAL GROUND")==0)
{           lat=26.503594;lon=80.2284336;

        }
        latLng = new LatLng(lat,lon);
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(lat,
                        lon));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(17);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        String addressText =item;

        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(addressText);

        mMap.addMarker(markerOptions);








    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(26.512333, 80.232667);
        mMap.addMarker(new MarkerOptions().position(sydney).title("IIT Kanpur"));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(26.512333, 80.232667));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(17);


        mMap.moveCamera(center);

        mMap.animateCamera(zoom);

        // Add a marker in Sydney and move the camera
    /*    LatLng sydney = new LatLng(26.5114, 80.2349);
        mMap.addMarker(new MarkerOptions().position(sydney).title("IIT Kanpur"));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(26.5114, 80.2349)).zoom(15).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
     /*  Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(26.5114, 80.2349))
                .radius(250011)
                .strokeColor(R.color.cyan)
                .fillColor(R.color.white));
     /   circle.setVisible(true);*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

    }

    class GeocoderTask extends AsyncTask<String, Void, List<Address>> {
        @Override
        protected List<Address> doInBackground(String... locationName) {
            Geocoder geocoder = new Geocoder(getBaseContext());

                List<Address> addresses = null;

                try {

                    addresses = geocoder.getFromLocationName(locationName[0]
                            , 3);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return addresses;
            }



        @Override
        protected void onPostExecute(List<Address> addresses) {



            if(addresses==null || addresses.size()==0){


            }

else
            {

                mMap.clear();
                Address address =  addresses.get(0);
double lat=address.getLatitude();
                double lon=address.getLongitude();

                latLng = new LatLng(address.getLatitude(), address.getLongitude());
                CameraUpdate center=
                        CameraUpdateFactory.newLatLng(new LatLng(lat,
                                lon));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(17);

                mMap.moveCamera(center);
                mMap.animateCamera(zoom);

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(addressText);

                mMap.addMarker(markerOptions);


        }}
    }


}
