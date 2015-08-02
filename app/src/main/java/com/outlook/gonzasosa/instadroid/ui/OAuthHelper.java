package com.outlook.gonzasosa.instadroid.ui;

/*
import android.util.Log;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
*/

public class OAuthHelper {
    /*public static final String OAUTH_SERVICE_CODE_URL = "";
    public static final String OAUTH_SERVICE_TOKEN_URL = "";
    public static final String OAUTH_CLIENT_REDIRECT_URL = "";
    public static final String TAG = "OAUTH HELPER";

    public static String getUrl () {
        OAuthClientRequest request = null;
        String result = null;

        try {
            request = OAuthClientRequest.authorizationLocation (OAUTH_SERVICE_CODE_URL)
                        .setClientId ("")
                        .setResponseType ("code")
                        .setRedirectURI (OAUTH_CLIENT_REDIRECT_URL)
                        .buildQueryMessage();

            result = request.getLocationUri ();
        } catch (OAuthSystemException e) {
            Log.e(TAG, "Error OAUTH", e);
        } catch (NullPointerException e) {
            Log.e (TAG, "NPE", e);
        }

        return result;
    }

    public static void getAccessToken (final String accessCode) {
        new Runnable() {
            @Override
            public void run() {

            }
        }.run ();
    }*/
}
