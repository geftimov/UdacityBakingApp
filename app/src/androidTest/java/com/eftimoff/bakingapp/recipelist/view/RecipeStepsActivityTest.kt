package com.eftimoff.bakingapp.recipelist.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.recipelist.utils.PositionMatcher.childAtPosition
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeStepsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RecipeListActivity::class.java)

    @Test
    fun recipeStepsActivityTest() {
        onView(allOf(withId(R.id.recipeContainer),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeList),
                                0),
                        0),
                isDisplayed()))
                .perform(click())

        onView(allOf(withId(R.id.recipeStepIngredients), withText("Ingredients"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeSteps),
                                0),
                        0),
                isDisplayed()))
                .check(matches(withText("Ingredients")))

        onView(allOf(withId(R.id.recipeStepIngredientsSteps), withText("Steps"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.recipeSteps),
                                0),
                        3),
                isDisplayed()))
                .check(matches(withText("Steps")))

    }

}
