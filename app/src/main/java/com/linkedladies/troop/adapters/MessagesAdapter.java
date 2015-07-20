package com.linkedladies.troop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkedladies.troop.R;
import com.linkedladies.troop.helpers.Constants;
import com.linkedladies.troop.helpers.Utils;
import com.linkedladies.troop.models.Message;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private Context context;
    private List<Message> messages;

    public MessagesAdapter(Context context, List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Message message = messages.get(position);
        holder.tvMessagesType.setText(message.getMessageType());
        holder.tvSender.setText(message.getSender());
        holder.tvTime.setText(Utils.getInstance().getTimeSince(message.getTime()));

        switch (Constants.SessionAction.fromString(message.getMessageType())) {
            case LOVE:
                Picasso.with(context).load(R.mipmap.ic_love).into(holder.ivActionIcon);
                break;
            case HELP:
                Picasso.with(context).load(R.mipmap.ic_help).into(holder.ivActionIcon);
                break;
            case RECOVER:
                Picasso.with(context).load(R.mipmap.ic_recover).into(holder.ivActionIcon);
                break;
            case SUPPORT:
                Picasso.with(context).load(R.mipmap.ic_support).into(holder.ivActionIcon);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.ivActionIcon)
        ImageView ivActionIcon;
        @InjectView(R.id.tvSender)
        TextView tvSender;
        @InjectView(R.id.tvMessageType)
        TextView tvMessagesType;
        @InjectView(R.id.tvTime)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
