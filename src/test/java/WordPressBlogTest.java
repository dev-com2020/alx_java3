import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WordPressBlogTest {

    WebDriver driver;
    String username = "admin";
    String password = "$$SUU3$$N#";

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }
    
    @Test
    public void testAddNewPost(){
        AllPostPage allPostPage = new AllPostPage(driver);
        allPostPage.createNewPost(username, driver.getPageSource());
    }
}
