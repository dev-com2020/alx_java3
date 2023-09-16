import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class Navi {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();

    }

    @Test
    public void searchProduct() throws IOException {
        driver.navigate().to("http://demo-store.seleniumacademy.com/");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("GLASS VASE");
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();

        assertThat(driver.getTitle()).isEqualTo("Search results for: 'GLASS VASE'");

        driver.navigate().back();
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./target/ekran5.png"));
        driver.navigate().forward();
        File screenShot1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot1, new File("./target/ekran6.png"));
        driver.navigate().refresh();

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}

