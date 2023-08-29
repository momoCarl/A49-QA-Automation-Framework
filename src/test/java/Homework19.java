import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    String url;

    @Test
    @Parameters({"BaseURL"})
    public void deletePlaylist(String baseUrl) {
        driver.get(baseUrl);
        loginValideEmailPassowrd("momo.carlos5@gmail.com", "te$t$tudent");
    }

    private void loginValideEmailPassowrd(String e, String p) {
        WebElement email = driver.findElement(By.cssSelector("input[placeholder=\"Email Address\"]"));
        email.clear();
        email.sendKeys(e);
        driver.findElement(By.cssSelector("[placeholder=\"Password\"] ")).sendKeys(p);
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }

}
