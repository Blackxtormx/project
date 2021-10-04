package com.example.payment;

public class MainModel {
    String name,cardnumber,price;

    MainModel(){

    }

    public MainModel(String name, String cardnumber, String price) {
        this.name = name;
        this.cardnumber = cardnumber;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
