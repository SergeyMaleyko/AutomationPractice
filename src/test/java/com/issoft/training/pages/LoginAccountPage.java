package com.issoft.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginAccountPage {

    public LoginAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#email")
    private WebElement emailField;

    @FindBy(css = "#passwd")
    private WebElement passwdField;

    @FindBy(css = "#SubmitLogin > span")
    private WebElement loginAccountBtn;

    @FindBy(css = "div.header_user_info > a.logout")
    private WebElement SignOutBtn;

    public void inputLoginEmail(String login) { emailField.sendKeys(login); }

    public void inputLoginPasswd(String login) { passwdField.sendKeys(login); }

    public void clickLoginAccountBtn(){ loginAccountBtn.click(); }

    public void clickSignOutBtn(){ SignOutBtn.click(); }
}
