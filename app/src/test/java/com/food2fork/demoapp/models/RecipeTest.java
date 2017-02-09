package com.food2fork.demoapp.models;

import com.food2fork.demoapp.BasetTest;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RecipeTest extends BasetTest {

    private final static String PUBLISHER       = "Test Publisher";
    private final static String RECIPE_ID       = "102030";
    private final static String IMAGE_URL       = "htpp://www.testURL.com/image/test.jpg";
    private final static String TITLE           = "Test Title";

    private Recipe recipe;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        recipe = new Recipe();
    }

    @Test
    public void testGetSet() throws Exception {
        recipe.setRecipeId(RECIPE_ID);
        recipe.setTitle(TITLE);
        recipe.setImageUrl(IMAGE_URL);
        recipe.setPublisher(PUBLISHER);

        assertThat(recipe.getRecipeId(), is(RECIPE_ID));
        assertThat(recipe.getTitle(), is(TITLE));
        assertThat(recipe.getImageUrl(), is(IMAGE_URL));
        assertThat(recipe.getPublisher(), is(PUBLISHER));
    }

    @Test
    public void testEquals_False() throws Exception {
        recipe.setRecipeId(RECIPE_ID);

        Recipe other = new Recipe();
        other.setRecipeId("1111");

        boolean result = recipe.equals(other);
        assertFalse(result);
    }

    @Test
    public void testEquals_True() throws Exception {
        recipe.setRecipeId(RECIPE_ID);

        Recipe other = new Recipe();
        other.setRecipeId(RECIPE_ID);

        boolean result = recipe.equals(other);
        assertTrue(result);
    }

    @Test
    public void testEquals_ObjectType() throws Exception {
        recipe.setRecipeId(RECIPE_ID);

        String other = "";

        boolean result = recipe.equals(other);
        assertFalse(result);
    }
}
