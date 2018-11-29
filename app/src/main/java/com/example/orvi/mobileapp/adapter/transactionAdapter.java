package com.example.orvi.mobileapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.model.transaction;

import java.util.List;

public class transactionAdapter extends ArrayAdapter<transaction> {
    private List<transaction> transactionList;
    private Context context;
    public transactionAdapter(List<transaction> p, Context c) {
        super(c, R.layout.list_transaction, p);
        this.context = c;
        this.transactionList = p;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_transaction,null,true);

        TextView date = (TextView) view.findViewById(R.id.tranDate);
        TextView time = (TextView) view.findViewById(R.id.tranTime);
        TextView amount = (TextView) view.findViewById(R.id.tranAmount);
        TextView type = (TextView) view.findViewById(R.id.tranType);


        transaction tran = transactionList.get(position);
        date.append(tran.getDate());
        time.append(tran.getTime());
        amount.append(tran.getAmount());
        if(tran.getFine()){
            type.append("Fine");
        }else if(tran.getToll()){
            type.append("Toll");
        }else{
            type.append("Cash In");
        }
        return view;
    }
}
