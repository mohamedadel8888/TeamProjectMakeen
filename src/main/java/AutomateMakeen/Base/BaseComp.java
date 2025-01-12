package AutomateMakeen.Base;

import AutomateMakeen.Pages.ContentAside;
import AutomateMakeen.Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComp {
    private WebDriver driver;
    private  WebDriverWait exWait;
    private Actions actions ;
    public ContentAside contentAside ; ;
    public BaseComp(WebDriver driver)
    {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        exWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        this.driver = driver;
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

    public String getValidatorState(WebElement validator){
        switch(validator.getAttribute("class")){
            case "fa fa-question-circle redText": return "Red Circle";
            case "fa arow-none fa-asterisk":
            case "fa fa-asterisk redText": return "Asterisk";
            default: return "Error !!";
        }
    }
    @FindBy(id = "txt_pickPopUp_srchParam")
    WebElement docTypeControlTxtSearchWebElement;

    @FindBy(id = "btn_pickPopUp_srch")
    WebElement docTypeControlSearchBtnWebElement;

    @FindBy(id = "btn_session_time_out")
    WebElement signOutBtn;

    public void control(WebElement openControlWebElement,String searchTxt){
        openControlWebElement.click();
        docTypeControlTxtSearchWebElement.sendKeys(searchTxt);
        docTypeControlSearchBtnWebElement.click();
        driver.findElement(By.xpath("//div[@title='"+searchTxt+"']/../../td/input")).click();
    }

    public LoginPage signOut(){
        signOutBtn.click();
        return new LoginPage(driver);
    }

}
