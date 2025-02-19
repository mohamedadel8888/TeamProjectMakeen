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

public class OutboxMails extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public OutboxMails(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }
//    @FindBy(xpath = "(//td[10]/div[text()='انشاء بريد خارجي'])[1]")
//    WebElement firstRowInTable;

    public void getRecentlyAddedMail(String mailSub){
        driver.findElement(By.xpath("(//td[10]/div[text()='"+mailSub+"'])[1]"))
                .click();
    }
    @FindBy(id="dv_inbox_trFullSub")
    WebElement subjectWebElement;

    @FindBy(id="dv_inbox_trSend")
    WebElement recipientWebElement;


    @FindBy(id = "dv_inbox_trSource")
    WebElement senderWebElement;

    @FindBy(id = "dv_inbox_trType")
    WebElement docTypeWebElement;

    @FindBy(id = "dv_inbox_trIncNum")
    WebElement importNumberWebElement;
    public List<String> getMailData(){
        exWait.until(ExpectedConditions.visibilityOf(subjectWebElement));
        List<String> mailData = new ArrayList<>();
        mailData.add(subjectWebElement.getText());
        mailData.add(recipientWebElement.getText());
        mailData.add(senderWebElement.getText());
        mailData.add(docTypeWebElement.getText());
        mailData.add(importNumberWebElement.getText());
        return mailData;
    }


}
