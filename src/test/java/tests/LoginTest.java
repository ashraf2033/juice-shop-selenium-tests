package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;

import pages.LoginPage;
import utils.BaseTest;


public class LoginTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    LandingPage landingPage;
    LoginPage loginPage;

public void setUp(){
        landingPage = new LandingPage(getDriver());
        if(landingPage.isWelcomeModalDisplayed()){
            landingPage.waitForModal();
            landingPage.dismissModal();
        }

        landingPage.dismissCookies();
        landingPage.expandAccountMenu();
        loginPage = landingPage.navigateToLogin();
        loginPage.waitForLogin();
}


    @Test(description = "Verify Login success with valid credentials", groups ={"login","smoke"} )
    public void validLoginTest() {

        setUp();
        landingPage = loginPage.loginWithNameAndPass("basil@juice-sh.op","my little nest of vipers");
        landingPage.expandAccountMenu();
        Assert.assertTrue(landingPage.getPageSource().contains("basil@juice-sh.op"));

    }



    @Test(description = "Test Login with invalid credintials", groups ={"login","smoke"} )
    public void invalidEmailLoginTest() {
    setUp();
    loginPage.loginWithNameAndPass("invalid@juice-sh.op","my little nest of vipers");
        loginPage.waitForError();
        Assert.assertEquals(loginPage.getErrorText(),"Invalid email or password.");

    }

}
