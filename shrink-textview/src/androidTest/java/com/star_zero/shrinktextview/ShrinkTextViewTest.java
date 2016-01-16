package com.star_zero.shrinktextview;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.TypedValue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.star_zero.shrinktextview.TextSizeMatcher.withTextSize;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShrinkTextViewTest {

    @Rule
    public ActivityTestRule<TestActivity> activityRule = new ActivityTestRule(TestActivity.class);

    @Test
    public void textViewShrink() {
        onView(withId(R.id.test_shrink)).check(matches(isDisplayed()));
        onView(withId(R.id.test_shrink)).check(matches(withText("Hello World!")));

        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, InstrumentationRegistry.getTargetContext().getResources().getDisplayMetrics());
        onView(withId(R.id.test_shrink)).check(matches(withTextSize(px)));
    }

    @Test
    public void textViewNoShrink() {
        onView(withId(R.id.test_no_shrink)).check(matches(isDisplayed()));
        onView(withId(R.id.test_no_shrink)).check(matches(withText("Hello World!")));

        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, InstrumentationRegistry.getTargetContext().getResources().getDisplayMetrics());
        onView(withId(R.id.test_no_shrink)).check(matches(withTextSize(px)));
    }
}