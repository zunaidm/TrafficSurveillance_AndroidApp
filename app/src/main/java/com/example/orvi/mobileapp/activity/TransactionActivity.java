package com.example.orvi.mobileapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.adapter.transactionAdapter;
import com.example.orvi.mobileapp.model.transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    ListView listView;
    List<transaction> transactionList;
    TextView amu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        listView = (ListView) findViewById(R.id.tranList);
        amu = (TextView) findViewById(R.id.tranMainAmount);
        transactionList = new ArrayList<>();

        transactionAdapter adapter = new transactionAdapter(listing(getIntent().getStringExtra("tran")),this);

        listView.setAdapter(adapter);

        int amount = 0;
        for (transaction x:transactionList) {
            if(!x.getToll() && !x.getFine()){
                amount = amount + Integer.parseInt(x.getAmount());
            }else{
                amount = amount - Integer.parseInt(x.getAmount());
            }
        }
        amu.append(amount+"");

    }

    private List<transaction> listing (String x){

        try {
            JSONObject jsonObject = new JSONObject(x);
            JSONArray jsonArray = jsonObject.getJSONArray("trans");
            if(jsonArray != null){
                for(int i = 0; i < jsonArray.length() ; i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    Log.i("json",o.toString());
                    transactionList.add(
                            new transaction(o.getString("date"),
                            o.getString("time"),
                            o.getString("amount"),
                            o.getBoolean("fine"),
                            o.getBoolean("toll")));
                }
            }
            else {
                Toast.makeText(this, "no Data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}
