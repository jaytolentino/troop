package com.linkedladies.troop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.linkedladies.troop.R;
import com.linkedladies.troop.fragments.MusicLoginFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flLoginContainer, new MusicLoginFragment());
        ft.commit();
    }

    @OnClick(R.id.btnSkip)
    public void onSkip() {
        startActivity(new Intent(LoginActivity.this, SessionActivity.class));
    }
}
