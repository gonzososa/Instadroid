package com.outlook.gonzasosa.instadroid.ui.fragments;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/***
 * La descarga de archivos debe realizarse en un hilo diferente del principal por lo que
 * es necesario envolver todas esas operaciones en un clase que herede de AsyncTask
 *
 * https://developer.android.com/reference/android/os/AsyncTask.html
 *
  */
public class DownloadTextFile extends AsyncTask<String, Void, String> {
    private static final String TAG = "DOWLOADTEXTFILE";
    private WeakReference<TextView> weakReference;

    /***
     * Constructor de la clase que nos ayuda a mantener una referencia al control donde se
     * desplegará el contenido del archivo de texto descargado
     *
     * Se hace uso de la clase WeakReference que nos permite liberar la memoria utilizada por el control
     * en caso de ser necesario, lo que nos evista memory leaks.
     *
     * @param textView control de usuario que visualiza el texto correspondiente
     */
    public DownloadTextFile (TextView textView) {
        weakReference = new WeakReference<>(textView);
    }

    /**
     * Esta función se ejecuta de forma asíncrona, por lo que aquí no pueden utilizarse los controles de usuario
     * unicamente se utiliza para realizar operaciones de entrada/salida que no bloqueen la UI
     *
     * @param params los parámetros necesarios para ejecutar las operaciones, la url del archivo en este caso
     * @return la cadena de caracteres contenidos en el archivo descargado
     */
    @Override
    protected String doInBackground(String... params) {
        return getFile (params [0]);
    }

    private String getFile (String uri) {
        HttpsURLConnection urlConnection;
        String response = "";

        try {
            urlConnection = (HttpsURLConnection) new URL(uri).openConnection ();
            if (urlConnection.getResponseCode () != HttpsURLConnection.HTTP_OK) return response;

            InputStreamReader isr = new InputStreamReader (urlConnection.getInputStream (), "utf8");
            BufferedReader reader = new BufferedReader (isr);
            StringBuilder builder = new StringBuilder ();
            String line;

            while ((line = reader.readLine ()) != null) {
                builder.append (line);
            }

            isr.close ();
            reader.close ();
            urlConnection.disconnect ();

            response = builder.toString ();
        } catch (IOException ioex) {
            Log.e (TAG, ioex.getMessage ());
        }

        return response;
    }

    /***
     * esta función se ejecuta en el mismo hilo que la interfaz de usuario por lo que aquí sí es posible
     * manipular los controles de la UI
     *
     * @param s la cadena que contiene el resultado de la ejecución del método doInBackground
     */
    @Override
    protected void onPostExecute (String s) {
        weakReference.get().setText (s);
        super.onPostExecute (s);
    }
}
