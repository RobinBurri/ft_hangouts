package com.example.ft_hangouts.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ft_hangouts.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class BaseActivity extends Activity {
    ImageView leftArrowIcon, settingsIcon;
    private long backgroundTime;
    private SimpleDateFormat dateFormat;
    private boolean isAppInBackground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        FrameLayout rootLayout = new FrameLayout(this);
        View activityContent = getLayoutInflater().inflate(layoutResID, rootLayout, false);
        rootLayout.addView(activityContent);

        super.setContentView(rootLayout);
        dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        initializeView();
        addClickListeners();
    }

    private void initializeView() {
        leftArrowIcon = findViewById(R.id.left_arrow_icon);
        settingsIcon = findViewById(R.id.settings_icon);
    }

    private void addClickListeners() {
        if (leftArrowIcon != null) {
            leftArrowIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLeftArrowClick();
                }
            });
        }
        if (settingsIcon != null) {
            settingsIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSettingsClick();
                }
            });
        }
    }

    protected void onLeftArrowClick() {
        if (!(this instanceof MainActivity)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

    protected void onSettingsClick() {
        if (!(this instanceof SettingsActivity)) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isAppInBackground) {
            isAppInBackground = false;
            if (backgroundTime > 0) {
                String formattedTime = dateFormat.format(new Date(backgroundTime));
                String message = getString(R.string.background_time_message, formattedTime);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isFinishing()) {
            backgroundTime = System.currentTimeMillis();
            isAppInBackground = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isAppInBackground = false;
    }

}