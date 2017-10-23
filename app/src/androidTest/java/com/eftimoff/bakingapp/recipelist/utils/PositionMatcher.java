package com.eftimoff.bakingapp.recipelist.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class PositionMatcher {

    public static Matcher<View> childAtPosition(Matcher<View> parentMatcher, int position) {
        return new TypeSafeMatcher<View>() {

            @Override
            protected boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view == ((ViewGroup) parent).getChildAt(position);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position $position in parent ");
                parentMatcher.describeTo(description);
            }
        };

    }

}
