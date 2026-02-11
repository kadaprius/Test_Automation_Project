package com.automationexercise.ui.pages;

import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private static final By HOME_BANNER = By.cssSelector("div.item.active");
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");
    private static final By CONTACT_US_LINK = By.cssSelector("a[href='/contact_us']");
    private static final By TEST_CASES_LINK = By.cssSelector("a[href='/test_cases']");
    private static final By PRODUCTS_LINK = By.cssSelector("a[href='/products']");
    private static final By LOGGED_IN_AS = By.xpath("//a[contains(.,'Logged in as')]");
    private static final By DELETE_ACCOUNT_LINK = By.cssSelector("a[href='/delete_account']");
    private static final By LOGOUT_LINK = By.cssSelector("a[href='/logout']");
    private static final By FOOTER = By.id("footer");
    private static final By SUBSCRIPTION_HEADER = By.xpath("//footer//h2[text()='Subscription']");
    private static final By SUBSCRIPTION_EMAIL_INPUT = By.id("susbscribe_email");
    private static final By SUBSCRIPTION_ARROW_BUTTON = By.id("subscribe");
    private static final By SUBSCRIPTION_SUCCESS_MESSAGE = By.xpath("//div[@id='success-subscribe']//div[contains(.,'You have been successfully subscribed!')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Automation Exercise home page")
    public HomePage open(String baseUrl) {
        driver.get(baseUrl);
        wait.waitForPageToLoadComplete();
        return this;
    }

    @Step("Verify home page is visible")
    public boolean isHomePageVisible() {
        return isVisible(HOME_BANNER);
    }

    @Step("Click Signup / Login")
    public LoginPage clickSignupLogin() {
        click(SIGNUP_LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Click Contact Us")
    public ContactUsPage clickContactUs() {
        click(CONTACT_US_LINK);
        return new ContactUsPage(driver);
    }

    @Step("Click Test Cases")
    public TestCasesPage clickTestCases() {
        click(TEST_CASES_LINK);
        return new TestCasesPage(driver);
    }

    @Step("Click Products")
    public ProductsPage clickProducts() {
        click(PRODUCTS_LINK);
        return new ProductsPage(driver);
    }

    @Step("Scroll down to footer")
    public HomePage scrollToFooter() {
        WebElement footer = wait.visible(FOOTER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        return this;
    }

    @Step("Verify text 'SUBSCRIPTION'")
    public boolean isSubscriptionVisible() {
        return isVisible(SUBSCRIPTION_HEADER);
    }

    @Step("Subscribe with email: {email}")
    public HomePage subscribeWithEmail(String email) {
        type(SUBSCRIPTION_EMAIL_INPUT, email);
        click(SUBSCRIPTION_ARROW_BUTTON);
        return this;
    }

    @Step("Verify subscription success message")
    public boolean isSubscriptionSuccessMessageVisible() {
        return isVisible(SUBSCRIPTION_SUCCESS_MESSAGE);
    }

    @Step("Verify 'Logged in as username' is visible")
    public boolean isLoggedInAsVisible(String expectedNamePart) {
        if (!isVisible(LOGGED_IN_AS)) {
            return false;
        }
        String actualText = wait.visible(LOGGED_IN_AS).getText();
        return actualText.toLowerCase().contains(expectedNamePart.toLowerCase());
    }

    @Step("Click Delete Account")
    public AccountDeletedPage clickDeleteAccount() {
        click(DELETE_ACCOUNT_LINK);
        return new AccountDeletedPage(driver);
    }

    @Step("Click Logout")
    public LoginPage clickLogout() {
        click(LOGOUT_LINK);
        return new LoginPage(driver);
    }
}
