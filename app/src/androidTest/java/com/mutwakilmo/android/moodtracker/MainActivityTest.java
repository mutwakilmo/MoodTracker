package com.mutwakilmo.android.moodtracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.mutwakilmo.android.moodtracker.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mutwakil Mo on ${Date}
 */

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;


    @Before// setUp methods call every time before an execute test case
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mutwakilmo.android.moodtracker",
                appContext.getPackageName());
    }

    @Test
    public void testLaunchCommentDialog() {


        // test launch comment

        Espresso.onView(withId(R.id.btn_add_comment)).perform(click());

    }

    @Test
    public void testViewBtnMoodHistory() {

        Espresso.onView(withId(R.id.btn_mood_history)).perform(click());

    }


    @Test
    public void testShareMood() {

        Espresso.onView(withId(R.id.shareButton)).perform(click());//test Share Mood

    }


    @After// tearDown methods that will be called every time after executing the test
    public void tearDown() throws Exception {
        mActivity = null;

    }


}
