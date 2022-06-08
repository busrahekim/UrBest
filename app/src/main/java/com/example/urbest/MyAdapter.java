package com.example.urbest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CardHolder> {
    private List<UserFavorites> favorites;

    public MyAdapter(List<UserFavorites> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_design_card,parent,false);

        return new CardHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {

        holder.favImg.setImageResource(favorites.get(position).favoriteImage);
        holder.favHeader.setText(favorites.get(position).getFavoriteName());

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder{

        public ImageView favImg;
        public TextView favHeader;

        public CardHolder(@NonNull View itemView) {
            super(itemView);

            favImg = itemView.findViewById(R.id.favoriteImage);
            favHeader = itemView.findViewById(R.id.favoriteTitle);

        }
    }
}
