package com.outlook.gonzasosa.instadroid.models.oauth;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName ("id")
    public int id;

    @SerializedName ("username")
    public String username;

    @SerializedName ("full_name")
    public String fullName;

    @SerializedName ("profile_picture")
    public String profilePicture;
}
