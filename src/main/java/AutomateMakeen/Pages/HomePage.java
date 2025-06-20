package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;



    @FindBy(id = "btn_session_time_out")
    WebElement signOutBtn;
    @FindBy(css = "li[onclick='goto_mainpage();'] a")
    WebElement homePageIconWebElement;
    @FindBy (id = "divRedirectToQElite")
    WebElement eliteHomePageIcon;
    @FindBy(css = "#QElite")
    WebElement QElite;



    public void goToHomePage(){
        homePageIconWebElement.click();
    }
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(20));
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
    public void goToElite (){
        try{
            eliteHomePageIcon.click();
        }
        catch (Exception e){
            exWait.until(ExpectedConditions.urlContains("MainPage.aspx"));
            eliteHomePageIcon.click();
        }
        QElite.click();
    }

    public WebElement getSignOutBtn() {
        return signOutBtn;
    }

}
