package com.linkedladies.troop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.linkedladies.troop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btnGetStarted)
    public void onGetStarted() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
