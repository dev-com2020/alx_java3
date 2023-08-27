import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ElementTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void navigate(){
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @Test
    public void elementGetAttrExamples(){
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Nazwa searchBoxa to: " + searchBox.getAttribute("name"));
        System.out.println("Id searchBoxa to: " + searchBox.getAttribute("id"));
        System.out.println("Klasa searchBoxa to: " + searchBox.getAttribute("class"));
        System.out.println("Placeholder searchBoxa to: " + searchBox.getAttribute("placeholder"));

    }

    @Test
    public void elementSendKeysCompositeExample(){
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("pillow");
        searchBox.submit();
        assertThat(driver.getTitle()).isEqualTo("Search results for: 'pillow'");
    }

    @Test
    public void elementGetCssValue(){
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Czcionka w boxie to: " + searchBox.getCssValue("font-family"));
    }

    @Test
    public void elementLocalizationAndSize(){
        WebElement searchBox = driver.findElement(By.name("q"));
        System.out.println("Lokalizacja elementu: "+ searchBox.getLocation());
        System.out.println("Rozmiar boxu " + searchBox.getSize());
    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
