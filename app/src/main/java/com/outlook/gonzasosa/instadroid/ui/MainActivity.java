package com.outlook.gonzasosa.instadroid.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.outlook.gonzasosa.instadroid.listeners.OnSearchTextChangedListener;
import com.outlook.gonzasosa.instadroid.ui.fragments.MediaFragment;
import com.outlook.gonzasosa.instadroid.R;
import com.outlook.gonzasosa.instadroid.listeners.OnAuthenticateSucessListener;
import com.outlook.gonzasosa.instadroid.models.oauth.Token;
import com.outlook.gonzasosa.instadroid.ui.fragments.AutenticateFragment;
import com.outlook.gonzasosa.instadroid.ui.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity implements
        OnAuthenticateSucessListener, OnSearchTextChangedListener /*, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener*/ {

    AutenticateFragment autenticateFragment;
    MediaFragment mediaFragment;

    @Override
    public void onBackPressed() {
       if (getSupportFragmentManager().getBackStackEntryCount () > 0) {
           getSupportFragmentManager().popBackStackImmediate ();
           getSupportFragmentManager().beginTransaction().commit ();
       } else {
           super.onBackPressed();
       }
    }

    @Override
    public void onCreate (Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);

        /*SharedPreferences e = getSharedPreferences (Utils.SHARED_PREFERENCES_CONTEXT, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = e.edit();
        ed.clear();
        ed.apply();*/

        if (Utils.getServiceToken (getBaseContext ()).equals ("")) {
            autenticateFragment = new AutenticateFragment();
            autenticateFragment.setOnAuthenticateListener(this);
            getSupportFragmentManager().beginTransaction ()
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add (R.id.main_content, autenticateFragment)
                .commit ();
        } else {
            mediaFragment = new MediaFragment ();
            mediaFragment.setOnSearchTextListener(this);
            getSupportFragmentManager().beginTransaction()
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add (R.id.main_content, mediaFragment)
                .commit ();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

//        if (getSupportActionBar () != null) {
//            MenuItem item = menu.findItem (R.id.action_search);
//            SearchView searchView = new SearchView (getSupportActionBar().getThemedContext ());
//            MenuItemCompat.setShowAsAction (item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
//            MenuItemCompat.setActionView(item, searchView);
//            searchView.setOnQueryTextListener(this);
//            MenuItemCompat.setOnActionExpandListener (item, this);
//        }

        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public void authenticateSucesss (Token token) {
        Utils.setServiceToken (getBaseContext(), token.accessToken);

        mediaFragment = new MediaFragment ();
        mediaFragment.setOnSearchTextListener(this);
        getSupportFragmentManager().beginTransaction()
                .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace (R.id.main_content, mediaFragment)
                .commit ();

    }

    @Override
    public void searchTextChanged (String query) {
        SearchFragment searchFragment = SearchFragment.getInstance (query);
        getSupportFragmentManager().beginTransaction ()
            .setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace (R.id.main_content, searchFragment)
                .addToBackStack (null)
                .commit ();
    }


    /*@Override
    public boolean onQueryTextSubmit (String query) {
        SearchFragment searchFragment = SearchFragment.getInstance (query);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction ();
        transaction.replace (R.id.main_content, searchFragment);
        transaction.commit ();

        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return true;
    }*/
}