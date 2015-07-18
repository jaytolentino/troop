package com.linkedladies.troop.net;

import com.linkedladies.troop.models.Friend;
import com.linkedladies.troop.models.Results;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;

interface TroopService {

    /**
     * Action Endpoints
     */
    String LOVE_ENDPOINT = "/love";
    String HELP_ENDPOINT = "/help";
    String SUPPORT_ENDPOINT = "/support";
    String RECOVER_ENDPOINT = "/recover";

    String SESSION_RESET_ENDPOINT = "/reset";
    String FRIENDS_ENDPOINT = "/friends";
    String MESSAGE_ENDPOINT = "/message";
    String MESSAGES_ENDPOINT = "/messages";

    @GET(FRIENDS_ENDPOINT)
    void getSessionFriends(Callback<Results> callback);

    @GET(MESSAGES_ENDPOINT)
    void getMessages(Callback<Results> callback);

    @POST(MESSAGE_ENDPOINT + LOVE_ENDPOINT)
    void postLove(@Body Friend friend, Callback<Results> callback);

    @POST(MESSAGE_ENDPOINT + HELP_ENDPOINT)
    void postHelp(@Body Friend friend, Callback<Results> callback);

    @POST(MESSAGE_ENDPOINT + SUPPORT_ENDPOINT)
    void postSupport(@Body Friend friend, Callback<Results> callback);

    @POST(MESSAGE_ENDPOINT + RECOVER_ENDPOINT)
    void postRecover(@Body Friend friend, Callback<Results> callback);

    @DELETE(SESSION_RESET_ENDPOINT)
    void deleteSession(Callback<Results> callback);
}
