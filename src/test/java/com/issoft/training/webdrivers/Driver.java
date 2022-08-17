package com.issoft.training.webdrivers;

import com.issoft.training.config.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private volatile static WebDriver driverInstance;
    // Singleton pattern
    public static WebDriver getInstance() {
        if (driverInstance != null) {
            return driverInstance;
        }
        synchronized (Driver.class) {
            if (driverInstance == null &&
                ConfProperties.getProperty("target").equalsIgnoreCase("local")) {
                if(ConfProperties.getProperty("browserName").equalsIgnoreCase("chrome")){
                        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
                        driverInstance = new ChromeDriver();
                    }
                else if(ConfProperties.getProperty("browserName").equalsIgnoreCase("firefox")){
                    System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("geckodriver"));
                    driverInstance = new FirefoxDriver();
                }
            }
            return driverInstance;
        }
    }
}
