package com.issoft.training.tests;

import com.issoft.training.config.ConfProperties;
import com.issoft.training.listener.AllureListener;
import com.issoft.training.pages.LoginAccountPage;
import com.issoft.training.utility.Constants;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.issoft.training.base.TestBase.getScreenShotFile;
import static com.issoft.training.config.ConfProperties.getProperty;
import static com.issoft.training.webdrivers.RemoteDriver.launchingRemoteDriver;

@Listeners({ AllureListener.class })
@Epic("Regression tests")
@Feature(value = "Automation practice - Login Account Remote Tests")
public class RemoteDriverLoginTests {
    private WebDriver driver;
    LoginAccountPage loginAccountPage;
    private static final String SAUCE_LABS_USERNAME = "sergeimaleyko";
    private static final String SAUCE_LABS_ACCESS_KEY = "4dc55c92-bccd-400d-993e-dad87507bff8";
    private static final String URL = "https://"+ SAUCE_LABS_USERNAME + ":" + SAUCE_LABS_ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    private static final String SELENOID_LOCALHOST_HUB = "http://localhost:4444/wd/hub";

    @BeforeTest(groups = { "saucelabsTests", "gridTests" })
    @Parameters({"webdriver", "browser", "version", "platform", "test_name"})
    public void setup(String wd, String br, String vr, String pf, String tn) {
        if(wd.equalsIgnoreCase("saucelabs")){
            driver = launchingRemoteDriver(br, vr, pf, tn, URL); }
        else if (wd.equalsIgnoreCase("grid")){
            driver = launchingRemoteDriver(br, null, null, null, SELENOID_LOCALHOST_HUB); }
    }

    @Test( priority = 5, groups = {"saucelabsTests", "gridTests"},
            description = "Verify the ability to login an account.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to login an account.")
    @Story("Story name: Account login.")
    public void remoteDriverLoginAccountTest(){
        driver.get(getProperty("url_automationpractice_loginpage"));
        loginAccountPage = new LoginAccountPage(driver);
        Assert.assertEquals(driver.getTitle(), Constants.TITLE_OF_ACCOUNT_PAGE);

        loginAccountPage.inputLoginEmail(ConfProperties.getProperty("email"));
        loginAccountPage.inputLoginPasswd(ConfProperties.getProperty("passwd"));
        getScreenShotFile(driver, Constants.LOGIN_EMAIL_SCREEN);
        loginAccountPage.clickLoginAccountBtn();

        // Expected result: You were able to login
        Assert.assertTrue(driver.findElement(Constants.HEADER_MY_ACCOUNT_PAGE).isDisplayed());
    }

    @AfterTest(groups = { "saucelabsTests", "gridTests" })
    public void tearDown(){
        System.out.println("quiting from RemoteWebDriver");
        driver.quit();
        driver = null;
    }
}
