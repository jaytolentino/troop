package com.linkedladies.troop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkedladies.troop.R;
import com.linkedladies.troop.app.User;
import com.linkedladies.troop.helpers.SoundManager;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MessageActionsFragment extends Fragment {

//    @InjectView(R.id.btnActionHandler)
//    Button btnActionHandler;

    private UIUtils uiUtils;
    private SoundManager soundManager;
    private MessageActionListener listener;

    public MessageActionsFragment() {}

    public interface MessageActionListener {
        public void onFriendsUpdated(List<Friend> updatedFriends);
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
    public void sendLove() {
        TroopClient.sendLove(User.getInstance().asFriend(), new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                soundManager.playLove();
                uiUtils.showToast(R.string.love_success_toast);

                List<Friend> updatedFriends = results.getFriends();
                if (updatedFriends != null && updatedFriends.size() > 0) {
                    listener.onFriendsUpdated(results.getFriends());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

    @OnClick(R.id.btnHelp)
    public void sendHelp() {
        TroopClient.sendHelp(User.getInstance().asFriend(), new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                soundManager.playHelp();
                uiUtils.showToast(R.string.help_success_toast);

                List<Friend> updatedFriends = results.getFriends();
                if (updatedFriends != null && updatedFriends.size() > 0) {
                    listener.onFriendsUpdated(results.getFriends());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

    @OnClick(R.id.btnSupport)
    public void sendSupport() {
        TroopClient.sendSupport(User.getInstance().asFriend(), new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                soundManager.playHelp();
                uiUtils.showToast(R.string.help_success_toast);

                List<Friend> updatedFriends = results.getFriends();
                if (updatedFriends != null && updatedFriends.size() > 0) {
                    listener.onFriendsUpdated(results.getFriends());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

    @OnClick(R.id.btnRecover)
    public void sendRecover() {
        TroopClient.sendRecover(User.getInstance().asFriend(), new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                soundManager.playHelp();
                uiUtils.showToast(R.string.help_success_toast);

                List<Friend> updatedFriends = results.getFriends();
                if (updatedFriends != null && updatedFriends.size() > 0) {
                    listener.onFriendsUpdated(results.getFriends());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

}