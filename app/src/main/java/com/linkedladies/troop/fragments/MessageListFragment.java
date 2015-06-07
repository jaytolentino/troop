package com.linkedladies.troop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkedladies.troop.R;
import com.linkedladies.troop.adapters.MessagesAdapter;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Message;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessageListFragment extends Fragment{

    @InjectView(R.id.rvMessages)
    RecyclerView rvMessages;

    private UIUtils uiUtils;
    private List<Message> messages;
    private MessagesAdapter messagesAdapter;

    public MessageListFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiUtils = new UIUtils(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_message_list, container, false);
        ButterKnife.inject(this, parent);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessages.setLayoutManager(layoutManager);

        messages = new ArrayList<>();
        TroopClient.getMessages(new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                messages.addAll(results.getChatRoom());
                messagesAdapter = new MessagesAdapter(messages);
                rvMessages.setAdapter(messagesAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });

        return parent;
    }

    public void addMessage(Message message) {
        messages.add(message);
        messagesAdapter.notifyDataSetChanged();
    }
}