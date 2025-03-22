package AutomateMakeen.Pages.EmployeesOperations;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Pages.ContentAside;
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

public class AppointEmployee extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public AppointEmployee(WebDriver driver){
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
        WebElement dateText1 = driver.findElement(dateText);
        dateText1.clear();
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
    private By validationIcon_AddWork = By.id("spnA_AddWorkDate");
    private By validationDiv_AddWork = By.cssSelector("p[class='span_error'] span");

    public void workDateSelect (String day, String month, String year){
        WebElement workDateText1 = driver.findElement(workDate);
        workDateText1.clear();
        workDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public void workDateSelect (String date){
        WebElement workDateText1 = driver.findElement(workDate);
        workDateText1.clear();
        workDateText1.sendKeys(date);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getWorkDateText(){
        WebElement workDateText1 = driver.findElement(workDate);
        return workDateText1.getAttribute("value");
    }
    public String getWorkdateValidationText(){
        WebElement validationIcon1 = driver.findElement(validationIcon_AddWork);
        Actions actions = new Actions(driver);
        actions.moveToElement(validationIcon1).perform();
        WebElement validationText1 = driver.findElement(validationDiv_AddWork);
        return validationText1.getText();
    }
    /****************************************************************************************/
                                  /* تاريخ التعيين على مرتبه */
    /****************************************************************************************/
    private By levelDate = By.id("txt_AddLevelDate");
    private By validationIcon_LevelDate = By.id("spnA_AddLevelDate");
    private By validationDiv_LevelDate = By.cssSelector("b[id='spnA_AddLevelDate'] span");
    public void levelDateSelect (String day, String month, String year){
        WebElement levelDateText1 = driver.findElement(levelDate);
        levelDateText1.clear();
        levelDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public void levelDateSelect (String date){
        WebElement levelDateText1 = driver.findElement(levelDate);
        levelDateText1.clear();
        levelDateText1.sendKeys(date);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getLevelDateText(){
        WebElement levelDateText1 = driver.findElement(levelDate);
        return levelDateText1.getAttribute("value");
    }
    public String getLevelDateValidationText(){
        WebElement validationIcon1 = driver.findElement(validationIcon_LevelDate);
        Actions actions = new Actions(driver);
        actions.moveToElement(validationIcon1).perform();
        WebElement validationText1 = driver.findElement(validationDiv_LevelDate);
        return validationText1.getText();
    }
    /****************************************************************************************/
                               /* تاريخ اول تعيين بالدولة */
    /****************************************************************************************/
    private By firstJobDate = By.id("txt_AddFrstjobDate");
    private By validationIcon_FirstJoblDate = By.id("spnA_AddFrstjobDate");
    private By validationDiv_FirstJoblDate = By.cssSelector("b[id='spnA_AddFrstjobDate'] span");
    public void firstJobDateSelect (String day, String month, String year){
        WebElement firstJobDateText1 = driver.findElement(firstJobDate);
        firstJobDateText1.clear();
        firstJobDateText1.sendKeys(year+"/"+month+"/"+day);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public void firstJobDateSelect (String date){
        WebElement firstJobDateText1 = driver.findElement(firstJobDate);
        firstJobDateText1.clear();
        firstJobDateText1.sendKeys(date);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public String getFirstJobDateText(){
        WebElement firstJobDateText1 = driver.findElement(firstJobDate);
        return firstJobDateText1.getAttribute("value");
    }
    public String getFirstJobDateValidationText(){
        WebElement validationIcon1 = driver.findElement(validationIcon_FirstJoblDate);
        Actions actions = new Actions(driver);
        actions.moveToElement(validationIcon1).perform();
        WebElement validationText1 = driver.findElement(validationDiv_FirstJoblDate);
        return validationText1.getText();
    }

    /****************************************************************************************/
                                    /* بيانات القرار */
    /****************************************************************************************/
    private By toggle = By.cssSelector(".slider.round");
    private By txtRecNo = By.id("txtRecNo_transEmpsDecSec");

    public void setToggle(){
        WebElement toggle1 = driver.findElement(toggle);
        toggle1.click();
    }
    public WebElement getRecNumberTextField (){
        return driver.findElement(txtRecNo);
    }
    public void setRecNumberTextField (String number){
        WebElement txtRecNo1 = driver.findElement(txtRecNo);
        txtRecNo1.clear();
        txtRecNo1.sendKeys(number);
    }

    /****************************************************************************************/
                                   /* زر حفظ و زر عودة */
    /****************************************************************************************/
    private By saveButton = By.id("btn_add_new_emp");
    private By cancelButton = By.cssSelector("input[onclick=\"Emps.onclick_button('HideDiv', 'Add');\"]");

    public void clickSaveButton(){
        WebElement saveButton1 = driver.findElement(saveButton);
        saveButton1.click();
    }
    public void clickCancelButton(){
        WebElement cancelButton1 = driver.findElement(cancelButton);
        cancelButton1.click();
    }


    /****************************************************************************************/
                        /* زر حفظ وانشاء معاملة ورسالة الحفظ الاخيرة */
    /****************************************************************************************/

    private By divConfirmationMsg = By.cssSelector(".popup_genrl.popup_genrl_width");
    private By saveAppoint = By.id("btnP0");
    private By savingSucessMsg = By.cssSelector("div[class='popup_content']");
    private By returnBtn = By.cssSelector("input[onclick=\"Emps.onclick_button('HideDiv', 'Add');\"]"); /*زر عودة*/
    public void saveTheEmployee(){
        clickSaveButton();
        WebElement divConfirmationMsg1 = driver.findElement(divConfirmationMsg);
        exWait.until(ExpectedConditions.visibilityOf(divConfirmationMsg1));
        WebElement save1 = driver.findElement(saveAppoint);
        save1.click();
    }
    public String validateSuccessfulSavingEmployee ( String nationNumber) {
        WebElement divConfirmationMsg1 = driver.findElement(divConfirmationMsg);
        exWait.until(ExpectedConditions.invisibilityOf(divConfirmationMsg1));
        clickCancelButton();
        WebElement returnOk = driver.findElement(saveAppoint);
        returnOk.click();
        exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
        WebElement nationNumberSearchText = driver.findElement(By.id("txt_MainNation"));
        nationNumberSearchText.sendKeys(nationNumber);
        WebElement searchBtn = driver.findElement(By.xpath("(//input[@value='بحث'])[1]"));
        searchBtn.click();
        WebElement returnEmployeeNation = driver.findElement(By.xpath("(//div[normalize-space()='"+nationNumber+"'])[1]"));
        return returnEmployeeNation.getText();
    }


    /****************************************************************************************/
                                   /*التأكد من تعيين موظف*/
    /****************************************************************************************/




}
