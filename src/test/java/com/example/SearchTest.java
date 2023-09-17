package com.example;

import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SearchTest {

    WebDriver driver;


//    @DataProvider(name = "searchWords")
//    public Object[][] provider(){
//        return new Object[][]{
//                {"phones", 0},
//                {"music", 5},
//                {"pillow", 2},
//                {"iphone", 0}
//        };
//    }

    @DataProvider(name = "searchWords")
    public Iterator<Object[]> provider() throws Exception {
        CSVReader reader = new CSVReader(
                new FileReader("./src/test/resources/data/data.csv"),',','\'',1);
        List<Object[]> myEntries = new ArrayList<Object[]>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) !=null){
            myEntries.add(nextLine);
        }
        reader.close();
        return myEntries.iterator();
    }

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

//    @Parameters({"searchWord","items"})
    @Test(dataProvider = "searchWords")
    public void searchProductByFakeData(String searchWord,String items) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchWord);
        WebElement searchButton = driver.findElement(By.className("search-button"));
        searchButton.click();
        assertThat(driver.getTitle()).isEqualTo("Search results for: '" + searchWord + "'");
        List<WebElement> searchItems = driver.findElements(By.xpath("//h2[@class='product-name']/a"));
        assertThat(searchItems.size()).isEqualTo(Integer.parseInt(items));
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}

