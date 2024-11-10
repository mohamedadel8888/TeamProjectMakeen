package AutomateMakeen.Base;

import AutomateMakeen.Pages.ContentAside;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComp {
    private WebDriver driver;
    private  WebDriverWait exWait;
    private Actions actions ;
    protected ContentAside contentAside ; ;
    public BaseComp(WebDriver driver)
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        exWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.driver = driver;
        contentAside = new ContentAside(driver);
    }
    public void cutPastAction(WebElement copyFrom, WebElement copyTo, String txt ){
        actions = new Actions(driver);
        copyFrom.sendKeys(txt);
        actions.moveToElement(copyFrom).keyDown(Keys.CONTROL).sendKeys("a").sendKeys("x").keyUp(Keys.CONTROL).build().perform();
        copyTo.sendKeys("");
        actions.moveToElement(copyTo).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }
    public void tapAction(){
        actions = new Actions(driver);
        actions.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
    }

    public void hoverOnAction(WebElement webElement){
        actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }
}
