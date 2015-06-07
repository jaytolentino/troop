package com.linkedladies.troop.net;

import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;

interface TroopService {

    static final String CHAT_ENDPOINT = "/chat";
    static final String LOVE_ENDPOINT = "/love";
    static final String HELP_ENDPOINT = "/help";
    static final String MESSAGES_ENDPOINT = "/messages";

    @GET(CHAT_ENDPOINT + MESSAGES_ENDPOINT)
    void getMessages(Callback<Results> callback);

    @POST(CHAT_ENDPOINT + LOVE_ENDPOINT)
    void postLove(Callback<Results> callback);

    @POST(CHAT_ENDPOINT + HELP_ENDPOINT)
    void postHelp(Callback<Results> callback);
}
