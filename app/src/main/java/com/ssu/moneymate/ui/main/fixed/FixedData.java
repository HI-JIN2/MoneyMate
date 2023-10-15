package com.ssu.moneymate.ui.main.fixed;

public class FixedData {
    String category;
    String money;

    public String getCategory() {
        return category;
    }

    public String getMoney() {
        return  money;
    }

    public  FixedData(String category, String money){
        this.category = category;
        this.money = money;
    }
}