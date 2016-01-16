package com.star_zero.shrinktextview;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.core.Is.is;

public class TextSizeMatcher {

    static Matcher<View> withTextSize(float textSize) {
        return withTextSize(is(textSize));
    }

    static Matcher<View> withTextSize(final Matcher<Float> floatMatcher) {
        return new BoundedMatcher<View, ShrinkTextView>(ShrinkTextView.class) {

            @Override
            protected boolean matchesSafely(ShrinkTextView item) {
                float textSize = item.getTextSize();
                return floatMatcher.matches(textSize);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text size: ");
                floatMatcher.describeTo(description);
            }

        };
    }
}
