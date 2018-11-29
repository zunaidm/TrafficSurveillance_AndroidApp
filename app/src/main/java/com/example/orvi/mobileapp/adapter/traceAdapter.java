package com.example.orvi.mobileapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.model.trace;

import java.util.List;

public class traceAdapter extends ArrayAdapter<trace> {

    private List<trace> traceList;
    private Context context;

    public traceAdapter( Context context,  List<trace> objects) {
        super(context, R.layout.list_trace, objects);

        this.context =context;
        this.traceList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_trace,null,true);

        TextView date = (TextView) view.findViewById(R.id.traceDate);
        TextView time = (TextView) view.findViewById(R.id.traceTime);
        TextView road = (TextView) view.findViewById(R.id.traceRoad);

        trace trace = traceList.get(position);
        date.append(trace.getDate());
        time.append(trace.getTime());
        road.append(trace.getRoad());

        return view;
    }
}
