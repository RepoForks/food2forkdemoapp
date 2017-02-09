package com.food2fork.demoapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.food2fork.demoapp.BasetTest;
import com.food2fork.demoapp.BuildConfig;
import com.food2fork.demoapp.R;
import com.food2fork.demoapp.models.SearchRecipeItem;
import com.food2fork.demoapp.ui.activity.MainActivity;
import com.food2fork.demoapp.ui.viewholders.RecipesItemViewHolder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RecipesAdapterTest extends BasetTest {
    private RecipesAdapter adapter;
    private RecipesItemViewHolder holder;
    private MainActivity activity;
    private Context context;
    private List<SearchRecipeItem> list;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        activity = Robolectric.buildActivity(MainActivity.class).create().get();

        SearchRecipeItem searchRecipe1 = new SearchRecipeItem();
        searchRecipe1.setTitle("test");

        SearchRecipeItem searchRecipe2 = new SearchRecipeItem();
        searchRecipe1.setTitle("test");

        SearchRecipeItem searchRecipe3 = new SearchRecipeItem();
        searchRecipe1.setTitle("test");

        list = new ArrayList<>();
        list.add(searchRecipe1);
        list.add(searchRecipe2);
        list.add(searchRecipe3);

        adapter = new RecipesAdapter(context, list);
    }
    @Test
    public void itemCount() {
        assertEquals(adapter.getItemCount(), 3);
    }

    @Test
    public void getItemAtPosition() {
        assertEquals(adapter.getItemAtPosition(1), list.get(1));
    }

    @Test
    public void onBindViewHolder() {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.layout_recipes_item, null, false);
        holder = new RecipesItemViewHolder(listItemView);
        adapter.onBindViewHolder(holder, 0);
        assertEquals(holder.getRecipesTitle().getText().toString(), "test");
    }
}
