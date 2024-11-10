package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

public class AddDelegatePage extends BaseComp {
    private WebDriver driver;
   private WebDriverWait wait;

    //Department Name
    private By departmentNameDropDownList = By.id("ddl_deps_ddlSelectButton");
    private By departmentNameSearch = By.id("ddl_deps_txtSearch");
    private By departmentNameText = By.id("ddl_deps_ddlSelectButtonTarget");

    //Delegated Employee
    private By delegatedEmployeeDropDownList = By.id("ddl_delg_jobs_ddlSelectButton");
    private By delegatedEmployeeSearch = By.id("ddl_delg_jobs_txtSearch");
    private By delegatedEmployeeText = By.id("ddl_delg_jobs_ddlSelectButtonTarget");

    //Radio Buttons
    private By acceptedPeriodRadioButton = By.id("rd_period_accepted");
    private By newPeriodRadioButton = By.id("rd_new_period");

    //Dates Input Fields
    private By delegatedFromDateField = By.id("txt_deleg_from");
    private By delegatedToDateField = By.id("txt_deleg_to");

    //Calender Icons
    private WebElement delegatedFromCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(0);
    private WebElement delegatedToCalenderIcon = driver.findElements(By.cssSelector(".fa.fa-calendar")).get(1);

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



    //------------------------Constructor------------------------

    public AddDelegatePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Department Name Methods

    public void selectDepartmentNameFromDropDown(String option) {
        driver.findElement(departmentNameDropDownList).click();
        driver.findElement(departmentNameSearch).sendKeys(option);
        driver.findElement(By.xpath("//label[contains(text(), '" + option + "')]")).click();
    }

    public String getDepartmentNameField(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(departmentNameText));
        return driver.findElement(departmentNameText).getAttribute("value");
    }

    public voi


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
        delegatedFromCalenderIcon.click();
        selectMonthFromCalender(month);
        driver.findElement(delegateCalenderYear).sendKeys(year);
        driver.findElement(By.id("btn_Day" + day + ")]")).click();
    }

    public void chooseDelegateToDateCalender(String month, String year, String day){
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

    public void clickGoBackButton(){
        driver.findElement(goBackButton).click();
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
