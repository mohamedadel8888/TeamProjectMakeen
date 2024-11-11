package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DelegatePage extends BaseComp {

    private WebDriver driver;
    //private WebDriverWait wait;

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
    private By delegatesTable = By.id("tbl_cpUsersDeleg");
    private By resultItems = By.cssSelector("td div");

    //Pop Up Text
    private By messagePopUp = By.className("popup_content");

    //PopUp Buttons
    private By acceptPopUpButton = By.id("btnP0");
    private By rejectPopUpButton = By.id("btnP1");



    //---------------------------------Constructor-------------------------------
    public DelegatePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


    public String getTitlePage() {
        return driver.findElement(delegatePageTitle).getText();
    }

    //Delegation Results
    public boolean getDelegateResult(String delegate) {
        List<WebElement> listSearchResults = driver.findElements(delegatesTable);
        return listSearchResults.stream().anyMatch(s -> s.findElement(resultItems).getText().contains(delegate));
    }

    public List<WebElement> getDelegatesList(String delegates) {
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


    public UsersControl clickGoBackButton(){
        driver.findElement(goBackButton).click();
        return new UsersControl(driver);

    }






}
