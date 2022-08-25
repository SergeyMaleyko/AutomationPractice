package com.issoft.training.base;

import com.issoft.training.utility.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.issoft.training.config.ConfProperties.*;
import static com.issoft.training.webdrivers.Driver.getInstance;

public class TestBase {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>(); //make the driver thread safe when running tests in parallel
    protected WebDriver driver = webDriver.get();

    @BeforeClass(groups = {"checkinTests","accountTests","loginTests","wishlistTests","cartTests"})
    public void setUp(){
        // driver instance
        driver = getInstance();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICITLY_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"checkinTests","accountTests","loginTests","wishlistTests","cartTests"})
    public void tearDown(){
        System.out.println("quiting Web Driver");
        driver.quit();
        driver = null;
    }

    public WebDriver getDriver() { return this.driver = getInstance(); }

    public static void getScreenShotFile(WebDriver driver, String filename) {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(getProperty("screenshots_pathname") + filename);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNewEmailAddress(){
        String email = getProperty("new_email");
        if (getProperty("new_email_registered").equalsIgnoreCase("yes")){
            int number = Integer.parseInt(email.substring(9));
            email = email.substring(0,9) + (number+1);
            setProperty("new_email", email);
            setProperty("new_email_registered", "no");
        }
        System.out.println("New email is '"+email+"' for an account creation.");
        return email;
    }
}
