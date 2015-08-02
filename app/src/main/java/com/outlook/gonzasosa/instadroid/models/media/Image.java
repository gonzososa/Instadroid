package com.outlook.gonzasosa.instadroid.models.media;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName ("low_resolution")
    private ResolutionInfo lowResolution;

    @SerializedName ("thumbnail")
    private ResolutionInfo thumbnail;

    @SerializedName ("standard_resolution")
    private ResolutionInfo standardResolution;

    public ResolutionInfo getLowResolution () {
        return lowResolution;
    }

    public void setLowResolution (ResolutionInfo resolutionInfo) {
        this.lowResolution = resolutionInfo;
    }

    public ResolutionInfo getThumbnail () {
        return thumbnail;
    }

    public void setThumbnail (ResolutionInfo resolutionInfo) {
        this.thumbnail = resolutionInfo;
    }

    public ResolutionInfo getStandardResolution () {
        return standardResolution;
    }

    public void setStandardResolution (ResolutionInfo resolutionInfo) {
        this.standardResolution = resolutionInfo;
    }

    public class ResolutionInfo {
        @SerializedName ("url")
        private String url;

        @SerializedName ("width")
        private int width;

        @SerializedName ("height")
        private int height;

        public int getHeight () {
            return height;
        }

        public void setHeight (int height) {
            this.height = height;
        }

        public String getUrl () {
            return url;
        }

        public void setUrl (String url) {
            this.url = url;
        }

        public int getWidth () {
            return width;
        }

        public void setWidth (int width) {
            this.width = width;
        }
    }
}

