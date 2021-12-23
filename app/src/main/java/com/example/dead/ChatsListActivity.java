package com.example.dead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ChatsListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChats;
    private ChatAdapter chatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_list);
        recyclerViewChats = findViewById(R.id.recyclerViewChats);
        recyclerViewChats.setLayoutManager(new LinearLayoutManager(ChatsListActivity.this));
        ImageButton imageButtonArrowBack = findViewById(R.id.imageButtonArrowBack);
        getChats();

        imageButtonArrowBack.setOnClickListener(e->
        {
            startActivity(new Intent(this, MainActivity.class)); //Заменить на переход в профиль
            finish();
        });
    }

    private void getChats()
    {
        OkHttpClient client = new OkHttpClient();

        /*FormBody formBody = new FormBody.Builder()
                        .add("token", String.valueOf(53424))
                        .build();*/

        Request request = new Request.Builder()
                .get()
                .addHeader("Authorization", "Bearer " + 563496)
                .url("http://cinema.areas.su/user/chats")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try(ResponseBody responseBody = response.body())
                {
                    List<Chat> chatList = new ArrayList<Chat>();
                    if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    assert responseBody != null;
                    JSONArray jsonArray = new JSONArray(responseBody.string());
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Chat a_chat = new Chat(jsonObject.getInt("chatId"), jsonObject.getString("name"));
                        chatList.add(a_chat);
                    }
                    chatAdapter = new ChatAdapter(chatList, ChatsListActivity.this);
                    Log.i("INFO", String.valueOf(chatAdapter.getItemCount()));
                    runOnUiThread(() -> recyclerViewChats.setAdapter(chatAdapter));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}