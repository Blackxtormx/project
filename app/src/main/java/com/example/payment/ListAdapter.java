package com.example.payment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {


    private Activity mContext;
    List<Cards> cardsList;

    public ListAdapter(Activity mContext, List<Cards> cardsList){
        super(mContext,R.layout.list_data, cardsList);
        this.mContext = mContext;
        this.cardsList = cardsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_data, null, true);

        TextView tvcardnum = listItemView.findViewById(R.id.tvcardnum);
        TextView tvcName = listItemView.findViewById(R.id.tvcName);
        TextView tvex_date = listItemView.findViewById(R.id.tvex_date);
        TextView tvcvv = listItemView.findViewById(R.id.tvcvv);

        Cards cards = cardsList.get(position);

        tvcardnum.setText(cards.getCardnumber());
        tvcName.setText(cards.getName());
        tvex_date.setText(cards.getExdate());
        tvcvv.setText(cards.getCcvv());

        return listItemView;

    }
}

