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

    public Friend(String userid) {
        this.userId = userid;
    }

    public Constants.UserState getState() {
        return Constants.UserState.fromString(state);
    }

    public String getUserId() {
        return userId;
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