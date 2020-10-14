package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private WebDriver driver;
    //fields
    private By usernameTxtBox = By.cssSelector("input[placeholder='User name']");
    private By passwordTxtBox = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.cssSelector("span[class='button-inner']");
    private By emptyUsername = By.xpath("//p[contains(normalize-space(),'Username is required')]");
    private By emptyPassword = By.xpath("//p[contains(normalize-space(),'Password is required')]");
    private By invalidCredentials = By.xpath("//div[contains(@class,'media-body text-center')]//p");

    //constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //set the username
    public void setUsername(String username) {
        driver.findElement(usernameTxtBox).sendKeys(username);
    }

    //set the password
    public void setPassword(String password) {
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
    public HomePage clickLoginButton() throws InterruptedException {
        driver.findElement(loginButton).click();
        Thread.sleep(2500); //calculate sleep timer
        return new HomePage(driver);
    }
}
