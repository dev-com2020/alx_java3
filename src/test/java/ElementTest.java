

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ElementTest {
    WebDriver driver;

    @BeforeClass
    @BeforeMethod
    public void setup() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setBrowserName("chrome");
        caps.setPlatform(Platform.WINDOWS);

        driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/"), caps);
    }

    @BeforeMethod
    public void navigate(){
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @Test(groups = {"szybki"})
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

    @Test
    public void byTagNameLocatorElement(){
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Znalezione linki: " + links.size());
        links.stream()
//                .filter(elem -> elem.getText().length() > 0)
                .filter(elem -> !elem.getText().isEmpty())
                .forEach(elem -> System.out.println(elem.getText()));
    }

    @Test
    public void byXpathLocator(){
        WebElement searchBox = driver.findElement(By.xpath("//*[@id='search']"));
        searchBox.sendKeys("Bags");
        searchBox.submit();
        assertThat(driver.getTitle()).isEqualTo("Search results for: 'Bags'");

    }
    @Test
    public void byXpathLocatorSearchResult(){
        WebElement searchBox = driver.findElement(By.xpath("//*[@id='search']"));
        searchBox.sendKeys("Bags");
        searchBox.submit();
        WebElement pageTitle = driver.findElement(By.className("page-title"));
        assertThat(pageTitle.getText()).isEqualTo("SEARCH RESULTS FOR 'BAGS'");
    }

    @Test
    public void byCssSelectorLocator(){

        WebElement aboutUs = driver.findElement(By.cssSelector("a[href*='about-magento-demo-store/']"));
        aboutUs.click();

        assertThat(driver.getTitle()).isEqualTo("About Us");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
