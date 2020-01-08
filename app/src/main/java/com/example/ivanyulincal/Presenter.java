package com.example.ivanyulincal;

import android.widget.ExpandableListView;

import org.json.JSONObject;

import java.util.function.Consumer;

class Presenter implements ExpandableListView.OnGroupExpandListener {
    private MyView mView;
    private int lastExpandedPosition = -1;

    Presenter(MyView view) {
        this.mView = view;
    }

    private Consumer<String> onCallSuccess = s -> {
        try {
            JSONObject jObject = new JSONObject(s);
            ExpandableListView ev = mView.getExpandableListView();
            ((ExpandablePlaylistsAdapter) ev.getExpandableListAdapter()).setData(jObject);

        } catch (Exception e) {
            e.printStackTrace();
            mView.onError();
        }
    };

    private Runnable onCallFailed = () -> mView.onError();

    void getData() {
        OkHttpHandler okHttpHandler = new OkHttpHandler(onCallSuccess, onCallFailed);
        okHttpHandler.execute("https://landing.cal-online.co.il/youtube/playlists.json");
    }

    void onVideoClicked(String id) {
        mView.onVideoClicked(id);
    }

    void onError() {
        mView.onError();
    }

    /**
     * just to collapse any other playlist when another one is opened
     */
    @Override
    public void onGroupExpand(int groupPosition) {
        if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
            mView.getExpandableListView().collapseGroup(lastExpandedPosition);
        }
        lastExpandedPosition = groupPosition;
    }
}
