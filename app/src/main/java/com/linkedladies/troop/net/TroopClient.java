package com.linkedladies.troop.net;

import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.RestAdapter;

public class TroopClient {

    private static final String BASE_URL = "https://troop-service.herokuapp.com/api";

    private static TroopService troopService;

    private static TroopService getService() {
        if (troopService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.HEADERS)
                    .build();

            troopService = restAdapter.create(TroopService.class);
        }
        return troopService;
    }

    public static void getSessionFriends(Callback<Results> callback) {
        getService().getSessionFriends(callback);
    }

    public static void getMessages(Callback<Results> callback) {
        getService().getMessages(callback);
    }

    public static void sendLove(Friend friend, Callback<Results> callback) {
        getService().postLove(friend, callback);
    }

    public static void sendHelp(Friend friend, Callback<Results> callback) {
        getService().postHelp(friend, callback);
    }

    public static void sendSupport(Friend friend, Callback<Results> callback) {
        getService().postSupport(friend, callback);
    }

    public static void sendRecover(Friend friend, Callback<Results> callback) {
        getService().postRecover(friend, callback);
    }

    public static void closeSession(Callback<Results> callback) {
        getService().deleteSession(callback);
    }

}
