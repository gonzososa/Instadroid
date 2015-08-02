package com.outlook.gonzasosa.instadroid.models.media;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.outlook.gonzasosa.instadroid.ui.Utils;

import java.util.ArrayList;

public class MediaElement {
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_LINK)
    private String link;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_CREATED_TIME)
    private long createDateTime;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_TYPE)
    private String type;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_FILTER)
    private String filter;

    @Nullable
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_TAGS)
    private ArrayList<String> tags;

    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_ID)
    private String id;

    @SerializedName ("images")
    private Image image;

    @Nullable
    @SerializedName ("location")
    private Location location;

    @Nullable
    private Caption caption;

    public Caption getCaption () {
        return caption;
    }

    public void setCaption (Caption caption) {
        this.caption = caption;
    }

    public long getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(long createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public Image getImages () {
        return image;
    }

    public void setImages (Image image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
