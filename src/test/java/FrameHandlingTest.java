import org.apache.commons.io.FileUtils;
import org.bson.BSONEncoder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FrameHandlingTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("http://guidebook.seleniumacademy.com/Frames.html");

    }
    @Test
    public void switchBetweenWindow() throws IOException {
//        pierwsza ramka
        driver.switchTo().frame(0);
        WebElement firstField = driver.findElement(By.name("1"));
        firstField.sendKeys("Jestem ramką pierwszą");
        driver.switchTo().defaultContent();

//        druga ramka
        driver.switchTo().frame(1);
        WebElement secondField = driver.findElement(By.name("2"));
        secondField.sendKeys("Jestem drugą ramką");
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./target/ekran4.png"));

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}