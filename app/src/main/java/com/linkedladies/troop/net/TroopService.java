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
    static final String LOVE_ENDPOINT = "/love";
    static final String HELP_ENDPOINT = "/help";
    static final String SUPPORT_ENDPOINT = "/support";
    static final String RECOVER_ENDPOINT = "/support";

    static final String SESSION_RESET_ENDPOINT = "/reset";
    static final String FRIENDS_ENDPOINT = "/friends";
    static final String MESSAGE_ENDPOINT = "/message";
    static final String MESSAGES_ENDPOINT = "/messages";

    @GET(FRIENDS_ENDPOINT)
    void getSessionFriends(Callback<Results> callback);

    @GET(MESSAGE_ENDPOINT + MESSAGES_ENDPOINT)
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
