package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

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

    /****************************************************************************************/
                                       /* الجنسية */
    /****************************************************************************************/
    private By ddlNationality = By.id("drp_AddEmpNation_ddlSelectButtonTarget");
    private By nationalityDiv = By.id("drp_AddEmpNation_collapsibleDiv");
    public void selectNationality(String nationality){
        WebElement nationalityAll = driver.findElement(ddlNationality);
        nationalityAll.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(nationalityDiv)));
        WebElement nationality1;
        if (Objects.equals(nationality, "اختر الجنسية"))
        {
            nationality1 = driver.findElement(By.xpath("//li[contains(text(),'اختر الجنسية')]"));
        }else {
            nationality1 = driver.findElement(By.xpath("//label[contains(text(),'"+nationality+"')]"));
        }
        nationality1.click();
    }
    public String getNationality(){
        return driver.findElement(ddlNationality).getText();
    }

    /****************************************************************************************/
                                       /* الفئه */
    /****************************************************************************************/
    private By ddlEmployeeType = By.id("drp_AddJobType");

    public void selectEmployeeType(String employeeType){
        Select ddlEmployeeType1 = new Select(driver.findElement(ddlEmployeeType));
        ddlEmployeeType1.selectByVisibleText(employeeType);
    }
    public String getEmployeeType(){
        Select ddlEmployeeType1 = new Select(driver.findElement(ddlEmployeeType));
        return ddlEmployeeType1.getFirstSelectedOption().getText();
    }

    /****************************************************************************************/
                                   /* الوظيفة الاساسية */
    /****************************************************************************************/
    private By ddlMajorJob = By.id("drp_AddBaseJob_ddlSelectButtonTarget");
    private By majorJobDiv = By.id("drp_AddBaseJob_collapsibleDiv");
    public void selectMajorJob(String majorJob){
        WebElement majorJobAll = driver.findElement(ddlMajorJob);
        majorJobAll.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(majorJobDiv)));
        WebElement majorJob1;
        if (Objects.equals(majorJob, "إختر الوظيفة الاساسية"))
        {
            majorJob1 = driver.findElement(By.xpath("//li[contains(text(),'إختر الوظيفة الاساسية')]"));
        }else {
            majorJob1 = driver.findElement(By.xpath("//label[contains(text(),'"+majorJob+"')]"));
        }
        majorJob1.click();
    }
    public String getMajorJob(){
        return driver.findElement(ddlMajorJob).getText();
    }

    /****************************************************************************************/
                                   /* الوظيفة المكلف بها */
    /****************************************************************************************/
    private By ddlMandateJob = By.id("drp_AddMandateJob_ddlSelectButtonTarget");
    private By mandateJobDiv = By.id("drp_AddMandateJob_collapsibleDiv");
    public void selectMandateJob(String mandateJob){
        WebElement majorJobAll = driver.findElement(ddlMandateJob);
        majorJobAll.click();
        exWait.until(ExpectedConditions.visibilityOf(driver.findElement(mandateJobDiv)));
        WebElement mandateJob1;
        if (Objects.equals(mandateJob, "اختر الوظيفة المكلف بها"))
        {
            mandateJob1 = driver.findElement(By.xpath("//li[contains(text(),'اختر الوظيفة المكلف بها')]"));
        }else {
            mandateJob1 = driver.findElement(By.xpath("//label[contains(text(),'"+ mandateJob +"')]"));
        }
        mandateJob1.click();
    }
    public String getMandateJob(){
        return driver.findElement(ddlMandateJob).getText();
    }
    /****************************************************************************************/
                                        /* الدرجة */
    /****************************************************************************************/
    private By ddlDegree = By.id("drp_AddDegree");

    public void selectDegree(String degree){
        Select ddlDegree1 = new Select(driver.findElement(ddlDegree));
        ddlDegree1.selectByVisibleText(degree);
    }
    public String getDegree(){
        Select ddlDegree1 = new Select(driver.findElement(ddlDegree));
        return ddlDegree1.getFirstSelectedOption().getText();
    }
    /****************************************************************************************/
                                /* رقم الموظف بالبلدية */
    /****************************************************************************************/
    private By organizationNumber = By.id("txt_AddOrganizationNumber");
    public void addOrganizationNumber(String number){
        WebElement organizationNumber1 = driver.findElement(organizationNumber);
        organizationNumber1.clear();
        organizationNumber1.sendKeys(number);
    }
    public String getOrganizationNumber(){
        return driver.findElement(organizationNumber).getAttribute("value");
    }

    /****************************************************************************************/
                                    /* تاريخ التعيين في الجهه */
    /****************************************************************************************/
    private By workDate = By.id("txt_AddWorkDate");

    public void workDateSelect (String day, String month, String year){
        WebElement workDateText1 = driver.findElement(workDate);
        workDateText1.clear();
        workDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getWorkDateText(){
        WebElement workDateText1 = driver.findElement(workDate);
        return workDateText1.getAttribute("value");
    }
    /****************************************************************************************/
                                  /* تاريخ التعيين على مرتبه */
    /****************************************************************************************/
    private By levelDate = By.id("txt_AddLevelDate");
    public void levelDateSelect (String day, String month, String year){
        WebElement levelDateText1 = driver.findElement(levelDate);
        levelDateText1.clear();
        levelDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getLevelDateText(){
        WebElement levelDateText1 = driver.findElement(levelDate);
        return levelDateText1.getAttribute("value");
    }
    /****************************************************************************************/
                               /* تاريخ اول تعيين بالدولة */
    /****************************************************************************************/
    private By firstJobDate = By.id("txt_AddFrstjobDate");
    public void firstJobDateSelect (String day, String month, String year){
        WebElement firstJobDateText1 = driver.findElement(firstJobDate);
        firstJobDateText1.clear();
        firstJobDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getFirstJobDateText(){
        WebElement firstJobDateText1 = driver.findElement(firstJobDate);
        return firstJobDateText1.getAttribute("value");
    }



}
