package com.example.ivanyulincal;

import android.widget.ExpandableListView;

interface MyView {

    void onError();

    ExpandableListView getExpandableListView();

    void onVideoClicked(String id);
}
