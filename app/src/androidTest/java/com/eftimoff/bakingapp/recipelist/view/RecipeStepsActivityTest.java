package com.eftimoff.bakingapp.recipelist.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.eftimoff.bakingapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.eftimoff.bakingapp.recipelist.utils.PositionMatcher.childAtPosition;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeStepsActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(RecipeListActivity.class);


    @Test
    public void recipeStepsActivityTest() {
        onView(allOf(withId(R.id.recipeContainer),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeList),
                                0),
                        0),
                isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.recipeStepIngredients), withText("Ingredients"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeSteps),
                                0),
                        0),
                isDisplayed()))
                .check(matches(withText("Ingredients")));

        onView(allOf(withId(R.id.recipeStepIngredientsSteps), withText("Steps"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeSteps),
                                0),
                        3),
                isDisplayed()))
                .check(matches(withText("Steps")));

    }

}