package com.jasonhada.inclass07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MessageThreadsActivity extends AppCompatActivity implements IFunctions{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Thread> threads = new ArrayList<Thread>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_threads);

        TextView name = findViewById(R.id.textViewUserFullname);
        //name.setText(user.getFirstName() + " " + user.getLastName());

        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor sharedPreferences = getSharedPreferences(getString(R.string.app_name), MessageThreadsActivity.this.MODE_PRIVATE).edit();
                sharedPreferences.remove("token");
                sharedPreferences.commit();
                finish();
            }
        });

        try {
            new CallAPI(MessageThreadsActivity.this).GetThreads(getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ThreadAdapter(threads);
        mRecyclerView.setAdapter((mAdapter));

    }

    private String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), this.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }

    @Override
    public void saveToken(User user) {

    }

    @Override
    public void displayMessage(String message) {

    }

    @Override
    public void returnThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }
}
