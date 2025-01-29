package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Base.ContentAside;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private By showAllBy = By.id("cph_main_rd_inbox_all");
    public void getRecentlyAddedMail(String mailSub){
        driver.findElement(showAllBy).click();
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

    private By internalMemoTabBy = By.id("tab_inbox_uldiv_Tab_Hrf_0");
    private By coverLetterTabBy = By.id("tab_inbox_uldiv_Tab_Hrf_6");
    public void goToEtTabs(String tabName){
        switch (tabName){
            case "مذكرة داخلية":{
                    driver.findElement(internalMemoTabBy).click();
                break;
            } case "خطابات التغطية":{
                driver.findElement(coverLetterTabBy).click();
                break;
            }
        }
    }

    private By ddlFromDeptBy = By.id("ddlFromDeptIntLet_ddlSelectButton");
    private By intenalMemoBodyBy = By.id("dv_interMemo_body");
    private By textBodyBy = By.id("tinymce");
    private By saveBodyBy = By.cssSelector("p[onclick='editorPopUp.save();']");
    private By directToBy = By.id("txt_interMemo_rcvrId");
    private By saveInternalMemoBtnBy = By.id("btn_IntrMemo_save");
    private By alterPopupMsgBy = By.cssSelector("div[id='dv_alertPopup'] div div[class='popup_content']");
    private By closeAlterMsgBy = By.id("divMasterAlert_close-lnk2");
    private By subjectTitleBy = By.id("txt_interMemo_title");
    private By decisionCkBoxBy = By.id("chk_interMemo_title");
    private By saveWithOfficialPaperCkBox = By.id("previewWithOfficalPaper_Memo");
    public String createDecisionForInternalMemo(String userDeptLocal,String redirectNumLocal,String dataLocal) throws InterruptedException {
        try {
            driver.findElement(ddlFromDeptBy).click();
            driver.findElement(By.xpath("//li[@data-target='ddlFromDeptIntLet_collapsibleDiv']/div/label[text()='"+userDeptLocal+"']/..")).click();
            driver.findElement(ddlFromDeptBy).click();
        }catch (Exception e) {
        }
        driver.findElement(decisionCkBoxBy).click();
        driver.findElement(intenalMemoBodyBy).click();
        driver.switchTo().frame("ifr_editorPopUp_content");
        driver.switchTo().frame("letter_body_ifr");
//        driver.findElement(textBodyBy).click();
        driver.findElement(textBodyBy).sendKeys(dataLocal);
        driver.switchTo().defaultContent();
        driver.findElement(directToBy).sendKeys(redirectNumLocal);
        driver.findElement(saveBodyBy).click();
        driver.findElement(saveWithOfficialPaperCkBox).click();
        driver.findElement(saveInternalMemoBtnBy).click();
        String text = driver.findElement(alterPopupMsgBy).getAttribute("value");
        driver.findElement(closeAlterMsgBy).click();
        driver.findElement(saveInternalMemoBtnBy).click();
        driver.findElement(closeAlterMsgBy).click();
        return text;
    }
    public String getDecisionNum(){
        String subject = driver.findElement(subjectTitleBy).getAttribute("value");
        System.out.println(subject);
        String[] parts = subject.split("\\(\\s*|\\s*\\)");
        String number = parts[1].trim();
        System.out.println("Extracted Number: " + number);
        return number;
    }

}
