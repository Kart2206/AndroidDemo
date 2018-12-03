package com.mytaxi.android_demo.pages;

import org.hamcrest.Matcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import android.content.Context;
import android.support.coreui.*;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions.*;
import android.util.Log;
import android.support.test.espresso.Espresso;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.model.User;

import junit.framework.Assert;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;

import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import android.support.test.uiautomator.UiDevice;
import android.support.test.espresso.ViewInteraction;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.AllOf.allOf;

/**
 * This page contains all the elements related to login that needs to be validated.
 */

public class AuthenticationPage extends BasePage{

    String TAG = "AuthenticationPage";
    User user ;
    /**
     * This funtion is to verify if login page is launched
     */


    public boolean ifAuthenticationPageLaunched(){

        boolean temp =true;
        Log.d(TAG,"Test message launch");

        try {
            onView(withId(R.id.btn_login)).check(matches(isDisplayed()));

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,e.toString()+"window display details");
            temp=false;
        }

        return temp;
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
     * This function is used to verify the hint text displayed for Username
     **/
    public void hintUsername(){
        Log.d(TAG,"Verifying hint Username");
        onView(withId(R.id.edt_password)).perform(click());
        Log.d(TAG,"To enable hint text of Username");
        onView(withText(R.string.text_username)).check(matches(withHint("Username")));
        Log.d(TAG,onView(withText(R.string.text_username))+"is Username hint text");
    }


    /**
     * This function is used to verify the hint text displayed for Password
     **/
    public void hintPassword(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"Verifying hint Password");
        Log.d(TAG,onView(withId(R.id.edt_password)).getClass().getName().toString()+"is Password hint text");
        onView(withId(R.id.edt_password)).check(matches(withHint("Password")));

    }

    /**
     * This function is used to verify if error message is shown if login failed
     **/
    public void verifyLoginFailedErrorMessage(){
        Log.d(TAG, "Verify the snackbar(Toast) is shown properly");
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.message_login_fail)))
                .check(matches(isDisplayed()));
    }

    public void usernameGen() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://randomuser.me/api/?seed=a1f30d446f820665";
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url, null, future, future);
        queue.add(request);
        try {
            JSONObject response = future.get(120, TimeUnit.SECONDS);
            Log.d(TAG, "response" + response);
            JSONObject login = response.getJSONArray("results").getJSONObject(0).getJSONObject("login");
            String username = login.getString("username");
            String password = login.getString("password");
            Log.d(TAG, "username" + username);
            Log.d(TAG, "password" + password);

            //Log.d(TAG,user.getUsername()+user.getPassword());
            //authenticationPage.login(user.getUsername(), user.getPassword());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
