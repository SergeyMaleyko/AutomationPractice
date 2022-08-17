package com.issoft.training.base;

import com.issoft.training.config.ConfProperties;
import com.issoft.training.utility.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.issoft.training.webdrivers.Driver.getInstance;

public class TestBase {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>(); //make the driver thread safe when running tests in parallel
    protected WebDriver driver = webDriver.get();
    private static WebDriverWait wait;

    @BeforeClass (groups = { "failedTests", "checkinTests", "brokenTests" })
    public void setUp(){
        // driver instance
        driver = getDriver();

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICITLY_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT), Duration.ofMillis(Constants.POLLING));
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("automationpractice_loginpage"));
    }

    @AfterClass (groups = { "failedTests", "checkinTests", "brokenTests" })
    public void tearDown(){
        System.out.println("quiting the browser");
        driver.quit();
        driver = null;
    }

    public WebDriver getDriver() {
        return this.driver = getInstance();
    }

    public static void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void getScreenShotFile(WebDriver driver, String filename) {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(ConfProperties.getProperty("screenshots_pathname") + filename);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
