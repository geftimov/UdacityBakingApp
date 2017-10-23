package com.eftimoff.bakingapp.recipelist.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.eftimoff.bakingapp.R;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.eftimoff.bakingapp.recipelist.utils.PositionMatcher.childAtPosition;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(RecipeListActivity.class);

    @Test
    public void recipeListActivityTest() {
        onView(allOf(withId(R.id.recipeName), withText("Nutella Pie"),
                childAtPosition(childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class),
                        1),
                        0),
                isDisplayed()))
                .check(matches(withText("Nutella Pie")));

        onView(allOf(withId(R.id.recipeName), withText("Brownies"),
                childAtPosition(childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout.class),
                        1),
                        0),
                isDisplayed()))
                .check(matches(withText("Brownies")));

        onView(allOf(withText("Kotlin Bakery"),
                childAtPosition(
                        allOf(withId(R.id.action_bar),
                                childAtPosition(
                                        withId(R.id.action_bar_container),
                                        0)),
                        0),
                isDisplayed()))
                .check(matches(withText("Kotlin Bakery")));
    }

}
