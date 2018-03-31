package com.salmanitb.alumnisalman.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.adapter.SearchAlumniAdapter;
import com.salmanitb.alumnisalman.model.User;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    SearchAlumniAdapter adapter;
    SearchView editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(true);
        searchView.requestFocus();
        searchView.setFocusableInTouchMode(true);

        // Locate the editText in search_list_item.xml
        editSearch = (android.support.v7.widget.SearchView) findViewById(R.id.search);


        //Locate the recyclerView in search_list_item
        recyclerView = (RecyclerView) findViewById(R.id.alumniSearchListRecycler);

        final Context context = this;
        adapter = new SearchAlumniAdapter(this, new ArrayList<User>(), new SearchAlumniAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User tutor) {

            }
        });

        for (int i = 0; i < 10; i++) {
            User user = new User("1", "Muhammad Hilmi A", "mhilmiasyrofi@gmail.com", "Imam Tua");
            adapter.add(i, user);
        }

        // Binds the adapter to the listview
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }
}
