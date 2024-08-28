package com.example.ft_hangouts.Activities;

import android.os.Bundle;
import android.widget.ListView;

import com.example.ft_hangouts.Adapters.MsgListViewAdapter;
import com.example.ft_hangouts.Utils.DBHelper;
import com.example.ft_hangouts.Models.Message;
import com.example.ft_hangouts.R;
import com.example.ft_hangouts.Utils.ThemeUtils;

import java.util.ArrayList;

public class MessagesActivity extends BaseActivity {

    private DBHelper db;
    private ListView messagesListView;
    private MsgListViewAdapter adapter;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        messagesListView = findViewById(R.id.messagesListView);
        db = DBHelper.getInstance(this);

        messages = new ArrayList<>();

        adapter = new MsgListViewAdapter(this, messages);
        messagesListView.setAdapter(adapter);
    }
}