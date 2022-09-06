package com.issoft.training.pages;

import com.issoft.training.config.ConfProperties;
import com.issoft.training.utility.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.issoft.training.config.ConfProperties.getProperty;

public class LoginAccountPage {
    private final WebDriver driver;

    public LoginAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#email")
    private WebElement emailField;

    @FindBy(css = "#passwd")
    private WebElement passwdField;

    @FindBy(css = "#SubmitLogin > span")
    private WebElement loginAccountBtn;

    @FindBy(css = ".logout")
    private WebElement signOutBtn;

    @FindBy(css = ".login")
    private WebElement signInBtn;

    public void inputLoginEmail(String login) { emailField.sendKeys(login); }

    public void inputLoginPasswd(String login) { passwdField.sendKeys(login); }

    public void clickLoginAccountBtn(){ loginAccountBtn.click(); }

    public void clickSignOutBtnIfLoggedOn(){
        driver.get(getProperty("url_automationpractice_loginpage"));
        if(driver.getTitle().equalsIgnoreCase(Constants.MY_ACCOUNT_TITLE)) {
            signOutBtn.click(); }
    }

    public void loginToAccount() {
        clickSignOutBtnIfLoggedOn();
        inputLoginEmail(ConfProperties.getProperty("email"));
        inputLoginPasswd(ConfProperties.getProperty("test_passwd"));
        clickLoginAccountBtn();
    }
}
