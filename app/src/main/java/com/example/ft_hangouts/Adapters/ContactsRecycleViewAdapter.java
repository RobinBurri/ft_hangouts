package com.example.ft_hangouts.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ft_hangouts.Activities.MainActivity;
import com.example.ft_hangouts.Activities.ManageContactActivity;
import com.example.ft_hangouts.Activities.MessagesActivity;
import com.example.ft_hangouts.Models.Contact;
import com.example.ft_hangouts.R;

import java.util.ArrayList;

public class ContactsRecycleViewAdapter extends RecyclerView.Adapter<ContactsRecycleViewAdapter.ViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private Context context;

    public ContactsRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.contacts_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.contactFirstName.setText(contacts.get(position).getFirstName());
        holder.contactLastName.setText(contacts.get(position).getLastName());

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
//        Glide.with(mainContext)
//                .asBitmap()
//                .load(contacts.get(position).getImageUrl())
//                .into(holder.contactImage);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // hold the view for each item in the recycler view
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView contactImage;
        private TextView contactFirstName, contactLastName;
        private ImageView contactIcon, messageIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initializeViews();
        }

        private void initializeViews() {
            contactImage = itemView.findViewById(R.id.contactImage);
            contactFirstName = itemView.findViewById(R.id.contactFirstName);
            contactLastName = itemView.findViewById(R.id.contactLastName);
            contactIcon = itemView.findViewById(R.id.icClientManager);
            messageIcon = itemView.findViewById(R.id.icMessage);
        }

    }
}
