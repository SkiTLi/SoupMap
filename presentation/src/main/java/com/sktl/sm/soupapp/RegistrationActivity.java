


package com.sktl.sm.soupapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.backendless.*;
import com.backendless.Messaging;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.sktl.sm.soupapp.databinding.RegistrationActivityBinding;

import java.util.ArrayList;
import java.util.List;


public class RegistrationActivity extends AppCompatActivity {


    public static final String USERNAME_IN_REGISTRATON = "////RESULT////";
    public static final String PASSWORD_IN_REGISTRATON = "////GROGER////";


    public ObservableField<String> button1 = new ObservableField<>("Register Me");
    public ObservableField<String> editText1PersonName = new ObservableField<>("SteveRogers");
    public ObservableField<String> editText2Email = new ObservableField<>("kotinsandy@gmail.com");
    public ObservableField<String> editText3Password = new ObservableField<>("CapitanAmerica");
    public ObservableField<String> editText4ConfirmPassword = new ObservableField<>("CapitanAmerica");
    public ObservableField<String> editText5AdditionalInfo = new ObservableField<>("да все что угодно");
    public ObservableField<String> editText6UrlImage = new ObservableField<>("https://www.tec.com.pe/wp-content/uploads/2015/02/marvel-13.jpg");
    public static final String APP_ID = "90B75A37-6336-2F1A-FFE6-9F696B499100";
    public static final String SECRET_KEY = "E7A03A1C-7A25-2DF8-FF15-5F0E5DD37100";
    //    public static final String SECRET_KEY = "050C5E2F-C686-0D92-FF6E-113958A8C700";
    public static final String VERSION = "v1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RegistrationActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.registration_activity);
//        binding.setVeryFirstActivityXml(this);//вводим все ObservableField
        binding.setRegistrationActivityXml(this); //вводим все ObservableField
        // binding.button1.setText("или напрямую вбить текст в кнопку например");


//todo
        //error sending email - Version is disabled or provided wrong application info (application id or secret key)
        //проинициализируем бэкэндлесс
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              передает инфу в мэйнактивити( через интент)
                //поместили в поле введенный editText1PersonName текст в активити
//                editText1PersonName.set(binding.editText1.getText().toString());
//                editText2Password.set(binding.editText2.getText().toString());
//                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                /* Создаем интент с экшеном на отправку */
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//это не то, но оно работает(
        /* Заполняем данными: тип текста, адрес, сабж и собственно текст письма */
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{""});
//                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Тема: СупАпп");
//                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "текст письма (который не должен быть виден до отправки)");

        /* Отправляем на выбор!*/
//                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//работает, но это не то (т.к. требует выбрать пользователю приложение отправки)


                AsyncCallback<Void> responder = new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Log.d("eee", "[ASYNC] email has been sent");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.d("eee", "error sending email - " + fault.getMessage());
                    }
                };

// async request. Plain text message to one recipient
                Backendless.Messaging.sendTextEmail("SoupApp", "123",
                        "kotinsandy@gmail.com", responder);

// sync request. HTML messahe to multiple recipients EXAMPLE:
//                ArrayList<String> recipients = new ArrayList<String>();
//                recipients.add( "kotinsandy@gmail.com" );
//                recipients.add( "pracuem@gmail.com" );//
//                String mailBody = "Guys, the dinner last night was <b>awesome</b>";//
//                Backendless.Messaging.sendHTMLEmail( "SoupApp", mailBody, recipients );//
//                Log.d("eee", "[SYNC] email has been sent");
//                intent.putExtra(MainActivity.USERNAME_IN_MAIN, editText1PersonName.get());
//                intent.putExtra(MainActivity.PASSWORD_IN_MAIN, editText2Password.get());

                Log.d("eee", " до startActivity(intent);");
//                startActivity(intent);
                Log.d("eee", " после startActivity(intent); Backendless.getVersion() = " + Backendless.getVersion());
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
