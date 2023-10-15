package com.ssu.moneymate.ui.main.property;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PropertyViewModel extends ViewModel {
    private final MutableLiveData<Boolean> kbChecked = new MutableLiveData<>();
    private final MutableLiveData<Boolean> nhChecked = new MutableLiveData<>();

    public void setKbChecked(boolean checked) {
        kbChecked.setValue(checked);
        Log.d("kbset", String.valueOf(kbChecked.getValue()));
    }

    public void setNhChecked(boolean checked) {
        nhChecked.setValue(checked);
    }

    public LiveData<Boolean> isKbChecked() {
        Log.d("kbsetget", String.valueOf(kbChecked.getValue()));
        return kbChecked;
    }

    public LiveData<Boolean> isNhChecked() {
        return nhChecked;
    }


    private MutableLiveData<Integer> balance = new MutableLiveData<>();
    private MutableLiveData<Integer> nhBalance = new MutableLiveData<>();

    public LiveData<Integer> getBalance() {
        return balance;
    }

    public LiveData<Integer> getNhBalance() {
        return nhBalance;
    }

    public void setBalance(int newBalance) {
        balance.setValue(newBalance);
    }

    public void setNhBalance(int newNhBalance) {
        nhBalance.setValue(newNhBalance);
    }
}
