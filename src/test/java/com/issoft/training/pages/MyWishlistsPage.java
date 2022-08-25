package com.issoft.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWishlistsPage {

    public MyWishlistsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='mywishlist']/h1[text()='My wishlists']")
    private WebElement myWishListHeader;

    @FindBy(xpath = "//*[@id='name']")
    private WebElement myWishListNameField;

    @FindBy(xpath = "//tr[contains(@id,'wishlist')]//a[contains(text(),'My Wishlist')]")
    private WebElement myWishListName;

    @FindBy(xpath = "//button[@id='submitWishlist']")
    private WebElement submitWishlistBtn;

    @FindBy(xpath = "//*[@id='best-sellers_block_right']//li[1]/a")
    private WebElement firstProductDetailsLink;

    @FindBy(xpath = "//*[@id='wishlist_button'][@title='Add to my wishlist']")
    private WebElement wishlistBtn;

    @FindBy(xpath = "//*[@id='product']//p[text()='Added to your wishlist.']")
    private WebElement productAddedAlert;

    @FindBy(xpath = "//*[@id='product']//a[@title='Close']")
    private WebElement productAddedAlertClose;

    @FindBy(xpath = "//*[@id='header']//a[@title='View my customer account']")
    private WebElement viewMyCustomerAccountBtn;

    @FindBy(xpath = "//*[@class='wishlist_delete']/a[@class='icon']")
    private WebElement deleteMyWishlistBtn;

    @FindBy(xpath = "//*[@id='add_to_cart']//span[text()='Add to cart']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//*[@id='layer_cart']//span[@title='Continue shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@id='header']//a[@title='View my shopping cart']")
    private WebElement viewMyShoppingCartBtn;

    @FindBy(xpath = "//*[@id='header']//a[@title='View my shopping cart']/span")
    private WebElement myShoppingCartQuantity;

    public void clickViewMyCustomerAccountBtn(){ viewMyCustomerAccountBtn.click(); }
    public boolean isDisplayedMyWishListHeader(){ return myWishListHeader.isDisplayed(); }
    public void inputWishListName(String name) { myWishListNameField.sendKeys(name); }
    public void clickSubmitWishlistBtn(){ submitWishlistBtn.click(); }
    public boolean isDisplayedMyWishListByName(){ return myWishListName.isDisplayed(); }
    public void clickFirstProductDetailsLink(){ firstProductDetailsLink.click(); }
    public boolean isDisplayedMyWishListBtn(){ return wishlistBtn.isDisplayed(); }
    public void clickMyWishListBtn(){ wishlistBtn.click(); }
    public boolean isDisplayedProductAddedAlert(){ return productAddedAlert.isDisplayed(); }
    public void clickCloseProductAddedAlert(){ productAddedAlertClose.click(); }
    public boolean isDisplayedDeleteMyWishlistBtn(){ return deleteMyWishlistBtn.isDisplayed(); }
    public void clickDeleteMyWishlistBtn(){ deleteMyWishlistBtn.click(); }
    public boolean isDisplayedAddToCartBtn(){ return addToCartBtn.isDisplayed(); }
    public void clickAddToCartBtn(){ addToCartBtn.click(); }
    public void clickContinueShoppingBtn(){ continueShoppingBtn.click(); }
    public boolean isDisplayedViewMyShoppingCartBtn(){ return viewMyShoppingCartBtn.isDisplayed(); }
    public String getMyShoppingCartQuantity(){ return myShoppingCartQuantity.getText(); }

}
