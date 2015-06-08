package com.linkedladies.troop.app;

import com.linkedladies.troop.data.UserDataManager;
import com.linkedladies.troop.helpers.Constants;
import com.linkedladies.troop.models.Friend;

/**
 * Represents the currently logged-in user
 */
public class User {

    private static User instance;
    private UserDataManager userDataManager;

    private User() {
        userDataManager = new UserDataManager();
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setUserid(String userid) {
        userDataManager.put("userid", userid);
    }

    public void setState(Constants.UserState state) {
        userDataManager.put("userState", state.toString());
    }

    public Constants.UserState getState() {
        return Constants.UserState.fromString(userDataManager.getString("userState"));
    }

    public String getUserid() {
        return userDataManager.getString("userid");
    }

    public Friend asFriend() {
        return new Friend(getUserid());
    }

}
