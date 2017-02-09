package com.food2fork.demoapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.food2fork.demoapp.R;
import com.food2fork.demoapp.models.Recipe;
import com.food2fork.demoapp.models.RecipeResponse;
import com.food2fork.demoapp.network.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBar actionBar;
    private String recipeId;
    private Bundle extras;
    private Recipe recipe;

    @BindView(R.id.details_image) ImageView detailsImageView;
    @BindView(R.id.details_ingredients_container) LinearLayout detailsIngredientsContainer;
    @BindView(R.id.details_btn_instructions) Button detailsBtnInstructions;
    @BindView(R.id.details_btn_view_original) Button detailsBtnViewOriginal;
    @BindView(R.id.details_publisher_name) TextView detailsPublisherName;
    @BindView(R.id.details_social_rank) TextView detailsSocialRank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        extras = getIntent().getExtras();

        recipeId = extras.getString("RecipeId");
        getRecipe(recipeId);

        detailsBtnInstructions.setOnClickListener(this);
        detailsBtnViewOriginal.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getRecipe(String searchTerm) {

        Call<RecipeResponse> call = ApiClient.getRecipeService().getRecipe(searchTerm);

        Callback<RecipeResponse> callback = new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                updateViews(response.body().getRecipe());
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.error_text), Snackbar.LENGTH_LONG)
                        .show();
            }
        };
        call.enqueue(callback);
    }

    private void updateViews(Recipe recipe) {
        this.recipe = recipe;

        String socialRank = getResources().getString(R.string.details_social_rank);
        String socialRankText = socialRank + (int)recipe.getSocialRank();

        actionBar.setTitle(recipe.getTitle());
        detailsPublisherName.setText(recipe.getPublisher());
        detailsSocialRank.setText(socialRankText);


        for (String s : recipe.getIngredients()) {
            TextView ingredientItem = new TextView(this);
            ingredientItem.setPadding(25, 10, 10, 10);
            ingredientItem.setText(s);
            detailsIngredientsContainer.addView(ingredientItem);
        }

        Glide
                .with(this)
                .load(recipe.getImageUrl())
                .into(detailsImageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_btn_instructions:
                openWebURL(recipe.getSourceUrl());
                break;

            case R.id.details_btn_view_original:
                openWebURL(recipe.getF2fUrl());
                break;
        }
    }

    private void openWebURL(String url) {
        // open webview
    }

}
