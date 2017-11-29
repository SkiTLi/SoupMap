package com.sktl.sm.domain.interaction;


import com.sktl.sm.data.net.RestService;

import com.sktl.sm.data.entity.Pointer;
import com.sktl.sm.domain.entity.PointerDomain;

import io.reactivex.Observable;

public class SavePointerUseCase extends UseCase<PointerDomain, Void> { //ProfileModel и domain . исходящий параметр Void  т.е. его не будет
    @Override
    protected Observable<Void> buildUseCase(PointerDomain pointerDomain) {
        Pointer pointerData = new Pointer();
        pointerData.setName(pointerDomain.getName());
        pointerData.setIdPointer(pointerDomain.getIdPointer());
        pointerData.setLatitude(pointerDomain.getLatitude());
        pointerData.setLongitude(pointerDomain.getLongitude());
        pointerData.setImage(pointerDomain.getImage());
        pointerData.setDescription(pointerDomain.getDescription());
        return RestService.getInstance().savePointerService(pointerData);
    }
}
