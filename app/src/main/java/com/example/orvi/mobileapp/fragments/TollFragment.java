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
import com.example.orvi.mobileapp.adapter.tollAdapter;
import com.example.orvi.mobileapp.model.Toll;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TollFragment extends Fragment {

    List<Toll> tollList;
    Context context;
    String x;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toll,container,false);
        context = view.getContext();

        tollList =  new ArrayList<>();

        ListView listView = (ListView) view.findViewById(R.id.tollList);

        Log.i("tollFrag",getArguments().getString("data"));

        x = getArguments().getString("data");


        tollAdapter  adapter  =  new tollAdapter(context,listing(x));

        listView.setAdapter(adapter);

        return view;
    }

    private List<Toll> listing (String x){

        try {
            JSONObject jsonObject = new JSONObject(x);
            JSONArray jsonArray = jsonObject.getJSONArray("toll");
            if(jsonArray != null){
                for(int i = 0; i < jsonArray.length() ; i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    Log.i("json",o.toString());
                    tollList.add(
                            new Toll(o.getString("date"),
                                    o.getString("time"),
                                    o.getString("amount")));
                }
            }
            else {
                Toast.makeText(context, "no Data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tollList;
    }
}
