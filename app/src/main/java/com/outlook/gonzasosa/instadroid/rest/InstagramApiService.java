package com.outlook.gonzasosa.instadroid.rest;

import com.outlook.gonzasosa.instadroid.models.media.MediaData;
import com.outlook.gonzasosa.instadroid.models.search.SearchData;
import com.outlook.gonzasosa.instadroid.ui.Utils;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface InstagramApiService {
    @GET(Utils.URL_USERS_MEDIA_RECENT)
    void getUsersMediaRecent (@Path(Utils.MEDIAELEMENT_SERIALIZED_NAME_ID) int id,
                              @Query(Utils.MEDIAELEMENT_SERIALIZED_NAME_ACCESS_TOKEN) String accessToken,
                              @Query("count") int count,
                              Callback<MediaData> callback);


    @GET(Utils.URL_USERS_SEARCH)
    void searchUsers (@Query(Utils.MEDIAELEMENT_SERIALIZED_NAME_QUERY) String query,
                      @Query(Utils.MEDIAELEMENT_SERIALIZED_NAME_ACCESS_TOKEN) String token,
                      Callback<SearchData> callback);
}
