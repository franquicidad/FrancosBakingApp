package com.example.mac.francosbakingapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    ActivityTestRule<MainActivity> mainActivityActivityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkIfRecyclerViewHasRecipeInPosition(){
        onView(ViewMatchers.withId(R.id.recyclerView_recipies)).perform(RecyclerViewActions.scrollToPosition(1));

    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.mac.francosbakingapp", appContext.getPackageName());
    }
}
