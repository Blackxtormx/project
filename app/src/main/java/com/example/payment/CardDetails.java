package com.example.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardDetails extends AppCompatActivity {
    EditText etCardnumber, etName, etexpire_date, etcvv;
    Button btnInsertData;

    DatabaseReference  RootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        btnInsertData = findViewById(R.id.btnInsertData);
        etCardnumber = findViewById(R.id.etCardnumber);
        etName = findViewById(R.id.etName);
        etexpire_date = findViewById(R.id.etexpire_date);
        etcvv = findViewById(R.id.etcvv);

        RootRef = FirebaseDatabase.getInstance().getReference().child("Card details");


        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insetCardData();
            }
        });
    }

    private void insetCardData() {

        String cardnumber = etCardnumber.getText().toString();
        String name = etName.getText().toString();
        String exdate = etexpire_date.getText().toString();
        String ccvv = etcvv.getText().toString();

        Cards cards =new Cards(cardnumber, name, exdate, ccvv);

        RootRef.push().setValue(cards);

        Toast.makeText(CardDetails.this, "Data Inserted!", Toast.LENGTH_SHORT).show();



    }
}