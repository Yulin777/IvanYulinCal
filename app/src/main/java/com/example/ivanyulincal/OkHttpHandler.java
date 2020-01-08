package com.example.ivanyulincal;

import android.os.AsyncTask;

import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class OkHttpHandler extends AsyncTask<String, Void, String> {
    private OkHttpClient client = new OkHttpClient();
    private Consumer<String> onSuccess;
    private Runnable onFailure;

    OkHttpHandler(Consumer<String> onSuccess, Runnable onFailure) {
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;
    }

    @Override
    protected String doInBackground(String... strings) {
        Request request = new Request.Builder()
                .url(strings[0])
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            onFailure.run();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            onSuccess.accept(s);
        }
    }
}

