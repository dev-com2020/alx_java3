import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws IOException {
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
    }

//    @BeforeMethod
//    public void setup() throws MalformedURLException {
//
//        DesiredCapabilities caps = new DesiredCapabilities();
//
//        caps.setBrowserName("firefox");
//        caps.setPlatform(Platform.WINDOWS);
//
//        driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/"), caps);
//        driver.get("http://demo-store.seleniumacademy.com/");
//
//    }

    @Test
    public void searchProduct() throws IOException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("pillow");
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./target/ekran222.png"));
        assertThat(driver.getTitle()).isEqualTo("Search results for: 'pillow'");
    }

    @Test
    public void searchProductByFakeData() throws IOException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("pillow");
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();
        assertThat(driver.getTitle()).isEqualTo("Search results for: 'pillow'");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
