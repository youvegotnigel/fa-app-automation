package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    private By pageHeader = By.cssSelector("div[class='tool-block app-hdr'] div[class='toolbar-title toolbar-title-md']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageHeaderName(){
        return driver.findElement(pageHeader).getText();
    }

    public void explicitWaitForPageHeader(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }


}
