package com.outlook.gonzasosa.instadroid.models.oauth;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("access_token")
    public String accessToken;

    public User user;
}