package com.linkedladies.troop.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkedladies.troop.R;
import com.linkedladies.troop.adapters.FriendsAdapter;
import com.linkedladies.troop.app.User;
import com.linkedladies.troop.helpers.UIUtils;
import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;
import com.linkedladies.troop.net.TroopClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FriendListFragment extends Fragment {

    private UIUtils uiUtils;
    private FriendsAdapter friendsAdapter;

    @InjectView(R.id.rvFriends)
    RecyclerView rvFriends;

    public FriendListFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiUtils = new UIUtils(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_friend_list, container, false);
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

    public void updateFriends(List<Friend> updatedFriends) {
        friendsAdapter.updateFriends(updatedFriends);
    }
}