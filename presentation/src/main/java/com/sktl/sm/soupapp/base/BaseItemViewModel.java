package com.sktl.sm.soupapp.base;

/**
 * Created by USER-PC on 18.08.2017.
 */

public abstract class BaseItemViewModel<Model> implements BaseViewModel {

    protected abstract void setItem(Model item, int position);//должен быть public
//    public abstract void setItem(Model item, int position);//но так не работает
    @Override
    public void init() {
    }

    @Override
    public void release() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }
}