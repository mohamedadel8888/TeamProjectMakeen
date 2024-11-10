package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateExternalMailPage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    public CreateExternalMailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }
    @FindBy (xpath = "(//i[@class='fa fa-angle-double-down'])[3]")
    WebElement mailArrowWebElement;

    @FindBy (id = "s_m_141")
    WebElement createExternalMailWebELement;


    public void goToCreateExternalMail(){
        exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
        mailArrowWebElement.click();
        createExternalMailWebELement.click();
    }
}
