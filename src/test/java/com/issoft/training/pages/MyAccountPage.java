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

    public boolean isDisplayedMyWishListBtn(){ return myWishListBtn.isDisplayed(); }

    public void clickMyWishListBtn(){ myWishListBtn.click(); }
}
