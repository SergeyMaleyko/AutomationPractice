package com.issoft.training.tests;

import com.issoft.training.base.TestBase;
import com.issoft.training.config.ConfProperties;
import com.issoft.training.listener.AllureListener;
import com.issoft.training.pages.*;
import com.issoft.training.utility.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ AllureListener.class })
@Epic("Regression tests")
@Feature(value = "Automation practice - Shopping tests")
public class AutomationPracticeShoppingTests extends TestBase {
    // page objects
    CreateAccountPage createAccountPage;
    AccountInformationPage accountInformationPage;
    LoginAccountPage loginAccountPage;
    MyAccountPage myAccountPage;
    MyWishlistsPage myWishlistsPage;

    @Test(groups = {"accountTests", "checkinTests"},
            description = "Verify the ability to create an account.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to create an account.")
    @Story("Story name: Account creation.")
    public void accountCreateTest() {
        // test AP-1
        createAccountPage = new CreateAccountPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        myAccountPage = new MyAccountPage(driver);
        loginAccountPage = new LoginAccountPage(driver);
        createAccountPage.createAccount(driver, loginAccountPage);
        accountInformationPage.populateAccountInfo();
        // Expected result: Account was created
        Assert.assertTrue(myAccountPage.isDisplayedMyAccountPageTitle());
        ConfProperties.setProperty("new_email_registered", "yes");
    }

    @Test( groups = {"loginTests", "checkinTests"},
            description = "Verify the ability to login an account.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verify the ability to login an account.")
    @Story("Story name: Account login.")
    public void accountLoginTest() {
        // test AP-2
        loginAccountPage = new LoginAccountPage(driver);
        myAccountPage = new MyAccountPage(driver);
        loginAccountPage.loginToAccount(driver);
        // Expected result: You were able to login
        Assert.assertTrue(myAccountPage.isDisplayedMyAccountPageTitle());
    }

    @Test(groups = {"wishlistTests", "checkinTests"},
            description = "Verify the ability to add a product to auto-created Wishlist.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add a product to auto-created Wishlist.")
    @Story("Story name: Add a product to Wishlist.")
    public void addProductToAutoWishlistTest() {
        // test AP-3
        loginAccountPage = new LoginAccountPage(driver);
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);
        loginAccountPage.loginToAccount(driver);
        myAccountPage.clickMyWishListBtn();
        myWishlistsPage.addProductToAutoWishlist(driver);
        // Expected result: Wishlist was created automatically and your product is in the list
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListByName());
    }

    @Test(groups = {"wishlistTests", "checkinTests"},
            description = "Verify the ability to add to my Wishlist.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add to my Wishlist.")
    @Story("Story name: Add a product to Wishlist.")
    public void addProductToWishlistTest() {
        // test AP-4
        loginAccountPage = new LoginAccountPage(driver);
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);
        loginAccountPage.loginToAccount(driver);
        myAccountPage.clickMyWishListBtn();
        myWishlistsPage.addProductToWishlist(driver);
        // Expected result: Product was added to your Wishlist
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListByName());
    }

    @Test(groups = {"cartTests", "checkinTests"},
            description = "Verify the ability to add to Cart.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add to Cart.")
    @Story("Story name: Add products to Cart.")
    public void addProductsToCart() {
        // test AP-5
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);
        loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.loginToAccount(driver);
        myAccountPage.clickMyWishListBtn();
        myWishlistsPage.addProductsToCart(driver);
        // Expected result: All 3 products are in the cart and total is correct
        Assert.assertEquals(myWishlistsPage.getMyShoppingCartQuantity(),Constants.TOTAL_PRODUCTS_IN_CART);
    }
}
