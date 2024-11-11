package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateExternalMailPage_Nabil extends BaseComp {
    protected WebDriver driver;
    private WebDriverWait exWait;
    public CreateExternalMailPage_Nabil(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }

    @FindBy(id = "txt_tr_subject")
    WebElement subjectOfTextBox;                    /*حقل ادخال موضوع البريد*/

    @FindBy (id = "cph_main_txt_tr_date")
    WebElement dateOfCreatingMail;                   /*تاريخ انشاء البريد و يأخذ تاريخ اليوم تلقائيا ولا يمكن تغيره */

    @FindBy (id = "txt_tr_letter_num")
    WebElement numberOfEmail;                      /*رقم الخطاب */

    @FindBy (id = "txt_tr_letter_date")
    WebElement dateOfEmail;                        /*تاريخ الخطاب*/

    @FindBy (id = "txt_tr_ref_letter")
    WebElement referredEmail;                      /*الخطاب المشار اليه */

    @FindBy (id = "cph_main_chkLetter")
    WebElement referredEmailCheckBox;              /*مربع اختيار حقل الخطاب المشار اليه*/

    @FindBy (id = "cph_main_chk_storage_num")
    WebElement storageNumberCheckBox;               /*مربع اختيار اختبار رقم الخزينه*/

    @FindBy (xpath = "//input[@id='cph_main_chk_urgent_tr']")
    WebElement urgentTransactionCheckBox;           /*مربع اختيار معاملة عاجلة*/

    @FindBy (id = "cph_main_chk_secret_tr")
    WebElement secretEmailCheckBox;                 /*مربع اختيار بريد سري*/

    @FindBy (id = "cph_main_chk_LinkDealToCitizen")
    WebElement linkTransactionCheckBox;             /*مربع اختيار ربط المعاملة*/

    @FindBy (id = "cph_main_chk_sendT3mem")
    WebElement generalSendingCheckBox;



    public void enteringTheSubjectOfMail() {
        subjectOfTextBox.sendKeys();
    }

    public void enteringTheNumberOfMail() {
        numberOfEmail.sendKeys();
    }

    public void enteringTheDateOfMail() {
        dateOfEmail.sendKeys();
    }

    public void enteringTheReferralMailNumber() {
        referredEmail.sendKeys();
    }

    public void pressOnDeactivateReferralNumber() {
        referredEmailCheckBox.click();
    }

    public void pressOnNumberOfStorage() {
        storageNumberCheckBox.click();
    }

    public void pressOnUrgentTransaction() {
        urgentTransactionCheckBox.click();
    }

    public void pressOnSecretEmail() {
        secretEmailCheckBox.click();
    }

    public void pressOnReferralMailNumber() {
        linkTransactionCheckBox.click();
    }


    public void getTheValueOfCreatingDate() {
        String date = dateOfCreatingMail.getAttribute("value");
        System.out.println(date);
    }

    public void getTheValueOfSubjectOfTheMail() {
        String subject = subjectOfTextBox.getAttribute("value");
        System.out.println(subject);
    }

    public void getTheValueOfNumberOfMail() {
        String numberMail = numberOfEmail.getAttribute("value");
        System.out.println(numberMail);
    }

    public void getTheValueOfTheDateOfTheMail() {
        String MailDate = dateOfEmail.getAttribute("value");
        System.out.println(MailDate);
    }

    public void getTheValueOfReferralNumberOfTheMail() {
        String numberReferralMail = referredEmail.getAttribute("value");
        System.out.println(numberReferralMail);
    }

}
