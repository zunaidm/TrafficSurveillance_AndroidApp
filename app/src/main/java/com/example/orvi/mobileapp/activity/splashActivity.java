package com.example.orvi.mobileapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.orvi.mobileapp.R;

public class splashActivity extends AppCompatActivity {

    private SharedPreferences dataSave;
    Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dataSave = getApplicationContext().getSharedPreferences("LoginPref",MODE_PRIVATE);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.move_up);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setAnimation(anim);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!dataSave.getBoolean("LoggedIn", false)) {

                    startActivity(new Intent(splashActivity.this, LoginActivity.class));
                    finish();

                } else {
                    startActivity(new Intent(splashActivity.this, MainActivity.class));
                    finish();
                }

            }
        },4000);
    }
}
