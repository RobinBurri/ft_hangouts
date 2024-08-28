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

public class CreateContactActivity extends BaseActivity {

    private DBHelper db;
    private EditText phoneNumberInput, firstNameInput, lastNameInput, nicknameInput;
    private TextView pictureTxtInput;
    private Button btnSelectPic, btnSaveContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);
    }

    private void initializeView() {
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        nicknameInput = findViewById(R.id.nickNameInput);
        pictureTxtInput = findViewById(R.id.pictureTxtInput);
        btnSelectPic = findViewById(R.id.btnSelectPic);
        btnSaveContact = findViewById(R.id.btnSaveContact);
    }

    private void addClickListener() {
        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateContactActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                if (phoneNumberInput.getText().toString().isEmpty() || firstNameInput.getText().toString().isEmpty()) {
                    Toast.makeText(CreateContactActivity.this, "firstName and Phone number are mandatory.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact newContact = new Contact(phoneNumberInput.getText().toString(), firstNameInput.getText().toString(), "pictureTxtInput", lastNameInput.getText().toString(), nicknameInput.getText().toString());
                db.addContact(newContact);
                clearInputs();
                Intent intent = new Intent(CreateContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnSelectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateContactActivity.this, "btnSelectPic clicked", Toast.LENGTH_SHORT).show();
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