package com.issoft.training.webdriver;

import com.issoft.training.config.ConfProperties;
import com.issoft.training.utility.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class Driver {
    private static final String SAUCE_LABS_HUB = "saucelabs.com:443/wd/hub";
    private static final String SAUCE_LABS_BUILD_ID = "SauceLabs_BuildId_Training";

    private volatile static WebDriver driverInstance;

    // Singleton pattern
    public static WebDriver getInstance() {
        if (driverInstance != null) {
            return driverInstance;
        }
        synchronized (Driver.class) {
            if (driverInstance == null) {
                if(ConfProperties.getProperty("ap_browserName").equalsIgnoreCase("chrome")){
                        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
                        driverInstance = new ChromeDriver();
                    }
                else if(ConfProperties.getProperty("ap_browserName").equalsIgnoreCase("firefox")){
                    System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriver"));
                    driverInstance = new FirefoxDriver();
                }
            }
            return driverInstance;
        }
    }

    private static RemoteWebDriver launchingRemoteDriver(String browser,
                                                        String version,
                                                        String platform,
                                                        String test_name,
                                                        String url) {
        RemoteWebDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome"); //by default

        try {
            if (browser.equalsIgnoreCase("firefox")) {
                capabilities.setCapability("browserName", "firefox");
            } else if (browser.equalsIgnoreCase("edge")) {
                capabilities.setCapability("browserName", "edge");
            } else if (browser.equalsIgnoreCase("safari")) {
                capabilities.setCapability("browserName", "safari");
            }
            capabilities.setCapability("browserVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            capabilities.setCapability("timeouts", Map.<String, Object>of(
                    "implicit", Constants.IMPLICITLY_WAIT_TIMEOUT*1000,
                    "pageLoad", Constants.PAGE_LOAD_TIMEOUT*1000,
                    "script", Constants.SCRIPT_WAIT_TIMEOUT*1000
            ));
            if (url.contains(SAUCE_LABS_HUB)){
                capabilities.setCapability("sauce:options", Map.<String, Object>of(
                        "build", SAUCE_LABS_BUILD_ID,
                        "name", test_name
                ));
            } driver = new RemoteWebDriver(new URL(url), capabilities);
        }
        catch (Exception e) {
            System.out.println("Check the standalone server is running on the machine");
            e.printStackTrace();
        }

        return driver;
    }

    public static WebDriver configWebDriver(){
        WebDriver driver = null;
        String target = ConfProperties.getProperty("ap_target");
        if(target.equalsIgnoreCase("local")){
            driver = getInstance();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICITLY_WAIT_TIMEOUT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
            driver.manage().window().maximize();
        }
        else if (target.equalsIgnoreCase("grid")){
            driver = launchingRemoteDriver(ConfProperties.getProperty("ap_browserName"), null, null, null,
                    ConfProperties.getProperty("url_selenoid_local_hub")); }
        else if(target.equalsIgnoreCase("saucelabs")){
            driver = launchingRemoteDriver(ConfProperties.getProperty("ap_browserName"),
                    ConfProperties.getProperty("ap_browserVersion"),
                    ConfProperties.getProperty("ap_platform"),
                    ConfProperties.getProperty("ap_saucelabs_test_name"),
                    ConfProperties.getProperty("url_saucelabs_hub")); }

        return driver;
    }
}
