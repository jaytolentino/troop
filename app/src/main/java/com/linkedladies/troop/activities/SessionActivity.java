package com.linkedladies.troop.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.linkedladies.troop.R;
import com.linkedladies.troop.fragments.MessageActionsFragment;
import com.linkedladies.troop.fragments.MessageListFragment;
import com.linkedladies.troop.models.Message;

public class SessionActivity extends AppCompatActivity implements MessageActionsFragment.MessageActionListener{

    private MessageListFragment messageListFragment;
    private MessageActionsFragment messageActionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        messageListFragment = new MessageListFragment();
        messageActionsFragment = new MessageActionsFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMessagesList, messageListFragment);
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
    public void onLoveSuccess(Message message) {
        messageListFragment.addMessage(message);
    }
}
