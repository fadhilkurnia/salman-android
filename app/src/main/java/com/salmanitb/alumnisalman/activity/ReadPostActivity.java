package com.salmanitb.alumnisalman.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.model.Post;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadPostActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @BindView(R.id.news_title)
    TextView newsTitle;

    @BindView(R.id.news_time)
    TextView newsTime;

    @BindView(R.id.news_view_count)
    TextView newsViewCount;

    @BindView(R.id.news_like_count)
    TextView newsLikeCount;

    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.main_image)
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_post);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("POST");

        newsTitle.setText(post.getHeadline());
        Picasso.get().load(post.getImageLocation()).fit().centerCrop().into(mainImage);
        newsTime.setText(post.getDatetime());

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Loading", "Please wait ...", true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressDialog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });

        webView.loadUrl("https://today.line.me/id/article/Tarik+Produk+dari+Pasar+Heinz+ABC+Indonesia+Investigasi+Temuan+Cacing-RemzMw");
    }

}
