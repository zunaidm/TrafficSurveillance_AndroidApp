package com.example.orvi.mobileapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.adapter.parkAdapter;
import com.example.orvi.mobileapp.model.Park;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParkingActivity extends AppCompatActivity {

    ListView listView;
    List<Park> parkList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        listView = (ListView) findViewById(R.id.parkList);
        parkList = new ArrayList<>();

        parkAdapter adapter = new parkAdapter(this,listing(getIntent().getStringExtra("park")));

        listView.setAdapter(adapter);
    }

    private List<Park> listing (String x){

        try {
            JSONObject jsonObject = new JSONObject(x);
            JSONArray jsonArray = jsonObject.getJSONArray("park");
            if(jsonArray != null){
                for(int i = 0; i < jsonArray.length() ; i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    Log.i("json",o.toString());
                    parkList.add(
                            new Park(o.getString("id"),
                                    o.getString("s1"),
                                    o.getString("s2")));
                }
            }
            else {
                Toast.makeText(this, "no Data Found", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parkList;
    }
}
