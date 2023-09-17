import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllPostPage {

    WebDriver driver;

    @FindBy(id = "the-list")
    WebElement postContainer;

    @FindBy(id = "post-search-input")
    WebElement searchPosts;

    @FindBy(id = "cat")
    WebElement viewByCategory;

    @FindBy(linkText = "Add New")
    WebElement addNewPost;

    public AllPostPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://demo-blog.seleniumacademy.com/wp/wp-admin/edit.php");
    }

    public void createNewPost(String title, String description) {
        addNewPost.click();

    }
}