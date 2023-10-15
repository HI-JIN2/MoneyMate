package com.ssu.moneymate.ui.main.fixed;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class FixedData implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    String category;
    @ColumnInfo(name = "money")
    String money;

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getMoney() {
        return  money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    //    public  FixedData(int id, String category, String money){
//        this.id = id;
//        this.category = category;
//        this.money = money;
//    }
}