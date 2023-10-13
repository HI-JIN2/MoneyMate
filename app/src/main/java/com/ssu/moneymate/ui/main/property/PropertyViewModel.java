package com.ssu.moneymate.ui.main.property;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PropertyViewModel extends ViewModel {
    private MutableLiveData<Boolean> kbChecked = new MutableLiveData<>();
    private MutableLiveData<Boolean> nhChecked = new MutableLiveData<>();

    public LiveData<Boolean> isKbChecked() {
        return kbChecked;
    }

    public LiveData<Boolean> isNhChecked() {
        return nhChecked;
    }

    public void setKbChecked(boolean checked) {
        kbChecked.setValue(checked);
    }

    public void setNhChecked(boolean checked) {
        nhChecked.setValue(checked);
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
