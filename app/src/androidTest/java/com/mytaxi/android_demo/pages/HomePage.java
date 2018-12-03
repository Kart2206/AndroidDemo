package com.mytaxi.android_demo.pages;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import com.mytaxi.android_demo.R;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;


/**
 *  This class contains all the functions related to Home page
 **/
public class HomePage extends BasePage {

    String TAG = "Home page";
    ActivityTestRule mActivityTestRule;
    private Resources resources;

    public HomePage(ActivityTestRule activityTestRule) {
        mActivityTestRule = activityTestRule;
    }

    public void dashboard(){
        resources = mActivityTestRule.getActivity().getResources();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withText("mytaxi demo"))).check(matches(isDisplayed()));
        String dashboardTxt=resources.getString(R.string.app_name);
        onView(allOf(withText("mytaxi demo"))).check(matches(withText(dashboardTxt)));
        onView(withId(R.id.searchContainer)).check(matches(isDisplayed()));
        Log.d(TAG,"Dashboard verified");


    }

    public void searchAndTapDriver(String searchQuery, String driverName) {
        //assertHomePageOpened();
        Log.d(TAG, "Type driver name query in edit text");
        setTextInAutoComplete(searchQuery);
        Log.d(TAG, "Tap driver name in populated list");
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    /**
     * This function is used to set text in auto complete text view
     *
     * @param searchQuery search query to be set in auto complete
     **/
    public void setTextInAutoComplete(String searchQuery) {
        Log.d(TAG, "Setting text " + searchQuery + " in auto complete text view");
        onView(withId(R.id.textSearch)).perform(click());
        onView(withId(R.id.textSearch)).perform(typeText(searchQuery));
    }

    public void logOut() {
        Log.d(TAG, "Tapping hamburger button");
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();

        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        Log.d(TAG, "Tapping logout button");
        onView(withText(R.string.text_item_title_logout)).perform(click());
    }
}
