package com.example.dead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Profile extends AppCompatActivity {
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email = findViewById(R.id.Email);
        name = findViewById(R.id.Name);
        OkHttpClient client = new OkHttpClient();
        Log.d("Token2", String.valueOf(GlobalVars.token));
        Request request = new Request.Builder().url("http://cinema.areas.su/user").addHeader("Authorization","Bearer " + GlobalVars.token).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error","Dnt");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {


                    JSONArray jsonar = new JSONArray(response.body().string());
                    JSONObject json = jsonar.getJSONObject(0);

                    runOnUiThread(() -> {
                        try {
                            email.setText(json.getString("email"));
                            name.setText(json.getString("firstName")+" " + json.getString("lastName"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}