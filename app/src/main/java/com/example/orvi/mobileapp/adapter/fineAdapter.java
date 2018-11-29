package com.example.orvi.mobileapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.model.Fine;

import java.util.List;

public class fineAdapter extends ArrayAdapter<Fine> {
    private List<Fine> fineList;
    private  Context context;

    public fineAdapter( Context context, List<Fine> objects) {
        super(context, R.layout.list_fine, objects);

        this.context = context;
        this.fineList = objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_fine,null,true);


        TextView date = (TextView) view.findViewById(R.id.fineDate);
        TextView time = (TextView) view.findViewById(R.id.fineTime);
        TextView amount = (TextView) view.findViewById(R.id.fineAmount);
        TextView reason = (TextView) view.findViewById(R.id.fineCause);


        Fine fine = fineList.get(position);
        date.append(fine.getDate());
        time.append(fine.getTime());
        amount.append(fine.getAmount());
        reason.append(fine.getReason());

        return view;
    }
}
