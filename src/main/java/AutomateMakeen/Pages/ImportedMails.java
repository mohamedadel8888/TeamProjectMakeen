package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ImportedMails extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ImportedMails(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(15));
        contentAside = new ContentAside(driver);
    }

    @FindBy(xpath = "(//td)[12]")
    WebElement firstRowInTable;

    public void getRecentlyAddedMail(String mailSub){
        driver.findElement(By.xpath("(//td[12]/div[text()='"+mailSub+"'])[1]")).click();
    }
    @FindBy(id="dv_inbox_trFullSub")
    WebElement subjectImpWebElement;

    @FindBy(id="dv_inbox_trRecOwner")
    WebElement recipientOwnerWebElement;


    @FindBy(id = "dv_inbox_trSource")
    WebElement senderImpWebElement;

    @FindBy(id = "dv_inbox_trType")
    WebElement docTypeImpWebElement;

    @FindBy(id = "dv_inbox_trIncNum")
    WebElement importNumberWebElement;
    public List<String> getMailData(){
        //exWait.until(ExpectedConditions.visibilityOf(subjectImpWebElement));
        List<String> mailData = new ArrayList<>();
        mailData.add(subjectImpWebElement.getText());
        mailData.add(recipientOwnerWebElement.getText());
        mailData.add(senderImpWebElement.getText());
        mailData.add(docTypeImpWebElement.getText());
        mailData.add(importNumberWebElement.getText());
        return mailData;
    }
}
