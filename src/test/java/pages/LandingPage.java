package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class LandingPage extends BaseTest {
WebDriver driver;
    By link = By.linkText("https://owasp-juice.shop");
    By cookiesLink = By.linkText("Me want it!");
    By accountBtn = By.id("navbarAccount");
    By loginBtn = By.id("navbarLoginButton");
    By addToBasketBtn = By.xpath(
"//*[(self::button or @role=\"button\") and contains(normalize-space(.), \"Add to Basket\")]");
    By viewBasketBtn = By.xpath(
            "//span[@class='hide-lt-md basket-label']");

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public LandingPage getUrl() {
       driver = getDriver();
        return new LandingPage(driver);
    }

public boolean isWelcomeModalDisplayed(){
        return !driver.findElements(link).isEmpty();
}
    public  void  waitForModal(){
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(link));
}
public  void  waitForAddtoBasketToBeClickable(){
    getWait().until(ExpectedConditions.elementToBeClickable(addToBasketBtn));
}

public void  dismissModal() {
    driver.findElement(link).sendKeys(Keys.ESCAPE);
}
    public void  dismissCookies() {
        driver.findElement(cookiesLink).click();
    }

    public void expandAccountMenu() {
        driver.findElement(accountBtn).click();
    }

    public void  clickAddToBasketButton()
    {
        driver.findElement(addToBasketBtn).click();
    }
  public BasketPage clickViewBasketButton()
    {
        driver.findElement(viewBasketBtn).click();
        return new BasketPage(driver);
    }


public LoginPage navigateToLogin() {
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }

    public String getPageSource() {

        return driver.getPageSource();
    }


}
