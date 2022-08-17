package com.issoft.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
