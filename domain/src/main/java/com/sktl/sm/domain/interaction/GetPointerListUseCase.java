package com.sktl.sm.domain.interaction;


import android.util.Log;

import com.sktl.sm.data.entity.Pointer;
import com.sktl.sm.data.net.RestService;

import com.sktl.sm.domain.entity.IdDomain;
import com.sktl.sm.domain.entity.PointerDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GetPointerListUseCase extends UseCase<IdDomain, List<PointerDomain>> {

    @Override
    protected Observable<List<PointerDomain>> buildUseCase(IdDomain param) {
        return RestService
                .getInstance()
                .getPointersService()
                .map(new Function< List<Pointer> ,  List<PointerDomain> >() {
                    @Override
                    public List<PointerDomain> apply( List<Pointer> pointers)
                            throws Exception {
                        Log.d("eee", " в GetPointerListUseCase коллекция List<Pointer> pointers содержит элементов: "+ pointers.size());
                        List<PointerDomain> list = new ArrayList<>();
                        for (Pointer pointerOfList : pointers) {
                            list.add(convert(pointerOfList));
                        }
                        Log.d("eee", " в GetPointerListUseCase коллекция  List<PointerDomain> list содержит элементов: "+ list.size());
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
//        pointerDomain.setCreatedByUserDomain(null);
        pointerDomain.setIsVisible(true);

        return pointerDomain;
    }
}
