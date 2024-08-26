package com.example.ft_hangouts.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import com.example.ft_hangouts.DataBase.DBHelper;
import com.example.ft_hangouts.R;

public class SettingsActivity extends BaseActivity {

    private DBHelper db;
    private Button btnLanguageDefault, btnLanguageEnglish, btnColorDefault, btnColorDark, btnColorLight, btnColorOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);
    }

    private void initializeView() {
        btnLanguageDefault = findViewById(R.id.btnLanguageDefault);
        btnLanguageEnglish = findViewById(R.id.btnLanguageEnglish);
        btnColorDefault = findViewById(R.id.btnColorDefault);
        btnColorDark = findViewById(R.id.btnColorBlackWhite);
        btnColorLight = findViewById(R.id.btnColorBlue);
        btnColorOrange = findViewById(R.id.btnColorOrange);
    }

    private void addClickListener() {
        if (btnLanguageDefault != null) {
            btnLanguageDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "Default Language clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (btnLanguageEnglish != null) {
            btnLanguageEnglish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "English Language clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (btnColorDefault != null) {
            btnColorDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "Default Color clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (btnColorDark != null) {
            btnColorDark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "Dark Color clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (btnColorLight != null) {
            btnColorLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "Light Color clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (btnColorOrange != null) {
            btnColorOrange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingsActivity.this, "Orange Color clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}