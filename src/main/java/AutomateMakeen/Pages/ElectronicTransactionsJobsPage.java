package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElectronicTransactionsJobsPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public ElectronicTransactionsJobsPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }

    @FindBy(id="cph_main_btn_add_new_job")
    WebElement addNewJob;   /*زر اضافة وظيفة جديدة*/
    @FindBy(id="AddEditTitle")
    WebElement addJobPage; /*صفحه اضافة وظيفة جديدة*/
    @FindBy(id="txtJobName_1")
    WebElement txtJobNameArabic; /*حقل اسم الوظيفة*/
    @FindBy(id="drp_empDep_ddlSelectButtonTarget")
    WebElement ddlChooseDept; /*اختر الادارة*/
    @FindBy(id="drp_empDep_txtSearch")
    WebElement deptSearchWebElement; /*حقل البحث في اختر الادارة*/
    @FindBy(css = "div[id='div_maken_content'] li")
    List<WebElement> deptListWebElement;
    @FindBy(id="btn_action")
    WebElement btnSave; /*زر حفظ */
    @FindBy (xpath = "//div[@class='popup_genrl popup_genrl_width']")
    WebElement divConfirmSave;  /*نافذة تاكيد الحفظ*/
    @FindBy (id = "btnP0")
    WebElement btnOk; /*زر موافق*/

    public void insertDepartment (String department){
            ddlChooseDept.click();
            deptSearchWebElement.sendKeys(department);
            WebElement recipient = deptListWebElement.stream().filter(s -> s.getText().equals(department)).findFirst().orElse(null);
            recipient.click();
    }
    public String addNewJob (String department,String jobName){
        addNewJob.click();
        exWait.until(driver -> addJobPage.isDisplayed());
        txtJobNameArabic.sendKeys(jobName);
        insertDepartment(department);
        btnSave.click();
        exWait.until(driver -> divConfirmSave.isDisplayed());
        btnOk.click();
        return jobName;
    }
}
