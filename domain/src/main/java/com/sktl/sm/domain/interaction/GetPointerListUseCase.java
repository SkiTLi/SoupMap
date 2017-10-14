package com.sktl.sm.domain.interaction;


import com.sktl.sm.data.entity.Pointer;
import com.sktl.sm.data.net.RestService;

import com.sktl.sm.domain.entity.Id;
import com.sktl.sm.domain.entity.PointerDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class GetPointerListUseCase extends PointerUseCase<Id, List<PointerDomain>> {

    @Override
    protected Observable<List<PointerDomain>> buildUseCase(Id param) {
        return RestService
                .getInstance()
                .getPointersService()
                .map(new Function<List<Pointer>, List<PointerDomain>>() {
                    @Override
                    public List<PointerDomain> apply(@NonNull List<Pointer> pointers)
                            throws Exception {
                        List<PointerDomain> list = new ArrayList<>();
                        for (Pointer pointerOfList : pointers) {
                            list.add(convert(pointerOfList));
                        }
                        return list;
                    }
                });
    }

    private PointerDomain convert(Pointer pointer) {
        PointerDomain pointerDomain = new PointerDomain();
        pointerDomain.setName(pointer.getName());
        pointerDomain.setDescription(pointer.getDescription());
        pointerDomain.setIdPointer(pointer.getIdPointer());
        pointerDomain.setImage(pointer.getImage());
        pointerDomain.setLatitude(pointer.getLatitude());
        pointerDomain.setLongitude(pointer.getLongitude());
        pointerDomain.setCreatedByUserDomain(null);
        pointerDomain.setIsVisible(true);

        return pointerDomain;
    }
}
