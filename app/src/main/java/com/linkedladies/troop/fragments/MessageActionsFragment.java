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

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActionsFragment extends Fragment{

    private UIUtils uiUtils;
    private SoundManager soundManager;

    public MessageActionsFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiUtils = new UIUtils(activity);
        soundManager = new SoundManager(activity);
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
        uiUtils.showToast(R.string.love_toast);
        soundManager.playLove();
    }

}