package com.salmanitb.alumnisalman;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(R.string.title_home);
                    fragment = new SalmanMenyapaFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle(R.string.title_profile);
                    return true;
                case R.id.navigation_map:
                    toolbar.setTitle(R.string.title_map);
                    return true;
                case R.id.navigation_gift:
                    toolbar.setTitle(R.string.title_gift);
                    return true;
                case R.id.navigation_contact:
                    toolbar.setTitle(R.string.title_contact);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        loadFragment(new SalmanMenyapaFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
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
        String datetime, headline, content, imageLocation;

        datetime = ((TextView) view.findViewById(R.id.datetime)).getText().toString();
        headline = ((TextView) view.findViewById(R.id.headline)).getText().toString();
        content = ((TextView) view.findViewById(R.id.content_full)).getText().toString();
        imageLocation = ((WebView) view.findViewById(R.id.webview_image)).getUrl().toString();

        Post post = new Post(datetime, headline, "", content, imageLocation);
        intent.putExtra("POST", post);
        startActivity(intent);
    }
}
