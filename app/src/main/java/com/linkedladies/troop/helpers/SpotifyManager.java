package com.linkedladies.troop.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.linkedladies.troop.R;
import com.linkedladies.troop.data.UserDataManager;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SpotifyManager {

    private static final int REQUEST_CODE = 1313;
    private static final String CLIENT_ID = "8bcb9084f49d4db7b2548652542b47dc";
    private static final String REDIRECT_URI = "fitfam-android://callback";

    private Activity activity;

    private LoginHandler loginHandler;

    public SpotifyManager(Activity activity) {
        this.activity = activity;
    }

    public interface LoginHandler {
        public void onSuccess();
        public void onFailure(AuthenticationResponse.Type responseType);
    }

    /**
     * Initiate the Spotify login flow using the Spotify SDK's webview implementation
     *
     * @param loginHandler - handles the login results
     */
    public void login(LoginHandler loginHandler) {
        String[] permissions = new String[]{"streaming"};

        AuthenticationRequest request =
                new AuthenticationRequest.Builder(
                        CLIENT_ID,
                        AuthenticationResponse.Type.TOKEN,
                        REDIRECT_URI)
                .setScopes(permissions)
                .build();

        AuthenticationClient.openLoginActivity(activity, REQUEST_CODE, request);
    }

    /**
     * Place inside the onActivityResult() of any activity or fragment using the SpotifyManager
     *
     * @param requestCode - request code caught by the owning activity or fragment
     * @param resultCode - result code caught by the owning activity or fragment
     * @param data - intent caught by the owning activity or fragment
     */
    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) {
            return;
        }

        AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);
        switch (response.getType()) {
            case TOKEN:
                UserDataManager dataManager = new UserDataManager();
                String tokenKey = activity.getString(R.string.spotify_token);
                dataManager.put(tokenKey, response.getAccessToken());
                loginHandler.onSuccess();
                break;

            case ERROR:
                loginHandler.onFailure(response.getType());
                break;

            default:
                // Most likely a cancellation
                loginHandler.onFailure(response.getType());
        }
    }

    public static void logout(Context context) {
        AuthenticationClient.logout(context);
    }
}
