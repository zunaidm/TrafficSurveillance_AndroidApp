package com.example.orvi.mobileapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.model.Toll;

import java.util.List;

public class tollAdapter extends ArrayAdapter<Toll> {

    private List<Toll> tollList;
    private Context context;
    public tollAdapter( Context context,  List<Toll> objects) {
        super(context, R.layout.list_toll, objects);

        this.context = context;
        this.tollList = objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_toll,null,true);

        TextView date = (TextView) view.findViewById(R.id.tollDate);
        TextView time = (TextView) view.findViewById(R.id.tollTime);
        TextView amount = (TextView) view.findViewById(R.id.tollAmount);


        Toll toll = tollList.get(position);
        date.append(toll.getDate());
        time.append(toll.getTime());
        amount.append(toll.getAmount());

        return view;
    }
}
