package testcase;


import Page.LumaPage;
import base.Assertion;
import base.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class LumaTest extends BaseTest {

    @Test
    public void SignUpTest() throws InterruptedException {

        LumaPage lumaPage = new LumaPage();

        String mockFirstName = "John";
        String mockLastName = "Doe";
        String mockEmail = "johndoe@example.com";
        String mockPassword = "password,@#123";
        String mockPasswordConfirmation = "password,@3123";

        step("Clicking the 'Create an Account' button");
        lumaPage.clickCreateAccountBtn();

        step("Verifying if navigation to the Sign Up page was successful");
        boolean isOnSignUpPage = lumaPage.isOnSignUpPage();
        Assertion.assertThat("Assertion: Checking if user is on the Sign Up page", isOnSignUpPage, is(true));

        step("Entering user credentials");
        lumaPage.inputUserDetails (mockFirstName, mockLastName, mockEmail, mockPassword, mockPasswordConfirmation);


//        step("Verifying successful login");
//        boolean isUserLoggedIn = lumaPage.isUserLoggedIn();
//        Assertion.assertThat("Assertion: Verifying user login status", isUserLoggedIn, is(true));
    }

    @Test
    public void SignInTest() throws InterruptedException {

        LumaPage lumaPage = new LumaPage();

        String user = "kartikdixit37093@gmail.com";
        String pass = "TestLuma@123";

        step("Clicking the 'Sign In' button");
        lumaPage.clickSignIn();

        step("Verifying if navigation to the Sign Up page was successful");
        boolean isOnSignUpPage = lumaPage.isOnSignInPage();
        Assertion.assertThat("Assertion: Checking if user is on the Sign Up page", isOnSignUpPage, is(true));

        step("Entering user credentials");
        lumaPage.enterCredentialsAndLogin(user, pass);

        step("Verifying successful login");
        boolean isUserLoggedIn = lumaPage.isUserLoggedIn();
        Assertion.assertThat("Assertion: Verifying user login status", isUserLoggedIn, is(true));
    }
}
