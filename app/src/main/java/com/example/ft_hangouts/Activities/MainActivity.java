package com.example.ft_hangouts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ft_hangouts.Adapters.ContactsListViewAdapter;
import com.example.ft_hangouts.DataBase.DBHelper;
import com.example.ft_hangouts.Models.Contact;
import com.example.ft_hangouts.R;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends BaseActivity {

    private DBHelper db;
    private ListView contactsListView;
    private ContactsListViewAdapter adapter;
    private ArrayList<Contact> contacts;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);

        ArrayList<Contact> contacts = db.getAllContacts();
        adapter = new ContactsListViewAdapter(this, contacts);
        contactsListView.setAdapter(adapter);
        updateEmptyView(contacts);
    }

    private void initializeView() {
        btnAddContact = findViewById(R.id.btnAddContact);
        contactsListView = findViewById(R.id.contactsListView);
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
            contactsListView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            contactsListView.setVisibility(View.VISIBLE);
        }
    }
}
