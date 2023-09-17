import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class IAmTheEventListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeNavigateTo(String url, WebDriver driver){
        System.out.println("Before navigate to " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver){
        System.out.println("Before navigate back. Right now I'm at "+ driver.getCurrentUrl());
    }

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
    }

}
