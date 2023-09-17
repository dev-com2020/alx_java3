import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class IAmTheDriver {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
            IAmTheEventListener eventListener = new IAmTheEventListener();
            eventFiringWebDriver.register(eventListener);
            eventFiringWebDriver.get("https://www.alx.pl");
            eventFiringWebDriver.get("https://www.facebook.com");
            eventFiringWebDriver.navigate().back();
        } finally {
            driver.close();
            driver.quit();
        }
    }
}
