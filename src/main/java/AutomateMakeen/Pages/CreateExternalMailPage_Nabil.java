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


    @FindBy (id = "cph_main_txt_tr_date")
    WebElement dateOfCreatingMail;                   /*تاريخ انشاء البريد و يأخذ تاريخ اليوم تلقائيا ولا يمكن تغيره */


    @FindBy (id = "txt_tr_ref_letter")
    WebElement referredEmail;                      /*الخطاب المشار اليه */



    /**************************************************
     *Subject Name Methods.
     ***************************************************/

    @FindBy(id = "txt_tr_subject")
    WebElement subjectOfTextBox;                    /*حقل ادخال موضوع البريد*/

    /*  1- enteringTheSubjectOfMail
     *       - Return : Non
     *       - Param : Non
     *       - Description : Insert String into 'الموضوع' Field.
     */
    public void enteringTheSubjectOfMail(String mailSub) {
        subjectOfTextBox.sendKeys(mailSub);
    }

    /*  2- enteringTheSubjectOfMail
     *       - Return : String
     *       - Param : Non
     *       - Description : Return the existing data in 'موضوع ' Field
     *                       using getAttribute("value").
     */
    public String getTheValueOfSubjectOfTheMail() {
        return subjectOfTextBox.getAttribute("value");
    }



    /**************************************************
     *Number Of Document(mail) Methods.
     ***************************************************/
    @FindBy (id = "txt_tr_letter_num")
    WebElement numberOfEmail;                      /*رقم الخطاب */

    public void enteringTheNumberOfMail(String numberOfMail) {
        numberOfEmail.sendKeys(numberOfMail);
    }

    public String getTheValueOfNumberOfMail() {
        return numberOfEmail.getAttribute("value");
    }



    /**************************************************
     *Date Of Document(mail) Methods.
     ***************************************************/
    @FindBy (id = "txt_tr_letter_date")
    WebElement dateOfEmail;                        /*تاريخ الخطاب*/

    public void enteringTheDateOfMail(String DateOfMail) {
        dateOfEmail.sendKeys(DateOfMail);
    }




    /**************************************************
     *Number Of Referral Document(mail) Methods.
     ***************************************************/
    public void enteringTheReferralMailNumber(String ReferralMailNumber) {
        referredEmail.sendKeys(ReferralMailNumber);
    }

    public String getTheValueOfReferralNumberOfTheMail() {
        return referredEmail.getAttribute("value");
    }


    /**************************************************
     *Click On CheckBox Methods.
     ***************************************************/
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
    WebElement generalSendingCheckBox;              /*مربع اختيار تعميم المعاملة*/

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

    public void pressOnGeneralSending() {
        generalSendingCheckBox.click();
    }


    public String getTheValueOfCreatingDate() {
        return dateOfCreatingMail.getAttribute("value");
    }
}
