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

import static com.salmanitb.alumnisalman.activity.MainActivity.users;


public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    SearchAlumniAdapter adapter;
    SearchView editSearch;

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
        searchView.setQueryHint("Ketikkan nama farah!");
        searchView.setFocusable(true);
        searchView.requestFocus();
        searchView.setFocusableInTouchMode(true);
        if (searchQuery != null) {
            searchView.setQuery(searchQuery, true);
            getAlumniList(searchQuery);
            // Binds the adapter to the listview
            recyclerView.setAdapter(adapter);
//            Log.d("Users ", "" + users.size());

        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getAlumniList(query);
                if (adapter.getItemCount() == 0) {
                    searchView.setQueryHint("Ketikkan nama farah!");
                } else {
                }
                // Binds the adapter to the listview
                recyclerView.setAdapter(adapter);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getAlumniList(newText);
                if (adapter.getItemCount() == 0) {
                    searchView.setQueryHint("Ketikkan nama farah!");
                } else {
                }

                // Binds the adapter to the listview
                recyclerView.setAdapter(adapter);

                return false;
            }
        });

    }

    private void getAlumniList(String query) {

//        for (int i = 0; i < users.size(); i++) {
//            adapter.add(i, users.get(i));
//        }
        APIConnector.getInstance().searchUser(query, new APIConnector.ApiCallback<SearchUserResponse>() {
            @Override
            public void onSuccess(SearchUserResponse response) {
//                appDescription.setText(response.getAbout());
//                txtAddress.setText(response.getAddress());
//                txtPhone.setText(response.getPhone());
//                txtEmail.setText(response.getEmail());
//                PreferenceManager.getInstance().setAboutData(response);
//                Log.e("API Search", response.getName().toString());
            }

            @Override
            public void onFailure(Throwable t) {
//                Toast.makeText(, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
