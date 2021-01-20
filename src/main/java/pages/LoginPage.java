package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private WebDriver driver;
    //fields
    private By usernameTxtBox = By.cssSelector("input[placeholder='Username']");
    private By passwordTxtBox = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By emptyUsername = By.xpath("//p[normalize-space()='Enter username']");
    private By emptyPassword = By.xpath("//p[normalize-space()='Enter password']");
    private By invalidCredentials = By.xpath("//p[@class='error-msg-invalid']");

    //constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //set the username
    public void setUsername(String username) {

        driver.findElement(usernameTxtBox).clear();
        driver.findElement(usernameTxtBox).sendKeys(username);
    }

    //set the password
    public void setPassword(String password) {
        driver.findElement(passwordTxtBox).clear();
        driver.findElement(passwordTxtBox).sendKeys(password);
    }

    //error message for username filed empty
    public String getErrorMessageForEmptyUN(){
        return driver.findElement(emptyUsername).getText();
    }

    //error message for password filed empty
    public String getErrorMessageForEmptyPW(){
        return driver.findElement(emptyPassword).getText();
    }

    //error message for incorrect credentials
    public String getErrorMessageForWrongCredentials(){
        return driver.findElement(invalidCredentials).getText();
    }

    //click on the login button
    public HomePage clickLoginButton(){

        //mouse move action
        //Actions action = new Actions(driver);
        //action.moveToElement(driver.findElement(loginButton)).build().perform();

        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

        return new HomePage(driver);
    }

    public void explicitWaitForEmptyUsername(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyUsername));
    }

    public void explicitWaitForEmptyPassword(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyPassword));
    }

    public void explicitWaitForInvalidCredentials(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentials));
    }

    public void inputLoginCredentials(String un, String pw) {
        setUsername(un);
        setPassword(pw);
    }
}
