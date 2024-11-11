package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class AddDelegatePage extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    //Department Name
    //private By departmentNameDropDownList = By.id("ddl_deps_ddlSelectButton");
    private By departmentNameDropDownList = By.cssSelector("#ddl_deps_ddlSelectButton > p");
    private By departmentNameSearch = By.id("ddl_deps_txtSearch");
    private By departmentNameText = By.id("ddl_deps_ddlSelectButtonTarget");
    //private By departmentNameListItems = By.id("ddl_deps_collapsibleDiv");
    private By departmentNameListItems = By.className("list_child");

    //Delegate Employee
    private By delegatedEmployeeDropDownList = By.id("ddl_delg_jobs_ddlSelectButton");
    private By delegatedEmployeeSearch = By.id("ddl_delg_jobs_txtSearch");
    private By delegatedEmployeeText = By.id("ddl_delg_jobs_ddlSelectButtonTarget");

    //Radio Buttons
    private By acceptedPeriodRadioButton = By.id("rd_period_accepted");
    private By newPeriodRadioButton = By.id("rd_new_period");

    //Dates Input Fields
    private By delegatedFromDateField = By.id("txt_deleg_from");
    private By delegatedToDateField = By.id("txt_deleg_to");


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
    private By goBackButton = By.cssSelector("[value='عودة']");


    //PopUp Text
    private By messagePopUp = By.className("popup_content");

    //PopUp Buttons
    private By acceptPopUpButton = By.id("btnP0");
    private By rejectPopUpButton = By.id("btnP1");

    //Errors
    private By departmentNameErrorIcon = By.id("span_A_ddl_deps");
    private By departmentNameErrorMessage = By.cssSelector("#span_A_ddl_deps .span_error span");

    private By delegateEmployeeErrorIcon = By.id("span_A_ddl_delg_jobs");
    private By delegateEmployeeErrorMessage = By.cssSelector("#span_A_ddl_delg_jobs .span_error span");

    private By periodTypeErrorIcon = By.id("span_A_ddl_period");
    private By periodTypeErrorMessage = By.cssSelector("#span_A_ddl_period .span_error span");

    private By delegateDateFromErrorIcon = By.id("span_A_txt_deleg_from");
    private By delegateDateFromErrorMessage =  By.cssSelector("#span_A_txt_deleg_from .span_error span");

    private By delegateDateToErrorIcon = By.id("span_A_txt_deleg_to");
    private By delegateDateToErrorMessage =  By.cssSelector("#span_A_txt_deleg_to .span_error span");

    private By delegateTimeFromErrorIcon = By.id("spanA_txtDelegateTimeFrom");
    private By delegateTimeFromErrorMessage =  By.cssSelector("#spanA_txtDelegateTimeFrom .span_error span");

    private By delegateTimeToErrorIcon = By.id("spanA_txtDelegateTimeTo");
    private By delegateTimeToErrorMessage =  By.cssSelector("#spanA_txtDelegateTimeTo .span_error span");

    private By delegateTimeFromPeriodErrorIcon = By.id("spanA_ddlDelegateTimeFrom");
    private By delegateTimeFromPeriodErrorMessage =  By.cssSelector("#spanA_ddlDelegateTimeFrom .span_error span");

    private By delegateTimeToPeriodErrorIcon = By.id("spanA_ddlDelegateTimeTo");
    private By delegateTimeToPeriodErrorMessage =  By.cssSelector("#spanA_ddlDelegateTimeTo .span_error span");




    //------------------------Constructor------------------------

    public AddDelegatePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Department Name Methods

    public void selectDepartmentNameFromDropDown(String option) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(departmentNameDropDownList));
        driver.findElement(departmentNameDropDownList).click();
        driver.findElement(departmentNameSearch).sendKeys(option);
        driver.findElement(By.xpath("//label[contains(text(), '" + option + "')]")).click();
    }

    public String getDepartmentNameField(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(departmentNameText));
        return driver.findElement(departmentNameText).getAttribute("value");
    }


    public void scrollInDepartmentNameDDL(String targetText){
        // Locate the dropdown element and open it if necessary
        wait.until(ExpectedConditions.visibilityOfElementLocated(departmentNameDropDownList));
        //driver.findElement(departmentNameDropDownList).click();
        WebElement dropdown = driver.findElement(departmentNameDropDownList);
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        dropdown.click();
        boolean found = false;
        js = (JavascriptExecutor) driver;

        // Loop through the dropdown items to find the specific text
        while (!found) {
            // Get all the currently visible items in the dropdown
            List<WebElement> items = driver.findElements(departmentNameListItems);

            for (WebElement item : items) {
                if (item.getText().equals(targetText)) {
                    item.click(); // Click the item if it matches the target text
                    found = true;
                    break;
                }
            }

            // If not found, scroll the dropdown
            if (!found) {
                js.executeScript("arguments[0].scrollTop += arguments[0].offsetHeight;", dropdown);
            }
        }
    }


    //Delegate Employee Name Methods

    public void selectDelegatedEmployeeFromDropDown(String option) {
        driver.findElement(delegatedEmployeeDropDownList).click();
        driver.findElement(delegatedEmployeeSearch).sendKeys(option);
        driver.findElement(By.xpath("//label[contains(text(), '" + option + "')]")).click();
    }

    public String getDelegatedEmployeeNameField(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(delegatedEmployeeText));
        return driver.findElement(delegatedEmployeeText).getAttribute("value");
    }

    //Radio Buttons Methods

    public void chooseNewPeriodRadioButton(){
        driver.findElement(newPeriodRadioButton).click();
    }

    //Input Dates In Fields Methods

    public void inputDelegateDateFrom(String date){
        driver.findElement(delegatedFromDateField).sendKeys(date);
    }

    public void inputDelegateDateTo(String date){
        driver.findElement(delegatedToDateField).sendKeys(date);
    }

    //Choose Dates From Calender Methods

    public void chooseDelegateFromDateCalender(String month, String year, String day){
        WebElement delegatedFromCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(0);
        delegatedFromCalenderIcon.click();
        selectMonthFromCalender(month);
        driver.findElement(delegateCalenderYear).sendKeys(year);
        driver.findElement(By.id("btn_Day" + day + ")]")).click();
    }

    public void chooseDelegateToDateCalender(String month, String year, String day){
        WebElement delegatedToCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(1);
        delegatedToCalenderIcon.click();
        selectMonthFromCalender(month);
        driver.findElement(delegateCalenderYear).sendKeys(year);
        driver.findElement(By.id("btn_Day" + day + ")]")).click();
    }

    public void selectMonthFromCalender(String option){
        Select dropdownElement = new Select(driver.findElement(delegateCalenderMonthDropDown));
        dropdownElement.selectByVisibleText(option);
    }


    //Time Period Methods

    public void inputTimePeriodFrom(String date){
        driver.findElement(delegateTimePeriodFromField).sendKeys(date);
    }

    public void inputTimePeriodTo(String date){
        driver.findElement(delegateTimePeriodToField).sendKeys(date);
    }

    public void selectTimePeriodFromDropDown(String option){
        Select dropdownElement = new Select(driver.findElement(delegateTimePeriodFromDropDown));
        dropdownElement.selectByVisibleText(option);
    }

    public void selectTimePeriodToDropDown(String option){
        Select dropdownElement = new Select(driver.findElement(delegateTimePeriodToDropDown));
        dropdownElement.selectByVisibleText(option);
    }

    //  Buttons Methods

    public void clickSaveButton(){
        driver.findElement(saveButton).click();
    }

    public DelegatePage clickGoBackButton(){
        driver.findElement(goBackButton).click();
        return new DelegatePage(driver);
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



}
