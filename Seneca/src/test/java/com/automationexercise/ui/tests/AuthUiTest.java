package com.automationexercise.ui.tests;

import com.automationexercise.core.data.TestDataFactory;
import com.automationexercise.core.model.UserData;
import com.automationexercise.ui.pages.AccountCreatedPage;
import com.automationexercise.ui.pages.AccountDeletedPage;
import com.automationexercise.ui.pages.HomePage;
import com.automationexercise.ui.pages.LoginPage;
import com.automationexercise.ui.pages.SignupPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthUiTest extends BaseUiTest {

    @Test(description = "Test Case 1: Register User")
    @Description("Implements official UI Test Case 1 from automationexercise.com/test_cases")
    public void tc01_registerUser() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc01");

        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        LoginPage loginPage = homePage.clickSignupLogin();
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), "'New User Signup!' should be visible.");

        SignupPage signupPage = loginPage.signupWithNameAndEmail(user.name(), user.email());
        Assert.assertTrue(signupPage.isEnterAccountInformationVisible(), "'Enter Account Information' should be visible.");

        AccountCreatedPage accountCreatedPage = signupPage.fillDetailsAndCreateAccount(user);
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), "'ACCOUNT CREATED!' should be visible.");

        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isLoggedInAsVisible(user.name()), "'Logged in as username' should be visible.");

        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
        Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' should be visible.");
        accountDeletedPage.clickContinue();
    }

    @Test(description = "Test Case 2: Login User with correct email and password")
    @Description("Implements official UI Test Case 2 from automationexercise.com/test_cases")
    public void tc02_loginUserWithCorrectCredentials() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc02");
        userAccountApiHelper.ensureAccountExists(user);

        boolean deletedViaUi = false;
        try {
            HomePage homePage = new HomePage(driver).open(baseUrl);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

            LoginPage loginPage = homePage.clickSignupLogin();
            Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), "'Login to your account' should be visible.");

            homePage = loginPage.login(user.email(), user.password());
            Assert.assertTrue(homePage.isLoggedInAsVisible(user.name()), "'Logged in as username' should be visible.");

            AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();
            Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' should be visible.");
            deletedViaUi = true;
        } finally {
            if (!deletedViaUi) {
                userAccountApiHelper.deleteAccountSilently(user);
            }
        }
    }

    @Test(description = "Test Case 3: Login User with incorrect email and password")
    @Description("Implements official UI Test Case 3 from automationexercise.com/test_cases")
    public void tc03_loginUserWithIncorrectCredentials() {
        HomePage homePage = new HomePage(driver).open(baseUrl);
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

        LoginPage loginPage = homePage.clickSignupLogin();
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), "'Login to your account' should be visible.");

        loginPage.login("invalid_user@examplemail.com", "wrongPassword123");
        Assert.assertTrue(loginPage.isIncorrectLoginErrorVisible(),
            "Error 'Your email or password is incorrect!' should be visible.");
    }

    @Test(description = "Test Case 4: Logout User")
    @Description("Implements official UI Test Case 4 from automationexercise.com/test_cases")
    public void tc04_logoutUser() {
        UserData user = TestDataFactory.buildUniqueUser("ui_tc04");
        userAccountApiHelper.ensureAccountExists(user);

        try {
            HomePage homePage = new HomePage(driver).open(baseUrl);
            Assert.assertTrue(homePage.isHomePageVisible(), "Home page should be visible.");

            LoginPage loginPage = homePage.clickSignupLogin();
            Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), "'Login to your account' should be visible.");

            homePage = loginPage.login(user.email(), user.password());
            Assert.assertTrue(homePage.isLoggedInAsVisible(user.name()), "'Logged in as username' should be visible.");

            loginPage = homePage.clickLogout();
            Assert.assertTrue(loginPage.isLoginToYourAccountVisible(),
                "User should be navigated to login page after logout.");
        } finally {
            userAccountApiHelper.deleteAccountSilently(user);
        }
    }
}
