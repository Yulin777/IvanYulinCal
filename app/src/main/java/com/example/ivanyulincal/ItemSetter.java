package com.example.ivanyulincal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

class ItemSetter {
    ItemJsonParser itemJsonParser;
    View itemView;
    private ImageView thumbnail;
    private TextView channelTitle;
    private TextView localTitle;
    private TextView publishedAt;

    ItemSetter(@NonNull View itemView) {
        this.itemView = itemView;
        findViews();
    }

    private void findViews() {
        thumbnail = itemView.findViewById(R.id.list_thumbnail);
        channelTitle = itemView.findViewById(R.id.channel_title);
        localTitle = itemView.findViewById(R.id.local_title);
        publishedAt = itemView.findViewById(R.id.published_at);
    }

    void setViews(JSONObject jsonObject) throws Exception {
        itemJsonParser = new ItemJsonParser(jsonObject);

        Glide.with(thumbnail).load(itemJsonParser.getThumbnail()).apply(new RequestOptions().centerCrop()).into(thumbnail);

        channelTitle.setText(itemJsonParser.getChannelTitle());

        localTitle.setText(itemJsonParser.getLocalTitle());

        publishedAt.setText(itemJsonParser.getPublishedAt());
    }

    void setChildClick(Presenter mPresenter) {
        itemView.setOnClickListener(v -> {
            try {
                String id = itemJsonParser.getVideoId();
                mPresenter.onVideoClicked(id);
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
            }
        });

    }

}
