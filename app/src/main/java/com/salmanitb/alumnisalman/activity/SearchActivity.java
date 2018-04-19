package com.salmanitb.alumnisalman.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.adapter.SearchAlumniAdapter;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.SearchUserResponse;
import com.salmanitb.alumnisalman.model.User;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    SearchAlumniAdapter adapter;
    SearchView editSearch;

    ArrayList<User> alumni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String searchQuery;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                searchQuery= null;
            } else {
                searchQuery= extras.getString("SEARCH_QUERY");
            }
        } else {
            searchQuery= (String) savedInstanceState.getSerializable("SEARCH_QUERY");
        }

        setContentView(R.layout.activity_search);

        //Locate the recyclerView in search_list_item
        recyclerView = (RecyclerView) findViewById(R.id.alumniSearchListRecycler);
        // Binds the adapter to the listview
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final Context context = this;
        adapter = new SearchAlumniAdapter(this, new ArrayList<User>(), new SearchAlumniAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Toast.makeText(getApplicationContext(),user.getName() + "clicked", Toast.LENGTH_LONG).show();
            }
        });

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Ketikkan minimal 2 huruf!");
        searchView.setFocusable(true);
        searchView.requestFocus();
        searchView.setFocusableInTouchMode(true);
        if (searchQuery != null) {
            searchView.setQuery(searchQuery, true);
            getAlumniList(searchQuery);
            if (adapter.getItemCount() == 0)
                searchView.setQueryHint("Ketikkan minimal 2 huruf!");
            recyclerView.setAdapter(adapter);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() >= 2) {
                    getAlumniList(query);
                } else {
                    adapter.removeAll();
                    Toast.makeText(SearchActivity.this, "Minimal 2 huruf!", Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(adapter);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length() >= 2) {
                    getAlumniList(newText);
                } else {
                    adapter.removeAll();
                }
                recyclerView.setAdapter(adapter);
                return false;
            }
        });
    }

    private void getAlumniList(String query) {

        APIConnector.getInstance().searchUser(query, new APIConnector.ApiCallback<ArrayList<SearchUserResponse>>() {
            @Override
            public void onSuccess(ArrayList<SearchUserResponse> response) {

                if (alumni == null)
                    alumni = new ArrayList<User>();
                else
                    alumni.clear();

                if (response.isEmpty()) {
                    Toast.makeText(SearchActivity.this, "Pencarian tidak ditemukan", Toast.LENGTH_SHORT).show();
                    adapter.removeAll();
                    recyclerView.setAdapter(adapter);
                } else {
                    for (SearchUserResponse s : response) {
                        User user = new User(1, s.getName(), s.getEmail(), s.getUrlImg(), s.getCity());
                        alumni.add(user);
                    }
                    adapter.addAll(alumni);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
