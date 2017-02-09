package com.food2fork.demoapp.ui.activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.food2fork.demoapp.BasetTest;
import com.food2fork.demoapp.BuildConfig;
import com.food2fork.demoapp.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest extends BasetTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull("MainActivity is null???", this.mainActivity);
    }

    @Test
    public void titleIsCorrect() throws Exception {
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        assertTrue(activity.getTitle().toString().equals("Food2fork Demo"));
    }
    @Test
    public void validateRecyclerViewContent() throws Exception {
        RecyclerView recyclerView = (RecyclerView) mainActivity.findViewById(R.id.recyclerview);
        assertNotNull("RecyclerView could not be found", recyclerView);
    }

    @Test
    public void testOnCreate() throws Exception {
        String actionBarTitle = mainActivity.getSupportActionBar().getTitle().toString();
        assertEquals(actionBarTitle, mainActivity.getResources().getString(R.string.app_name));
    }

}
