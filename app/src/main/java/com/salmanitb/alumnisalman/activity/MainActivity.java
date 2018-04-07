package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.ContactFragment;
import com.salmanitb.alumnisalman.fragment.MapFragment;
import com.salmanitb.alumnisalman.fragment.ProfilFragment;
import com.salmanitb.alumnisalman.fragment.SalmanMenyapaFragment;
import com.salmanitb.alumnisalman.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Fragment salmanMenyapaFragment;
    Fragment alumniFragment;
    Fragment contactFragment;
    Fragment profilFragment;
    Fragment mapFragment;


    @BindView(R.id.my_toolbar) Toolbar toolbar;
    @BindView(R.id.navigation) BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setVisibility(View.GONE);
                    loadFragment(salmanMenyapaFragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setVisibility(View.GONE);
//                    toolbar.setTitle(R.string.title_profile);
                    loadFragment(profilFragment);
//                    toolbar.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_map:
                    toolbar.setVisibility(View.GONE);
//                    toolbar.setTitle(R.string.title_map);
                    loadFragment(mapFragment);
//                    toolbar.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_contact:
                    toolbar.setVisibility(View.GONE);
                    loadFragment(contactFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        salmanMenyapaFragment = new SalmanMenyapaFragment();
        alumniFragment = new SalmanMenyapaFragment();
        contactFragment = new ContactFragment();
        profilFragment = new ProfilFragment();
        mapFragment = new MapFragment();

        loadFragment(salmanMenyapaFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void openPost(View view) {
        Intent intent = new Intent(this, ReadPostActivity.class);
        String datetime, headline,youtubeVideoID , content, webViewImage;

        datetime = ((TextView) view.findViewById(R.id.datetime)).getText().toString();
        headline = ((TextView) view.findViewById(R.id.headline)).getText().toString();
        youtubeVideoID = ((TextView) view.findViewById(R.id.youtube_video_ID)).getText().toString();
        content = ((TextView) view.findViewById(R.id.content_full)).getText().toString();
        webViewImage = ((WebView) view.findViewById(R.id.webview_image)).getUrl().toString();

        Post post = new Post(datetime, headline, youtubeVideoID, content, webViewImage);
        intent.putExtra("POST", post);
        startActivity(intent);
    }

}
