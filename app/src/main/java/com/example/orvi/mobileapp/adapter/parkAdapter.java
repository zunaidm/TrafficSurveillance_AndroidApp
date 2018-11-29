package com.example.orvi.mobileapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.model.Park;

import java.util.List;

public class parkAdapter extends ArrayAdapter<Park> {
    private  List<Park> parkList;
    private  Context context;

    public parkAdapter( Context context, List<Park> objects) {
        super(context, R.layout.list_park, objects);

        this.context = context;
        this.parkList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_park,null,true);

        TextView zone = (TextView) view.findViewById(R.id.zoneNum);
        TextView space1 = (TextView) view.findViewById(R.id.space1TB);
        TextView space2 = (TextView) view.findViewById(R.id.space2TB);
        RelativeLayout s1 = (RelativeLayout) view.findViewById(R.id.space1);
        RelativeLayout s2 = (RelativeLayout) view.findViewById(R.id.space2);


        Park park = parkList.get(position);

        if(park.getS1().equals("Booked")){
            s1.setBackgroundColor(Color.RED);
        }else{
            s1.setBackgroundColor(Color.GREEN);
        }

        if(park.getS2().equals("Booked")){
            s2.setBackgroundColor(Color.RED);
        }else{
            s2.setBackgroundColor(Color.GREEN);
        }


        zone.append(park.getID());
        space1.append(park.getS1());
        space2.append(park.getS2());



        return view;
    }
}
