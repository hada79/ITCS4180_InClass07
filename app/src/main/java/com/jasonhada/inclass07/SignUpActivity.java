package com.jasonhada.inclass07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity implements IFunctions {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
                EditText lastName = (EditText) findViewById(R.id.editTextLastName);
                EditText email = (EditText) findViewById(R.id.editTextEMail);
                EditText password = (EditText) findViewById(R.id.editTextPassword);
                EditText password2 = (EditText) findViewById(R.id.editTextPasswordConfirm);

                if(password.getText().toString().equals(password2.getText().toString()) ) {
                    User user = new User(email.getText().toString(), password.getText().toString());
                    user.firstName = firstName.getText().toString();
                    user.lastName = lastName.getText().toString();

                    try {
                        new CallAPI(SignUpActivity.this).SignUp(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this,"Passwords do not match!", Toast.LENGTH_LONG);
                }
            }
        });
    }


    @Override
    public void saveToken(User user) {
        SharedPreferences.Editor sharedPreferences = getSharedPreferences(getString(R.string.app_name), this.MODE_PRIVATE).edit();
        sharedPreferences.putString("token", user.getToken());
        sharedPreferences.commit();

        showToast("User created!");

        Intent intent = new Intent(this, MessageThreadsActivity.class);
        startActivity(intent);
        finish();
    }

    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SignUpActivity.this, toast, Toast.LENGTH_SHORT).show();
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
