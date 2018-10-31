import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class TvNetTest {
    private final String HOME_PAGE = "www.tvnet.lv";
    private WebDriver browser;

    private final By ARTICLE = By.xpath(".//*[@itemprop = 'url']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//span[contains(@class, 'heading__count')]");
    private final By COMMENTS_BTN = By.xpath(".//a[contains(@class, 'button--comment')]");

    @Test
    public void commentCountTest() {
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().window().maximize();
        browser.get(HOME_PAGE);

        List<WebElement> articles = browser.findElements(ARTICLE);
        Integer commentCount = Integer.valueOf(articles.get(3).findElement(COMMENT_COUNT).getText());

        getElementById(2).click();

        Integer articlePageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());

        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");

        browser.findElement(COMMENTS_BTN).click();

        Integer commentPageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());

        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Article page Comment Count");
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
