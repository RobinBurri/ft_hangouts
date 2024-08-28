package com.example.ft_hangouts.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    private static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 2;

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

    protected void pickImage() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_READ_STORAGE);
        } else {
            openImagePicker();
        }
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(BaseActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            onImagePicked(selectedImageUri);
        }
    }

    protected void onImagePicked(Uri imageUri) {
    }

}