package com.example.ft_hangouts.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ft_hangouts.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class BaseActivity extends Activity {
    ImageView leftArrowIcon, settingsIcon;
    private long lastPauseTime;
    private static int activityReferences = 0;
    private static boolean isActivityChangingConfigurations = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActionBar()).hide();
    }

    @Override
    public void setContentView(int layoutResID) {
        FrameLayout rootLayout = new FrameLayout(this);
        View activityContent = getLayoutInflater().inflate(layoutResID, rootLayout, false);
        rootLayout.addView(activityContent);
        super.setContentView(rootLayout);
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
    protected void onStart() {
        super.onStart();
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            if (lastPauseTime > 0) {
                String timeString = DateFormat.getTimeInstance().format(new Date(lastPauseTime));
                Toast.makeText(this, "App went to background at: " + timeString, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActivityChangingConfigurations = isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            lastPauseTime = System.currentTimeMillis();
        }
    }

}