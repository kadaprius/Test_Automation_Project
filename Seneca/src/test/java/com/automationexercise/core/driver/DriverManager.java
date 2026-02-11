package com.automationexercise.core.driver;

import com.automationexercise.core.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void createDriver() {
        if (DRIVER.get() != null) {
            return;
        }

        String browser = ConfigManager.get("browser").toLowerCase();
        if (!"chrome".equals(browser)) {
            throw new IllegalArgumentException("Only 'chrome' browser is configured in this framework. Provided: " + browser);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (ConfigManager.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        ChromeDriver chromeDriver = new ChromeDriver(options);

        if (ConfigManager.getBoolean("window.maximize")) {
            chromeDriver.manage().window().maximize();
        }

        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getInt("page.load.timeout.seconds")));
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(ConfigManager.getInt("script.timeout.seconds")));

        DRIVER.set(chromeDriver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized for this thread.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
