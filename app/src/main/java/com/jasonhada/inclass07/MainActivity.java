package com.jasonhada.inclass07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements IFunctions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(!isConnected())
        {
            Toast.makeText(this,"No Internet detected, please check your Internet connectivity!", Toast.LENGTH_LONG);
        }


        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login() throws Exception {
        final EditText email = (EditText) findViewById(R.id.editTextEMail);
        final EditText password = (EditText) findViewById(R.id.editTextPassword);

        User loginUser = new User(email.getText().toString(), password.getText().toString());
        new CallAPI(this).Login(loginUser);
    }

    @Override
    public void saveToken(User user) {
        SharedPreferences.Editor sharedPreferences = getSharedPreferences(getString(R.string.app_name), this.MODE_PRIVATE).edit();
        sharedPreferences.putString("token", user.getToken());
        sharedPreferences.commit();

        Intent intent = new Intent(this, MessageThreadsActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;

    }

    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void displayMessage(String message) {
        showToast(message);
    }

    @Override
    public void returnThreads(ArrayList<Thread> threads) {

    }
}
