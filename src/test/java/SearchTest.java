import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @Test
    public void searchProduct(){
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
