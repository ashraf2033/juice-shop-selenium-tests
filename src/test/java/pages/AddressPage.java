package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class AddressPage extends BaseTest {
WebDriver driver;
    By addNewAddressBtn = By.xpath("//button[contains(., 'Add New Address')]");
    By addressRadioBtn = By.cssSelector("input[type='radio'], [role='radio']");
    By continueBtn = By.xpath("//button[contains(., 'Continue')]");
    ShippingPage shippingPage;
    public AddressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public AddressPage getUrl() {
       driver = getDriver();
        return new AddressPage(driver);
    }

public  void  waitForAddressPage(){
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(addNewAddressBtn));
}

    public  void  waitForContinueButton(){
        getWait().until(ExpectedConditions.
                elementToBeClickable(continueBtn));
    }


public  void  selectAddress(){
        driver.findElement(addressRadioBtn).click();
}

public  ShippingPage  clickContinue(){
        driver.findElement(continueBtn).click();
        return new ShippingPage(driver);
    }

public  NewAddressPage  clickAddNewAddressButton(){

        driver.findElement(addNewAddressBtn).click();
        return new NewAddressPage(driver);
}

    public String getPageSource() {

        return driver.getPageSource();
    }


}
