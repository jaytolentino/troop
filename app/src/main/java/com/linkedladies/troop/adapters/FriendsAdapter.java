package com.linkedladies.troop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkedladies.troop.R;
import com.linkedladies.troop.models.Friend;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private Context context;
    private List<Friend> friends;

    public FriendsAdapter(Context context, List<Friend> friends) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Friend friend = friends.get(position);

        // TODO consider storing a prettyName on server
        holder.tvFriendName.setText(friend.getFirstName() + " " + friend.getLastName());
        holder.tvFriendState.setText(friend.getState().toString());

        Picasso.with(context).load(friend.getImageUrl()).into(holder.ivFriendProfile);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.ivFriendProfile)
        ImageView ivFriendProfile;

        @InjectView(R.id.tvFriendName)
        TextView tvFriendName;

        @InjectView(R.id.tvFriendState)
        TextView tvFriendState;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public void updateFriends(List<Friend> updatedFriends) {
        friends.clear();
        friends.addAll(updatedFriends);
        notifyDataSetChanged();
    }
}
