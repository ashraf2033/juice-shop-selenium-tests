package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class NewAddressPage extends BaseTest {
WebDriver driver;
    By countryField = By.xpath("//input[contains(@placeholder,'Please provide a country.')]");
    By nameField = By.xpath("//input[contains(@placeholder,'Please provide a name.')]");
    By phoneField = By.xpath("//input[contains(@placeholder,'Please provide a mobile number.')]");
    By zipField = By.xpath("//input[contains(@placeholder,'Please provide a ZIP code.')]");
    By addressField = By.id("address");
    By cityField = By.xpath("//input[contains(@placeholder,'Please provide a city.')]");
    By stateField = By.xpath("//input[contains(@placeholder,'Please provide a state.')]");

    public NewAddressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public NewAddressPage getUrl() {
       driver = getDriver();
        return new NewAddressPage(driver);
    }

public  void  waitForAddressPage(){
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(countryField));
}
public  void  fillNewAddressForm(String coutnry,String name, String phone, String zip, String address, String city,String state){

        driver.findElement(countryField).sendKeys(coutnry);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(zipField).sendKeys(zip);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(stateField).submit();

}

    public String getPageSource() {

        return driver.getPageSource();
    }


}
