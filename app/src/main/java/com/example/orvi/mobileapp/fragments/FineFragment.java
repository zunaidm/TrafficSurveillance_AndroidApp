package com.example.orvi.mobileapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.adapter.fineAdapter;
import com.example.orvi.mobileapp.model.Fine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FineFragment extends Fragment {
    List<Fine> fineList;
    Context context;
    String x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toll,container,false);
        context = view.getContext();

        fineList =  new ArrayList<>();

        ListView listView = (ListView) view.findViewById(R.id.tollList);

        x = getArguments().getString("data");

        fineAdapter  adapter  =  new fineAdapter(context,listing(x));

        listView.setAdapter(adapter);

        return view;
    }

    private List<Fine> listing (String x){

        try {
            JSONObject jsonObject = new JSONObject(x);
            JSONArray jsonArray = jsonObject.getJSONArray("fine");
            if(jsonArray != null){
                for(int i = 0; i < jsonArray.length() ; i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    Log.i("json",o.toString());
                    fineList.add(
                            new Fine(o.getString("date"),
                                    o.getString("time"),
                                    o.getString("amount"),
                                    o.getString("reason")));
                }
            }
            else {
                Toast.makeText(context, "no Data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fineList;
    }
}
