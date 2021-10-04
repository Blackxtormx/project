package com.example.payment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetreiveDataActivity extends AppCompatActivity {

    ListView myListview;
    List<Cards> cardsList;

    DatabaseReference RootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);


        myListview = findViewById(R.id.myListView);
        cardsList = new ArrayList<>();

        RootRef = FirebaseDatabase.getInstance().getReference("Cards");
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                cardsList.clear();

                for (DataSnapshot cardDatasnap : datasnapshot.getChildren()) {

                    Cards cards = cardDatasnap.getValue(Cards.class);
                    cardsList.add(cards);
                }


                ListAdapter adapter = new com.example.payment.ListAdapter(RetreiveDataActivity.this,cardsList);
                myListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                Cards cards = cardsList.get(position);
                showUpdateDialog(cards.getCardnumber(), cards.getName(), cards.getExdate(), cards.getCcvv());

                return false;
            }
        });
    }

    private void showUpdateDialog(String id, String cardnumber, String exdate, String ccvv){

        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog, null);

        mDialog.setView(mDialogView);

        EditText etUpdateCardnumber = mDialogView.findViewById(R.id.etUpdateCardnumber);
        EditText etUpdateNameoncard = mDialogView.findViewById(R.id.etUpdateNameoncard);
        EditText etUpdateExpire_date = mDialogView.findViewById(R.id.etUpdateExpire_date);
        EditText etUpdateCvv = mDialogView.findViewById(R.id.etUpdateCvv);

        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating" +cardnumber+ "record");
        mDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String newcardnumber= etUpdateCardnumber.getText().toString();
                String newmm_yy= etUpdateExpire_date.getText().toString();
                String newcvv= etUpdateCvv.getText().toString();
                String newcardname= etUpdateNameoncard.getText().toString();

                updateDate( newcardnumber, newcardname, newmm_yy, newcvv);

                Toast.makeText(RetreiveDataActivity.this, "Record Update", Toast.LENGTH_SHORT).show();

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(cardnumber);

        }


        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String cardnumber){
        DatabaseReference DbRef =FirebaseDatabase.getInstance().getReference("CardDetails").child(cardnumber);

        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("error deleting recode");
            }
        });
    }

    private void updateDate(  String cardnumber, String name, String exdate, String ccvv){
        DatabaseReference DbRef =FirebaseDatabase.getInstance().getReference("Cards").child(cardnumber);
        Cards cards = new Cards( cardnumber, name, exdate, ccvv);
        DbRef.setValue(cards);
    }
}
