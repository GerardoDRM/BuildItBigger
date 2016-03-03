package com.udacity.gerardo.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gerardo.builitbigger.backend.jokeApi.JokeApi;
import com.udacity.gerardo.jokedisplay.JokeActivity;

import java.io.IOException;

/**
 * Created by gerardo on 2/03/16.
 */
public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private static JokeApi myApiService = null;
    private Context context;
    private ProgressBar mSpinner;

    public EndpointsAsyncTask(Context context, ProgressBar spinner) {
        this.context = context;
        this.mSpinner = spinner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mSpinner != null) {
            mSpinner.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mSpinner != null) {
            mSpinner.setVisibility(View.GONE);
        }
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}