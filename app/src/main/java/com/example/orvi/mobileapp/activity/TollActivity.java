package com.example.orvi.mobileapp.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.fragments.FineFragment;
import com.example.orvi.mobileapp.fragments.TollFragment;

public class TollActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String data;
    private Fragment tollFrag;
    private Fragment fineFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        data = getIntent().getStringExtra("toll");

        Log.i("toll",data);
        tollFrag = new TollFragment();
        fineFrag = new FineFragment();

        Bundle datas = new Bundle();
        datas.putString("data",data);

        tollFrag.setArguments(datas);
        fineFrag.setArguments(datas);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tollFrag).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    /*mTextMessage.setText(R.string.title_home);
                    return true;*/
                    fragment = tollFrag;
                    break;
                case R.id.navigation_dashboard:
                    fragment = fineFrag;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };
}
