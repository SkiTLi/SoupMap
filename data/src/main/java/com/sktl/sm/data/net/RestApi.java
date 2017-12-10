package com.sktl.sm.data.net;


import com.sktl.sm.data.entity.IdUF;
import com.sktl.sm.data.entity.Pointer;
import com.sktl.sm.data.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * окончания длинной идетификационной строки backendless.com
 */


public interface RestApi {

    //возврощает все метки на карте из backendless.com
    //скорее всего возвращает только 10 а не все
    //backendless.com может максимально отдать только 100 записей, а по умолчанию только 10
    //@GET("data/pointersbel)//было , а стало:
    @GET("data/pointersbel?pageSize=100")
    Observable<List<Pointer>> getPointers();

    //в backendless должна появиться новая метка
    @POST("data/pointersbel")
    Observable<Void> savePointer(@Body Pointer pointer);

    //возврощает все метки определенного юзера на карте из backendless.com
    @GET("data/userpointers?pageSize=100")
    Observable<List<String>> getUserPointers(@Body String userId);


    //добавляет точку к определенному юзеру в backendless.com
    @POST("data/userpointers")
    Observable<Void> saveUserPointer(@Body String userId, String pointerId, boolean visible);

    //добавляет юзера к тебе в друзья в backendless.com
    @POST("data/userfriends")
    Observable<Void> saveUserFriend(@Body String userId, String friendId);

    //возврощает всех юзеров из backendless.com
    @GET("data/usersbel?pageSize=100")
    Observable<List<User>> getUsers();

    //171128 возврощает всех юзера из backendless.com по id)
    @GET("data/usersbel?pageSize=100")
//без ?pageSize=100 тоже не работает (выдает только 10)
    Observable<List<User>> getUser(@Query("Where") String idUser);

    //в backendless должна появиться новый пользователь
    @POST("data/usersbel")
    Observable<Void> saveUser(@Body User user);

    //171204 возврощает всех друзей юзера из backendless.com по id
    //плохо работает
//    @GET("data/userfriends?pageSize=100")
//    Observable<List<User>> getUserFriends(@Query("Where") String idUser);
    @GET("data/userfriends?pageSize=100")
    Observable<List<IdUF>> getUserFriends(@Query("Where") String idUser);
}
