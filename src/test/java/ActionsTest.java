import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ActionsTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        ścieżka do driveraccc
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
//        driver.get("http://guidebook.seleniumacademy.com/Selectable.html");

    }

    @Test
    public void shouldPerformCompositeAction() throws IOException {

        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("five"));

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(one)
                .click(three)
                .click(five)
                .keyUp(Keys.CONTROL);
//                .click(one);


//    Action compositeAction = actions.build();
//    compositeAction.perform();
        actions.perform();

    File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenShot, new File("./target/ekran8.png"));
    }

    @Test
    public void shouldMoveByOffset(){
        WebElement three = driver.findElement(By.name("three"));
        System.out.println(" X:" + three.getLocation().getX() + " Y:" + three.getLocation().getY());
        Actions actions = new Actions(driver);
        actions.moveByOffset(three.getLocation().getX() + 1, three.getLocation().getY() + 1).click();
        actions.perform();
    }

    @Test
    public void shouldMoveByOffsetAndClickMultiple(){
        WebElement three = driver.findElement(By.name("three"));
        int tileWidth = 100;
        int tileHeight = 80;
        Actions actions = new Actions(driver);
        actions.moveByOffset(3 * tileWidth,3 * tileHeight).click();
        actions.perform();

    }

    @Test
    public void shouldMoveByOffsetAndClickBingMap(){
        int tileWidth = 100;
        int tileHeight = 100;
        driver.get("https://www.bing.com/maps?cp=52.40237%7E16.942753&lvl=17.2");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement but = driver.findElement(By.id("bnp_btn_accept"));
        but.click();
        Actions actions = new Actions(driver);
        actions.moveByOffset(10 * tileWidth,4 * tileHeight).contextClick();
        actions.perform();

    }

}
