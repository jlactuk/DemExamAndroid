package com.example.dead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SignUp extends AppCompatActivity {
    EditText name, email,lastname, password,password2;
    Button signup, signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(v -> {
            Signup();
        });
        signin = findViewById(R.id.SignIn);
        signin.setOnClickListener(v -> {
            Intent intent = new Intent(this,  SignIn.class);
            startActivity(intent);
            finish();
        });
    }

    private void Signup() {
        name = findViewById(R.id.Name);
        lastname = findViewById(R.id.lastName);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        password2 = findViewById(R.id.Password2);
        String nameStr = name.getText().toString(),
                emailStr = email.getText().toString(),
                passwordStr = password.getText().toString(),
                password2Str = password2.getText().toString(),
                lastnameStr = lastname.getText().toString();
        if(!password2Str.equals(passwordStr)) {
            Toast.makeText(getApplicationContext(),"Пароли не совпадают",Toast.LENGTH_LONG).show();
        }
        else {
            if (password2Str.trim().equals("") && passwordStr.trim().equals("") && nameStr.trim().equals("") && emailStr.trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Не все данные введены", Toast.LENGTH_LONG).show();
            }
            else {
                OkHttpClient client = new OkHttpClient();
                FormBody formBody = new FormBody.Builder()
                        .add("firstName", nameStr)
                        .add("lastName",lastnameStr)
                        .add("email",emailStr)
                        .add("password", passwordStr).build();
                Request request = new Request.Builder().post(formBody).url("http://cinema.areas.su/auth/register").build();
                Log.d("Ya ebal","Rabotaem");
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("Error", "Govno");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()) {
                            Log.d("Answer", response.body().string());
                            Intent intent =  new Intent(getApplicationContext(),SignIn.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Log.d("Error", response.body().string());
                        }
                    }
                });

            }

        }
    }
}