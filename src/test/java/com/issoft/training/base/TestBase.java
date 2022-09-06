package com.issoft.training.base;

import com.issoft.training.pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static com.issoft.training.config.ConfProperties.*;
import static com.issoft.training.webdriver.Driver.*;

public class TestBase {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>(); //make the driver thread safe when running tests in parallel
    protected static WebDriver driver = webDriver.get();
    protected CreateAccountPage createAccountPage;
    protected AccountInformationPage accountInformationPage;
    protected LoginAccountPage loginAccountPage;
    protected MyAccountPage myAccountPage;
    protected MyWishlistsPage myWishlistsPage;

    @BeforeClass(groups = {"checkinTests","accountTests","loginTests","wishlistTests","cartTests"})
    public void setUp() {
        this.driver = configWebDriver();
        createAccountPage = new CreateAccountPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        loginAccountPage = new LoginAccountPage(driver);
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);
    }

    public static WebDriver getDriver(){ return driver; }

    @AfterClass(groups = {"checkinTests","accountTests","loginTests","wishlistTests","cartTests"})
    public void tearDown() {
        System.out.println("quiting Web Driver");
        driver.quit();
        driver = null;
    }

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
