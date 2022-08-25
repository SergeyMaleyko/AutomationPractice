package com.issoft.training.tests;

import com.issoft.training.base.TestBase;
import com.issoft.training.config.ConfProperties;
import com.issoft.training.listener.AllureListener;
import com.issoft.training.pages.*;
import com.issoft.training.utility.Constants;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.issoft.training.config.ConfProperties.getProperty;

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

    @Test(priority = 0, groups = {"accountTests", "checkinTests"},
            description = "Verify the ability to create an account.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to create an account.")
    @Story("Story name: Account creation.")
    public void createMyAccountTest() {
        // test AP-1
        driver.get(getProperty("url_automationpractice_loginpage"));
        Assert.assertEquals(driver.getTitle(), Constants.TITLE_OF_ACCOUNT_PAGE);
        createAccountPage = new CreateAccountPage(driver);
        accountInformationPage = new AccountInformationPage(driver);

        createAccountPage.inputEmail(getNewEmailAddress());
        getScreenShotFile(driver, Constants.INPUT_EMAIL_SCREEN);
        createAccountPage.clickCreateAccountBtn();
        Assert.assertTrue(driver.findElement(Constants.HEADER_PERSONAL_INFO_PAGE).isDisplayed());

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
        accountInformationPage.inputAddressState(ConfProperties.getProperty("state"));
        accountInformationPage.inputAddressPostCode(ConfProperties.getProperty("postcode"));
        accountInformationPage.inputCompanyCountry(ConfProperties.getProperty("country"));
        accountInformationPage.inputPhoneMobile(ConfProperties.getProperty("phone"));
        accountInformationPage.inputAddressAlias(ConfProperties.getProperty("alias"));
        accountInformationPage.clickSubmitAccountBtn();

        // Expected result: Account was created
        Assert.assertTrue(driver.findElement(Constants.HEADER_MY_ACCOUNT_PAGE).isDisplayed());
        ConfProperties.setProperty("new_email_registered", "yes");
        // Sign out
        driver.findElement(Constants.MY_ACCOUNT_SIGN_OUT).click();
    }

    @Test( priority = 1, groups = {"loginTests", "checkinTests"},
            description = "Verify the ability to login an account.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verify the ability to login an account.")
    @Story("Story name: Account login.")
    public void loginMyAccountTest() {
        // test AP-2
        driver.get(getProperty("url_automationpractice_loginpage"));

        loginAccountPage = new LoginAccountPage(driver);
        Assert.assertEquals(driver.getTitle(), Constants.TITLE_OF_ACCOUNT_PAGE);

        loginAccountPage.inputLoginEmail(ConfProperties.getProperty("email"));
        loginAccountPage.inputLoginPasswd(ConfProperties.getProperty("passwd"));
        getScreenShotFile(driver, Constants.LOGIN_EMAIL_SCREEN);
        loginAccountPage.clickLoginAccountBtn();

        // Expected result: You were able to login
        Assert.assertTrue(driver.findElement(Constants.HEADER_MY_ACCOUNT_PAGE).isDisplayed());
    }

    @Test( priority = 2, groups = {"wishlistTests", "checkinTests"},
            description = "Verify the ability to add a product to auto-created Wishlist.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add a product to auto-created Wishlist.")
    @Story("Story name: Add a product to Wishlist.")
    public void AddProductToAutoWishlistTest() {
        // test AP-3
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);

        driver.get(getProperty("url_my_account_page"));
        Assert.assertTrue(driver.findElement(Constants.HEADER_MY_ACCOUNT_PAGE).isDisplayed());

        myAccountPage.clickMyWishListBtn();
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListHeader());

        myWishlistsPage.clickFirstProductDetailsLink();
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListBtn());

        // Expected result: Wishlist was created automatically and your product is in the list
        myWishlistsPage.clickMyWishListBtn();
        Assert.assertTrue(myWishlistsPage.isDisplayedProductAddedAlert());

        // go to My Wishlist
        myWishlistsPage.clickCloseProductAddedAlert();
        driver.navigate().to(getProperty("url_my_wishlist_page"));
        // clear My Wishlist
        Assert.assertTrue(myWishlistsPage.isDisplayedDeleteMyWishlistBtn());
        myWishlistsPage.clickDeleteMyWishlistBtn();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.navigate().refresh();
    }

    @Test( priority = 3, groups = {"wishlistTests", "checkinTests"},
            description = "Verify the ability to add to my Wishlist.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add to my Wishlist.")
    @Story("Story name: Add a product to Wishlist.")
    public void AddProductToWishlistTest() {
        // test AP-4
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);

        driver.navigate().to(getProperty("url_my_wishlist_page"));
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListHeader());

        // create Wishlist
        myWishlistsPage.inputWishListName(Constants.MY_WISHLIST_NAME);
        myWishlistsPage.clickSubmitWishlistBtn();
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListByName());

        myWishlistsPage.clickFirstProductDetailsLink();
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListBtn());

        // Expected result: Product was added to your Wishlist
        myWishlistsPage.clickMyWishListBtn();
        Assert.assertTrue(myWishlistsPage.isDisplayedProductAddedAlert());

        // verify My Wishlist
        myWishlistsPage.clickCloseProductAddedAlert();
        driver.navigate().to(getProperty("url_my_wishlist_page"));
        Assert.assertTrue(myWishlistsPage.isDisplayedMyWishListByName());
    }


    @Test( priority = 4, groups = {"cartTests", "checkinTests"},
            description = "Verify the ability to add to Cart.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify the ability to add to Cart.")
    @Story("Story name: Add products to Cart.")
    public void AddProductsToCart() {
        // test AP-5
        myAccountPage = new MyAccountPage(driver);
        myWishlistsPage = new MyWishlistsPage(driver);
        driver.navigate().to(getProperty("url_my_wishlist_page"));
        Assert.assertTrue(myWishlistsPage.isDisplayedViewMyShoppingCartBtn());

        // add 3 different products to cart
        for(int i = 1; i <= Integer.parseInt(Constants.TOTAL_PRODUCTS_IN_CART); i++){
            driver.findElement(By.xpath("//*[@id='best-sellers_block_right']//li["+i+"]/a")).click();
            Assert.assertTrue(myWishlistsPage.isDisplayedAddToCartBtn());
            myWishlistsPage.clickAddToCartBtn();
            myWishlistsPage.clickContinueShoppingBtn();
            Assert.assertTrue(myWishlistsPage.isDisplayedAddToCartBtn());
            driver.navigate().to(getProperty("url_my_wishlist_page"));
            Assert.assertTrue(myWishlistsPage.isDisplayedViewMyShoppingCartBtn());
        }

        // Expected result: All 3 products are in the cart and total is correct
        System.out.println("Number of products in Shopping Cart: " + myWishlistsPage.getMyShoppingCartQuantity());
        Assert.assertEquals(myWishlistsPage.getMyShoppingCartQuantity(),Constants.TOTAL_PRODUCTS_IN_CART);

        // Sign out
        driver.findElement(Constants.MY_ACCOUNT_SIGN_OUT).click();
    }
}
