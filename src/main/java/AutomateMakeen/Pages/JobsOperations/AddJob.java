package AutomateMakeen.Pages.JobsOperations;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Pages.ContentAside;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddJob extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public AddJob(WebDriver driver){
        super(driver);
        this.driver = driver;
        contentAside = new ContentAside(driver);
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    @FindBy(id= "cph_main_btn_MainAdd") /*زر احداث وظيفه*/
    WebElement addJobBtn;

    public void goToAddJob (){
        addJobBtn.click();
    }

    /***********************************************************************************/
                                 /*   السلسلة الوظيفية   */
    /***********************************************************************************/
    private By searchIconJobClassCode = By.id("spn_AddJobClass"); /*ايقونة بحث السلسله الوظيفية*/
    private By classCodeDiv = By.id("dv_pickPopupCntrl"); /*نافذه السلسله الوظيفيه*/
    private By searchTextClassCode = By.xpath("(//input[@type='text'])[1]"); /* حقل نصي البحث في نافذة السلسله الوظيفية*/
    private By searchBtn = By.xpath("(//input[@value='بحث'])[1]"); /* زر بحث  */
    private By chkBoxSelect = By.id("cb_Select_undefined"); /*تشك بوكس السلسله الوظيفية*/

    public void selectClassCode (String text){
        WebElement searchIcon = driver.findElement(searchIconJobClassCode);
        searchIcon.click();
        WebElement classDiv = driver.findElement(classCodeDiv);
        exWait.until(ExpectedConditions.visibilityOf(classDiv));
        WebElement searchText = driver.findElement(searchTextClassCode);
        searchText.sendKeys(text);
        WebElement searchBtn1 = driver.findElement(searchBtn);
        searchBtn1.click();
        WebElement checkBox = driver.findElement(chkBoxSelect);
        checkBox.click();
    }
    /***********************************************************************************/
                                /*   المسمى الوظيفي   */
    /***********************************************************************************/
    private By searchIconJobName = By.id("spn_JobNameAdd"); /*ايقونة بحث المسمى الوظيفي*/
    private By JobNameDiv = By.id("dv_pickPopupCntrl"); /*نافذه المسمى الوظيفي*/
    private By searchTextJobName = By.xpath("(//input[@type='text'])[1]"); /* حقل نصي البحث في نافذة المسمى الوظيفي*/
    public String selectJobName (String text){
        WebElement searchIcon = driver.findElement(searchIconJobName);
        searchIcon.click();
        WebElement JobDiv = driver.findElement(JobNameDiv);
        exWait.until(ExpectedConditions.visibilityOf(JobDiv));
        WebElement searchText = driver.findElement(searchTextJobName);
        searchText.sendKeys(text);
        WebElement searchBtn1 = driver.findElement(searchBtn);
        searchBtn1.click();
        WebElement jobName = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]"));
        String jobName1 = jobName.getText();
        WebElement checkBox = driver.findElement(chkBoxSelect);
        checkBox.click();
        return jobName1;
    }
    /***********************************************************************************/
                               /*    التدرج المهني   */
    /***********************************************************************************/
    private By searchIconJobGradiant = By.xpath("(//span[@class='srch_btn'])[5]"); /*ايقونة بحث التدرج المهني*/

    public void selectJobGradient (String text){
        WebElement searchIcon = driver.findElement(searchIconJobGradiant);
        searchIcon.click();
        WebElement JobDiv = driver.findElement(JobNameDiv);
        exWait.until(ExpectedConditions.visibilityOf(JobDiv));
        WebElement searchText = driver.findElement(searchTextJobName);
        searchText.sendKeys(text);
        WebElement searchBtn1 = driver.findElement(searchBtn);
        searchBtn1.click();
        WebElement checkBox = driver.findElement(chkBoxSelect);
        checkBox.click();
    }
    /***********************************************************************************/
                                    /*    الفئة    */
    /***********************************************************************************/
    private By jobType = By.id("drp_JobType"); /*قائمة الفئة */

    public void selectJobType (String text){
        Select selectJob = new Select(driver.findElement(jobType));
        selectJob.selectByVisibleText(text);
    }
    /***********************************************************************************/
                                   /*  المرتبة    */
    /***********************************************************************************/
    private By jobDegree = By.id("drp_JobDegree"); /*قائمة المرتية*/

    public void selectJobDegree (String text){
        Select selectJob = new Select(driver.findElement(jobDegree));
        selectJob.selectByVisibleText(text);
    }
    /***********************************************************************************/
                                    /*  رقم الوظيفة    */
    /***********************************************************************************/
    private By jobNumber = By.id("txt_JobNumber"); /*حقل نصي رقم الوظيفة*/
    public void addJobNumber (String text){
        WebElement jobNumber1 = driver.findElement(jobNumber);
        jobNumber1.sendKeys(text);
    }
    /***********************************************************************************/
                                    /*   اسم الادارة   */
    /***********************************************************************************/
    private By ddlChooseDept = By.id("drp_Departs_ddlSelectButtonTarget");
    private By deptSearchWebElement = By.id("drp_Departs_txtSearch");
    @FindBy(css = "div[id='div_maken_content'] li")
    List<WebElement> deptListWebElement;

    public void insertDepartment (String department){
        WebElement ddlChooseDept1 = driver.findElement(ddlChooseDept);
        ddlChooseDept1.click();
        WebElement deptSearchWebElement1 = driver.findElement(deptSearchWebElement);
        deptSearchWebElement1.sendKeys(department);
        WebElement recipient = deptListWebElement.stream().filter(s -> s.getText().equals(department)).findFirst().orElse(null);
        recipient.click();
    }
    /***********************************************************************************/
                                  /*   حاله الوظيفة   */
    /***********************************************************************************/
    private By jobStatus = By.id("drp_job_Type_ddlSelectButton");
    @FindBy(css = "div[id='div_maken_content'] li")
    List<WebElement> jobStatusListWebElement;

    public void insertJobStatus (String status){
        WebElement ddlStatusJob1 = driver.findElement(jobStatus);
        ddlStatusJob1.click();
        WebElement status1 = jobStatusListWebElement.stream().filter(s -> s.getText().equals(status)).findFirst().orElse(null);
        status1.click();
    }
    /***********************************************************************************/
                                 /*   تاريخ الانشاء    */
    /***********************************************************************************/
    private By createDate = By.id("txt_JobStartDate");
    public void startDateSelect(String day, String month, String year){
        WebElement levelDateText1 = driver.findElement(createDate);
        levelDateText1.clear();
        levelDateText1.sendKeys(day+"/"+month+"/"+year);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    /****************************************************************************************/
                                    /* بيانات القرار */
    /****************************************************************************************/
    private By toggle = By.cssSelector(".slider.round");
    private By txtRecNo = By.id("txtRecNo_dv_JobsDecSecAdd");
    private By txtDecNo = By.id("txtDecNo_dv_JobsDecSecAdd"); /*رقم القرار*/
    private By textDecDate = By.id("txtDecDate_dv_JobsDecSecAdd"); /*تاريخ القرار */
    private By ddlDecSelect = By.id("drpDecSrc_dv_JobsDecSecAdd"); /*جهه القرار*/
    private By ddlSearchText = By.id("drpDecSrc_dv_JobsDecSecAdd_txtSearch"); /*حقل البحث*/
    private By ddlListDiv = By.id("drpDecSrc_dv_JobsDecSecAdd_collapsibleDiv");

    public void setToggle(){
        WebElement toggle1 = driver.findElement(toggle);
        toggle1.click();
    }
    public WebElement getRecNumberTextField (){
        return driver.findElement(txtRecNo);
    }
    public void setRecNumberTextField (String number){  /*رقم الارشيف /رقم الوارد*/
        WebElement txtRecNo1 = driver.findElement(txtRecNo);
        txtRecNo1.clear();
        txtRecNo1.sendKeys(number);
    }
    public void decNumber(String text) { /*رقم القرار */
        WebElement decText = driver.findElement(txtDecNo);
        decText.clear();
        decText.sendKeys(text);
    }
    public void decDateSelect (String date){ /*تاريخ القرار */
        WebElement decDateText1 = driver.findElement(textDecDate);
        decDateText1.clear();
        decDateText1.sendKeys(date);
        Actions actions = new Actions(driver);
        actions.moveByOffset(50, 50).click().perform();
    }
    public void decSelectDirection (String text){  /*جهة القرار*/
        WebElement ddlButton = driver.findElement(ddlDecSelect);
        ddlButton.click();
        WebElement ddlDiv = driver.findElement(ddlListDiv);
        exWait.until(ExpectedConditions.visibilityOf(ddlDiv));
        WebElement ddlSearch = driver.findElement(ddlSearchText);
        ddlSearch.sendKeys(text);
        WebElement selection = driver.findElement(By.xpath("//label[normalize-space()='"+text+"']"));
        selection.click();
    }
    /****************************************************************************************/
                                          /* الحفظ والعودة */
    /****************************************************************************************/
    private By btnSave = By.id("btn_SaveJob"); /*زر حفظ*/
    private By btnBack = By.id("btn_backToMain"); /*زر عودة*/
    public void saveJob (){   /* حفظ */
        WebElement save1 = driver.findElement(btnSave);
        save1.click();
    }
    public void returnToMain(){  /*عودة */
        WebElement btnBack1 = driver.findElement(btnBack);
        btnBack1.click();
    }

    /****************************************************************************************/
                                 /*  التاكد من اضافه الوظيفة */
    /****************************************************************************************/
    private By divConfirm = By.xpath("//div[@class='popup_genrl popup_genrl_width']");
    private By btnOk = By.id("btnP0");
    private By srchJobNumText = By.id("txt_mainJobNum");
    private By srchJobClassCode = By.id("txt_SrchJobClassCode");
    private By searchBtnJob = By.cssSelector("input[value='بحث']");

    public void confirmJob (){
        WebElement divConfirm1 = driver.findElement(divConfirm);
        exWait.until(ExpectedConditions.visibilityOf(divConfirm1));
        WebElement btnOk1 = driver.findElement(btnOk);
        btnOk1.click();
        exWait.until(ExpectedConditions.invisibilityOf(divConfirm1));
    }
    public void searchJob (String jobNum, String classCode){
        WebElement searchJobNum = driver.findElement(srchJobNumText);
        searchJobNum.sendKeys(jobNum);
        WebElement searchClasCode = driver.findElement(srchJobClassCode);
        searchClasCode.sendKeys(classCode);
        WebElement searchBtnJob1 = driver.findElement(searchBtnJob);
        searchBtnJob1.click();
    }
    public String getJobName (){
        WebElement jobName = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]"));
        return jobName.getText();
    }




}
