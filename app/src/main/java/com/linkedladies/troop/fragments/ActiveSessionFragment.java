package com.linkedladies.troop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.linkedladies.troop.R;
import com.linkedladies.troop.adapters.FriendsAdapter;
import com.linkedladies.troop.app.User;
import com.linkedladies.troop.helpers.Constants;
import com.linkedladies.troop.helpers.SoundManager;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActiveSessionFragment extends Fragment{

    @InjectView(R.id.btnLove)
    Button btnLove;
    @InjectView(R.id.btnHelp)
    Button btnHelp;
    @InjectView(R.id.btnSupport)
    Button btnSupport;
    @InjectView(R.id.btnRecover)
    Button btnRecover;

    @InjectView(R.id.rvFriends)
    RecyclerView rvFriends;

    private UIUtils uiUtils;
    private SoundManager soundManager;

    private FriendsAdapter friendsAdapter;

    public ActiveSessionFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiUtils = new UIUtils(activity);
        soundManager = new SoundManager(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_active_session, container, false);
        ButterKnife.inject(this, parent);

        TroopClient.getSessionFriends(new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                List<Friend> friends = results.getFriends();

                if (friends != null && friends.size() > 0) {
                    setupFriendsList(new ArrayList<>(friends));

                    // TODO remove; always setting first user as the logged-in user
                    User.getInstance().setUserid(friends.get(0).getUserId());
                } else {
                    uiUtils.showToast(R.string.error_toast);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
        return parent;
    }

    private void setupFriendsList(List<Friend> friends) {
        // TODO (jay) calculate ideal span number in future depending on # of friends instead of using 2
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rvFriends.setLayoutManager(layoutManager);

        friendsAdapter = new FriendsAdapter(getActivity(), friends);
        rvFriends.setAdapter(friendsAdapter);
    }

    @OnClick(R.id.btnLove)
    public void sendLove() {
        TroopClient.sendLove(User.getInstance().asFriend(), new Callback<Results>() {
            @Override
            public void success(Results results, Response response) {
                soundManager.playLove();
                uiUtils.showToast(R.string.love_success_toast);
                processActionResults(results);
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
                processActionResults(results);
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
                uiUtils.showToast(R.string.support_success_toast);
                processActionResults(results);
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
                uiUtils.showToast(R.string.recover_success_toast);
                processActionResults(results);
            }

            @Override
            public void failure(RetrofitError error) {
                uiUtils.showToast(R.string.error_toast);
            }
        });
    }

    private void processActionResults(Results results) {
        List<Friend> updatedFriends = results.getFriends();
        if (updatedFriends != null && updatedFriends.size() > 0) {
            friendsAdapter.updateFriends(results.getFriends());

            // TODO remove; currently always using 1st friend as logged-in user
            updateActions(updatedFriends.get(0).getState());
        }
    }

    // TODO consider moving this logic to the server
    private void updateActions(Constants.UserState userState) {
        switch (userState) {
            case NORMAL:
                btnLove.setEnabled(true);
                btnHelp.setEnabled(true);
                btnSupport.setEnabled(false);
                btnRecover.setEnabled(false);
                break;
            case REQUESTS_SUPPORT:
                btnLove.setEnabled(true);
                btnHelp.setEnabled(false);
                btnSupport.setEnabled(false);
                btnRecover.setEnabled(false);
                break;
            case UNDECIDED:
                btnLove.setEnabled(true);
                btnHelp.setEnabled(false);
                btnSupport.setEnabled(true);
                btnRecover.setEnabled(false);
                break;
            case SUPPORTS:
                btnLove.setEnabled(true);
                btnHelp.setEnabled(false);
                btnSupport.setEnabled(false);
                btnRecover.setEnabled(false);
                break;
            case RECOVERABLE:
                btnLove.setEnabled(true);
                btnHelp.setEnabled(false);
                btnSupport.setEnabled(false);
                btnRecover.setEnabled(true);
                break;
            default:
                Log.e(ActiveSessionFragment.class.getSimpleName(), "Unrecognized user state: " + userState.toString());
                btnLove.setEnabled(true);
                btnHelp.setEnabled(true);
                btnSupport.setEnabled(false);
                btnRecover.setEnabled(false);
                break;

        }
    }
}
