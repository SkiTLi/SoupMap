package com.sktl.sm.domain.interaction;


import com.sktl.sm.data.entity.User;
import com.sktl.sm.data.net.RestService;
import com.sktl.sm.domain.entity.IdDomain;
import com.sktl.sm.domain.entity.UserDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class GetListUsersUseCase extends UseCase<IdDomain, List<UserDomain>> {

    @Override
    protected Observable<List<UserDomain>> buildUseCase(IdDomain param) {
        return RestService
                .getInstance()
                .getUsersService()
                .map(new Function<List<User>, List<UserDomain>>() {
                    @Override
                    public List<UserDomain> apply(@NonNull List<User> users)
                            throws Exception {
                        List<UserDomain> list = new ArrayList<>();
                        for (User userOfList : users) {
                            list.add(convert(userOfList));
                        }
                        return list;
                    }
                });
    }


    private UserDomain convert(User user) {
        UserDomain userDomain = new UserDomain();
        userDomain.setIdUser(user.getIdUser());
        userDomain.setUsername(user.getUsername());
        userDomain.setPassword(user.getPassword());
        userDomain.setEmail(user.getEmail());
        userDomain.setImageUser(user.getImageUser());
        userDomain.setAdditionalInformation(user.getAdditionalInformation());

        return userDomain;
    }
}
