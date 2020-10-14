package login;

import base.BaseTests;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {


    @Test(priority=3, description="Verify invalid login")
    @Description("Verify invalid login")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() throws InterruptedException {

        loginPage.setUsername("ba-bengal");  //set username
        loginPage.setPassword("123");  //set password

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Assert.assertEquals(homePage.getHomePageHeaderName(),"Business Administrator App");

    }

    @Test(priority=1, description="Verify invalid login with empty data set")
    @Description("Verify invalid login with empty data set")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyEmptyUsernameAndPassword() throws InterruptedException {

        loginPage.setUsername("");  //set username
        loginPage.setPassword("");  //set password

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Assert.assertEquals(loginPage.getErrorMessageForEmptyUN(),"Username is required");
        Assert.assertEquals(loginPage.getErrorMessageForEmptyPW(),"Password is required");

    }

    @Test(priority=2, description="Verify invalid login with invalid data set")
    @Description("Verify invalid login with invalid data set")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyIncorrectUsernameAndPassword() throws InterruptedException {

        loginPage.setUsername("abc");  //set username
        loginPage.setPassword("qwer");  //set password

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        //Assert.assertEquals(loginPage.getErrorMessageForWrongCredentials(),"Error !! Invalid username or password");
        assertTrue(loginPage.getErrorMessageForWrongCredentials().contains("Error !! Invalid"));

    }


}
