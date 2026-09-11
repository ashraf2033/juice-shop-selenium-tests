package tests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.BaseTest;
public class CheckoutTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    LandingPage landingPage;
    LoginPage loginPage;
    BasketPage basketPage;
    AddressPage addressPage;
    NewAddressPage newAddressPage;
ShippingPage shippingPage;
    public void setUp(){
        landingPage = new LandingPage(getDriver());
        if (landingPage.isWelcomeModalDisplayed()){
            landingPage.waitForModal();
            landingPage.dismissModal();
        }

        landingPage.dismissCookies();
        landingPage.expandAccountMenu();
        loginPage = landingPage.navigateToLogin();
        loginPage.waitForLogin();
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setupDriver")
    @Override
    public void setupTestData() {
        super.setupTestData();
        log.info("============== Overrided Method =============");
    }

    @Test(description = "Verify user can complete a full purchase with happy path", groups ={"checkout","smoke"})
    public void happyPathCheckoutTest() {
        setUp();
        landingPage = loginPage.loginWithNameAndPass("basil@juice-sh.op","my little nest of vipers");
        landingPage.waitForAddtoBasketToBeClickable();
        landingPage.clickAddToBasketButton();
        basketPage = landingPage.clickViewBasketButton();
        basketPage.waitForBasket();
        addressPage = basketPage.clickCheckoutButton();
        addressPage.waitForAddressPage();
        newAddressPage = addressPage.clickAddNewAddressButton();
        newAddressPage.waitForAddressPage();
        newAddressPage.fillNewAddressForm("Canada","Basil","0874587412",
                "456258","22524 Avocado ST APT 004","Ontario","London");
        addressPage.selectAddress();
        addressPage.waitForContinueButton();
     shippingPage = addressPage.clickContinue();
     shippingPage.waitForShippingPage();
    Assert.assertEquals(shippingPage.getPageTitle(),"Delivery Address");

    }


}
