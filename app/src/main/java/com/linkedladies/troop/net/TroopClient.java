package com.linkedladies.troop.net;

import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.RestAdapter;

public class TroopClient {

    private static final String BASE_URL = "http://troop.herokuapp.com/v1.0";

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

    public static void sendLove(Callback<Results> callback) {
        getService().postLove(callback);
    }

}
