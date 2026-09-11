package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseTest;

public class LoginPage extends BaseTest {
WebDriver driver;
By emailField = By.id("email");
By passwordField = By.id("password");
By errorLabel = By.className("error");

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


public  void  waitForLogin() {
    getWait().until(ExpectedConditions.
            visibilityOfElementLocated(emailField));
}
    public  LandingPage  loginWithNameAndPass(String email, String pass) {
            driver.findElement(emailField).sendKeys(email);
            driver.findElement(passwordField).sendKeys(pass);
            driver.findElement(passwordField).submit();

            return new LandingPage(driver);

    }
    public void waitForError(){
        getWait().until(ExpectedConditions.
                visibilityOfElementLocated(errorLabel));
    }
     public String getErrorText(){
        return driver.findElement(errorLabel).getText();
     }
    public String getPageSource() {

        return driver.getPageSource();
    }



}

