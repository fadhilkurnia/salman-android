package com.salmanitb.alumnisalman.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.adapter.PostAdapter;
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

        newsTitle.setText(post.getTitle());
        Picasso.get().load(post.getImageURL()).fit().centerCrop().into(mainImage);
        newsTime.setText(PostAdapter.decodeUnixTime(post.getCreatedAt()));

        String txtLike = String.valueOf(post.getLoveCount()) + " suka";
        String txtView = String.valueOf(post.getViewCount()) + " tayang";
        newsLikeCount.setText(txtLike);
        newsViewCount.setText(txtView);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Loading", "Sedang memuat konten ...", true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
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

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                webView.setVisibility(View.GONE);
                Toast.makeText(ReadPostActivity.this, "Gagal memuat konten", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(post.getContentURL());
    }

}
