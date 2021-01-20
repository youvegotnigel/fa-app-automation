package login;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utills.AllureListener;

import static org.testng.Assert.assertTrue;

@Listeners({AllureListener.class})
public class LoginTests extends BaseTests {

    @Test(priority = 3, description = "Verify valid login", dataProvider = "dataForValidLogin")
    @Description("Verify valid login")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin(String username, String password) throws InterruptedException {

        loginPage.inputLoginCredentials(username,password);

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver
        homePage.explicitWaitForPageHeader();
        Assert.assertEquals(homePage.getHomePageHeaderName(),"Business Administrator App");
    }

    @Test(priority = 2, description = "Verify invalid login with invalid data set", dataProvider = "dataForInValidLogin")
    @Description("Verify invalid login with invalid data set")
    @Severity(SeverityLevel.NORMAL)
    public void verifyIncorrectUsernameAndPassword(String username, String password){

        refreshBrowser();
        loginPage.inputLoginCredentials(username,password);

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver
        loginPage.explicitWaitForInvalidCredentials();

        Assert.assertEquals(loginPage.getErrorMessageForWrongCredentials(),"Invalid username or password");
        assertTrue(loginPage.getErrorMessageForWrongCredentials().contains("Invalid"));
    }

    @Test(priority = 2, description = "Verify invalid login for incorrect username but correct password")
    @Description("Verify invalid login for incorrect username but correct password")
    @Severity(SeverityLevel.MINOR)
    public void verifyIncorrectUsernameAndCorrectPassword(){

        refreshBrowser();
        loginPage.inputLoginCredentials("123","123");

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver
        loginPage.explicitWaitForInvalidCredentials();

        Assert.assertEquals(loginPage.getErrorMessageForWrongCredentials(),"Invalid username or password");
        assertTrue(loginPage.getErrorMessageForWrongCredentials().contains("Invalid"));
    }

    @Test(priority = 2, description = "Verify invalid login for correct username but incorrect password")
    @Description("Verify invalid login for correct username but incorrect password")
    @Severity(SeverityLevel.MINOR)
    public void verifyCorrectUsernameAndIncorrectPassword(){

        refreshBrowser();
        loginPage.inputLoginCredentials("ba-bengal","ba-bengal");

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver
        loginPage.explicitWaitForInvalidCredentials();

        Assert.assertEquals(loginPage.getErrorMessageForWrongCredentials(),"Invalid username or password");
        assertTrue(loginPage.getErrorMessageForWrongCredentials().contains("Invalid"));
    }

    @Test(priority=1, description="Verify invalid login with empty data set")
    @Description("Verify invalid login with empty data set")
    @Severity(SeverityLevel.NORMAL)
    public void verifyEmptyUsernameAndPassword() {

        refreshBrowser();
        loginPage.inputLoginCredentials("","");

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver
        loginPage.explicitWaitForEmptyUsername();
        loginPage.explicitWaitForEmptyPassword();

        Assert.assertEquals(loginPage.getErrorMessageForEmptyUN(),"Enter username");
        Assert.assertEquals(loginPage.getErrorMessageForEmptyPW(),"Enter password");

    }

}
