package com.automationexercise.ui.tests;

import com.automationexercise.core.data.TestDataFactory;
import com.automationexercise.core.model.UserData;
import com.automationexercise.ui.pages.ContactUsPage;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.LoginPage;
import io.qameta.allure.Description;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupAndContactUiTest extends BaseUiTest {

    @Test(description = "Test Case 5: Register User with existing email")
    @Description("Implements official UI Test Case 5 from automationexercise.com/test_cases")
    public void tc05_registerUserWithExistingEmail() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc05");
        userAccountApiHelper.ensureAccountExists(user);

        try {
            HomePage homePage = new HomePage(driver).open(baseUrl);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

            LoginPage loginPage = homePage.clickSignupLogin();
            Assert.assertTrue(loginPage.isNewUserSignupVisible(), "'New User Signup!' should be visible.");

            loginPage.signupWithNameAndEmail("Existing User Attempt", user.email());
            Assert.assertTrue(loginPage.isExistingEmailErrorVisible(),
                "Error 'Email Address already exist!' should be visible.");
        } finally {
            userAccountApiHelper.deleteAccountSilently(user);
        }
    }

    @Test(description = "Test Case 6: Contact Us Form")
    @Description("Implements official UI Test Case 6 from automationexercise.com/test_cases")
    public void tc06_contactUsForm() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc06");

        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        ContactUsPage contactUsPage = homePage.clickContactUs();
        Assert.assertTrue(contactUsPage.isGetInTouchVisible(), "'GET IN TOUCH' should be visible.");

        contactUsPage
            .fillContactDetails(
                user.name(),
                user.email(),
                "Automation Contact Request",
                "Please validate the contact us workflow."
            )
            .uploadFile(resolveUploadFilePath())
            .submitAndAcceptAlert();

        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(),
            "Success message should be visible after form submission.");

        homePage = contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isHomePageVisible(), "User should land on home page after clicking Home.");
    }

    private String resolveUploadFilePath() {
        try {
            return Paths.get(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("test-data/contact-us-upload.txt"),
                    "Upload test resource not found."
                ).toURI()
            ).toFile().getAbsolutePath();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Unable to resolve upload file path.", ex);
        }
    }
}
