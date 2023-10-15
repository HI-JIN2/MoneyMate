package com.ssu.moneymate.ui.main.property;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "property_table")
public class PropertyData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "balance")
    private int balance;

    // Getter 메서드 추가
    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    // Setter 메서드 추가
    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
