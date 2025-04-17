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
    public void selectJobName (String text){
        WebElement searchIcon = driver.findElement(searchIconJobName);
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

}
