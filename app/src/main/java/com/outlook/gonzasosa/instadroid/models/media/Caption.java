package com.outlook.gonzasosa.instadroid.models.media;

import com.google.gson.annotations.SerializedName;
import com.outlook.gonzasosa.instadroid.ui.Utils;

public class Caption {
    @SerializedName(Utils.MEDIAELEMENT_SERIALIZED_NAME_CREATED_TIME)
    private long createdTime;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_TEXT)
    private String text;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_ID)
    private long id;

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }
}