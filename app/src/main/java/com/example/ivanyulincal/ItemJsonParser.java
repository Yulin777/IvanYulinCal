package com.example.ivanyulincal;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

class ItemJsonParser {

    private final JSONObject data;
    private JSONObject snippet;

    ItemJsonParser(JSONObject jsonObject) throws JSONException {
        this.data = jsonObject;
        this.snippet = data.getJSONObject("snippet");
    }

    String getThumbnail() throws JSONException {
        return snippet.getJSONObject("thumbnails").getJSONObject("default").getString("url");
    }

    String getChannelTitle() throws JSONException {
        return snippet.getString("channelTitle");
    }

    String getLocalTitle() throws JSONException {
        return snippet.getString("title");
    }

    String getPublishedAt() throws Exception {
        String date = snippet.getString("publishedAt");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date);
        return new SimpleDateFormat("MMM dd, yyyy").format(date1); //change date format
    }


    String getVideoId()  throws Exception {
        return data.getJSONObject("contentDetails").getString("videoId");
    }
}
