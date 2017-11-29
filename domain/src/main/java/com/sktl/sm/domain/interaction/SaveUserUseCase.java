package com.sktl.sm.domain.interaction;



import com.sktl.sm.data.entity.User;
import com.sktl.sm.data.net.RestService;

import com.sktl.sm.domain.entity.UserDomain;

import io.reactivex.Observable;
// здесь пишет что нулпоинтер
public class SaveUserUseCase extends UseCase<UserDomain, Void> { //ProfileModel и domain . исходящий параметр Void  т.е. его не будет
    @Override
    protected Observable<Void> buildUseCase(UserDomain userDomain) {
        User userData = new User();
        userData.setUsername(userDomain.getUsername());
        userData.setIdUser(userDomain.getIdUser());
        userData.setPassword(userDomain.getPassword());
        userData.setEmail(userDomain.getEmail());
        userData.setImageUser(userDomain.getImageUser());
        userData.setAdditionalInformation(userDomain.getAdditionalInformation());
        return RestService.getInstance().saveUserService(userData);
    }
}
