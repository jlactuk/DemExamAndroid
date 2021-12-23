package com.example.dead;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolderChat>
{
    private List<Chat> chatsList;
    private Context context;

    public ChatAdapter(List<Chat> chatsList, Context context) {
        this.chatsList = chatsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderChat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolderChat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChat holder, int position) {
        Chat a_chat = chatsList.get(position);
        holder.textViewMovieName.setText(String.valueOf(a_chat.getName()));

        holder.cardViewTheChat.setOnClickListener(e->
        {
            Intent intent = new Intent(context, TheChatActivity.class);
            intent.putExtra("chatId", a_chat.getChatId());
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }

    public static class ViewHolderChat extends RecyclerView.ViewHolder {

        final CardView cardViewTheChat;
        final TextView textViewMovieName;

        public ViewHolderChat(@NonNull View itemView) {
            super(itemView);
            this.cardViewTheChat = itemView.findViewById(R.id.cardViewTheChat);
            this.textViewMovieName = itemView.findViewById(R.id.textViewMovieName);
        }
    }
}
