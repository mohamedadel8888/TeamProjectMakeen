package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ContentAside extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ContentAside(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    private By notificationBy = By.id("div_extGeha_notification");
    private By hrTreeBy = By.id("prog_prs");
    private By employeeTreeBy = By.id("s_m_67");
    public HR_Employee_Grid goToEmployeePage() throws InterruptedException {
    try {
        exWait.until(ExpectedConditions.invisibilityOf(driver.findElement(notificationBy)));
        exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(hrTreeBy)));
        driver.findElement(hrTreeBy).click();
        driver.findElement(employeeTreeBy).click();
//        Thread.sleep(2000);
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));

        return new HR_Employee_Grid(driver);
    }catch(NoSuchElementException e){
        driver.findElement(hrTreeBy).click();
        driver.findElement(employeeTreeBy).click();
//        Thread.sleep(2000);
//        exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
        return new HR_Employee_Grid(driver);
        }
    }
    private By treatmentJobTreeBy = By.id("s_m_66");
    public HR_TreatmentJob_grid goToTreatmentJob() throws InterruptedException {
        exWait.until(ExpectedConditions.invisibilityOf(driver.findElement(notificationBy)));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(hrTreeBy));
            driver.findElement(hrTreeBy).click();
            driver.findElement(treatmentJobTreeBy).click();
//            exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
            return new HR_TreatmentJob_grid(driver);
        }catch(NoSuchElementException e){
            driver.findElement(hrTreeBy).click();
            driver.findElement(treatmentJobTreeBy).click();
//            exWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
            return new HR_TreatmentJob_grid(driver);
        }
    }
    private By archiveTreeBy = By.id("prog_arc");
    private By archiveSearchTreeBy = By.id("s_m_73");
    private By archiveTableBy = By.id("tbl_arc");
    public Archive_Search_Grid goToArchiveSearch() throws InterruptedException {
        WebDriverWait archivePageOpeningWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        exWait.until(ExpectedConditions.invisibilityOf(driver.findElement(notificationBy)));
        try{
            exWait.until(ExpectedConditions.elementToBeClickable(archiveTreeBy));
            driver.findElement(archiveTreeBy).click();
            driver.findElement(archiveSearchTreeBy).click();
            archivePageOpeningWait.until(ExpectedConditions.visibilityOf(driver.findElement(archiveTableBy)));
//            archivePageOpeningWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
            return new Archive_Search_Grid(driver);
        }catch(NoSuchElementException e){
            driver.findElement(archiveTreeBy).click();
            driver.findElement(archiveSearchTreeBy).click();
            archivePageOpeningWait.until(ExpectedConditions.visibilityOf(driver.findElement(archiveTableBy)));
//            archivePageOpeningWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-icon")));
            return new Archive_Search_Grid(driver);
        }
    }

}

