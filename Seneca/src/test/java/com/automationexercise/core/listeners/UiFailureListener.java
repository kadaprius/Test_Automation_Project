package com.automationexercise.core.listeners;

import com.automationexercise.core.allure.AllureAttachmentUtils;
import com.automationexercise.core.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class UiFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver;
        try {
            driver = DriverManager.getDriver();
        } catch (Exception ex) {
            driver = null;
        }

        AllureAttachmentUtils.attachScreenshot(driver);
        AllureAttachmentUtils.attachPageSource(driver);
    }
}
