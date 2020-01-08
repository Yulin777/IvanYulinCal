package com.example.ivanyulincal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MetadataJsonParser {
    private final JSONObject data;
    private JSONArray playlists;

    MetadataJsonParser(JSONObject jsonObject) throws JSONException {
        data = jsonObject;
        playlists = data.getJSONArray("items");
    }

    int getPlaylistsCount() throws Exception {
        return data.getJSONObject("pageInfo").getInt("totalResults");
    }

    int getTotalVideosCount() throws Exception {
        int cnt = 0;

        for (int i = 0; i < getPlaylistsCount(); i++) {
            cnt += getVideosCount(i);
        }

        return cnt;
    }

    JSONObject getPlaylist(int playlistPosition) throws Exception {
        return playlists.getJSONObject(playlistPosition);
    }

    JSONObject getVideo(int playlistPosition, int videoPosition) throws Exception {
        return getPlaylist(playlistPosition).getJSONObject("playlistItems").getJSONArray("items").getJSONObject(videoPosition);
    }

    int getVideosCount(int listPosition) throws Exception {
        return getPlaylist(listPosition).getJSONObject("contentDetails").getInt("itemCount");
    }
}
