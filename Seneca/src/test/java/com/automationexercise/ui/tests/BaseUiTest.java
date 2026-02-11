package com.automationexercise.ui.tests;

import com.automationexercise.api.client.ApiClient;
import com.automationexercise.api.client.UserAccountApiHelper;
import com.automationexercise.core.config.ConfigManager;
import com.automationexercise.core.driver.DriverManager;
import com.automationexercise.core.listeners.UiFailureListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(UiFailureListener.class)
public abstract class BaseUiTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected UserAccountApiHelper userAccountApiHelper;

    @BeforeMethod(alwaysRun = true)
    public void setupUiTest() {
        DriverManager.createDriver();
        driver = DriverManager.getDriver();
        baseUrl = ConfigManager.get("ui.base.url");
        userAccountApiHelper = new UserAccountApiHelper(new ApiClient());
    }

    @AfterMethod(alwaysRun = true)
    public void teardownUiTest() {
        DriverManager.quitDriver();
    }
}
