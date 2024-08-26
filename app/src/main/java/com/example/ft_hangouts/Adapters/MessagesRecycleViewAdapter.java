package com.example.ft_hangouts.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ft_hangouts.Models.Message;
import com.example.ft_hangouts.R;

import java.util.ArrayList;

public class MessagesRecycleViewAdapter extends RecyclerView.Adapter<MessagesRecycleViewAdapter.ViewHolder> {

    private ArrayList<Message> messages = new ArrayList<>();
    private Context context;

    public MessagesRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.messages_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.messageText.setText(messages.get(position).getMessage());
        holder.messageDate.setText(messages.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView messageText;
        private TextView messageDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            messageDate = itemView.findViewById(R.id.messageDate);
        }
    }
}
