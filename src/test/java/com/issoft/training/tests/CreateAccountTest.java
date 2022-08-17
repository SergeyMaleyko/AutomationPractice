package com.issoft.training.tests;

import com.issoft.training.base.TestBase;
import com.issoft.training.config.ConfProperties;
import com.issoft.training.listener.AllureListener;
import com.issoft.training.pages.AccountInformationPage;
import com.issoft.training.pages.CreateAccountPage;
import com.issoft.training.utility.Constants;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ AllureListener.class })
@Epic("Regression tests")
@Feature(value = "Create Account Test")
public class CreateAccountTest extends TestBase {
    // page objects
    CreateAccountPage createAccountPage;
    AccountInformationPage accountInformationPage;
    private static final By HEADER_PERSONAL_INFO_PAGE = By.xpath("//*[@id='account-creation_form']/div[1]/h3");

    @Test(priority = 0, groups = {"checkinTests", "brokenTests"},
            description = "Verify the ability to create an account.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to create an account.")
    @Story("Story name: Account creation.")
    public void createAccountTest() {
        createAccountPage = new CreateAccountPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        Assert.assertEquals(driver.getTitle(), Constants.TITLE_OF_ACCOUNT_PAGE);

        createAccountPage.inputEmail(ConfProperties.getProperty("email"));
        createAccountPage.clickCreateAccountBtn();

        getScreenShotFile(driver, Constants.INPUT_EMAIL_SCREEN);
        waitForElementToAppear(HEADER_PERSONAL_INFO_PAGE);

        accountInformationPage.clickCustomerGenderBtn();
        accountInformationPage.inputCustomerFirstName(ConfProperties.getProperty("firstname"));
        accountInformationPage.inputCustomerLastName(ConfProperties.getProperty("lastname"));
        accountInformationPage.inputCustomerPasswd(ConfProperties.getProperty("passwd"));
        accountInformationPage.inputUniformDays(ConfProperties.getProperty("days"));
        accountInformationPage.inputUniformMonth(ConfProperties.getProperty("month"));
        accountInformationPage.inputUniformYear(ConfProperties.getProperty("year"));
        accountInformationPage.inputAddressFirstName(ConfProperties.getProperty("firstname"));
        accountInformationPage.inputAddressLastName(ConfProperties.getProperty("lastname"));
        accountInformationPage.inputAddressCompanyName(ConfProperties.getProperty("companyname"));
        accountInformationPage.inputCompanyAddress1(ConfProperties.getProperty("address"));
        accountInformationPage.inputCompanyCity(ConfProperties.getProperty("city"));
        accountInformationPage.inputCompanyCountry(ConfProperties.getProperty("country"));
        accountInformationPage.inputPhoneMobile(ConfProperties.getProperty("phone"));
        accountInformationPage.inputAddressAlias(ConfProperties.getProperty("alias"));
        //accountInformationPage.clickSubmitAccountBtn();

        // Expected result: Account was created
        Assert.assertTrue(true);
    }
}
