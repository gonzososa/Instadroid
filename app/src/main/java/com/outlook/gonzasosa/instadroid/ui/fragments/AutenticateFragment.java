package com.outlook.gonzasosa.instadroid.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.outlook.gonzasosa.instadroid.listeners.OnAuthenticateSucessListener;
import com.outlook.gonzasosa.instadroid.models.oauth.Token;
import com.outlook.gonzasosa.instadroid.ui.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AutenticateFragment extends Fragment {
    OnAuthenticateSucessListener listener;
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        webView = new WebView (getActivity().getBaseContext());
        webView.setWebChromeClient(new WebChromeClient());

        //webview needs a progressbar
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith (Utils.OAUTH_CLIENT_REDIRECT_URL)) {
                    Uri uri = Uri.parse (url);
                    if (uri.getQueryParameter ("code") != null) {
                        String code = uri.getQueryParameter ("code");
                        //Snackbar.make (getView (), "Code: " + code, Snackbar.LENGTH_LONG).show ();
                        getAuthenticateToken (code);
                        return true;
                    } else if (uri.getQueryParameter("error") != null) {
                        String message = uri.getQueryParameter ("error");
                        Snackbar.make (getView (), message, Snackbar.LENGTH_LONG).show ();
                    }
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        };

        webView.setWebViewClient(webViewClient);
        WebSettings settings = webView.getSettings ();
        settings.setJavaScriptEnabled (true);

        return webView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String OAUTH_SERVICE_CODE_URL = Utils.OAUTH_SERVICE_CODE_URL_BASE;
        OAUTH_SERVICE_CODE_URL += Utils.OAUTH_SERVICE_CODE_CLIENT_ID + Utils.INSTAGRAM_CLIENT_ID;
        OAUTH_SERVICE_CODE_URL += Utils.OAUTH_SERVICE_CODE_REDIRECT_URI + Utils.OAUTH_CLIENT_REDIRECT_URL;
        OAUTH_SERVICE_CODE_URL += Utils.OAUTH_SERVICE_CODE_RESPONSE_TYPE;

        Log.i(Utils.TAG, OAUTH_SERVICE_CODE_URL);
        webView.loadUrl(OAUTH_SERVICE_CODE_URL);
    }

    public void setOnAuthenticateListener (OnAuthenticateSucessListener listener) {
        this.listener = listener;
    }

    public void getAuthenticateToken (String code) {
        requestToken (code, Utils.OAUTH_SERVICE_TOKEN_URL)
            .subscribeOn (Schedulers.io ())
            .observeOn  (AndroidSchedulers.mainThread ())
            .subscribe  (new Observer<Token>() {
                @Override
                public void onCompleted () {}

                @Override
                public void onError (Throwable e) {}

                @Override
                public void onNext (Token token) {
                    if (token != null) {
                        listener.authenticateSucesss(token);
                    } else {
                        Snackbar.make (getView (), Utils.OAUTH_ERROR_DURING_AUTHENTICATION,
                                        Snackbar.LENGTH_LONG).show ();
                    }
                }
            });
    }

    private Observable<Token> requestToken (final String code, final String uri) {
        return Observable.create (new Observable.OnSubscribe<Token>() {
            @Override
            public void call (Subscriber<? super Token> subscriber) {
                subscriber.onNext (donwloadStreamAndParseJson (code, uri));
            }
        });
    }

    private Token donwloadStreamAndParseJson (String code, String uri) {
        Token token = null;

        try {
            URL url = new URL (uri);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection ();
            connection.setRequestMethod ("POST");
            connection.setDoOutput (true);
            connection.setRequestProperty("Accept", "application/json");

            OutputStream outputStream = connection.getOutputStream ();
            byte [] buffer = getRequestTokenBody (code).getBytes ("UTF-8");
            outputStream.write (buffer);
            outputStream.flush();
            outputStream.close ();

            InputStreamReader isr = new InputStreamReader (connection.getInputStream(), "utf8");
            BufferedReader reader = new BufferedReader (isr);
            StringBuilder builder = new StringBuilder ();
            String line;

            while ((line = reader.readLine ()) != null) {
                builder.append (line);
            }

            reader.close ();
            isr.close();
            connection.disconnect ();

            String content = builder.toString ();
            Log.i (Utils.TAG, content);

            Gson gson = new Gson ();
            token = gson.fromJson (content, Token.class);
        } catch (IOException e) {
            Log.e (Utils.TAG, "IOError", e);
        }

        return token;
    }

    private String getRequestTokenBody (String code) {
        return Utils.OAUTH_SERVICE_TOKEN_CLIENT_ID + Utils.INSTAGRAM_CLIENT_ID
        + Utils.OAUTH_SERVICE_TOKEN_CLIENT_SECRET + Utils.INSTAGRAM_CLIENT_SECRET
        + Utils.OAUTH_SERVOCE_TOKEN_GRANT_TYPE
        + Utils.OAUTH_SERVICE_TOKEN_REDIRECT_URI + Utils.OAUTH_CLIENT_REDIRECT_URL
        + Utils.OAUTH_SERVICE_TOKEN_CODE + code;
    }
}
