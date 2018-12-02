package com.mytaxi.android_demo.base;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.activities.MainActivity_MembersInjector;
import com.mytaxi.android_demo.pages.AuthenticationPage;
import com.mytaxi.android_demo.pages.BasePage;
import com.mytaxi.android_demo.pages.HomePage;
import com.mytaxi.android_demo.pages.DriverDetailsPage;
//import com.mytaxi.android_demo.pages.HomePage;
//import com.mytaxi.android_demo.rules.RetryActivityTestRule;
//import com.mytaxi.android_demo.utils.Constants;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

/**
 * This is the base test class. Common functions for all tests can be added here
 **/

public class BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule public TestName currentTestName = new TestName();

    public AuthenticationPage authenticationPage = new AuthenticationPage();
    public HomePage homePage = new HomePage(mActivityRule);
    public DriverDetailsPage driverDetailsPage = new DriverDetailsPage();

    /**
     * This will handle runtime permission dialogs
     **/
    public void allowPermissionsIfNeeded()  {
        String TAG = "BaseTestLog";
        if (Build.VERSION.SDK_INT >= 23) {
            UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            UiObject allowPermissions = mDevice.findObject(new UiSelector().text("ALLOW"));
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                    Log.d(TAG,"ALLOW");
                } catch (UiObjectNotFoundException e) {
                }
            }
        }
    }

}