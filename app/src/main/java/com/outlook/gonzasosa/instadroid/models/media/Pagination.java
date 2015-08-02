package com.outlook.gonzasosa.instadroid.models.media;


import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName ("next_url")
    private String nextUrl;

    @SerializedName ("next_max_id")
    private String maxId;
}
