package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.ContactFragment;
import com.salmanitb.alumnisalman.fragment.MapFragment;
import com.salmanitb.alumnisalman.fragment.ProfilFragment;
import com.salmanitb.alumnisalman.fragment.SalmanMenyapaFragment;
import com.salmanitb.alumnisalman.model.City;
import com.salmanitb.alumnisalman.model.Post;
import com.salmanitb.alumnisalman.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Fragment salmanMenyapaFragment;
    Fragment alumniFragment;
    Fragment contactFragment;
    Fragment profilFragment;
    Fragment mapFragment;

    //untuk nyoba maps dan profile
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<City> cities = new ArrayList<City>();

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
                    loadFragment(profilFragment);
                    return true;
                case R.id.navigation_map:
                    toolbar.setVisibility(View.GONE);
                    loadFragment(mapFragment);
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

        //untuk nyoba maps dan profile
        users.clear();
        User user = new User(1, "Muhammad Hilmi A", "mhilmiasyrofi@gmail.com", "Imam Tua");
        users.add(user);
        user = new User(2, "Farah Ghezi Athaya", "farah@gmail.com", "Istri Tua");
        users.add(user);
        user = new User(3, "Winsya Hesaputri Suryawan", "winsy@gmail.com", "Istri Muda");
        users.add(user);
        user = new User(4, "Hasna Marhamah Aulia", "hasna@gmail.com", "Istri Muda Kedua");
        users.add(user);
        user = new User(5, "Fadhil Imam Kurnia", "fadhil@gmail.com", "Carry");
        users.add(user);

        cities.clear();
        City city = new City("Bandung", -6.8919607, 107.6156134);
        cities.add(city);
        city = new City("Jakarta", -5.7773029, 106.3971043);
        cities.add(city);
        city = new City("Yogyakarta", -7.803249, 110.3397397);
        cities.add(city);
        city = new City("Papua", -4.8703029, 135.5597428);
        cities.add(city);

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

}
