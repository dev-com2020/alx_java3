import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void handleWindow(){
        String firstWindow = driver.getWindowHandle();
        System.out.println("Pierwszy hanlder strony to: " + firstWindow);

        WebElement link = driver.findElement(By.linkText("Google Search"));
        link.click();

        String secondWindow = driver.getWindowHandle();
        System.out.println("Pierwszy hanlder strony to: " + secondWindow);
        System.out.println("Ilość handlerów:" + driver.getWindowHandles().size());

        driver.switchTo().window(firstWindow);

    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

}
