package com.example.ft_hangouts.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import com.example.ft_hangouts.DataBase.DBHelper;
import com.example.ft_hangouts.R;

public class MessagesActivity extends BaseActivity {

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_messages);
        db = DBHelper.getInstance(this);
    }
}