package com.linkedladies.troop.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.linkedladies.troop.R;
import com.linkedladies.troop.fragments.FriendListFragment;
import com.linkedladies.troop.fragments.MessageActionsFragment;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SessionActivity extends AppCompatActivity implements MessageActionsFragment.MessageActionListener{

    private FriendListFragment friendListFragment;
    private MessageActionsFragment messageActionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        friendListFragment = new FriendListFragment();
        messageActionsFragment = new MessageActionsFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flFriendsList, friendListFragment);
        ft.replace(R.id.flMessageActions, messageActionsFragment);
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFriendsUpdated(List<Friend> updatedFriends) {
        friendListFragment.updateFriends(updatedFriends);
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
}
