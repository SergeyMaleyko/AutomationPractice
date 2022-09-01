package com.issoft.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage{

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='lnk_wishlist']/a[@title='My wishlists']")
    private WebElement myWishListBtn;

    @FindBy(xpath = "//*[@id='center_column']/h1")
    private WebElement myAccountPageTitle;

    @FindBy(xpath = "//*[@class='logout']")
    private WebElement myAccountSignOut;

    public boolean isDisplayedMyAccountPageTitle(){ return myAccountPageTitle.isDisplayed(); }

    public void clickMyWishListBtn(){ myWishListBtn.click(); }

    public void clickSignOutBtn(){ myAccountSignOut.click(); }
}
