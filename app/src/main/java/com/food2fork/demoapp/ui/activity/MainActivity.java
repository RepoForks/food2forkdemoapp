package com.food2fork.demoapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.food2fork.demoapp.R;
import com.food2fork.demoapp.models.SearchRecipe;
import com.food2fork.demoapp.models.SearchRecipeItem;
import com.food2fork.demoapp.network.ApiClient;
import com.food2fork.demoapp.ui.adapters.RecipesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        RecipesAdapter.RecipesListener {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getNextRecipe(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_actionbar, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getResources().getString(R.string.search_title));

        View closeButton = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.clearFocus();
                searchView.setQuery("", false);
                getNextRecipe(null);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void getNextRecipe(String searchTerm) {

        Call<SearchRecipe> call = ApiClient.getRecipeService().search(searchTerm);

        Callback<SearchRecipe> callback = new Callback<SearchRecipe>() {
            @Override
            public void onResponse(Call<SearchRecipe> call, Response<SearchRecipe> response) {
                if (response.body() != null) {
                    if (response.body().getRecipes().size() > 0) {
                        setupAdapter(response.body().getRecipes());
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.no_results), Snackbar.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.error_text), Snackbar.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<SearchRecipe> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.error_text), Snackbar.LENGTH_LONG)
                        .show();
            }
        };
        call.enqueue(callback);
    }

    @Override
    protected void onResume() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, InputMethodManager.HIDE_IMPLICIT_ONLY);
        super.onResume();
    }

    private void setupAdapter(List<SearchRecipeItem> recipes) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecipesAdapter adapter = new RecipesAdapter(this, recipes);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getNextRecipe(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onRecipesClicked(SearchRecipeItem item) {
        // open details
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("RecipeId", item.getRecipeId());
        startActivity(intent, null);
    }
}
