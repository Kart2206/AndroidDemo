package com.mytaxi.android_demo.pages;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.models.Driver;


import junit.framework.Assert;

import java.text.SimpleDateFormat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 *  This class contains all the functions related to Driver details page
 **/
public class DriverDetailsPage extends BasePage {

    String TAG = "Driver details page";

    /**
     * This function is used to verify driver details page
     **/
    public void verifyDriverDetailsPage() throws Exception{
        Log.d(TAG, "Verifying driver name");
        onView(withId(R.id.textViewDriverName)).check(matches(withText("Sarah Scott")));

        Log.d(TAG, "Verifying driver location");
        onView(withId(R.id.textViewDriverLocation)).check(matches(withText("6834 charles st")));

        Log.d(TAG, "Verifying driver date");
        onView(withId(R.id.textViewDriverDate)).check(matches(withText("2002-10-18")));

        Log.d(TAG,"driverLocation is displayed");
        onView(withId(R.id.imageViewDriverLocation)).check(matches(isDisplayed()));

        Log.d(TAG, "Verifying date icon set properly");
        onView(withId(R.id.imageViewDriverDate)).check(matches(isDisplayed()));


    }

    /**
     * This function is used to verify call feature
     **/
    public void verifyCallIntent(String phoneNumber){
        tapCallButton();
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.wait(Until.hasObject(By.pkg("com.android.dialer").depth(0)), 2000);
        Assert.assertTrue(device.hasObject(By.text(phoneNumber)));
    }

    /**
     * This function is used to tap call button
     **/
    public void tapCallButton(){
        Log.d(TAG, "Taping call button");
        onView(withId(R.id.fab)).perform(click());
    }


}