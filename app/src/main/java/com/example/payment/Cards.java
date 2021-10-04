package com.example.payment;

public class Cards {

    String cardnumber ;
    String name;
    String exdate;
    String ccvv;


    public Cards(String cardnumber, String name, String exdate, String ccvv) {
        this.cardnumber = cardnumber;
        this.name = name;
        this.exdate = exdate;
        this.ccvv = ccvv;
    }
    public Cards() { }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getName() {
        return name;
    }

    public String getExdate() {
        return exdate;
    }

    public String getCcvv() {
        return ccvv;
    }


}
