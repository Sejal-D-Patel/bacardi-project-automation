package tests;

import drivermanager.DriverFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.IOException;

import static org.testng.Reporter.log;

@Listeners({tests.TestListener.class})
@Epic("Regression Test Suite - Login Details")
public class SignUpTest extends DriverFactory {
    @Step("Success Message: ")
    public void logToReport(String message) {
        log(message);
    }
    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_001 - verify user landed to sign up page")
    public void test_001_verifyUserInSignUpPage() throws IOException {
        try {
            signUpPage.getURL().clickOnLogInButton().clickOnCreateAnAccountButton();
            logToReport("user landed to sign up page successfully.");
            Assert.assertEquals(signUpPage.getSignupText(),"Sign Up");
        } catch (AssertionError | Exception E) {
            Assert.fail("User Could not landed to sign up." + E.getMessage());
        }
    }
    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_002 - verify user click on submit button without filling any field")
    public void test_002_verifySubmitWithoutFillingFields() throws IOException {
        try {
            signUpPage.clickOnSubmitButton();
            signUpPage.getErrorMessage();
            logToReport("User clicked on submit button without filling any field.");
        } catch (AssertionError | Exception E) {
            Assert.fail("Test failed: " + E.getMessage());
        }
    }
    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Step("TestCase_003 - verify successful signup with valid data")
    public void test_003_verifySuccessfulSignupWithValidData() throws IOException {
        try {
            signUpPage.getURL().clickOnLogInButton().clickOnCreateAnAccountButton();
            signUpPage.enterFirstName("John")
                    .enterLastName("Doe")
                    .enterEmailAddress("johndoe@bacardi.com")
                    .enterPassword("SecurePass123!")
                    .enterConfirmPassword("SecurePass123!")
                    .enterStreetAddress("123 Main St")
                    .enterCity("Anytown")
                    .enterZipcode("12345")
                    .enterPhoneNumber("555-123-4567")
                    .selectState("California")
                    .selectCountry("United States")
                    .clickOnSubmitButton();

            logToReport("User submitted signup form with valid data including address and contact information.");

            Assert.assertTrue(signUpPage.isSuccessMessageDisplayed(), "Success message should be displayed");
            Assert.assertEquals(signUpPage.getSuccessMessage(), "Account Created Successfully!!!");

            // Optionally, verify redirection to a new page or dashboard
            // Assert.assertTrue(dashboardPage.isDisplayed(), "User should be redirected to dashboard after successful signup");

        } catch (AssertionError | Exception E) {
            Assert.fail("Test failed: " + E.getMessage());
        }
    }
}
