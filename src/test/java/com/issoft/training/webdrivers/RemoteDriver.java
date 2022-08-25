package com.issoft.training.webdrivers;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

public class RemoteDriver {
    private static final String SAUCE_LABS_HUB = "saucelabs.com:443/wd/hub";
    private static final String SAUCE_LABS_BUILD_ID = "SauceLabs_BuildId";

    public static RemoteWebDriver launchingRemoteDriver(String browser,
                                                        String version,
                                                        String platform,
                                                        String test_name,
                                                        String url)
    {
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
                    "implicit", 60000,
                    "pageLoad", 120000,
                    "script", 90000
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

    public RemoteDriver() {}
}
