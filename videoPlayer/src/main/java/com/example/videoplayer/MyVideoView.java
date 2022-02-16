package com.example.videoplayer;

import android.content.Context;
import android.widget.VideoView;

import java.util.List;

public class MyVideoView extends VideoView {
    private List<String> urlList;
    private String poster;

    public MyVideoView(Context context) {
        super(context);
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
