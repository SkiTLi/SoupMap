package com.sktl.sm.soupapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


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
    //здесь все инициализируется единожды при старте приложения
    //глобальный контекст
    @Override
    public void onCreate() {
        super.onCreate();



        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...


    }
}



