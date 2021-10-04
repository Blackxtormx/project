package com.example.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Payment2 extends AppCompatActivity {
    private Button cardpayment;
    private Button cashondelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        cardpayment = (Button) findViewById(R.id.cardpayment);
        cardpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCardDetails();
            }
        });
        cardpayment = (Button) findViewById(R.id.cashondelivery);
        cardpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCashondelivery();
            }
        });



    }

    public void openCardDetails() {
        //Intent intent = new Intent(this, CardDetails.class);
        //Intent intent = new Intent(this, discount.class);
        Intent intent = new Intent(this, flashsc.class);
        startActivity(intent);


    }
    public void openCashondelivery() {
        Intent intent = new Intent(this, paymentAc.class);
        startActivity(intent);


    }
}