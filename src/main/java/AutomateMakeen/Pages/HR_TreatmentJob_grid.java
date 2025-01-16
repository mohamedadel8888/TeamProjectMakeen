package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HR_TreatmentJob_grid extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public HR_TreatmentJob_grid(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //specific wait for every page
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
    }

    private By addNewJobBtnBy = By.id("cph_main_btn_add_new_job");
    private By jobNameFieldBy = By.id("txtJobName_1");
    private By ddlMangamentBy = By.id("drp_empDep_ddlSelectButtonTarget");
    private By ddlMangamentSearchBy = By.id("drp_empDep_txtSearch");
    private By divToScrollManagementBy = By.id("drp_empDep_divDest");
    private By saveBtn = By.id("btn_action");
    private By confirmSaveBtnBy = By.cssSelector("input[value='موافق']");
    private By jobListTableBy = By.xpath("//tr[8]/td");
    private By progressBy = By.id("progressBar");
    public void addNewTreatmentJob(String jobName , String management) throws InterruptedException {
        driver.findElement(addNewJobBtnBy).click();
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        driver.findElement(jobNameFieldBy).sendKeys(jobName);
        driver.findElement(ddlMangamentBy).click();
        driver.findElement(ddlMangamentSearchBy).sendKeys(management);
        try {
            driver.findElement(By.xpath("//ul[@id='drp_empDep_collapsibleDiv']/li/div/label[text()='"+management+"']")).click();
        }catch (Exception e){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollBy(0,30);", divToScrollManagementBy);
            driver.findElement(By.xpath("//ul[@id='drp_empDep_collapsibleDiv']/li/div/label[text()='"+management+"']")).click();
        }
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        driver.findElement(saveBtn).click();
        driver.findElement(confirmSaveBtnBy).click();
    }




}
