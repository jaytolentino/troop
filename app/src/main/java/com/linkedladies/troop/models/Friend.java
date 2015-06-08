package com.linkedladies.troop.models;

import com.google.gson.annotations.SerializedName;
import com.linkedladies.troop.helpers.Constants;

public class Friend {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("image_url")
    private String imageUrl;

    private String state;

    // TODO remove (temporary while waiting for backend release
    public Friend(String userId, String firstName, String lastName, String imageUrl, String state) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.state = state;
    }

    public Friend(String userid) {
        this.userId = userid;
    }

    public Constants.UserState getState() {
        return Constants.UserState.fromString(state);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}