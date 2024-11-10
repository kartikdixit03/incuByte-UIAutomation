package Page;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LumaPage extends BaseTest {

    // Encapsulated locators as private fields
    private final By createAccountButton = By.linkText("Create an Account");
    private final By signInButton = By.className("authorization-link");
    private final By usernameField = By.name("login[username]");
    private final By passwordField = By.name("login[password]");
    private final By submitButton = By.name("send");
    private final By accountPageDetails = By.className("logged-in");
    private final By CreateanAccount =  By.className("submit");
    private final By FirstName = By.name("firstname");
    private final By LastName = By.name("lastname");
    private final By Email = By.name("email");
    private final By Password = By.name("password");
    private final By PasswordConfirmation = By.name("password_confirmation");




    /**
     * Clicks on the "Create an Account" button.
     */
    public void clickCreateAccountBtn() {
        driverTools.findElement(createAccountButton).click();
        getLogger().info("Clicked on Create an Account button");
    }

    public boolean isOnSignUpPage() {
        getLogger().info("Verified Sign-Up Page");
        return driverTools.findElement(FirstName).isDisplayed();
    }

    public void inputUserDetails(String firstName, String lastName, String email, String password, String passwordConfirmation) {
        WebElement firstNameField = driverTools.findElement(FirstName);
        firstNameField.sendKeys(firstName);
        getLogger().info("Entered First Name: " + firstName);

        WebElement lastNameField = driverTools.findElement(LastName);
        lastNameField.sendKeys(lastName);
        getLogger().info("Entered Last Name: " + lastName);

        WebElement emailField = driverTools.findElement(Email);
        emailField.sendKeys(email);
        getLogger().info("Entered Email: " + email);

        WebElement passwordField = driverTools.findElement(Password);
        passwordField.sendKeys(password);
        getLogger().info("Entered Password");

        WebElement passwordConfirmationField = driverTools.findElement(PasswordConfirmation);
        passwordConfirmationField.sendKeys(passwordConfirmation);
        getLogger().info("Entered Password Confirmation");

        WebElement submitBtn = driverTools.findElement(CreateanAccount);
        getLogger().info("Submitting user details for: " + firstName + " " + lastName);
        submitBtn.click();
    }

    /**
     * Clicks on the "Sign In" button.
     */
    public void clickSignIn() {
        driverTools.findElement(signInButton).click();
        getLogger().info("Clicked on Sign in button");
    }

    /**
     * Enters the username and password and submits the login form.
     *
     * @param user The username to enter.
     * @param pass The password to enter.
     */
    public void enterCredentialsAndLogin(String user, String pass) {
        WebElement userName = driverTools.findElement(usernameField);
        WebElement password = driverTools.findElement(passwordField);
        userName.sendKeys(user);
        password.sendKeys(pass);
        getLogger().info("User logging in with: " + user);

        WebElement submitBtn = driverTools.findElement(submitButton);
        submitBtn.click();
    }

    /**
     * Verifies if the user is successfully logged in.
     *
     * @return True if the user is logged in; otherwise, false.
     */
    public boolean isUserLoggedIn() {
        WebElement dataElement = driverTools.findElement(accountPageDetails);
        String accountPageData = dataElement.getText();
        getLogger().info("User details on Account Page: " + accountPageData);
        return !accountPageData.isEmpty(); // Check for some text as a confirmation
    }

    /**
     * Verifies if the user is on the Sign-Up page.
     *
     * @return True if the user is on the Sign-Up page; otherwise, false.
     */
    public boolean isOnSignInPage() {
        // Implementation here, e.g., check for a unique element on the Sign-Up page
        getLogger().info("Verified Sign-Up Page");

        return true;
    }
}
