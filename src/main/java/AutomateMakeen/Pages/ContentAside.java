package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
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
        exWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    private By notificationBy = By.id("div_extGeha_notification");
    private By hrTreeBy = By.id("prog_prs");
    private By employeeTreeBy = By.id("s_m_67");
    public HR_Employee goToEmployeePage(){
        exWait.until(ExpectedConditions.invisibilityOf(driver.findElement(notificationBy)));
        exWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(hrTreeBy)));
        driver.findElement(hrTreeBy).click();
        driver.findElement(employeeTreeBy).click();
        return new HR_Employee(driver);
    }
}
