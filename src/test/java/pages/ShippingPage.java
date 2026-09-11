package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class ShippingPage extends BaseTest {
WebDriver driver;
    By shippingPageH1Title = By.tagName("h1");

    public ShippingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public ShippingPage getUrl() {
       driver = getDriver();
        return new ShippingPage(driver);
    }

public  void  waitForShippingPage(){
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(shippingPageH1Title));
}

    public  String  getPageTitle(){
        return driver.findElement(shippingPageH1Title).getText();
         }


    public String getPageSource() {

        return driver.getPageSource();
    }


}
