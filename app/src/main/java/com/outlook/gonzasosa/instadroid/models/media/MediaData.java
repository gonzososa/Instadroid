package com.outlook.gonzasosa.instadroid.models.media;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MediaData {
    @Nullable
    private Pagination pagination;

    @Nullable
    private Meta meta;

    @SerializedName ("data")
    private ArrayList<MediaElement> elements;

    public Pagination getPagination () {
        return pagination;
    }

    public void setPagination (Pagination pagination) {
        this.pagination = pagination;
    }

    public ArrayList<MediaElement> getElements () {
        return elements;
    }

    public void setData(ArrayList<MediaElement> elements) {
        this.elements = elements;
    }

    public Meta getMeta () {
        return meta;
    }

    public void setMeta (Meta meta) {
        this.meta = meta;
    }

    public class Meta {
        @SerializedName ("code")
        public int code;
    }
}
