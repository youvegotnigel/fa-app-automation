package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By pageHeader = By.cssSelector("div[class='tool-block app-hdr'] div[class='toolbar-title toolbar-title-md']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageHeaderName(){
        return driver.findElement(pageHeader).getText();
    }

}
