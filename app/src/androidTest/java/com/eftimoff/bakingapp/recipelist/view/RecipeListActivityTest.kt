package com.eftimoff.bakingapp.recipelist.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.recipelist.utils.PositionMatcher.childAtPosition
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RecipeListActivity::class.java)

    @Test
    fun recipeListActivityTest() {
        onView(allOf(withId(R.id.recipeName), withText("Nutella Pie"),
                childAtPosition(childAtPosition(
                        IsInstanceOf.instanceOf<View>(android.widget.LinearLayout::class.java),
                        1),
                        0),
                isDisplayed()))
                .check(matches(withText("Nutella Pie")))

        onView(allOf(withId(R.id.recipeName), withText("Brownies"),
                childAtPosition(childAtPosition(
                        IsInstanceOf.instanceOf<View>(android.widget.LinearLayout::class.java),
                        1),
                        0),
                isDisplayed()))
                .check(matches(withText("Brownies")))

        onView(allOf(withText("Kotlin Bakery"),
                childAtPosition(
                        allOf(withId(R.id.action_bar),
                                childAtPosition(
                                        withId(R.id.action_bar_container),
                                        0)),
                        0),
                isDisplayed()))
                .check(matches(withText("Kotlin Bakery")))
    }

}
