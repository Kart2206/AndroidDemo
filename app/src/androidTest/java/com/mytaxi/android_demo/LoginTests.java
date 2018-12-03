package com.mytaxi.android_demo;

import android.support.test.espresso.intent.Intents;
import com.mytaxi.android_demo.base.BaseTest;
import com.mytaxi.android_demo.model.User;
import org.junit.Before;
import org.junit.Test;


public class LoginTests extends BaseTest {

    User user;
    String TAG="Login Tests";

    @Before
    public void setUp() throws Exception{
        allowPermissionsIfNeeded();
        Intents.init();
        /*if(homepage.ifhomepageOpened()){
            homepage.logout;
        }*/
    }

    /**
     * This is how we pass test data to the test. We can add multiple users to the list.
     * Can be useful for stress testing with n number of users.
     * **/
    /*@Parameterized.Parameters
    public static Iterable<? extends Object> data() {
        User user1 = new User("carzydog335","venture");
        return Arrays.asList(user1);
     }
        */

    public LoginTests(){

    }

    /*public LoginTests(User mUser){
        user = mUser;
    }*/

    /**
     * This testcase will verify login
     * Test ID:01
     * **/
    @Test
    public void verifyLogin() throws Exception {
        user = new User ("crazydog335","venture");
        authenticationPage.usernameGen();
        authenticationPage.login(user.getUsername(), user.getPassword());
        homePage.dashboard();
        homePage.searchAndTapDriver("sa","Sarah Scott");
        driverDetailsPage.verifyDriverDetailsPage();
        driverDetailsPage.verifyCallIntent("(413) 868-2228");



    }

}
