package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Base.ContentAside;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Mail_Inbox_Grid extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public Mail_Inbox_Grid(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(15));
        contentAside = new ContentAside(driver);
    }

    public void getRecentlyAddedMail(String mailSub){
        driver.findElement(By.xpath("(//td[12]/div[contains(.,'"+mailSub+"')])[1]")).click();
    }
    private By etSubjectBy = By.id("dv_inbox_trFullSub");

    private By etRecipientOwnerBy = By.id("dv_inbox_trRecOwner");

   private By etSenderBy = By.id ("dv_inbox_trSource");

   private By etTypeBy = By.id ( "dv_inbox_trType");

    private By etImportNumBy = By.id ( "dv_inbox_trIncNum");

    private By etArchiveNymBy = By.id("div_inbox_trRec");
    public List<String> getMailData(){
        //exWait.until(ExpectedConditions.visibilityOf(subjectImpWebElement));
        List<String> mailData = new ArrayList<>();
        mailData.add(driver.findElement(etSubjectBy).getText());
        mailData.add(driver.findElement(etRecipientOwnerBy).getText());
        mailData.add(driver.findElement(etSenderBy).getText());
        mailData.add(driver.findElement(etTypeBy).getText());
        mailData.add(driver.findElement(etImportNumBy).getText());
        mailData.add(driver.findElement(etArchiveNymBy).getText());
        return mailData;
    }
}
