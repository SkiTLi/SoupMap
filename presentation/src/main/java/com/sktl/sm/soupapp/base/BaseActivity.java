package com.sktl.sm.soupapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;



abstract public class BaseActivity extends Activity {

//    это вью модель
//    поэтому она должна знать когда активити открыта/закрыта когда онпауз когда онрезюм




    protected BaseViewModel viewModelInBaseActivity; //инициилизируется в классе-аследнике

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewModelInBaseActivity.init();
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModelInBaseActivity.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModelInBaseActivity.pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModelInBaseActivity.release();
    }
}
