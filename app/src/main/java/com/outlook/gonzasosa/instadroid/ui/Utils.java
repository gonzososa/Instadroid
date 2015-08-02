package com.outlook.gonzasosa.instadroid.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    public static final String INSTAGRAM_CLIENT_ID                  = "b5b89b553c394b619199790df5dc8ddf";
    public static final String INSTAGRAM_CLIENT_SECRET              = "df5a04ef60f64267ae8d943d347c4754";

    public static final String OAUTH_SERVICE_CODE_URL_BASE          = "https://api.instagram.com/oauth/authorize/?";
    public static final String OAUTH_SERVICE_CODE_CLIENT_ID         = "client_id=";
    public static final String OAUTH_SERVICE_CODE_REDIRECT_URI      = "&redirect_uri=";
    public static final String OAUTH_SERVICE_CODE_RESPONSE_TYPE     = "&response_type=code";
    public static final String OAUTH_SERVICE_TOKEN_URL              = "https://api.instagram.com/oauth/access_token";
    public static final String OAUTH_CLIENT_REDIRECT_URL            = "http://www.testingapiv1.net/auth";
    public static final String OAUTH_SERVICE_TOKEN_CLIENT_ID        = "client_id=";
    public static final String OAUTH_SERVICE_TOKEN_CLIENT_SECRET    = "&client_secret=";
    public static final String OAUTH_SERVOCE_TOKEN_GRANT_TYPE       = "&grant_type=authorization_code";
    public static final String OAUTH_SERVICE_TOKEN_REDIRECT_URI     = "&redirect_uri=";
    public static final String OAUTH_SERVICE_TOKEN_CODE             = "&code=";

    public static final String OAUTH_ERROR_DURING_AUTHENTICATION    = "Error during authentication. Try again";

    public static final String TAG                                  = "OAUTH HELPER";
    public static final String SHARED_PREFERENCES_KEY               = "token";
    public static final String SHARED_PREFERENCES_CONTEXT           = "Instadroid";

    public static final String MEDIAELEMENT_SERIALIZED_NAME_ACCESS_TOKEN   = "access_token";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_ID      = "id";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_CREATED_TIME = "created_time";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_LINK    = "link";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_TYPE    = "type";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_USERS_IN_PHOTO = "users_in_photo";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_FILTER  = "filter";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_TAGS    = "tags";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_TEXT    = "text";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_LATITUDE  = "latitude";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_LONGITUDE = "longitude";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_NAME      = "name";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_STREET_ADDRESS = "street_address";

    public static final String URL_SERVICE_BASE                     = "https://api.instagram.com/v1";
    public static final String URL_USERS_MEDIA_RECENT               = "/users/{id}/media/recent/?access_token=token&count=max";
    public static final String URL_USERS_SEARCH                     = "/users/search?q=query&access_token=token";
    public static final String MEDIAELEMENT_SERIALIZED_NAME_QUERY   = "q";

    public static String getServiceToken (Context context) {
        SharedPreferences preferences = context.getSharedPreferences (SHARED_PREFERENCES_CONTEXT, Context.MODE_PRIVATE);
        return preferences.getString (SHARED_PREFERENCES_KEY, "");
    }

    public static void setServiceToken (Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences (SHARED_PREFERENCES_CONTEXT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit ();
        editor.putString (SHARED_PREFERENCES_KEY, token);
        editor.apply ();
    }
}
