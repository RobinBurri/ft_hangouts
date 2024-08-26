package com.example.ft_hangouts.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ft_hangouts.Models.Message;
import com.example.ft_hangouts.R;

import java.util.ArrayList;

public class MsgListViewAdapter extends BaseAdapter {
    private ArrayList<Message> messages;
    private Context context;
    private LayoutInflater inflater;

    public MsgListViewAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.messages_list_item, parent, false);
            holder = new ViewHolder();
            holder.messageText = convertView.findViewById(R.id.messageText);
            holder.messageDate = convertView.findViewById(R.id.messageDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Message message = messages.get(position);
        holder.messageText.setText(message.getMessage());
        holder.messageDate.setText(message.getDate());

        return convertView;
    }

    static class ViewHolder {
        TextView messageText;
        TextView messageDate;
    }
}
