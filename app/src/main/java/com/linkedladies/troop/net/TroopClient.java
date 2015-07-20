package com.linkedladies.troop.net;

import com.linkedladies.troop.BuildConfig;
import com.linkedladies.troop.models.Device;
import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.RestAdapter;

public class TroopClient {

    private static final String BASE_URL = "https://troop-service.herokuapp.com/api";
    private static final String SANDBOX = "https://gfwm.herokuapp.com/";

    private static TroopService troopService;
    private static DebugService debugService;

    private static TroopService getTroopService() {
        if (troopService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            troopService = restAdapter.create(TroopService.class);
        }
        return troopService;
    }

    private static DebugService getDebugService() {
        if (debugService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(SANDBOX)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            debugService = restAdapter.create(DebugService.class);
        }
        return debugService;
    }

    public static void getSessionFriends(Callback<Results> callback) {
        getTroopService().getSessionFriends(callback);
    }

    public static void getMessages(Callback<Results> callback) {
        getTroopService().getMessages(callback);
    }

    public static void sendLove(Friend friend, Callback<Results> callback) {
        getTroopService().postLove(friend, callback);
    }

    public static void sendHelp(Friend friend, Callback<Results> callback) {
        getTroopService().postHelp(friend, callback);
    }

    public static void sendSupport(Friend friend, Callback<Results> callback) {
        getTroopService().postSupport(friend, callback);
    }

    public static void sendRecover(Friend friend, Callback<Results> callback) {
        getTroopService().postRecover(friend, callback);
    }

    public static void closeSession(Callback<Results> callback) {
        getTroopService().deleteSession(callback);
    }

    public static void sendDeviceInfo(Device device, Callback<Results> callback) {
        if (BuildConfig.DEBUG) {
            getDebugService().postDeviceInfo(device, callback);
        }
    }

}
