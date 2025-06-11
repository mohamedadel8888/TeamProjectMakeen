package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InboxPageQCMS extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public InboxPageQCMS(WebDriver driver) {
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(12));
    }

    private By inboxPage = By.xpath("//div[@class='et_inbox']");
    public  InboxPageQCMS getInboxPage() {
        WebElement inboxPage1 = driver.findElement(inboxPage);
        exWait.until(ExpectedConditions.invisibilityOf(inboxPage1));
        return  new InboxPageQCMS(driver);
    }
}
