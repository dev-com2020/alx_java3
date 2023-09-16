import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.assertThat;

public class LoadCookieInfo {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
//        ścieżka do drivera
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//        inicjalizacja sesji
        driver = new ChromeDriver();
        driver.get("http://demo-store.seleniumacademy.com");

    }

    @Test
    public void loadCookies() {
        try {
            File dataFile = new File("./target/browser.data");
            FileReader fr = new FileReader(dataFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreElements()) {
                    String name = str.nextToken();
                    String value = str.nextToken();
                    String domain = str.nextToken();
                    String path = str.nextToken();
                    Date expiry = null;
                    String dt;
                    if ((dt = str.nextToken()) != null) {
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("E MMM d HH:mm:ss z yyyy", Locale.ENGLISH);// Sat Sep 16 12:44:19 CEST 2023
                        expiry = formatter.parse(dt);
                    }

                    boolean isSecure = Boolean.parseBoolean(str.nextToken());
                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    driver.manage().addCookie(ck);

                }

            }

            driver.get("http://demo-store.seleniumacademy.com/customer/account/index/");
            File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File("./target/ekran7.png"));
            assertThat(driver.findElement(By.cssSelector("div.page-title")).getText()).isEqualTo("MY DASHBOARD");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
