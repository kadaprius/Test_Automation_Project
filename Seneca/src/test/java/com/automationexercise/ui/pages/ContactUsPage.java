package com.automationexercise.ui.pages;

import com.automationexercise.core.config.ConfigManager;
import com.automationexercise.core.ui.BasePage;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage extends BasePage {

    private static final By GET_IN_TOUCH_HEADER = By.xpath("//h2[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'get in touch')]");
    private static final By NAME_INPUT = By.cssSelector("input[data-qa='name']");
    private static final By EMAIL_INPUT = By.cssSelector("input[data-qa='email']");
    private static final By SUBJECT_INPUT = By.cssSelector("input[data-qa='subject']");
    private static final By MESSAGE_TEXTAREA = By.cssSelector("textarea[data-qa='message']");
    private static final By FILE_UPLOAD_INPUT = By.cssSelector("input[name='upload_file']");
    private static final By SUBMIT_BUTTON = By.cssSelector("input[data-qa='submit-button']");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'alert-success') and contains(.,'Success! Your details have been submitted successfully.')]");
    private static final By HOME_BUTTON = By.cssSelector("#form-section a.btn.btn-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify 'GET IN TOUCH' is visible")
    public boolean isGetInTouchVisible() {
        return isVisible(GET_IN_TOUCH_HEADER);
    }

    @Step("Fill contact form details")
    public ContactUsPage fillContactDetails(String name, String email, String subject, String message) {
        type(NAME_INPUT, name);
        type(EMAIL_INPUT, email);
        type(SUBJECT_INPUT, subject);
        type(MESSAGE_TEXTAREA, message);
        return this;
    }

    @Step("Upload file in contact form")
    public ContactUsPage uploadFile(String absoluteFilePath) {
        wait.visible(FILE_UPLOAD_INPUT).sendKeys(absoluteFilePath);
        return this;
    }

    @Step("Submit contact form and accept browser alert")
    public ContactUsPage submitAndAcceptAlert() {
        click(SUBMIT_BUTTON);

        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInt("explicit.wait.seconds")));
        Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }

    @Step("Verify contact form success message")
    public boolean isSuccessMessageVisible() {
        return isVisible(SUCCESS_MESSAGE);
    }

    @Step("Click Home button from contact form result")
    public HomePage clickHomeButton() {
        click(HOME_BUTTON);
        return new HomePage(driver);
    }
}
