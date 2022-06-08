package com.example.urbest;

public class UserFavorites {
    int  favoriteImage;
    String favoriteName;

    public UserFavorites() {
    }

    public UserFavorites(int favoriteImage, String favoriteName) {
        this.favoriteImage = favoriteImage;
        this.favoriteName = favoriteName;
    }

    public int getFavoriteImage() {
        return favoriteImage;
    }

    public String getFavoriteName() {
        return favoriteName;
    }
}
