package com.issoft.training.pages;

import com.issoft.training.config.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountInformationPage {

    public AccountInformationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='id_gender1']")
    private WebElement customerGenderBtn;

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement customerFirstname;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement customerLastname;

    @FindBy(xpath = "//*[@id='passwd']")
    private WebElement customerPasswd;

    @FindBy(xpath = "//*[@id='days']")
    private WebElement uniformDays;

    @FindBy(xpath = "//*[@id='months']")
    private WebElement uniformMonth;

    @FindBy(xpath = "//*[@id='years']")
    private WebElement uniformYear;

    @FindBy(xpath = "//*[@id='firstname']")
    private WebElement addressFirstname;

    @FindBy(xpath = "//*[@id='lastname']")
    private WebElement addressLastname;

    @FindBy(xpath = "//*[@id='company']")
    private WebElement companyName;

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement companyAddress1;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement companyCity;

    @FindBy(xpath = "//*[@id='id_state']")
    private WebElement addressState;

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement addressPostcode;

    @FindBy(xpath = "//*[@id='id_country']")
    private WebElement addressCountry;

    @FindBy(xpath = "//*[@id='phone_mobile']")
    private WebElement phoneMobile;

    @FindBy(xpath = "//*[@id='alias']")
    private WebElement addressAlias;

    @FindBy(xpath = "//*[@id='submitAccount']")
    private WebElement submitAccountBtn;

    public void clickCustomerGenderBtn() {
        customerGenderBtn.click();
    }
    public void inputCustomerFirstName(String firstname) { customerFirstname.sendKeys(firstname); }
    public void inputCustomerLastName(String lastname) { customerLastname.sendKeys(lastname); }
    public void inputCustomerPasswd(String passwd) { customerPasswd.sendKeys(passwd); }
    public void inputUniformDays(String days) {
        Select select = new Select(uniformDays);
        select.selectByValue(days);
    }
    public void inputUniformMonth(String month) {
        Select select = new Select(uniformMonth);
        select.selectByValue(month);
    }
    public void inputUniformYear(String year) {
        Select select = new Select(uniformYear);
        select.selectByValue(year);
    }
    public void inputAddressFirstName(String firstname) {
        addressFirstname.clear();
        addressFirstname.sendKeys(firstname);
    }
    public void inputAddressLastName(String lastname) {
        addressLastname.clear();
        addressLastname.sendKeys(lastname);
    }
    public void inputAddressCompanyName(String companyname) { companyName.sendKeys(companyname); }
    public void inputCompanyAddress1(String address) { companyAddress1.sendKeys(address); }
    public void inputCompanyCity(String city) { companyCity.sendKeys(city); }
    public void inputAddressState(String state) {
        Select select = new Select(addressState);
        select.selectByVisibleText(state);
    }
    public void inputAddressPostCode(String postcode) { addressPostcode.sendKeys(postcode); }
    public void inputCompanyCountry(String country) {
        Select select = new Select(addressCountry);
        select.selectByVisibleText(country);
    }
    public void inputPhoneMobile(String phone) { phoneMobile.sendKeys(phone); }
    public void inputAddressAlias(String alias) {
        addressAlias.clear();
        addressAlias.sendKeys(alias);
    }
    public void clickSubmitAccountBtn() { submitAccountBtn.click(); }

    public void populateAccountInfo() {
        clickCustomerGenderBtn();
        inputCustomerFirstName(ConfProperties.getProperty("test_firstname"));
        inputCustomerLastName(ConfProperties.getProperty("test_lastname"));
        inputCustomerPasswd(ConfProperties.getProperty("test_passwd"));
        inputUniformDays(ConfProperties.getProperty("test_days"));
        inputUniformMonth(ConfProperties.getProperty("test_month"));
        inputUniformYear(ConfProperties.getProperty("test_year"));
        inputAddressFirstName(ConfProperties.getProperty("test_firstname"));
        inputAddressLastName(ConfProperties.getProperty("test_lastname"));
        inputAddressCompanyName(ConfProperties.getProperty("test_companyname"));
        inputCompanyAddress1(ConfProperties.getProperty("test_address"));
        inputCompanyCity(ConfProperties.getProperty("test_city"));
        inputAddressState(ConfProperties.getProperty("test_state"));
        inputAddressPostCode(ConfProperties.getProperty("test_postcode"));
        inputCompanyCountry(ConfProperties.getProperty("test_country"));
        inputPhoneMobile(ConfProperties.getProperty("test_phone"));
        inputAddressAlias(ConfProperties.getProperty("test_alias"));
        clickSubmitAccountBtn();
    }
}
