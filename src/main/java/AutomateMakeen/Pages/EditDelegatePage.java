package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditDelegatePage extends BaseComp {

    protected WebDriver driver;
    private WebDriverWait wait;

    //Page Title
    private By editDelegatePageTitle = By.id("spn_AddEdittitle");

    //Department Name
    private By departmentNameEditDelegate = By.id("ddl_deps_ddlSelectButtonTarget");

    //Delegate Employee
    private By delegateEmployeeEdit = By.id("ddl_delg_jobs");
    private By delegateEmployeeFieldContent = By.id("ddl_delg_jobs_ddlSelectButtonTarget");

    //Dates Input Fields
    private By delegateEditFromDateField = By.id("txt_deleg_from");
    private By delegateEditToDateField = By.id("txt_deleg_to");

    /*
    //Calender Icons
    private WebElement delegatedFromCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(0);
    private WebElement delegatedToCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(1);


     */
    //Calender Inputs
    private By delegateCalenderMonthDropDown = By.id("drp_Month");
    private By delegateCalenderYear = By.id("tb_Year");


    //Time Period Input Fields
    private By delegateTimePeriodFromField = By.id("txtDelegateTimeFrom");
    private By delegateTimePeriodToField = By.id("txtDelegateTimeTo");

    //Time Period Drop Down AM-PM
    private By delegateTimePeriodFromDropDown = By.cssSelector("#ddlDelegateTimeFrom");
    private By delegateTimePeriodToDropDown = By.cssSelector("#ddlDelegateTimeTo");

    //Buttons
    private By saveButton = By.cssSelector("[value='حفظ']");
    private By goBackButton = By.cssSelector("#fs_add_deleg > div.btn-bx > input:nth-child(2)");

    //Errors

    private By delegateEditDateFromErrorIcon = By.id("span_A_txt_deleg_from");
    private By delegateEditDateFromErrorMessage =  By.cssSelector("#span_A_txt_deleg_from .span_error span");

    private By delegateEditDateToErrorIcon = By.id("span_A_txt_deleg_to");
    private By delegateEditDateToErrorMessage =  By.cssSelector("#span_A_txt_deleg_to .span_error span");

    private By delegateEditTimeFromErrorIcon = By.id("spanA_txtDelegateTimeFrom");
    private By delegateEditTimeFromErrorMessage =  By.cssSelector("#spanA_txtDelegateTimeFrom .span_error span");

    private By delegateEditTimeToErrorIcon = By.id("spanA_txtDelegateTimeTo");
    private By delegateEditTimeToErrorMessage =  By.cssSelector("#spanA_txtDelegateTimeTo .span_error span");


    //PopUp Text
    private By messagePopUp = By.className("popup_content");

    //PopUp Buttons
    private By acceptPopUpButton = By.id("btnP0");
    private By rejectPopUpButton = By.id("btnP1");



    public EditDelegatePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void inputEditDelegateDateFrom(String date){
        driver.findElement(delegateEditFromDateField).sendKeys(date);
    }

    public void inputEditDelegateDateTo(String date){
        driver.findElement(delegateEditToDateField).sendKeys(date);
    }

    public void clearEditDelegateDateFrom(){
        driver.findElement(delegateEditFromDateField).clear();
    }

    public void clearEditDelegateDateTo(){
        driver.findElement(delegateEditToDateField).clear();
    }


    public String getEditDelegateTitlePage() {
        return driver.findElement(editDelegatePageTitle).getText();
    }

    public String getEditDelegateEmployeeField(){
        return driver.findElement(delegateEmployeeFieldContent).getText();
    }


    public void inputEditTimePeriodFrom(String date){
        wait.until(ExpectedConditions.visibilityOfElementLocated(delegateTimePeriodFromField));
        driver.findElement(delegateTimePeriodFromField).click();
        driver.findElement(delegateTimePeriodFromField).sendKeys(date);
    }

    public void inputEditTimePeriodTo(String date){
        driver.findElement(delegateTimePeriodToField).sendKeys(date);
    }

    public void selectEditTimePeriodFromDropDown(String option){
        Select dropdownElement = new Select(driver.findElement(delegateTimePeriodFromDropDown));
        dropdownElement.selectByVisibleText(option);
    }

    public void selectEditTimePeriodToDropDown(String option){
        Select dropdownElement = new Select(driver.findElement(delegateTimePeriodToDropDown));
        dropdownElement.selectByVisibleText(option);
    }


    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }

    public DelegatePage clickGoBack(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(goBackButton));
        driver.findElement(goBackButton).click();
        return new DelegatePage(driver);
    }

    public void acceptPopUp(){
        driver.findElement(acceptPopUpButton).click();
    }

    public void rejectPopUp(){
        driver.findElement(rejectPopUpButton).click();
    }


    public String getEditDelegateDateFromErrorMessage(){
        WebElement hoverOnAction = driver.findElement(delegateEditDateFromErrorIcon);
        hoverOnAction(hoverOnAction);
        WebElement toggletipNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(delegateEditDateFromErrorMessage));
        return toggletipNameElement.getText();
    }

    public String getEditDelegateDateToErrorMessage(){
        WebElement hoverOnAction = driver.findElement(delegateEditDateToErrorIcon);
        hoverOnAction(hoverOnAction);
        WebElement toggletipNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(delegateEditDateToErrorMessage));
        return toggletipNameElement.getText();
    }



}
