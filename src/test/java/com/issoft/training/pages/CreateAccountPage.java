package com.issoft.training.pages;

import com.issoft.training.utility.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.issoft.training.base.TestBase.getNewEmailAddress;
import static com.issoft.training.base.TestBase.getScreenShotFile;
import static com.issoft.training.config.ConfProperties.getProperty;

public class CreateAccountPage {

    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#email_create")
    private WebElement emailField;

    @FindBy(css = "#SubmitCreate > span")
    private WebElement createAccountBtn;

    public void inputEmail(String login) {
        emailField.sendKeys(login);
    }

    public void clickCreateAccountBtn() {
        createAccountBtn.click();
    }

    public void createAccount(WebDriver driver, LoginAccountPage loginAccountPage){
        driver.get(getProperty("url_automationpractice_loginpage"));
        if(driver.getTitle().equalsIgnoreCase(Constants.MY_ACCOUNT_TITLE)){
            loginAccountPage.clickSignOutBtn(); }
        inputEmail(getNewEmailAddress());
        getScreenShotFile(driver, Constants.INPUT_EMAIL_SCREEN);
        clickCreateAccountBtn();
    }
}
