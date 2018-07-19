package com.example.mac.francosbakingapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import com.example.mac.francosbakingapp.ActivitiesUI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SelectRecipeTest {

    public static final String BROWNIES="Brownies";
    public static final String STEP_1="Step 1";
    public static final String STEP_2="Step 2";
    public static final String RECIPE_INTRO="Recipe Introduction";

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule=
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void browniesIsSecond(){
        onView(withId(R.id.rv_process))
                .perform(RecyclerViewActions.scrollToPosition(1));
        onView(withText(BROWNIES)).check(matches(isDisplayed()));
    }
    @Test
    public void selectRecipeAndSteps() {
        // If you tap the Brownies recipe card in RecipeListActivity ...
        onView(withId(R.id.rv_process))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(1, click()));

        // ... does it bring up RecipeDetailActivity, with Brownies as the selected recipe?
        onView(withId(R.id.textView_recipe)).check(matches(withText(BROWNIES)));

        // And if you then tap Step 1 in the list of steps (which is actually second in the list
        // of steps, following Recipe Introduction ...
        onView(withId(R.id.rv_process))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(1, click()));

        // ... does it bring up the instructions for Step 1, either in RecipeStepActivity (phone)
        // or in RecipeStepFragment in RecipeDetailActivity (tablet)?
        onView(withId(R.id.description_textview)).check(matches(withText(startsWith(STEP_1))));

        // And if you then tap the Next button ...
        onView(withId(R.id.next_button)).perform(click());

        // ... does it advance to step 2?
        onView(withId(R.id.step_name)).check(matches(withText(startsWith(STEP_2))));

        // And if you then tap the Previous button ...
        onView(withId(R.id.previous_button)).perform(click());

        // ... does it return to step 1?
        onView(withId(R.id.step_name)).check(matches(withText(startsWith(STEP_1))));

        // And if you then tap the Previous button a second time ...
        onView(withId(R.id.previous_button)).perform(click());

        // ... does it go to the Recipe Introduction step?
        onView(withId(R.id.description_textview)).check(matches(withText(RECIPE_INTRO)));

        // And when you're at the Recipe Introduction step, is the Previous button no longer enabled?
        onView(withId(R.id.previous_button)).check(matches(not(isEnabled())));
    }


}
