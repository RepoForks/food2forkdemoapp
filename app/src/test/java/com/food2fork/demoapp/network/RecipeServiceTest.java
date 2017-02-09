package com.food2fork.demoapp.network;

import com.food2fork.demoapp.BasetTest;
import com.food2fork.demoapp.models.SearchRecipe;
import com.food2fork.demoapp.models.SearchRecipeItem;
import com.food2fork.demoapp.network.service.RecipeService;

import org.junit.Test;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RecipeServiceTest extends BasetTest{

    private RecipeService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ApiClient client = new ApiClient();
        service = client.getRecipeService();
    }

    @Test
    public void search_getFirstRecipeFromSearch() throws Exception {

        Call<SearchRecipe> call = service.search("rice");

        Response<SearchRecipe> response = call.execute();
        assertTrue(response.isSuccessful());

        SearchRecipeItem recipe = response.body().getRecipes().get(0);
        assertNotNull(recipe);
    }

    @Test
    public void search_getZeroRecipesFromSearch() throws Exception {

        Call<SearchRecipe> call = service.search("aaaaaaaaaaaaaaa");

        Response<SearchRecipe> response = call.execute();
        assertTrue(response.isSuccessful());

        assertEquals(0, response.body().getRecipes().size());
    }

    @Test
    public void search_getRandomRecipeFromSearch() throws Exception {

        Call<SearchRecipe> call = service.search("chicken");

        Response<SearchRecipe> response = call.execute();
        assertTrue(response.isSuccessful());

        int random = new Random().nextInt(response.body().getRecipes().size());

        if (response.body().getCount() > 0){
            SearchRecipeItem recipe = response.body().getRecipes().get(random);
            assertNotNull(recipe);
        } else {
            System.out.println("Response not valid or search term invalid");
        }
    }
}
