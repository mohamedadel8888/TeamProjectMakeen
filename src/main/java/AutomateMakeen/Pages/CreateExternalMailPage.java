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
    @FindBy (xpath = "//a[@class='MasterPagetTree_Main_Program']")
    WebElement mailArrowWebElement;

    @FindBy (id = "s_m_141")
    WebElement createExternalMailWebELement;

//
//    public static void main(String[] args){
//        WebDriver driver1 = new EdgeDriver();
//        LoginPage loginPage = new LoginPage(driver1);
//        loginPage.goToLoginPage();
//        loginPage.loginUserWithRemMe("0342169","24602460");
//        CreateExternalMailPage createExternalMailPage = new CreateExternalMailPage(driver1);
//        WebDriverWait wait =  new WebDriverWait(driver1 , Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(createExternalMailPage.mailArrowWebElement));
//        createExternalMailPage.mailArrowWebElement.click();
//        createExternalMailPage.createExternalMailWebELement.click();
//    }
}
