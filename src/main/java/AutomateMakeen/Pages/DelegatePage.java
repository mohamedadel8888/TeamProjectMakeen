package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DelegatePage extends BaseComp {

    protected WebDriver driver;
    private WebDriverWait wait;

    private By delegatePageTitle = By.id("spn_title");

    //Buttons
    private By addDelegateButton = By.id("btn_add_deleg");
    private By goBackButton = By.id("btnn_back");
    private By delegateReportButton = By.id("cph_main_btn_deleg_rep");
    private By editDelegationButton = By.id("btn_edit_deleg");
    private By disableDelegationButton = By.id("btn_dis_deleg");

    //Empty Search Result
    private By emptyDelegates = By.id("dv_Empty");

    //Table of Delegates
    private By delegatesTable = By.cssSelector("#tbl_cpUsersDeleg tbody tr");
    //private By resultItems = By.cssSelector("td div");
    private By resultItemsRow = By.xpath("//tr/td[3]/div");

    //Pop Up Text
    private By messagePopUp = By.className("popup_content");

    //PopUp Buttons
    private By acceptPopUpButton = By.id("btnP0");
    private By rejectPopUpButton = By.id("btnP1");

    //Sign Out Button
    private By signOutButton = By.id("btn_session_time_out");



    //---------------------------------Constructor-------------------------------
    public DelegatePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getTitlePage() {
        return driver.findElement(delegatePageTitle).getText();
    }

    //Delegation Results
    public boolean getDelegateResult(String delegateName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(delegatesTable));
        List<WebElement> resultItems = driver.findElements(resultItemsRow);
        return resultItems.stream().anyMatch(s -> s.getText().contains(delegateName));
    }


    public List<WebElement> getDelegateList(String delegates) {
        return driver.findElements(By.xpath("//*[contains(@full_title, '" + delegates + "')]"));
    }


    //Button Methods
    public AddDelegatePage clickAddButton(){
        driver.findElement(addDelegateButton).click();
        return new AddDelegatePage(driver);
    }

    public EditDelegatePage clickEditButton(){
        driver.findElement(editDelegationButton).click();
        return new EditDelegatePage(driver);
    }

    public void clickDeleteDelegatation(){
        driver.findElement(disableDelegationButton).click();
    }

    //Pop Up Methods
    public String getPopUpMessage(){
        return driver.findElement(messagePopUp).getText();
    }

    public void acceptPopUp(){
        driver.findElement(acceptPopUpButton).click();
    }

    public void rejectPopUp(){
        driver.findElement(rejectPopUpButton).click();
    }


    //Go Back Method
    public UsersControl clickGoBackButton(){
        driver.findElement(goBackButton).click();
        return new UsersControl(driver);
    }

    //Sign Out Method

    public LoginPage clickSignOut(){
        driver.findElement(signOutButton).click();
        return new LoginPage(driver);
    }
}
