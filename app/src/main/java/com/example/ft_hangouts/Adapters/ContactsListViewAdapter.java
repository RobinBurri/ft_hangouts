package com.example.ft_hangouts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ft_hangouts.Activities.ManageContactActivity;
import com.example.ft_hangouts.Activities.MessagesActivity;
import com.example.ft_hangouts.Models.Contact;
import com.example.ft_hangouts.R;

import java.util.ArrayList;

public class ContactsListViewAdapter extends BaseAdapter {
    private ArrayList<Contact> contacts;
    private Context context;
    private LayoutInflater inflater;

    public ContactsListViewAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contacts_list_item, parent, false);
            holder = new ViewHolder();
            holder.contactImage = convertView.findViewById(R.id.contactImage);
            holder.contactFirstName = convertView.findViewById(R.id.contactFirstName);
            holder.contactLastName = convertView.findViewById(R.id.contactLastName);
            holder.contactIcon = convertView.findViewById(R.id.icClientManager);
            holder.messageIcon = convertView.findViewById(R.id.icMessage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Contact contact = contacts.get(position);
        holder.contactFirstName.setText(contact.getFirstName());
        holder.contactLastName.setText(contact.getLastName());

        holder.messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessagesActivity.class);
                context.startActivity(intent);
            }
        });

        holder.contactIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManageContactActivity.class);
                intent.putExtra("contact_id", contact.getId());
                context.startActivity(intent);
            }
        });

        // Optionally load images using a library like Glide or Picasso
        // Glide.with(context).load(contact.getImageUrl()).into(holder.contactImage);

        return convertView;
    }

    static class ViewHolder {
        ImageView contactImage;
        TextView contactFirstName, contactLastName;
        ImageView contactIcon, messageIcon;
    }
}
