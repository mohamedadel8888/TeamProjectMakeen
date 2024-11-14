package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExportedMails extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    public ExportedMails(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }
    @FindBy(xpath = "//table[@id='tbl_outbox']/tbody/tr[1]/td[10]")
    WebElement firstRowInTable;
    public void getRecentlyAddedMail(){
        firstRowInTable.click();
    }
    @FindBy(id="dv_inbox_trFullSub")
    WebElement subjectWebElement;

    @FindBy(id="dv_inbox_trSend")
    WebElement receiverWebElement;

    @FindBy(id ="dv_inbox_trPurp")
    WebElement recipientWebElement;

    @FindBy(id = "dv_inbox_trSource")
    WebElement senderWebElement;

    @FindBy(id = "dv_inbox_trType")
    WebElement docTypeWebElement;
/*
    public List<String> getMailData(){
        List<String> mailData = new ArrayList<>();
//        mailData
    }*/
}
