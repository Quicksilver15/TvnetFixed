import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class TvNetTest {
    private final String HOME_PAGE = ("http://tvnet.lv");
    private WebDriver browser;
    private final By ARTICLE = By.xpath(".//a[@class = 'list-article__url']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//span[@class = 'teaser-text__comment-count']");
    private final By COMMENTS_BTN = By.xpath(".//span[@class = 'teaser-text']");
    private final By SHARE = By.xpath(".//div[contains(@class, 'block-title')]");
    private final By COMMENTS_PAGE_COMMENT_COUNT = By.xpath(".//span[contains(@class, 'article-comments-heading__count')]");


    @Test
    public void commentCountTest() {
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().window().maximize();
        browser.get(HOME_PAGE);

        List<WebElement> articles = browser.findElements(ARTICLE);

        Integer commentCount = Integer.valueOf(articles.get(3).findElement(COMMENT_COUNT).getText());

        getElementById(3).click();

        WebElement dalities = browser.findElement(SHARE);
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].scrollIntoView()", dalities);

        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ARTICLE_PAGE_COMMENT_COUNT));

        String articlePageComments = browser.findElement(ARTICLE_PAGE_COMMENT_COUNT).getText();
        Integer articlePageCommentCount = Integer.valueOf(articlePageComments.substring(1, articlePageComments.length() -1));

        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comments Count");

        browser.findElement(COMMENTS_BTN).click();

        Integer commentsPageCommentCount = Integer.valueOf(browser.findElement(COMMENTS_PAGE_COMMENT_COUNT).getText());

        Assertions.assertTrue(commentCount == commentsPageCommentCount, "Wrong Comments page Comments Count");
    }

    private WebElement getElementById(int id) {
       List<WebElement> articles = browser.findElements(ARTICLE);

        for (int i = 0; i < articles.size(); i++) {
            WebElement element = articles.get(i);
            if (i == id) {
               return element;
            }
        }
        return null;
    }
}
