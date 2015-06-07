package com.linkedladies.troop.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkedladies.troop.R;
import com.linkedladies.troop.helpers.SpotifyManager;
import com.linkedladies.troop.helpers.UIUtils;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicLoginFragment extends Fragment {

    private SpotifyManager spotifyManager;
    private UIUtils uiUtils;

    public MusicLoginFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        spotifyManager = new SpotifyManager(activity);
        uiUtils = new UIUtils(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_music_login, container, false);
        ButterKnife.inject(this, parent);
        return parent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        spotifyManager.handleOnActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnSpotifyLogin)
    public void onSpotifyLogin() {
        spotifyManager.login(new SpotifyManager.LoginHandler() {
            @Override
            public void onSuccess() {
                uiUtils.showToast(R.string.success_toast);
            }

            @Override
            public void onFailure(AuthenticationResponse.Type responseType) {
                if (responseType == AuthenticationResponse.Type.ERROR) {
                    uiUtils.showToast(R.string.error_toast);
                }
            }
        });
    }

}
