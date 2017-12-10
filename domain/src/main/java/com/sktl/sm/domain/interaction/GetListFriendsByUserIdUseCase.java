package com.sktl.sm.domain.interaction;

import android.util.Log;

import com.sktl.sm.data.entity.IdUF;
import com.sktl.sm.data.net.RestService;
import com.sktl.sm.domain.entity.IdDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by USER-PC on 04.12.2017.
 */

public class GetListFriendsByUserIdUseCase extends UseCase<String, List<IdDomain>> {

    @Override
    protected Observable<List<IdDomain>> buildUseCase(final String idOfUserThatHasFriend) {

        String readyQueryByIdStr = ("idUser=").concat("'").concat(idOfUserThatHasFriend).concat("'");
        return RestService
                .getInstance()
                .getUserFriendsService(readyQueryByIdStr)
                .map(new Function<List<IdUF>, List<IdDomain>>() {
                    @Override
                    public List<IdDomain> apply(@NonNull List<IdUF> idsList)
                            throws Exception {
                        List<IdDomain> listIdDomain = new ArrayList<>();
//                        IdDomain idDomain = new IdDomain();//если здесь< то не работает
                        for (IdUF id : idsList) {
//                            Log.d("eee", "id.getIdUser() = " + id.getIdUser()
//                                    + ", id.getIdFriend() = " + id.getIdFriend()
//                                    + ", id.getVisibility() = " + id.getVisibilityFriend());
                            if (id.getIdUser().equals(idOfUserThatHasFriend)) {
                                IdDomain idDomain = new IdDomain();
                                idDomain.setId1(id.getIdUser());
                                idDomain.setId2(id.getIdFriend());
                                idDomain.setVisibilityIds(id.getVisibilityFriend());
                                listIdDomain.add(idDomain);
                            }

                        }
//                        for (IdDomain idDomainAfter : listIdDomain) {
//                            Log.d("eee", "idDomainAfter.getId1() = " + idDomainAfter.getId1()
//                                    + ", idDomainAfter.getId2() = " + idDomainAfter.getId2()
//                                    + ", idDomainAfter.getVisibilityIds() = " + idDomainAfter.getVisibilityIds());
//                        }
                        return listIdDomain;
                    }
                });
    }
}


/*
//плохо работает

public class GetListFriendsByUserIdUseCase extends UseCase<String, List<UserDomain>> {

    GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdUseCase();


    @Override
    protected Observable<List<UserDomain>> buildUseCase(final String idOfUserThatHasFriend) {


        String readyQueryByIdStr = ("idUser=").concat("'").concat(idOfUserThatHasFriend).concat("'");
        return RestService
                .getInstance()
                .getUserFriendsService(readyQueryByIdStr)
                .map(new Function<List<User>, List<UserDomain>>() {
                    @Override
                    public List<UserDomain> apply(@NonNull List<User> friends)
                            throws Exception {
                        List<UserDomain> list = new ArrayList<>();
                        for (User friendOfList : friends) {
                            list.add(convert(friendOfList, idOfUserThatHasFriend));
                        }
                        return list;
                    }
                });
                }


//
    private UserDomain convert(User user, String id) {
        UserDomain userDomain = new UserDomain();
        userDomain.setIdUser(user.getIdUser());
//        if(userDomain.getIdUser().equals(id)) {
        if(true) {
            getUserByIdUseCase.execute(userDomain.getIdUser(), new DisposableObserver<UserDomain>() {
                @Override
                public void onNext(@NonNull UserDomain userDomain) {
                    Log.d("eee", "выполняется  getUserByIdUseCase.execute() onNext()");

                    Log.d("eee", "userDomain.getUsername() = " + userDomain.getUsername());
                    Log.d("eee", "userDomain.getIdUser() = " + userDomain.getIdUser());

                    if (userDomain.getIdUser() == null) {// если ID равно null
                        Log.d("eee", "non-existent user");
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d("eee", "Ошибочка вышла в getUserByIdUseCase.execute() " + e.toString());
                }

                @Override
                public void onComplete() {
                    Log.d("eee", "выполнилось getUserByIdUseCase.execute() onComplete()");
                }
            });
        }
        return userDomain;
    }


}
*/