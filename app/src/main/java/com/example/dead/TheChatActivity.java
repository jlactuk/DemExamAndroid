package com.example.dead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class TheChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chat);
        ImageButton imageButtonArrowBack = findViewById(R.id.imageButtonArrowBack);
        imageButtonArrowBack.setOnClickListener(e->
        {
            startActivity(new Intent(this, ChatsListActivity.class)); //Заменить на переход в профиль
            finish();
        });
    }
}