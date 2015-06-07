package com.linkedladies.troop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkedladies.troop.R;
import com.linkedladies.troop.models.Message;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private List<Message> messages;

    public MessagesAdapter(List<Message> messages) {
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
        holder.tvMessagesType.setText(message.getTitle());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.tvMessageType)
        TextView tvMessagesType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
