package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(css = "#cph_main_rd_inbox_all")  /*الجميع */
    WebElement radioBtnAll;
    @FindBy (css = "#tab_container_search_uldiv_Tab_Hrf_4") /*تاب الارشيف في البحث*/
    WebElement archiveSearchTab;

    @FindBy (id = "txt_srch_rec_no") /*البحث برقم الارشيف*/
    WebElement searchTextArch;

    @FindBy (id = "btn_search") /*زر بحث*/
    WebElement btnSearch;

    @FindBy (id = "tab_inbox_uldiv_Tab_Hrf_6") /*تاب خطاب التغطيه*/
    WebElement lettersTab;

/**======================================================================================**/
    @FindBy (id = "drp_covrLet_letTypes_ddlSelectButtonTarget") /*قائمة صفه الخطاب */
    WebElement coverLetterTypeDDl;

    public void selectCoverLetterType (String text){   /*تحديد صفه الخطاب*/
        coverLetterTypeDDl.click();
        driver.findElement(By.xpath("//label[normalize-space()='"+text+"']")).click();
    }
/**======================================================================================**/
    @FindBy (id = "txt_covrLet_treatSubject")  /*الموضوع*/
    WebElement letterSubject;

    public void enterSubject (String text){
        letterSubject.sendKeys(text);
    }
    @FindBy(id = "ddl_letterSecretLevels")  /*درجه السرية*/
    Select securityDegree ;

    public void selectSecretDegree (String text){
        securityDegree.selectByVisibleText(text);
    }

    

/**======================================================================================**/
    @FindBy (xpath = "//span[@onclick=\"pickPopUp.initialize('covLetRecivers','txt_covrLet_rcvrId');\"]")   /* ايقونه البحث الموجه اليه*/
    WebElement searchIcon;
    @FindBy(id = "dv_pickPopupCntrl")  /*قائمة الموجه اليه*/
    WebElement pickUpDiv;
    @FindBy (id = "txt_pickPopUp_srchParam") /*حقل البحث عن اسم الموجه اليه*/
    WebElement searchTextInPopUp;
    @FindBy (id = "btn_pickPopUp_srch") /*زر البحث في نافذه الموجه اليه*/
    WebElement btnSearchPopUp;
    @FindBy (xpath = "(//input[@type='checkbox'])[1]")  /*تحديد الموجه اليه*/
    WebElement selectElement;
/**======================================================================================**/
    @FindBy (id = "dv_covrLet_body") /*فتح كنترول النص */
    WebElement mainTextControl;
    @FindBy (xpath = "//div[@class='tox-editor-header']") /*محرر النص*/
    WebElement textEditor;
    @FindBy (xpath = "//body//p")  /*حقل النص في الخطاب*/
    WebElement letterBody;

    @FindBy (css = "p[onclick='editorPopUp.save();']") /*عوده مع الاحتفاظ بالتعديلات */
    WebElement saveWithModified;
/**======================================================================================**/
    @FindBy (css = "input[value='توقيع الخطاب']") /*توقيع الخطاب*/
    WebElement signLetter;

    @FindBy (css = "div[class='dv_Sign_cntrlMain'] div[class='popup_genrl']")/*ديف توقيع الخطاب*/
    WebElement divSignLetter;

    @FindBy (xpath = "(//li)[485]") /*تأكيد*/
    WebElement confirm;
    @FindBy (id = "btnOk") /*موافق*/
    WebElement btnOk;








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
