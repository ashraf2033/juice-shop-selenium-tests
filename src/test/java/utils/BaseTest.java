package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.ITestResult;

import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    private static final Duration EXPLICIT_WAIT = Duration.ofSeconds(15);
    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new RuntimeException("WebDriver not initialized. Call @BeforeMethod first.");
        }
        return driver;
    }

     public static WebDriverWait getWait() {
        WebDriverWait wait = waitThreadLocal.get();
        if (wait == null) {
            throw new RuntimeException("WebDriverWait not initialized. Call @BeforeMethod first.");
        }
        return wait;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public  void setupDriver(@Optional("chrome") String browser) {

        logger.info("========== Setting up WebDriver for browser: " + browser + " ==========");

        try {
            WebDriver driver = createDriver(browser.toLowerCase());
            configureDriver(driver);

            driverThreadLocal.set(driver);
            waitThreadLocal.set(new WebDriverWait(driver, EXPLICIT_WAIT));

            logger.info("========== WebDriver initialized successfully ==========");
        } catch (Exception e) {
            logger.severe("Failed to initialize WebDriver: " + e.getMessage());
            throw new RuntimeException("WebDriver setup failed", e);
        }
    }
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setupDriver")
    public void setupTestData() {
        logger.info("========== Step 2: Setting up test data ==========");
        logger.info("Default: No test data setup. Override in test class to customize.");
    }

    private WebDriver createDriver(String browser) {
        switch (browser) {
            case "firefox":
                return new FirefoxDriver(getFirefoxOptions());
            case "chrome":
            default:
                return new ChromeDriver(getChromeOptions());
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        if(isHeadless){
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080"); 
        }        
        
        return options;
    }


    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        return options;
    }





    private void configureDriver(WebDriver driver) {
           boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);

         if (isHeadless) {
    
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1366, 768));
    } else {
        driver.manage().window().maximize();
    }
    }

    @BeforeMethod(dependsOnMethods = "setupDriver",alwaysRun = true)
    public void navigateToApp() {
        String appUrl = System.getProperty("app.url", "http://localhost:3000/");
        logger.info("Navigating to: " + appUrl);
        getDriver().get(appUrl);
    }
    @AfterMethod(alwaysRun = true)
    public  void TearDown(ITestResult result) {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
              
            if (result.getStatus() == ITestResult.FAILURE) {
                captureScreenshot(result.getName());
            }

            try {
                logger.info("Tearing down driver");
                driver.quit();
            } catch (Exception e) {
                logger.warning("Error during driver quit: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
                waitThreadLocal.remove();
            }
        }


    }

     private void captureScreenshot(String testName) {
        
        try {
            TakesScreenshot ts = (TakesScreenshot) driverThreadLocal.get();
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "failure_" + testName + "_" + timestamp + ".png";
            
            Files.copy(sourceFile.toPath(), Paths.get(fileName));
            System.out.println("NATIVE SCREENSHOT TAKEN ON FAILURE: " + fileName);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}