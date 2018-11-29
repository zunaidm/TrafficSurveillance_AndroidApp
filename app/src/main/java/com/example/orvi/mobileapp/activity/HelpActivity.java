package com.example.orvi.mobileapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.adapter.traceAdapter;
import com.example.orvi.mobileapp.model.trace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    ListView listView;
    List<trace> traceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        listView = (ListView) findViewById(R.id.traceList);
        traceList = new ArrayList<>();

        String x = getIntent().getStringExtra("help");

        traceAdapter adapter = new traceAdapter(this,listing(x));

        listView.setAdapter(adapter);
    }

    private List<trace> listing (String x) {

        try {
            JSONObject jsonObject = new JSONObject(x);
            JSONArray jsonArray = jsonObject.getJSONArray("trace");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject o = jsonArray.getJSONObject(i);
                    Log.i("json", o.toString());
                    traceList.add(
                            new trace(o.getString("road"),
                                    o.getString("time"),
                                    o.getString("date")));
                }
            } else {
                Toast.makeText(this, "no Data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return traceList;
    }
}
