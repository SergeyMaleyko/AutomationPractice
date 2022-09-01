package com.issoft.training.pages;

import com.issoft.training.utility.Constants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.issoft.training.config.ConfProperties.getProperty;

public class MyWishlistsPage {
    private static final By LAYER_CART_POPUP_SELECTOR = By.xpath("//*[@id='layer_cart']");
    private static final int EXPLICITLY_WAIT_TIMEOUT = 30;
    private static final int EXPLICITLY_TIMEOUT_POLLING = 100;

    public MyWishlistsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='name']")
    private WebElement myWishListNameField;

    @FindBy(xpath = "//tr[contains(@id,'wishlist')]//a[contains(text(),'My wishlist')]")
    private WebElement myWishListName;

    @FindBy(xpath = "//button[@id='submitWishlist']")
    private WebElement submitWishlistBtn;

    @FindBy(xpath = "//*[@id='best-sellers_block_right']//li[1]/a")
    private WebElement firstProductDetailsLink;

    @FindBy(xpath = "//*[@id='wishlist_button'][@title='Add to my wishlist']")
    private WebElement wishlistBtn;

    @FindBy(xpath = "//*[@id='product']//a[@title='Close']")
    private WebElement productAddedAlertClose;

    @FindBy(xpath = "//*[@class='wishlist_delete']/a[@class='icon']")
    private WebElement deleteMyWishlistBtn;

    @FindBy(xpath = "//*[@id='add_to_cart']//span[text()='Add to cart']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//*[@id='layer_cart']//span[@title='Continue shopping']")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@id='header']//a[@title='View my shopping cart']/span")
    private WebElement myShoppingCartQuantity;

    public void inputWishListName(String name) { myWishListNameField.sendKeys(name); }
    public void clickSubmitWishlistBtn(){ submitWishlistBtn.click(); }
    public boolean isDisplayedMyWishListByName(){ return myWishListName.isDisplayed(); }
    public void clickFirstProductDetailsLink(){ firstProductDetailsLink.click(); }
    public void clickMyWishListBtn(){ wishlistBtn.click(); }
    public void clickCloseProductAddedAlert(){ productAddedAlertClose.click(); }
    public void clickDeleteMyWishlistBtn(){ deleteMyWishlistBtn.click(); }
    public boolean isDisplayedAddToCartBtn(){ return addToCartBtn.isDisplayed(); }
    public void clickAddToCartBtn(){ addToCartBtn.click(); }
    public void clickContinueShoppingBtn(){ continueShoppingBtn.click(); }
    public String getMyShoppingCartQuantity(){ return myShoppingCartQuantity.getText(); }

    public void addProductsToCart(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT_TIMEOUT), Duration.ofMillis(EXPLICITLY_TIMEOUT_POLLING));
        // add 3 different products to cart
        for(int i = 1; i <= Integer.parseInt(Constants.TOTAL_PRODUCTS_IN_CART); i++){
            driver.findElement(By.xpath("//*[@id='best-sellers_block_right']//li["+i+"]/a")).click();
            Assert.assertTrue(isDisplayedAddToCartBtn());
            clickAddToCartBtn();
            //Firefox's exception: Element could not be scrolled into view
            wait.until(ExpectedConditions.visibilityOfElementLocated(LAYER_CART_POPUP_SELECTOR));
            clickContinueShoppingBtn();
            driver.navigate().to(getProperty("url_my_wishlist_page"));
        }
        System.out.println("Number of products in Shopping Cart: " + getMyShoppingCartQuantity());
    }

    public void addProductToAutoWishlist(WebDriver driver){
        clickFirstProductDetailsLink();
        clickMyWishListBtn();
        clickCloseProductAddedAlert();
        driver.navigate().to(getProperty("url_my_wishlist_page"));
    }

    public void addProductToWishlist(WebDriver driver){
        inputWishListName(Constants.MY_WISHLIST_NAME);
        clickSubmitWishlistBtn();
        addProductToAutoWishlist(driver);
    }

    public void clearMyWishlist(WebDriver driver){
        clickDeleteMyWishlistBtn();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.navigate().refresh();
    }
}
