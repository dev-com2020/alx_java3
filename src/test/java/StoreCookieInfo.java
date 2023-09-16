import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StoreCookieInfo {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("https://www.bing.com/maps?cp=52.40237%7E16.942753&lvl=17.2");

    }

    @Test
    public void storeCookies(){
//        driver.findElement(By.id("email")).sendKeys("user@seleniumacademy.com");
//        driver.findElement(By.id("pass")).sendKeys("tester");
//        driver.findElement(By.id("send2")).submit();

        File dataFile = new File("./target/browser2.data");
        try {
            dataFile.delete();
            dataFile.createNewFile();
            FileWriter fos = new FileWriter(dataFile);
            BufferedWriter bos = new BufferedWriter(fos);
            for (Cookie ck: driver.manage().getCookies()){
                bos.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";"
                + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.isSecure()));
                bos.newLine();
            }
            bos.flush();
            bos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
