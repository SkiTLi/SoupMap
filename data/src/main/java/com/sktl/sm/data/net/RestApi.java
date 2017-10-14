package com.sktl.sm.data.net;


import com.sktl.sm.data.entity.Pointer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * окончания длинной идетификационной строки backendless.com
 *
 */


public interface RestApi {
    //возврощает все метки на карте изи backendless.com
    @GET("data/pointersbel")
    Observable<List<Pointer>> getPointers();

    //в backendless должна появиться новая метка
    @POST("data/pointersbel")
    Observable<Void> savePointer(@Body Pointer pointer);
}
