package com.issoft.training.listener;

import com.issoft.training.base.TestBase;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener extends TestBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        // get browser name and version
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = (String)cap.getCapability("browserVersion");
        System.out.println( "browserName :: "+browserName + "\nbrowserVersion :: " + browserVersion );
        if(driver != null){
            System.out.println("Screenshot captured for failed test " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        // save a log on Allure
        saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }
    @Override
    public void onFinish(ITestContext iTestContext){
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }
    @Override
    public void onStart(ITestContext iTestContext){
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult){
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

}
