package com.sktl.sm.soupapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sktl.sm.domain.entity.PointerDomain;
import com.sktl.sm.domain.entity.UserDomain;
import com.sktl.sm.domain.interaction.GetPointerListUseCase;
import com.sktl.sm.domain.interaction.GetUserUseCase;
import com.sktl.sm.domain.interaction.GetUsersListUseCase;
import com.sktl.sm.domain.interaction.SavePointerUseCase;
import com.sktl.sm.domain.interaction.SaveUserPointerUseCase;
import com.sktl.sm.domain.interaction.SaveUserUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static java.security.AccessController.getContext;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    //  10/10/2017 добавил:
    private GetPointerListUseCase getPointerListUseCase = new GetPointerListUseCase();
    //(14/10/2017 добавил
    private SavePointerUseCase savePointerUseCase = new SavePointerUseCase();
    private PointerDomain pointerDomain;
    //)14/10/2017 добавил
    //(15/10/2017 добавил
    private SaveUserPointerUseCase saveUserPointerUseCase = new SaveUserPointerUseCase();
    private SaveUserUseCase saveUserUseCase = new SaveUserUseCase();
    private UserDomain userDomain;
    private GetUserUseCase getUserUseCase = new GetUserUseCase();
    private GetUsersListUseCase getUsersListUseCase = new GetUsersListUseCase();

    //)15/10/2017 добавил
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


        //(14/10/2017 добавил

        //метод возвращает объект Координаты по длинному нажатию на карту
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Toast.makeText(getContext(), "Your point has been added", Toast.LENGTH_LONG).show();


                mMap.addMarker(new MarkerOptions().position(latLng).title("u create: " + latLng.toString()));
                pointerDomain = new PointerDomain();
                pointerDomain.setIdPointer(String.valueOf((int) (Math.random() * 10000)));//заглушка
                pointerDomain.setName("Point №" + pointerDomain.getIdPointer());//заглушка
                pointerDomain.setLongitude(latLng.longitude);
                pointerDomain.setLatitude(latLng.latitude);
                pointerDomain.setDescription(pointerDomain.getName() + " " + latLng.toString());//заглушка
                pointerDomain.setIsVisible(true);//заглушка
                pointerDomain.setCreatedByUserDomain(new UserDomain());//заглушка
                pointerDomain.setImage("https://images-na.ssl-images-amazon.com/images/I/41-Sd-PIEfL.jpg");//заглушка


                //(15/10/2017 добавил
                userDomain = new UserDomain();

                userDomain.setIdUser(String.valueOf((int) (Math.random() * 10000)));
                userDomain.setUsername("tonny №" + userDomain.getIdUser());
                userDomain.setPassword("stark");
                userDomain.setEmail("iron_man@gmail.com");
                userDomain.setImageUser("http://www.freepngimg.com/download/iron_man/1-2-iron-man-picture.png");
                userDomain.setAdditionalInformation("super hero");
//TODO вот тут какая-то ошибка NUllPointerException:
                //сохранили пользователя
                saveUserUseCase.execute(userDomain, new DisposableObserver<Void>() {
                    @Override
                    public void onNext(@NonNull Void aVoid) {
                        Log.d("eee", "выполняется  saveUserUseCase.execute() onNext()");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("eee", "Ошибочка вышла в saveUserUseCase.execute() " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("eee", "выполнилось saveUserUseCase.execute() onComplete()");

                    }
                });
                //)15/10/2017 добавил


                //сохраняет точку на карте
                if (pointerDomain != null) {
                    savePointerUseCase.execute(pointerDomain, new DisposableObserver<Void>() {
                        @Override
                        public void onNext(@NonNull Void aVoid) {
                            Log.d("eee", "выполняется  savePointerUseCase.execute() onNext()");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("eee", "Ошибочка вышла в savePointerUseCase.execute() " + e.toString());
                        }

                        //в онкомплите может возвращать несколько значений
                        @Override
                        public void onComplete() {
                            Log.d("eee", "выполнилось savePointerUseCase.execute() onComplete()");
                        }
                    });

                    //(15/10/2017 добавил
                    Log.d("eee", " сейчас будет getUsersListUseCase.execute .... и :");

                    getUsersListUseCase.execute(null, new DisposableObserver<List<UserDomain>>() {
                        @Override
                        //userDomains по умолчанию состоит из 10-ти элементов
                        public void onNext(@NonNull List<UserDomain> userDomains) {
                            ArrayList<String> arrayList = new ArrayList<>();
                            Log.d("eee", "количество элементов в List<UserDomain> =" + userDomains.size());


                            for (UserDomain userDom : userDomains) {
                                arrayList.add(userDom.getIdUser());

                            }

                            //проверка на количество элементов
                            int ch = 0;
                            for (String list : arrayList) {
                                ch++;
                            }
                            Log.d("eee", "количество элементов в ArrayList<String> arrayList =" + ch);
                            //распечатаем все элементы arrayList
                            Log.d("eee", "arrayList :" + arrayList.toString());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("eee", "Ошибочка вышла в getUsersListUseCase.execute() " + e.toString());

                        }

                        @Override
                        public void onComplete() {
                            Log.d("eee", "выполнилось getUsersListUseCase.execute() onComplete()");
                        }
                    });


                    Log.d("eee", " сейчас будет getUserUseCase.execute .... и :");

                    getUserUseCase.execute("333", new DisposableObserver<UserDomain>() {
                        @Override
                        public void onNext(@NonNull UserDomain userDomain) {
                            Log.d("eee", "выполняется  getUserUseCase.execute() onNext()");

                            Log.d("eee", "userDomain.getUsername() = " + userDomain.getUsername());
                            Log.d("eee", "userDomain.getIdUser() = " + userDomain.getIdUser());

                            if (userDomain.getIdUser() == null) {// если ID равно null
                                Toast.makeText(getContext(), "non-existent user", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.d("eee", "Ошибочка вышла в getUserUseCase.execute() " + e.toString());
                        }

                        @Override
                        public void onComplete() {
                            Log.d("eee", "выполнилось getUserUseCase.execute() onComplete()");
                        }
                    });


//                    saveUserPointerUseCase.execute(pointerDomain, new DisposableObserver<Void>() {
//                        @Override
//                        public void onNext(@NonNull Void aVoid) {
//                            Log.d("eee", "выполняется  saveUserPointerUseCase.execute() onNext()");
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            Log.d("eee", "Ошибочка вышла в saveUserPointerUseCase.execute() " + e.toString());
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Log.d("eee", "выполнилось saveUserPointerUseCase.execute() onComplete()");
//                        }
//                    });
                    //)15/10/2017 добавил
                }
            }
        });
        //)14/10/2017 добавил


        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // возвращает список всех точек
        // но по факту только 10 а не все
        getPointerListUseCase.execute(null, new DisposableObserver<List<PointerDomain>>() {
            @Override
            public void onNext(List<PointerDomain> pointerDomainList) {
                //показывает каждую точку из списка на карте
                for (PointerDomain pointerDom : pointerDomainList) {
                    LatLng point = new LatLng(pointerDom.getLatitude(), pointerDom.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(point).title(pointerDom.getDescription()));
                    Log.d("eee", "добавлятся точечки в getPointerListUseCase.execute() onNext()");
                }
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


        // Add a marker in Sydney and move the camera
        LatLng zeroKilometerMinsk = new LatLng(53.902229, 27.561978);
        mMap.addMarker(new MarkerOptions().position(zeroKilometerMinsk).title("zero kilometer of Minsk " + zeroKilometerMinsk.toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zeroKilometerMinsk));
    }


}
