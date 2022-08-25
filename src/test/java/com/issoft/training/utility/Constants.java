package com.issoft.training.utility;

import org.openqa.selenium.By;

public class Constants {
    public static final Integer IMPLICITLY_WAIT_TIMEOUT = 90;
    public static final Integer PAGE_LOAD_TIMEOUT = 120;
    public static final String INPUT_EMAIL_SCREEN = "input_email_screen.png";
    public static final String LOGIN_EMAIL_SCREEN = "login_email_screen.png";
    public static final By HEADER_PERSONAL_INFO_PAGE = By.xpath("//*[@id='account-creation_form']/div[1]/h3");
    public static final String TITLE_OF_ACCOUNT_PAGE = "Login - My Store";
    public static final By HEADER_MY_ACCOUNT_PAGE = By.xpath("//*[@id='center_column']/h1");
    public static final String MY_WISHLIST_NAME = "My Wishlist";
    public static final String TOTAL_PRODUCTS_IN_CART = "3";
    public static final By MY_ACCOUNT_SIGN_OUT = By.xpath("//*[@class='logout']");
}
