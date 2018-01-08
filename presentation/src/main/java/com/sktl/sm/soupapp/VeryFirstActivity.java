
// 17/11/24 нужно переделать GetUserByIdUseCase чтобы он заработал (сделать возврат пользователя по id )
// 17/11/29 нужно запустить GetUserByIdUseCase в основном активити (походу он готов)
// 17/11/30 нужно сделать передачу логина и пароля из VeryFirstActivity
// ... в MainActivity по нажатию кнопки //17/12/03 сделано
// 17/12/03 нужно походу писать GetUserByNameUseCase или GetUserIdByNameUseCase
// ... для идентификации пользователя
// 17/12/04 нужно подготовить GetListFriendsOfUserUseCase (сейчас он не работает)//17/12/10 сделано
// 17/12/10 нужно сделать активити регистрации пользователя (и чтобы пользователь добавлялся в backendless)
// 17/12/19 нужно переписать RegistrationActivity чтобы оно отсылало через
// ... backendless (его я уже настроил) автоматический отсыл сообщений (есть скриншоты и закладки)
// 17/12/27

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

import static android.R.attr.fragment;
import static android.R.attr.password;


public class VeryFirstActivity extends AppCompatActivity {

    public ObservableField<String> button1 = new ObservableField<>("GO!");
    public ObservableField<String> editText1PersonName = new ObservableField<>("NatashaRomanova");
    public ObservableField<String> editText2Password = new ObservableField<>("BlackWidow");
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
//              передает инфу в мэйнактивити( через интент)
                //поместили в поле введенный editText1PersonName текст в активити
                editText1PersonName.set(binding.editText1.getText().toString());
                editText2Password.set(binding.editText2.getText().toString());
                Intent intent = new Intent(VeryFirstActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.USERNAME_IN_MAIN, editText1PersonName.get());
                intent.putExtra(MainActivity.PASSWORD_IN_MAIN, editText2Password.get());

                Log.d("eee", " до startActivity(intent);");
                startActivity(intent);
                Log.d("eee", " после startActivity(intent);");
            }
        });

//просто меняет местами два введенных поля (потом убрать)
        binding.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              передает инфу в мэйнактивити( через интент)
                //поместили в поле введенный editText1PersonName текст в активити
                editText1PersonName.set(binding.editText2.getText().toString());
                editText2Password.set(binding.editText1.getText().toString());
                Intent intent = new Intent(VeryFirstActivity.this, RegistrationActivity.class);
//                intent.putExtra(RegistrationActivity.USERNAME_IN_REGISTRATION, editText1PersonName.get());
//                intent.putExtra(RegistrationActivity.PASSWORD_IN_REGISTRATION, editText2Password.get());

                Log.d("eee", " до startActivity(intent);");
                startActivity(intent);
                Log.d("eee", " после startActivity(intent);");
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
