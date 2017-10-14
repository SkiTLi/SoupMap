package com.sktl.sm.soupapp.base;



//по сути baseviewmodel lifecycle


public interface BaseViewModel {



    public void init();

    public void release();

    public void resume();

    public void pause();
}
