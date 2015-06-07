package com.linkedladies.troop.net;

import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.http.POST;

interface TroopService {

    static final String LOVE_ENDPOINT = "/love";
    static final String HELP_ENDPOINT = "/help";

    @POST(LOVE_ENDPOINT)
    void postLove(Callback<Results> callback);

    @POST(HELP_ENDPOINT)
    void postHelp(Callback<Results> callback);
}