package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Pages.EmployeesOperations.AppointEmployee;
import AutomateMakeen.Pages.EmployeesOperations.ReassignEmployee;
import AutomateMakeen.Pages.JobsOperations.AddJob;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ContentAside extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ContentAside(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[1]")
    private WebElement empAffairArrowWebElement;

    @FindBy(id = "s_m_65")
    private WebElement adminStructureWebElement;

    @FindBy(id = "cph_main_btn_AddElement")
    private WebElement addNewDeptWebElement;

    @FindBy(id = "prog_prs")
    private WebElement humanResources;   /*الموارد البشرية*/

    @FindBy(id = "prog_cp")
    private WebElement controlPanel;  /*لوحه التحكم */

    @FindBy(id = "s_m_62")
    private WebElement userControl;  /*ادارة المستخدمين */

    @FindBy(xpath = "(//i[@class='fa fa-angle-double-down'])[1]")
    private WebElement mailArrowWebElement;  /*زر قائمةالبريد في القائمة الجانية*/

    @FindBy(id = "s_m_141")
    private WebElement createExternalMailWebELement;  /*اختيار انشاء بريد خارجي من قائمة البريد*/

    @FindBy(id = "cph_main_btn_edit_user")
    private WebElement editUserButtonWebElement;  /*اختيار زر تعديل الحساب من ادارة المستخدمين*/

    @FindBy(id = "cb_:1")
    private WebElement firstCheckboxWebElement;  /*اختيار اول مربع من النتائج*/

    @FindBy (id = "txt_user_id")  /* رقم المستخدم */
    private WebElement UserIDWebElement;

    @FindBy (id ="btn_srch")    /* بحث */
    private  WebElement singleSearch;

//    @FindBy(css=".alrt-bx")
//    WebElement alertMessage;
//    @FindBy (className = "close_box")
//    WebElement closeNotificationsIcon;

    @FindBy(id = "div_extGeha_notification")
    WebElement notificationWebElement;
    public UsersControl goToUsersControl() {
        exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        exWait.until(ExpectedConditions.elementToBeClickable(controlPanel));
        controlPanel.click();
        userControl.click();
        return new UsersControl(driver);
    }

    public CreateExternalMailPage goToCreateExternalMail() {
        try {
            exWait.until(ExpectedConditions.visibilityOf(notificationWebElement));
            exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            createExternalMailWebELement.click();
            return new CreateExternalMailPage(driver);
        }
        catch (Exception e) {
            mailArrowWebElement.click();
            createExternalMailWebELement.click();
            return new CreateExternalMailPage(driver);
        }
    }



    public EditAccountPage goToCreateExternalEditAccount() {
        exWait.until(ExpectedConditions.elementToBeClickable(controlPanel));
        controlPanel.click();
        userControl.click();
        exWait.until(ExpectedConditions.elementToBeClickable(firstCheckboxWebElement));
        UserIDWebElement.sendKeys("1654198");
        singleSearch.click();
        firstCheckboxWebElement.click();
        editUserButtonWebElement.click();
        return new EditAccountPage  (driver);
    }
    @FindBy(id = "s_m_69")
    WebElement exportedMailWebElement;

    @FindBy(id ="tbl_outbox")
    WebElement tableOutWebElement;
    @FindBy(id ="tbl_inbox")
    WebElement tableInWebElement;


    public OutboxMails goToExportedMail(){
        //exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            exportedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableOutWebElement));
            return new OutboxMails(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            exportedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableOutWebElement));
            return new OutboxMails(driver);
        }
    }
    @FindBy(id = "s_m_68")
    WebElement importedMailWebElement;

    public ImportedMails goToImportedMail(){
        exWait.until(ExpectedConditions.invisibilityOf(notificationWebElement));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(mailArrowWebElement));
            mailArrowWebElement.click();
            importedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableInWebElement));
            return new ImportedMails(driver);
        }catch(NoSuchElementException e){
            mailArrowWebElement.click();
            importedMailWebElement.click();
            exWait.until(ExpectedConditions.visibilityOf(tableInWebElement));
            return new ImportedMails(driver);
        }
    }

    @FindBy(id ="s_m_5")   /*عمليات على الموظفين*/
    WebElement employeeOperations;
    @FindBy(id = "DivMainEmp")
    WebElement divEmpOperations;
    public AppointEmployee goToEmployeeOperations_AppointEmployee(){

        try{
            exWait.until(ExpectedConditions.visibilityOf(notificationWebElement));
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new AppointEmployee(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new AppointEmployee(driver);
        }
    }
    public AppointEmployee goToEmployeeOperations_AppointEmployee_FromInside(){
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new AppointEmployee(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new AppointEmployee(driver);
        }
    }
    public ReassignEmployee goToEmployeeOperations_ReassignEmployee(){

        try{
            exWait.until(ExpectedConditions.visibilityOf(notificationWebElement));
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new ReassignEmployee(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new ReassignEmployee(driver);
        }
    }
    public ReassignEmployee goToEmployeeOperations_ReassignEmployee_FromInside(){

        try{
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new ReassignEmployee(driver);
        }catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            employeeOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(divEmpOperations));
            return new ReassignEmployee(driver);
        }
    }

    @FindBy (id = "s_m_66")  /*صفحه وظائف التعاملات الالكترونية*/
    WebElement electronicTransactionsJobs;
    @FindBy (id ="tbl_jobs")  /*جدول الوظائف*/
    WebElement tableJobs;
    public ElectronicTransactionsJobsPage goToElectronicTransactionsJobsPage(){  /*فتح صفحه و ظائف التعاملات الالكترونية*/
        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            electronicTransactionsJobs.click();
            exWait.until(ExpectedConditions.visibilityOf(tableJobs));
            return new ElectronicTransactionsJobsPage(driver);
        }
        catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            electronicTransactionsJobs.click();
            exWait.until(ExpectedConditions.visibilityOf(tableJobs));
            return new ElectronicTransactionsJobsPage(driver);
        }

    }
    @FindBy (id = "s_m_3") /*صفحه عمليات الوظائف*/
    WebElement jobOperations;
    @FindBy (id = "prs_transactionJobs_GridDiv")
    WebElement tableMandateJobs;
    public AddJob goToJobOperations(){  /*فتح صفحه عمليات الوظائف */
        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            jobOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(tableMandateJobs));
            return new AddJob(driver);
        }
        catch(NoSuchElementException e){
            exWait.until(ExpectedConditions.elementToBeClickable(humanResources));
            humanResources.click();
            jobOperations.click();
            exWait.until(ExpectedConditions.visibilityOf(tableMandateJobs));
            return new AddJob(driver);
        }

    }





}
