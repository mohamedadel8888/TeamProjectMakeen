package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeesOperations extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public EmployeesOperations (WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }
    @FindBy(id = "DivMainEmp") /*صفحه عمليات على الموظفين*/
    WebElement divEmpOperations;
    public WebElement getEmpOperations (){
        return divEmpOperations;
    }
    @FindBy (id ="btn_create")    /*زر تعيين موظف */
    WebElement appointEmployee;
    public void enterAppointEmployee () {
        appointEmployee.click();
    }
    private By divNewEmployee = By.id("DivNewEmp"); /*صفحه تعيين موظف*/
    public WebElement getDivNewEmployee() {
        return driver.findElement(divNewEmployee);
    }


    /****************************************************************************************/
                                       /*رقم الهوية*/
    /****************************************************************************************/

    private By nationNumberText = By.id("txt_AddNation");    /*رقم الهوية*/
    public void addNationNumber(String id) {
        WebElement nationNumber = driver.findElement(nationNumberText);
        nationNumber.clear();
        nationNumber.sendKeys(id);
    }
    public WebElement getNationalNumber() {
        return driver.findElement(nationNumberText);
    }


    private By masarButton = By.id("masarButtonT3een"); /*زر استدعاء البيانات من منصه مسار*/
    public WebElement masarBtn(){
        WebElement masarButton1 = driver.findElement(masarButton);
        return masarButton1;
    }
    private By validationAddNation = By.id("spnA_AddNation");
    private By notInMasarValidation = By.cssSelector("p[class='span_error'] span"); /*  رسالة "رقم الهوية غير موجود بمنصة مسار"  */

    public String getValidationMessage( ){
        WebElement validationDot = driver.findElement(validationAddNation);
        Actions actions = new Actions(driver);
        actions.moveToElement(validationDot).perform();
        WebElement validationMessage = driver.findElement(notInMasarValidation);
        return validationMessage.getText();
    }

    /****************************************************************************************/
                                         /*الاسم*/
    /****************************************************************************************/
    private By firstName = By.id("txt_AddEmpFrstName");
    private By secondName = By.id("txt_AddEmpSecName");
    private By thirdName = By.id("txt_AddEmpThrdName");
    private By lastName = By.id("txt_AddEmpFrthName");


    public void addFirstName (String first){
        WebElement firstNameInput = driver.findElement(firstName);
        firstNameInput.clear();
        firstNameInput.sendKeys(first);
    }
    public String getFirstName (){
        return driver.findElement(firstName).getAttribute("value");
    }

    public void addSecondName(String second){
        WebElement secondNameInput = driver.findElement(secondName);
        secondNameInput.clear();
        secondNameInput.sendKeys(second);
    }
    public String getSecondName(){
        return driver.findElement(secondName).getAttribute("value");
    }
    public void addThirdName(String third){
        WebElement thirdNameInput = driver.findElement(thirdName);
        thirdNameInput.clear();
        thirdNameInput.sendKeys(third);
    }
    public String getThirdName(){
        return driver.findElement(thirdName).getAttribute("value");
    }

    public void addFourthName(String fourth){
        WebElement fourthNameInput = driver.findElement(lastName);
        fourthNameInput.clear();
        fourthNameInput.sendKeys(fourth);
    }
    public String getFourthName(){
        return driver.findElement(lastName).getAttribute("value");
    }

    /****************************************************************************************/
                                        /*تاريخ الميلاد*/
    /****************************************************************************************/

    private By calendarIcon = By.cssSelector(".fa.fa-calendar[onclick=\"CalenderObj.showCalender('txt_AddEmpBoDate',true, prs_greg_date!=1?'Hij':'Georg')\"]");
    private By dateText = By.id("txt_AddEmpBoDate");

    public void dateSelectionFromIcon(String day, String month , String year){
        WebElement calenderIcon = driver.findElement(calendarIcon);
        calenderIcon.click();
        WebElement month1 = driver.findElement(By.id("drp_Month"));
        WebElement year1 = driver.findElement(By.id("tb_Year"));
        Select monthAll = new Select(month1);
        monthAll.selectByValue(month);
        Actions actions =new Actions(driver);
        actions.click(year1)
                .keyDown(Keys.CONTROL)
                .sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(year)
                .perform();
        WebElement day1 = driver.findElement(By.xpath("//input[@value="+day+"][@class='btn']"));
        day1.click();
    }
    public void dateSelect (String day, String month, String year){
        WebElement dateText1 = driver.findElement(dateText);
        dateText1.clear();
        dateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getDateText(){
        WebElement dateText1 = driver.findElement(dateText);
        return dateText1.getAttribute("value");
    }

    /****************************************************************************************/
                                        /* IBAN */
    /****************************************************************************************/
    private By ibanText = By.id("txt_AddBankNumber");
    public void addIBAN(String iban){
        WebElement ibanInput = driver.findElement(ibanText);
        ibanInput.clear();
        ibanInput.sendKeys(iban);
    }
    public String getIBAN(){
        return driver.findElement(ibanText).getAttribute("value");
    }









}
