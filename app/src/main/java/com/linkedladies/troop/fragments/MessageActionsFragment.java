package com.linkedladies.troop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkedladies.troop.R;
import com.linkedladies.troop.helpers.SoundManager;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Message;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessageActionsFragment extends Fragment{

    private UIUtils uiUtils;
    private SoundManager soundManager;
    private MessageActionListener listener;

    public MessageActionsFragment() {}

    public interface MessageActionListener {
        public void onLoveSuccess(Message message);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiUtils = new UIUtils(activity);
        soundManager = new SoundManager(activity);

        if (activity instanceof MessageActionListener) {
            listener = (MessageActionListener) activity;
        } else {
            throw new ClassCastException(
                    activity.getClass().getSimpleName()
                    + " must implement "
                    + MessageActionListener.class.getSimpleName()
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_message_actions, container, false);
        ButterKnife.inject(this, parent);
        return parent;
    }

    @OnClick(R.id.btnLove)
    public void onSendLove() {
        TroopClient.sendLove(new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                uiUtils.showToast(R.string.love_success_toast);
                soundManager.playLove();
                listener.onLoveSuccess(results.getMessage());
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

    @OnClick(R.id.btnHelp)
    public void onSendHelp() {
        TroopClient.sendHelp(new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                uiUtils.showToast(R.string.help_success_toast);
                soundManager.playHelp();
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

}