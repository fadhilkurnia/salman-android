package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.model.Post;

public class ReadPostActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCP4a36d6Q4ZKWav5e_ELlBhZmf6wOwgls";
    public static String VIDEO_ID = "";

    private Toolbar toolbar;
    private TextView datetime, headline;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_post);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("POST");

        datetime = (TextView) findViewById(R.id.datetime);
        headline = (TextView) findViewById(R.id.headline);
        webView = (WebView) findViewById(R.id.webview);

        datetime.setText(post.getDatetime());
        headline.setText(post.getHeadline());
        webView.loadUrl("http://kabar.salmanitb.com/2015/01/25/kalam-salman-keterhubungan-antar-alumni-penting/");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
