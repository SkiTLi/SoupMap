package com.sktl.sm.data.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sktl.sm.data.entity.Pointer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * создает соединение с backendless.com
 * в данном случае стучится до одной таблици "pointersbel"
 */

public class RestService {
    private static final RestService instance = new RestService();

    private RestApi restApi;

    private RestService() {
        init();
    }

    public static RestService getInstance() {
        return instance;
    }

    private void init() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(logging)//не могу создать
                .readTimeout(10, TimeUnit.SECONDS)//если в течении 10 сеунд ответа нет то запрос считать неправильным
                .connectTimeout(10, TimeUnit.SECONDS)//если в течении 10 секнд не можем подключиться к серверу
                .addInterceptor(logging)
                .build();
        //библиотекадля парсинга (походу здесь вообще не нужна)
//        Gson gson = new GsonBuilder().create();

        //создаем ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/90B75A37-6336-2F1A-FFE6-9F696B499100/E7A03A1C-7A25-2DF8-FF15-5F0E5DD37100/")//повторяющаяс часть ссылки
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//говорит что делать через rx
                .addConverterFactory(GsonConverterFactory.create())//как конвертировать данные
                .client(okHttpClient)//устанавливает клиент через который он будет ломиться (через okhttp)
                .build();

        restApi = retrofit.create(RestApi.class); //через это будем дергать все данные
    }


    public Observable<List<Pointer>> getPointersService() {
        return restApi.getPointers();
    }


        //это уже дополнительная накрутка для регистрации
//    public Observable<AccessTokenData> register(RegisterData registerData) {
//
//        //заменить на реальный вызов
//        AccessTokenData accessTokenData = new AccessTokenData();
//        accessTokenData.setAccessToken("dskjfhdskjhfalkjfhdl");
//        return Observable.just(accessTokenData);
//    }


    public Observable<Void> saveProfileService(Pointer pointer) {
        return restApi.savePointer(pointer);
    }
}