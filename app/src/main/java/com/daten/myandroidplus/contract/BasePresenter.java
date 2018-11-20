package com.daten.myandroidplus.contract;

public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
