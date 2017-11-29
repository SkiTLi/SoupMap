package com.sktl.sm.domain.interaction;


import com.sktl.sm.data.entity.Pointer;
import com.sktl.sm.data.entity.User;
import com.sktl.sm.data.net.RestService;
import com.sktl.sm.domain.entity.PointerDomain;

import io.reactivex.Observable;

public class SaveUserPointerUseCase extends UseCase<PointerDomain, Void> { //ProfileModel и domain . исходящий параметр Void  т.е. его не будет
    @Override
    protected Observable<Void> buildUseCase(PointerDomain pointerDomain) {
        Pointer pointerData = new Pointer();
        User userData = new User();
        userData.setIdUser(pointerDomain.getCreatedByUserDomain().getIdUser());
        pointerData.setIdPointer(pointerDomain.getIdPointer());
        return RestService.getInstance().saveUserPointerService(userData, pointerData, true);
    }
}
