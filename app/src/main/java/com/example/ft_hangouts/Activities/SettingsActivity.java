package com.example.ft_hangouts.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ft_hangouts.Utils.DBHelper;
import com.example.ft_hangouts.R;
import com.example.ft_hangouts.Utils.ThemeUtils;

public class SettingsActivity extends BaseActivity {

    private DBHelper db;
    private Button btnLanguageDefault, btnLanguageEnglish, btnColorDark, btnColorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initializeView();
        addClickListener();
        db = DBHelper.getInstance(this);
    }

    private void initializeView() {
        btnLanguageDefault = findViewById(R.id.btnLanguageDefault);
        btnLanguageEnglish = findViewById(R.id.btnLanguageEnglish);
        btnColorDark = findViewById(R.id.btnColorBlackWhite);
        btnColorLight = findViewById(R.id.btnColorBlue);
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
        if (btnColorDark != null) {
            btnColorDark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThemeUtils.saveTheme(SettingsActivity.this, true);
                    recreate();
                }
            });
        }
        if (btnColorLight != null) {
            btnColorLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThemeUtils.saveTheme(SettingsActivity.this, false);
                    recreate();
                }
            });
        }
    }
}