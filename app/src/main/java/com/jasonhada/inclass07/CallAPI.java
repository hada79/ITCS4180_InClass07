package com.jasonhada.inclass07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CallAPI {
    final static String url = "http://ec2-18-234-222-229.compute-1.amazonaws.com/api";
    final OkHttpClient client = new OkHttpClient();
    IFunctions functions;

    public CallAPI(IFunctions functions) {
        this.functions = functions;
    }

    public void GetThreads(String token) throws Exception {
        String threadUrl = url + "/thread";

        Request request = new Request.Builder()
                .url(threadUrl)
                .addHeader("Authorization", "BEARER " + token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    JSONObject root = null;
                    try {
                        root = new JSONObject(myResponse);
                        JSONArray threadsJSONArray = root.getJSONArray("threads");
                        if (threadsJSONArray.length() > 0) {
                            Gson gson = new Gson();
                            //TypeToken<ArrayList<Thread>> token = new TypeToken<ArrayList<Thread>>() {};
                            Type collectionType = new TypeToken<ArrayList<Thread>>(){}.getType();
                            ArrayList<Thread> threads = gson.fromJson(root.toString(), collectionType);

                            functions.returnThreads(threads);
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    final String myResponse = response.body().string();

                    JSONObject root = null;
                    try {
                        root = new JSONObject(myResponse);
                        String message = root.getString("message");
                        functions.displayMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void SignUp(User user) throws Exception {
        String signupUrl = url + "/signup";

        RequestBody formBody = new FormBody.Builder()
                .add("email", user.getEmail())
                .add("password", user.getPassword())
                .add("fname", user.getFirstName())
                .add("lname", user.getLastName())
                .build();

        Request request = new Request.Builder()
                .url(signupUrl)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    User user = new Gson().fromJson(myResponse, User.class);
                    functions.saveToken(user);
                } else {
                    final String myResponse = response.body().string();

                    JSONObject root = null;
                    try {
                        root = new JSONObject(myResponse);
                        String message = root.getString("message");
                        functions.displayMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void Login(User user) throws Exception{

        String loginUrl = url + "/login";

        RequestBody formBody = new FormBody.Builder()
                .add("email", user.getEmail())
                .add("password", user.getPassword())
                .build();

        Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    User user = new Gson().fromJson(myResponse, User.class);
                    functions.saveToken(user);
                } else {
                    final String myResponse = response.body().string();

                    JSONObject root = null;
                    try {
                        root = new JSONObject(myResponse);
                        String message = root.getString("message");
                        functions.displayMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
