package com.sktl.sm.soupapp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.sktl.sm.soupapp.R;
import com.sktl.sm.soupapp.databinding.RegistrationAuthorizationActivityBinding;

import static android.R.attr.password;


public class VeryFirstActivity extends AppCompatActivity {

    public ObservableField<String> button1 = new ObservableField<>("GO!");
    public ObservableField<String> editText2Password = new ObservableField<>("NatashaRomanova");
    public ObservableField<String> editText1PersonName = new ObservableField<>("BlackWidow");
    public ObservableField<String> textView4 = new ObservableField<>("а у меня нет учетной записи");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RegistrationAuthorizationActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_authorization_activity);
        binding.setVeryFirstActivityXml(this);//вводим все ObservableField
        // binding.button1.setText("или напрямую вбить текст в кнопку например");


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                только так нужно передовать инфу в активити через интент

                Intent intent = new Intent(VeryFirstActivity.this, MainActivity.class);

//                intent.putExtra(MainActivity.RESULT_OK, 123));
                Log.d("eee", "Ошибочка вышла до");
                startActivity(intent);
                Log.d("eee", "Ошибочка вышла после");
            }
        });

    }

    //для закрытия
    public void onBackPressed() {
        button1.set("или напрямую вбить текст в кнопку например");
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("eee", "onDestroy() был");
    }
}
