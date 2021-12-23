package com.example.dead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignIn extends AppCompatActivity {
    EditText email, password;
    Button signin, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signin = findViewById(R.id.SignIn);
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            finish();
        });
        signin.setOnClickListener(v -> {
            signIn();
        });

    }

    private void signIn() {
        email = findViewById(R.id.Email);
        password  = findViewById(R.id.Password);
        OkHttpClient client = new OkHttpClient();
        FormBody  formBody = new FormBody.Builder()
                .add("email", email.getText().toString())
                .add("password", password.getText().toString()).build();
        Request request = new Request.Builder().url("http://cinema.areas.su/auth/login").post(formBody).build();
        Log.d("Ready","Im ready");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Test","Test");
                Toast.makeText(getApplicationContext(), "Данные введены неверно", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {

                        JSONObject json = new JSONObject(response.body().string());

                        GlobalVars.token = json.getInt("token");
                        Log.d("Token", String.valueOf(GlobalVars.token));
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                   Log.d("Error","response.body().string()");
                }
            }
        });
    }
}