import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    @Description("Tutaj będzie jakiś opis testu")
    public void navigationToUrl(){
        driver.get("http://demo-store.seleniumacademy.com/");
        captureScreenshot();
        Assert.assertEquals(driver.getTitle(),"Madison Island");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] captureScreenshot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
