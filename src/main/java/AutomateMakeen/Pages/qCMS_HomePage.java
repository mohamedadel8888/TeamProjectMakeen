package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Base.ContentAside;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class qCMS_HomePage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;

    @FindBy(id = "btn_session_time_out")
    WebElement signOutBtn;


    public qCMS_HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);

    }
    public String getHomeUrl(){
        exWait.until(ExpectedConditions.urlContains("MainPage.aspx"));
        return driver.getCurrentUrl();
    }
    public LoginPage signOut(){
        signOutBtn.click();
        return new LoginPage(driver);
    }

    private By userNameBy = By.id("user_name");
    public String getUserName(){
        return driver.findElement(userNameBy).getText();
    }

    private By userDeptBy = By.id("user_dep");
    public String getUserDept(){
        hoverOnAction(driver.findElement(userNameBy));
        return driver.findElement(userDeptBy).getText();
    }
    private By userJobBy = By.id("user_job");
    public String getUserTreatJob(){
        hoverOnAction(driver.findElement(userNameBy));
        return driver.findElement(userJobBy).getText();
    }
}
