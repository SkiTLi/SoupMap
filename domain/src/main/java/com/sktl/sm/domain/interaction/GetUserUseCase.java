package com.sktl.sm.domain.interaction;


import android.util.Log;
import android.widget.Toast;

import com.sktl.sm.data.net.RestService;

import com.sktl.sm.data.entity.User;
import com.sktl.sm.domain.entity.Id;
import com.sktl.sm.domain.entity.UserDomain;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import static java.security.AccessController.getContext;


/**
 * Created by USER-PC on 11.08.2017.
 */


public class GetUserUseCase extends UseCase<String, UserDomain> {
    //получаем здесь профиль и отдаем его
    @Override
    protected Observable<UserDomain> buildUseCase(final String idOfUser) {
        //получаем здесь профиль и отдаем его
        //тут делается запрос к слою data в котором дергается метод на сервере (REST)
        //создаем  объект Profile который лежит в data
        //это дляя теста , будущем этот объект будетиз слоя data
        //сделать задержку в 3 секунды
        String readyQueryByIdStr = ("idUser=").concat("'").concat(idOfUser).concat("'");
        return RestService.getInstance().getUserService(readyQueryByIdStr)
                .map(new Function<List<User>, UserDomain>() {
                    @Override
                    public UserDomain apply(@NonNull List<User> usersList)
                            throws Exception {
                        UserDomain userDomain = new UserDomain();
                        for (User user : usersList) {
//                            Log.d("eee", "user.getIdUser() = " + user.getIdUser());
                            if (user.getIdUser().equals(idOfUser)) {
                                User userData = user;
                                userDomain.setUsername(userData.getUsername());
                                userDomain.setPassword(userData.getPassword());
                                userDomain.setEmail(userData.getEmail());
                                userDomain.setAdditionalInformation(userData.getAdditionalInformation());
                                userDomain.setIdUser(userData.getIdUser());
//                        userDomain.setPointerDomainListUser(new GetPointerListUseCase());//надо доделать
//                        userDomain.setFriendListUserDomain(new List<UserDomain>());//надо доделать
                                userDomain.setImageUser(userData.getImageUser());
                            }
                        }
                        return userDomain;
                    }
                });
    }
}
