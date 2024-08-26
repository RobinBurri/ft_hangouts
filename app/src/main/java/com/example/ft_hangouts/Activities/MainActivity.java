package com.example.ft_hangouts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ft_hangouts.Adapters.ContactsRecycleViewAdapter;
import com.example.ft_hangouts.DataBase.DBHelper;
import com.example.ft_hangouts.Models.Contact;
import com.example.ft_hangouts.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private DBHelper db;
    private RecyclerView contactsRecyclerView;
    private ContactsRecycleViewAdapter adapter;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);

        ArrayList<Contact> contacts = db.getAllContacts();
        adapter = new ContactsRecycleViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecyclerView.setAdapter(adapter);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateEmptyView(contacts);
    }

    private void initializeView() {
        btnAddContact = findViewById(R.id.btnAddContact);
        contactsRecyclerView = findViewById(R.id.contactsRecyclerView);
    }

    private void addClickListener() {
        if (btnAddContact != null) {
            btnAddContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void updateEmptyView(ArrayList<Contact> contacts) {
        TextView emptyView = findViewById(R.id.emptyView);
        if (contacts.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            contactsRecyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            contactsRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
