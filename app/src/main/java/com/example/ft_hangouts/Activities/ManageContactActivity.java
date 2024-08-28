package com.example.ft_hangouts.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ft_hangouts.Utils.DBHelper;
import com.example.ft_hangouts.Models.Contact;
import com.example.ft_hangouts.R;
import com.example.ft_hangouts.Utils.ThemeUtils;

public class ManageContactActivity extends BaseActivity {

    private DBHelper db;
    private EditText phoneNumberInput, firstNameInput, lastNameInput, nicknameInput;
    private TextView pictureTxtInput;
    private Button btnSelectPic, btnSaveContact, btnDeleteContact;
    int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_contact);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);
        contactId = getIntent().getIntExtra("contact_id", -1);
        if (contactId != -1) {
            loadContact();
        }
    }

    private void loadContact() {
        Contact contact = db.getContact(contactId);
        if (contact != null) {
            phoneNumberInput.setText(contact.getPhoneNumber());
            firstNameInput.setText(contact.getFirstName());
            lastNameInput.setText(contact.getLastName());
            nicknameInput.setText(contact.getNickName());
            pictureTxtInput.setText(contact.getPicture());
        }
    }

    private void initializeView() {
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        nicknameInput = findViewById(R.id.nickNameInput);
        pictureTxtInput = findViewById(R.id.pictureTxtInput);
        btnSelectPic = findViewById(R.id.btnSelectPic);
        btnSaveContact = findViewById(R.id.btnSaveContact);
        btnDeleteContact = findViewById(R.id.btnDeleteContact);
    }

    private void addClickListener() {
        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumberInput.getText().toString().isEmpty() || firstNameInput.getText().toString().isEmpty()) {
                    Toast.makeText(ManageContactActivity.this, "firstName and Phone number are mandatory.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact newContact = new Contact(phoneNumberInput.getText().toString(), firstNameInput.getText().toString(), "pictureTxtInput", lastNameInput.getText().toString(), nicknameInput.getText().toString());
                db.addContact(newContact);
                clearInputs();
                Intent intent = new Intent(ManageContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSelectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManageContactActivity.this, "btnSelectPic clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(contactId);
                clearInputs();
                Intent intent = new Intent(ManageContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearInputs() {
        phoneNumberInput.setText("");
        firstNameInput.setText("");
        lastNameInput.setText("");
        nicknameInput.setText("");
        pictureTxtInput.setText("");
    }
}