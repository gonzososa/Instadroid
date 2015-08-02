package com.outlook.gonzasosa.instadroid.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.outlook.gonzasosa.instadroid.R;
import com.outlook.gonzasosa.instadroid.adapters.SearchAdapter;
import com.outlook.gonzasosa.instadroid.models.search.SearchData;
import com.outlook.gonzasosa.instadroid.rest.InstagramApiAdapter;
import com.outlook.gonzasosa.instadroid.ui.Utils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchFragment extends Fragment {
    public static SearchFragment getInstance (String query) {
        SearchFragment searchFragment = new SearchFragment ();

        Bundle bundle = new Bundle ();
        bundle.putString("query", query);
        searchFragment.setArguments (bundle);

        return searchFragment;
    }

    private String getQuery () {
        String query = "";

        if (getArguments () != null) {
            query = getArguments().getString ("query");
        }

        return query;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RecyclerView listSearch = (RecyclerView) getActivity().findViewById (R.id.listSearch);
        listSearch.setLayoutManager (new LinearLayoutManager (getActivity()));

        String q = getQuery ();
        String token = Utils.getServiceToken (getActivity());

        InstagramApiAdapter.getAPI().searchUsers (q, token, new Callback<SearchData>() {
            @Override
            public void success (SearchData searchData, Response response) {
                listSearch.setAdapter (new SearchAdapter (getActivity (), searchData.data));
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make (getView (), error.getMessage (), Snackbar.LENGTH_LONG).show ();
            }
        });
    }


}
