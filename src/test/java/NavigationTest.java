import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
    }

    @Test
    public void navigationToUrl(){
        driver.get("http://demo-store.seleniumacademy.com/");
        Assert.assertEquals(driver.getTitle(),"Madison Island");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
