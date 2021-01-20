package base;

import com.google.common.io.Files;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import utills.CookieManager;
import utills.EventReporter;
import utills.WindowManager;

import java.io.File;
import java.io.IOException;

public class BaseTests extends DataForTests {

    private EventFiringWebDriver driver;
    protected LoginPage loginPage;
    //create a thread driver
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    @BeforeClass
    public WebDriver setup() {
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
        //driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goHome();
        //setCookie();

        //initialize the driver to create a tread
        tdriver.set(driver);
        return getDriver();
    }


    public void goHome(){
        driver.get("https://oneapp.ncinga.com/ba-app/#");
        //driver.get("https://nint-service-external.strawmine.com/ba-app/#");
        //driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            //cast the driver to "takes screenshot" class
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources\\screenshots\\" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("Screenshot taken : " + screenshot.getAbsolutePath());
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); // this is now disable by chrome latest versions
        //options.setHeadless(true);
        return options;
    }

    public void refreshBrowser(){
        driver.navigate().refresh();
    }

    private void setCookie(){
        //Cookie cookie = new Cookie.Builder("name","123").domain("the-internet.herokuapp.com").build();
        //driver.manage().addCookie(cookie);

    }

    public utills.CookieManager getCookieManager(){
        return new CookieManager(driver);
    }

    //pass the driver in thread
    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }
}
