package com.mytaxi.android_demo.pages;

import org.hamcrest.Matcher;
import org.junit.Test;
import android.support.coreui.*;
import android.support.test.espresso.assertion.ViewAssertions.*;
import android.util.Log;
import android.support.test.espresso.Espresso;
import android.view.View;

import com.mytaxi.android_demo.R;

import junit.framework.Assert;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import android.support.test.uiautomator.UiDevice;
import android.support.test.espresso.ViewInteraction;
import static org.hamcrest.core.AllOf.allOf;

/**
 * This page contains all the elements related to login that needs to be validated.
 */

public class AuthenticationPage extends BasePage{

    String TAG = "AuthenticationPage";

    /**
     * This funtion is to verify if login page is launched
     */


    public boolean ifAuthenticationPageLaunched(){

         //if(onView((withId(R.id.btn_login)).check(matches(isDisplayed()))));
        //onView(withText("allow")).perform(click());
        Log.d(TAG,"Test message launch");

       onView(withId(R.id.btn_login)).perform(click());

       return true;

    }

    public void assertLoginPageOpened(){
        Log.d(TAG, "Verify if login page is opened");
        Assert.assertTrue("Home page not opened",ifAuthenticationPageLaunched());
    }

    /**
     * This function is used to login to app
     **/
    public void login(String userName, String password){
        setTextInUserNameField(userName);
        setTextInPasswordField(password);
        Espresso.closeSoftKeyboard();
        tapLoginButton();
    }

    /**
     * This function is used to set text in username edit text
     * @param userName username
     **/
    public void setTextInUserNameField(String userName){
        Log.d(TAG, "Setting text "+userName+" in username field");
        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_username)).perform(typeText(userName));

    }

    /**
     * This function is used to set text in password edit text
     * @param password password
     **/
    public void setTextInPasswordField(String password){
        Log.d(TAG, "Setting text "+password+" in password field");
        onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_password)).perform(typeText(password));
    }

    /**
     * This function is used to tap login button
     **/
    public void tapLoginButton(){
        Log.d(TAG, "Taping login button");
        onView(withId(R.id.btn_login)).perform(click());
    }

    /**
     * This function is used to verify if error message is shown if login failed
     **/
    public void verifyLoginFailedErrorMessage(){
        Log.d(TAG, "Verify the snackbar(Toast) is shown properly");
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.message_login_fail)))
                .check(matches(isDisplayed()));
    }



}
