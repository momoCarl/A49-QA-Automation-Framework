import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.BasePage;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    public String url = "https://qa.koel.app/";

    public WebDriver driver;
    WebDriverWait wait;
    static Actions action = null;

    @BeforeClass
    void setupClass() throws MalformedURLException {
        String browser = System.getProperty("browser");
        driver = setupBrowser(browser);
        WebDriverManager.chromedriver().setup();
        setupLambda();

    }
    WebDriver setupLambda() throws MalformedURLException {
        String hubURL ="https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("117.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "nataliafdc2015");
        ltOptions.put("accessKey", "LFeuWpaO0SbfY6JhWiOWVBDCtEtuBPzu61PRsLcWpf2tss4AOL");
        ltOptions.put("project", "TestPro");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return  new RemoteWebDriver(new URL(hubURL),browserOptions);
    }

    public WebDriver setupBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String gridURL = "http://192.168.1.246:4444";
        desiredCapabilities.setCapability("browserName", "chrome");
        return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), desiredCapabilities);
    }


    public void setBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        action = new Actions(driver);
        BasePage basepage = new BasePage(driver);
        basepage.navigateToPage(url);
    }



   /* @AfterMethod
    public void closeBrowser() {
        driver.close();
    }**/

}