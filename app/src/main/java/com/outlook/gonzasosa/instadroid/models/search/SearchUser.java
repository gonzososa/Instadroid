package com.outlook.gonzasosa.instadroid.models.search;

import com.google.gson.annotations.SerializedName;

public class SearchUser {
    private String username;

    @SerializedName ("first_name")
    private String firstName;

    @SerializedName ("profile_picture")
    private String profilePicture;

    private int id;

    @SerializedName ("last_name")
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
