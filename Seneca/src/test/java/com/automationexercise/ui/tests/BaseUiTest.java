package com.automationexercise.ui.tests;

import com.automationexercise.api.client.ApiClient;
import com.automationexercise.api.client.UserAccountApiHelper;
import com.automationexercise.core.config.ConfigManager;
import com.automationexercise.core.driver.DriverManager;
import com.automationexercise.core.listeners.UiFailureListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(UiFailureListener.class)
public abstract class BaseUiTest {

    protected WebDriver driver;
    protected String baseUrl;
    protected UserAccountApiHelper userAccountApiHelper;

    public void closeVignetteAd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            //Finding all iframes that might contain the popup/ad
            var adIframes = driver.findElements(By.xpath("//iframe[contains(@id, 'aswift') or contains(@name, 'ad_iframe')]"));

            for (var iframe : adIframes) {
                driver.switchTo().frame(iframe);

                var dismissButton = driver.findElements(By.xpath("//div[@id='dismiss-button'] | //div[contains(@aria-label, 'Close ad')]"));

                if (!dismissButton.isEmpty()) {
                    wait.until(ExpectedConditions.elementToBeClickable(dismissButton.get(0))).click();
                    driver.switchTo().defaultContent();
                    return;
                }

                //Checking for nested iframes
                var nestedFrames = driver.findElements(By.id("ad_iframe"));
                if (!nestedFrames.isEmpty()) {
                    driver.switchTo().frame(nestedFrames.get(0));
                    var nestedDismiss = driver.findElements(By.xpath("//div[@id='dismiss-button'] | //div[contains(@aria-label, 'Close ad')]"));
                    if (!nestedDismiss.isEmpty()) {
                        wait.until(ExpectedConditions.elementToBeClickable(nestedDismiss.get(0))).click();
                        driver.switchTo().defaultContent();
                        return;
                    }
                }

                //Return to main content
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            System.out.println("No vignette ad detected or error while closing: " + e.getMessage());
            driver.switchTo().defaultContent();
        }
    }

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
