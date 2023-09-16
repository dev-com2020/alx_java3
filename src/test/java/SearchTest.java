import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
//        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screenShot, new File("./target/ekran1.png"));
    }

    @Test
    public void searchProduct() throws IOException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("pillow");
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./target/ekran2.png"));
        assertThat(driver.getTitle()).isEqualTo("Search results for: 'pillow'");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
