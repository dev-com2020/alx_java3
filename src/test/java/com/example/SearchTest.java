package com.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Parameters({"searchWord","items"})
    @Test
    public void searchProductByFakeData(String searchWord, int items ) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchWord);
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();
        assertThat(driver.getTitle()).isEqualTo("Search results for: '" + searchWord + "'");
        List<WebElement> searchItems = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[1]/div[2]/div/p/strong"));
        assertThat(searchItems.size()).isEqualTo(items);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}

