package com.ssu.moneymate.ui.main.fixed;

public class FixedData {
    String category;
    int money;

    public String getCategory() {
        return category;
    }

    public int getMoney() {
        return  money;
    }

    public  FixedData(String category, int money){
        this.category = category;
        this.money = money;
    }
}