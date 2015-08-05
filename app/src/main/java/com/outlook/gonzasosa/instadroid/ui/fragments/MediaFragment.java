package com.outlook.gonzasosa.instadroid.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.outlook.gonzasosa.instadroid.R;
import com.outlook.gonzasosa.instadroid.adapters.MediaAdapter;
import com.outlook.gonzasosa.instadroid.listeners.OnSearchTextChangedListener;
import com.outlook.gonzasosa.instadroid.models.media.MediaData;
import com.outlook.gonzasosa.instadroid.rest.InstagramApiAdapter;
import com.outlook.gonzasosa.instadroid.ui.Utils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MediaFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {
    OnSearchTextChangedListener listener;

    public MediaFragment() {}

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setHasOptionsMenu (true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_media, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i (Utils.TAG, "Media Fragment Created");

        final RecyclerView listMedia = (RecyclerView) getActivity().findViewById (R.id.listMedia);
        listMedia.setLayoutManager (new LinearLayoutManager(getActivity ()));

        InstagramApiAdapter.getAPI().getUsersMediaRecent
            (386274185,
            "14027441.ba6c721.9c2dd62a02c24cd2abb481ef6f6c3547",
            33,
            new Callback<MediaData>() {
                @Override
                public void success(MediaData mediaData, Response response) {
                    listMedia.setAdapter (new MediaAdapter (getActivity(), mediaData.getElements ()));
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText (getActivity(), error.getMessage (), Toast.LENGTH_LONG).show ();
                    Log.e (Utils.TAG, "Error GSON", error);
                }
            });
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        MenuItem item = menu.add ("Search");
        item.setIcon (R.drawable.ic_search_white_48dp);
        MenuItemCompat.setShowAsAction (item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS|MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        SearchView sv = new SearchView (getActivity ());
        MenuItemCompat.setActionView (item, sv);
        sv.setOnQueryTextListener (this);
        MenuItemCompat.setOnActionExpandListener(item, this);

        super.onCreateOptionsMenu (menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit (String query) {
        listener.searchTextChanged (query);
        return false;
    }

    public void setOnSearchTextListener (OnSearchTextChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }
}
