package com.linkedladies.troop.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.linkedladies.troop.R;
import com.linkedladies.troop.fragments.ActiveSessionFragment;
import com.linkedladies.troop.fragments.MessageListFragment;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;
import com.linkedladies.troop.push.GcmMessageHandler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SessionActivity extends AppCompatActivity {

    private UIUtils uiUtils;

    private ActiveSessionFragment activeSessionFragment;
    private MessageListFragment messageListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        uiUtils = new UIUtils(this);
        activeSessionFragment = new ActiveSessionFragment();
        messageListFragment = new MessageListFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flMainContainer, messageListFragment);
        ft.hide(messageListFragment);
        ft.add(R.id.flMainContainer, activeSessionFragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_messages) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (activeSessionFragment.isHidden()) {

                ft.show(activeSessionFragment);
                ft.hide(messageListFragment);
                ft.commit();

            } else {

                ft.hide(activeSessionFragment);
                ft.show(messageListFragment);
                ft.commit();

                TroopClient.getMessages(new Callback<Results>() {
                    @Override
                    public void success(Results results, Response response) {
                        messageListFragment.setMessages(results.getMessages());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        uiUtils.showToast(R.string.error_toast);
                    }
                });
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(actionReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        IntentFilter filter = new IntentFilter(GcmMessageHandler.PUSH_FILTER);
        filter.setPriority(1);
        registerReceiver(actionReceiver, filter);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TroopClient.closeSession(new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                /* NOP */
            }

            @Override
            public void failure(RetrofitError error) {
                new UIUtils(SessionActivity.this).showToast(R.string.error_toast);
            }
        });
    }

    final BroadcastReceiver actionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(SessionActivity.this, "Received push", Toast.LENGTH_SHORT).show();
        }
    };
}
