package com.sktl.sm.soupapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sktl.sm.domain.entity.PointerDomain;
import com.sktl.sm.domain.interaction.GetPointerListUseCase;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    //  10/10/2017 добавил:
    private GetPointerListUseCase getPointerListUseCase = new GetPointerListUseCase();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




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

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        //(  10/10/2017 добавил

        getPointerListUseCase.execute(null, new DisposableObserver<List<PointerDomain>>() {

            @Override
            public void onNext(List<PointerDomain> pointerDomainList) {
                //костыли(
                LatLng ali = new LatLng(pointerDomainList.get(0).getLatitude(),pointerDomainList.get(0).getLongitude());
                LatLng and = new LatLng(pointerDomainList.get(1).getLatitude(),pointerDomainList.get(1).getLongitude());
                mMap.addMarker(new MarkerOptions().position(ali).title(pointerDomainList.get(0).getDescription()));
                mMap.addMarker(new MarkerOptions().position(and).title(pointerDomainList.get(1).getDescription()));
                //костыли)
            }

            @Override
            public void onError(Throwable e) {
                Log.d("eee", "Ошибочка вышла в getPointerListUseCase.execute() " + e.toString());

            }

            @Override
            public void onComplete() {
                Log.d("eee", "выполнилось getPointerListUseCase.execute() onComplete()");
            }
        });
        //)  10/10/2017 добавил

        // Add a marker in Sydney and move the camera
        LatLng zeroKilometerMinsk = new LatLng(53.902229, 27.561978);
        mMap.addMarker(new MarkerOptions().position(zeroKilometerMinsk).title("zero kilometer of Minsk " + zeroKilometerMinsk.toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zeroKilometerMinsk));
    }


}
