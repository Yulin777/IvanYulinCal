package com.example.ivanyulincal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class MainActivity extends AppCompatActivity implements MyView {
    Presenter mPresenter;
    RecyclerView playlistsRecyclerView; //note: changed to ExpandableListView

    /*
    as i understood from "the app should drill down and show each item of the selected playlist"
    there should be no transition to a different activity/fragment
    that is the reason i chose the ExpandableListView to expand a sub list of videos on a playlist tap
     */
    ExpandableListView expandableList;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);

        findViews();
        setViews();
        mPresenter.getData();
    }

    private void setViews() {
        setExpandableList();
    }

    private void setExpandableList() {
        expandableList.setAdapter(new ExpandablePlaylistsAdapter(mPresenter));
        expandableList.setOnGroupExpandListener(mPresenter);
    }

    private void findViews() {
        playlistsRecyclerView = findViewById(R.id.playlists_list);
        expandableList = findViewById(R.id.expandable_list);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "oops, something happened...", Toast.LENGTH_LONG).show();
    }

    @Override
    public ExpandableListView getExpandableListView() {
        return expandableList;
    }

    @Override
    public void onVideoClicked(String id) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(MainActivity.this, getString(R.string.youtube_key), id, 0, true, true);
        startActivity(intent);
    }
}
