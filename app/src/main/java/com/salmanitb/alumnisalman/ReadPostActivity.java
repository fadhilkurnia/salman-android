package com.salmanitb.alumnisalman;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class ReadPostActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCP4a36d6Q4ZKWav5e_ELlBhZmf6wOwgls";
    public static final String VIDEO_ID = "5xUCnEixL-s";

    private Toolbar toolbar;
    private TextView datetime, headline, content;
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
        content = (TextView) findViewById(R.id.content);
        webView = (WebView) findViewById(R.id.webview_image);

        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager()
                .findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(API_KEY, this);

        datetime.setText(post.getDatetime());
        headline.setText(post.getHeadline());
        content.setText(post.getContent());
        webView.loadUrl(post.getImageLocation());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(null== player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
}
