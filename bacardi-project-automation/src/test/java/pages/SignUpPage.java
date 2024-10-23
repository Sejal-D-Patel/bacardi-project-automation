package pages;

import Utility.Utilities;
import drivermanager.Constant;
import drivermanager.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class SignUpPage extends DriverFactory {

    @FindBy(xpath = "//button[@title='Login']")
    protected WebElement btnLogin;
    @FindBy(xpath = "//button[text()='CREATE NEW CUSTOMER ACCOUNT']")
    protected WebElement btnNewAccount;
    @FindBy(xpath = "//div[text()='Sign Up']")
    protected WebElement txt_signup;
    @FindBy(id = "textFirstName]")
    protected WebElement txt_FirstName;
    @FindBy(id = "textLastName]")
    protected WebElement txt_LastName;
    @FindBy(id = "textEmail]")
    protected WebElement txt_Email;
    @FindBy(id = "textPassword]")
    protected WebElement txt_Password;
    @FindBy(id = "textConfirmPassword]")
    protected WebElement txt_ConfirmPassword;
    @FindBy(id = "textAddress]")
    protected WebElement txt_Address;
    @FindBy(id = "textZipCode]")
    protected WebElement txt_Zipcode;
    @FindBy(id = "textCity]")
    protected WebElement txt_City;
    @FindBy(id = "textState]")
    protected WebElement select_state;
    @FindBy(id = "textCountry]")
    protected WebElement select_Country;
    @FindBy(id = "textPhone]")
    protected WebElement txt_Phone;
    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement btnSubmit;
    @FindBy(xpath = "//div[contains(text(),'Successfully')]")
    protected WebElement msg_success;

    JavascriptExecutor js= (JavascriptExecutor) driver;


    /**
     * get website url
     * @return
     */

    public SignUpPage getURL(){
        String url = Utilities.getUtilities().readDataFromExcel(Constant.tecStubExcelFile, "loginCredential", "Url", 2);
        driver.get(url);
        return this;
    }
    /**
     * Click on Login button
     * @return
     */
    public SignUpPage clickOnLogInButton()  {
        Utilities.getUtilities().waitForVisibilityOfElement(btnLogin, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnLogin, driver);
        Utilities.getUtilities().clickOnElement(btnLogin);
        return this;
    }
    /**
     * Click on CreateAnAccount button
     * @return
     */
    public SignUpPage clickOnCreateAnAccountButton()  {
        Utilities.getUtilities().waitForVisibilityOfElement(btnNewAccount, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnNewAccount, driver);
        Utilities.getUtilities().clickOnElement(btnNewAccount);
        return this;
    }
    /**
     * get sign up text
     * @return
     */

    public String getSignupText(){
        Utilities.getUtilities().waitForVisibilityOfElement(txt_signup, driver);
        return txt_signup.getText();
    }
    /**
     * Enter firstname
     * @return
     */
    public SignUpPage enterFirstName(String firstname) {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_FirstName, driver);
       // String email=Utilities.getUtilities().readDataFromExcel(Constant.tecStubExcelFile, "loginCredential", "Email", 2);
        Utilities.getUtilities().sendKey(txt_FirstName,firstname);
        return this;
    }
    /**
     * Enter lastname
     * @return
     */
    public SignUpPage enterLastName(String lastname) {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_LastName, driver);
        Utilities.getUtilities().sendKey(txt_LastName,lastname);
        return this;
    }
    /**
     * Enter email
     * @return
     */
    public SignUpPage enterEmailAddress(String email) {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_Email, driver);
        Utilities.getUtilities().sendKey(txt_Email,email);
        return this;
    }
    /**
     * Enter password
     * @return
     */
    public SignUpPage enterPassword(String password) {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_Password, driver);
        Utilities.getUtilities().sendKey(txt_Password,password);
        return this;
    }

    /**
     * Enter confirm password
     * @return
     */
    public SignUpPage enterConfirmPassword(String confirmPassword)  {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_ConfirmPassword, driver);
        Utilities.getUtilities().sendKey(txt_ConfirmPassword,confirmPassword);
        return this;
    }
    /**
     * Enter Street address
     * @return
     */
    public SignUpPage enterStreetAddress(String address)  {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_Address, driver);
        Utilities.getUtilities().sendKey(txt_Address,address);
        return this;
    }
    /**
     * Enter Zipcode
     * @return
     */
    public SignUpPage enterZipcode(String zipcode)  {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_Zipcode, driver);
        Utilities.getUtilities().sendKey(txt_Zipcode,zipcode);
        return this;
    }
    /**
     * Enter city
     * @return
     */
    public SignUpPage enterCity(String city)  {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_City, driver);
        Utilities.getUtilities().sendKey(txt_City,city);
        return this;
    }
    /**
     * select state
     * @return
     */
    public SignUpPage selectState(String state)  {
        Utilities.getUtilities().waitForVisibilityOfElement(select_state, driver);
        Utilities.getUtilities().selectValueFromDropdown(select_state,state);
        return this;
    }
    /**
     * select country
     * @return
     */
    public SignUpPage selectCountry(String country)  {
        Utilities.getUtilities().waitForVisibilityOfElement(select_Country, driver);
        Utilities.getUtilities().selectValueFromDropdown(select_Country,country);
        return this;
    }
    /**
     * Enter city
     * @return
     */
    public SignUpPage enterPhoneNumber(String Phone)  {
        Utilities.getUtilities().waitForVisibilityOfElement(txt_Phone, driver);
        Utilities.getUtilities().sendKey(txt_Phone,Phone);
        return this;
    }
    /**
     * Click on submit button
     * @return
     */
    public SignUpPage clickOnSubmitButton()  {
        Utilities.getUtilities().waitForVisibilityOfElement(btnSubmit, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnSubmit, driver);
        Utilities.getUtilities().clickJavaScript(btnSubmit);
        return this;
    }

    /**
     * get  error messages
     * @return
     */
    public SignUpPage getErrorMessage(){
    List<WebElement> validationMessages = driver.findElements(By.xpath("//div[@class='text-red-500 text-s']"));
    for (int i = 0; i < validationMessages.size(); i++) {
        String actualMessage = validationMessages.get(i).getText();
        System.out.println(actualMessage);
        String expectedMessage = Constant.expectedMessages[i];
        Assert.assertEquals(actualMessage,expectedMessage);
        System.out.println("Validation message " + (i + 1) + " is correct: " + actualMessage);
    }

    return this;
}

    /**
     * success message
     * @return
     */
    public boolean isSuccessMessageDisplayed(){
        if(msg_success.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * get success message
     * @return
     */
    public String getSuccessMessage(){
        Utilities.getUtilities().waitForVisibilityOfElement(msg_success, driver);
        return msg_success.getText();
    }
}
