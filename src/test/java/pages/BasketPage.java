package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class BasketPage extends BaseTest {
WebDriver driver;
    By basket_item_label = By.xpath("//mat-cell[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cell-initial-font cdk-column-product mat-column-product']");
    By checkout_btn = By.id("checkoutButton");

    public BasketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public BasketPage getUrl() {
       driver = getDriver();
        return new BasketPage(driver);
    }

public  void  waitForBasket(){
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(basket_item_label));
}
public  AddressPage  clickCheckoutButton(){
    driver.findElement(checkout_btn).click();
    return new AddressPage(driver);
}

    public String getPageSource() {

        return driver.getPageSource();
    }


}
