package com.automationexercise.core.allure;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class AllureAttachmentUtils {

    private AllureAttachmentUtils() {
    }

    @Attachment(value = "UI Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        if (driver == null) {
            return new byte[0];
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "UI Failure Page Source", type = "text/html")
    public static String attachPageSource(WebDriver driver) {
        if (driver == null) {
            return "No page source: driver is null.";
        }
        return driver.getPageSource();
    }
}
