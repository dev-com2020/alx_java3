import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WindowHandlingTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("http://guidebook.seleniumacademy.com/Window.html");

    }
    @Test
    public void handleWindow() throws IOException {
        String firstWindow = driver.getWindowHandle();
        System.out.println("Pierwszy hanlder strony to: " + firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();

        String secondWindow = driver.getWindowHandle();
        System.out.println("Drugi hanlder strony to: " + secondWindow);
        System.out.println("Ilość handlerów:" + driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("./target/ekran3.png"));

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
