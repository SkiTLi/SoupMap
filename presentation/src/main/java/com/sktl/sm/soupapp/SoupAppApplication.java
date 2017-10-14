package com.sktl.sm.soupapp;

import android.app.Application;


//import com.example.user_pc.testsktl.di.AppComponent;
//import com.example.user_pc.testsktl.di.AppModule;
//import com.example.user_pc.testsktl.di.DaggerAppComponent;
//import com.squareup.leakcanary.LeakCanary;
//
//import io.realm.Realm;


/**
 * Created by USER-PC on 31.07.2017.
 */

public class SoupAppApplication extends Application {

//public static AppComponent appComponent;


    //здесь все инициализируется единожды при старте приложения
    //глобальный контеккст
    @Override
    public void onCreate() {
        super.onCreate();



//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...

        //инициализируем реалэм
//        Realm.init(this);

        //nr это здесь то  и находится все это будет в слое presentation

//        appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
    }
}



