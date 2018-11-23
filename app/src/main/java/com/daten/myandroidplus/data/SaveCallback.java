package com.daten.myandroidplus.data;

public interface SaveCallback {
    void onSaveSuccess();
    void onSaveFailed(String errorMsg);
}
