package com.outlook.gonzasosa.instadroid.rest;

import com.outlook.gonzasosa.instadroid.ui.Utils;

import retrofit.RestAdapter;

public class InstagramApiAdapter {
    private static InstagramApiService API;

    public static InstagramApiService getAPI () {
        if (API == null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint (Utils.URL_SERVICE_BASE)
                    .setLogLevel (RestAdapter.LogLevel.BASIC)
                    .build();

            API = adapter.create (InstagramApiService.class);
        }

        return API;
    }
}
