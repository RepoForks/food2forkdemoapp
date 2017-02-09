package com.food2fork.demoapp.network.service;

import com.food2fork.demoapp.models.RecipeResponse;
import com.food2fork.demoapp.models.SearchRecipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {
    @GET("search")
    Call<SearchRecipe> search(@Query("q") String searchTerm);

    @GET("get")
    Call<RecipeResponse> getRecipe(@Query("rId") String recipeId);
}