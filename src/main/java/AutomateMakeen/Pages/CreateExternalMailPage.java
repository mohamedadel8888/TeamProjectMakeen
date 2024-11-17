package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class CreateExternalMailPage extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;
    public CreateExternalMailPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver , Duration.ofSeconds(10));
        contentAside = new ContentAside(driver);
    }


    /******************************************
     * نوع المستند
     * *****************************************/

    @FindBy(id ="txt_doc_type_num")
    WebElement docTypeWebElement;

    @FindBy(id = "spnA_txt_doc_type_num")
    WebElement docTypeValidator;

    @FindBy(css = "#spnA_txt_doc_type_num p[class='span_error'] span")
    WebElement errorMsgWebElement;

    @FindBy(css = "span[onclick='extInboxCreation.showPickupDocType()']")
    WebElement openDocTypeControl;

    @FindBy(id = "txt_doc_type_name")
    WebElement typeNameWebElement;
    /*
     * Documnet Type Methods:
     *  1- insertDocType
     *       - Return : Void
     *       - Param : String docType
     *       - Description : Insert String into 'نوع المستند ' Feild.
     */

    public void insertDocTypeNum(String docType){
        docTypeWebElement.sendKeys(docType);
    }

    /*  2- getDocType
     *       - Return : String
     *       - Param : Non
     *       - Description : Return the existing data in 'نوع المستند ' Feild
     *                       using getAttribute("value").
     */
    public String getDocTypeNum(){
        return docTypeWebElement.getAttribute("value");
    }
    /*  3- clearDocType
     *       - Return : void
     *       - Param : Non
     *       - Description : Clear the existing data in 'نوع المستند ' Feild.
     */
    public void clearDocTypeNum(){
        docTypeWebElement.clear();
    }
    /*  4- copyPasteToDocType
     *       - Return : void
     *       - Param : String docType
     *       - Description : this method send data into "الشرح" then press (Ctrl + a + x)
     *                      to Cut data then go to "نوع المستند" and press (Crtl + v).
     */
    public void copyPasteToDocTypeNum(String docType){
        cutPastAction(commentWebElement,docTypeWebElement,docType);
    }
    /*  5- getDocTypeValidatorState
     *       - return : 1- "Red Circle"
                        2- "Asterisk"
     *       - Param : Non
     *       - Description : this method return the status of "نوع المستند" field.
     */
    public String getDocTypeValidatorState(){
        return getValidatorState(docTypeValidator);
    }
    /*  6- getDocTypeErrorMsg
     *       - return : String
     *       - Param : Non
     *       - Description : this method return the error Msg by click
     *                       on the validator then return the error msg.
     */
    public String getDocTypeErrorMsg(){
        docTypeValidator.click();
        return errorMsgWebElement.getText();
    }

    public void setDocTypeUsingControl(String docCode){
        control(openDocTypeControl,docCode);
    }

    public void setDocTypeNum(String docCode){
        docTypeWebElement.sendKeys(docCode);
    }

    public String getTypeName(){
        return typeNameWebElement.getAttribute("value");
    }

    /******************************************
     *     المرسل اليه
     * *****************************************/

    @FindBy(id ="txt_receiver_num")
    WebElement receiverNumWebElement;

    @FindBy(id = "spnA_txt_receiver_num")
    WebElement receiverValidator;

    @FindBy(css = "b[id='spnA_txt_receiver_num'] span")
    WebElement receiverErrorMsgWebElement;

    @FindBy(xpath = "//span[@onclick=\"pickPopUp.initialize('recivers','txt_receiver_num');\"]")
    WebElement openReceiverControl;

    @FindBy(id = "txt_receiver_name")
    WebElement receiverNameWebElement;

    public void insertReceiverNum(String receiverName){
        receiverNumWebElement.sendKeys(receiverName);
    }

    public String getReceiverNum(){
        return receiverNumWebElement.getAttribute("value");
    }

    public void clearReceiverNum(){
        receiverNumWebElement.clear();
    }

    public void copyPasteToReceiverNum(String receiverNumb){
        cutPastAction(commentWebElement,receiverNumWebElement,receiverNumb);
    }

    public String getReceiverValidatorState(){
        return getValidatorState(receiverValidator);
    }

    public String getReceiverErrorMsg(){
        receiverValidator.click();
        return receiverErrorMsgWebElement.getText();
    }

    public void setReceiverUsingControl(String receiverNum){
        exWait.until(ExpectedConditions.visibilityOf(openReceiverControl));
        control(openReceiverControl,receiverNum);
    }

    public String getReceiverName(){
        return receiverNameWebElement.getAttribute("value");
    }
    /******************************************
     * المرسل
     * *****************************************/

    @FindBy(id ="txt_sender_num")
    WebElement senderNumWebElement;

    @FindBy(id = "spnA_txt_sender_num")
    WebElement senderValidator;

    @FindBy(css = "b[id='spnA_txt_sender_num'] span")
    WebElement senderErrorMsgWebElement;

    @FindBy(xpath = "//span[@onclick=\"pickPopUp.initialize('senders','txt_sender_num');\"]")
    WebElement openSenderControl;

    @FindBy(id = "txt_sender_name")
    WebElement senderNameWebElement;

    public void insertSenderNum(String senderName){
        senderNumWebElement.sendKeys(senderName);
    }

    public String getSenderNum(){
        return senderNumWebElement.getAttribute("value");
    }

    public void clearSenderNum(){
        senderNumWebElement.clear();
    }

    public void copyPasteToSenderNum(String senderNumb){
        cutPastAction(commentWebElement,senderNumWebElement,senderNumb);
    }

    public String getSenderValidatorState(){
        return getValidatorState(senderValidator);
    }

    public String getSenderErrorMsg(){
        senderValidator.click();
        return senderErrorMsgWebElement.getText();
    }

    public void setSenderUsingControl(String senderNum){
        control(openSenderControl,senderNum);
    }

    public String getSenderName(){
        return senderNameWebElement.getAttribute("value");
    }
    /******************************************
     *    تصنيف المعاملة
     * *****************************************/

    @FindBy(id ="txt_treat_classification_num")
    WebElement treatClassificationNumWebElement;

    @FindBy(id = "spnA_txt_treat_classification_num")
    WebElement treatClassificationValidator;

    @FindBy(css = "b[id='spnA_txt_treat_classification_num'] span")
    WebElement treatClassificationErrorMsgWebElement;

    @FindBy(xpath = "//span[@onclick='extInboxCreation.initalizeClassificationPopUp()']")
    WebElement openTreatClassificationControl;

    @FindBy(id = "txt_treat_classification_name")
    WebElement treatClassificationNameWebElement;

    public void insertTreatClassificationNum(String treat_classificationName){
        treatClassificationNumWebElement.sendKeys(treat_classificationName);
    }

    public String getTreatClassificationNum(){
        return treatClassificationNumWebElement.getAttribute("value");
    }

    public void clearTreatClassificationNum(){
        treatClassificationNumWebElement.clear();
    }

    public void copyPasteToTreatClassificationNum(String treat_classificationNumb){
        cutPastAction(commentWebElement,treatClassificationNumWebElement,treat_classificationNumb);
    }

    public String getTreatClassificationValidatorState(){
        return getValidatorState(treatClassificationValidator);
    }

    public String getTreatClassificationErrorMsg(){
        treatClassificationValidator.click();
        return treatClassificationErrorMsgWebElement.getText();
    }

    public void setTreatClassificationUsingControl(String treat_classificationNum){
        control(openTreatClassificationControl,treat_classificationNum);
    }

    public String getTreatClassificationName(){
        return treatClassificationNameWebElement.getAttribute("value");
    }
    @FindBy(css = "span[onclick='extInboxCreation.initalizeClassificationPopUp()']")
    WebElement openControlClassification;
    @FindBy(id = "itemid_ddlSelectButtonTarget")
    WebElement controlDDLWebElement;

    @FindBy(css = "div[class='col-bx-5'] div[class=' '] input[type='text']")
    WebElement controlSearchWebElement;

    @FindBy(id = "itemid_txtSearch")
    WebElement ddlSearchWebElement;

    @FindBy(css = "div[class='content_bx'] li")
    List<WebElement> listOfDdlItems;

    @FindBy(css = "input[value='بحث']")
    WebElement searchWebElement;
    public String setTreatClassificationUsingControl(String mainClass , String subClass ){
        openTreatClassificationControl.click();
//        controlDDLWebElement.click();
//        ddlSearchWebElement.sendKeys(mainClass);
//        WebElement item = listOfDdlItems.stream().filter(s->s.getText().equals(mainClass)).findFirst().orElse(null);
//        item.click();
        controlSearchWebElement.sendKeys(subClass);
        searchWebElement.click();
        String activePeriod = driver.findElement(By.xpath("//div[contains(text(),'"+subClass+"')]/../../td[5]")).getText();
        driver.findElement(By.xpath("//div[contains(text(),'"+subClass+"')]/../../td[1]")).click();
        return activePeriod;        //return active period
    }
    /******************************************
     *    الاحالة
     ******************************************/
    @FindBy(id = "ddl_tr_recipient_ddlSelectButtonTarget")
    WebElement recipientWebElement;

    @FindBy(id = "ddl_tr_recipient_txtSearch")
    WebElement recipientSearchWebElement;

    @FindBy(css = "div[id='div_maken_content'] li")
    List<WebElement> recipientListWebElement;

    @FindBy(id = "ddl_tr_recipient_collapsibleDiv")
    WebElement recipientDivWebElement;

    @FindBy(css = "ul[id='ddl_tr_recipient_collapsibleDiv'] li[value='-1']")
    WebElement defaultChooseInRecipientList;

    @FindBy(id = "spnA_ddl_tr_recipient")
    WebElement recipientValidatorWebElement;

    @FindBy(css = "b[id='spnA_ddl_tr_recipient'] span")
    WebElement recipientErrorMsgWebElement;

    public void insertRecipient(String recipientName){
        recipientWebElement.click();
        recipientSearchWebElement.sendKeys(recipientName);
        WebElement recipient = recipientListWebElement.stream().filter(s->s.getText().equals(recipientName)).findFirst().orElse(null);
        recipient.click();
    }
    public void searchInRecipient(String recipientName){
        recipientWebElement.click();
        recipientSearchWebElement.sendKeys(recipientName);
    }
    public void clearRecipient(){
        recipientWebElement.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = 0;", recipientDivWebElement);
        defaultChooseInRecipientList.click();
    }

    public void getTopOfThePage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public String getRecipientValidatorState(){
        return getValidatorState(recipientValidatorWebElement);
    }

    public String getRecipientErrorMsg(){
        recipientValidatorWebElement.click();
        return recipientErrorMsgWebElement.getText();
    }
    @FindBy(id = "txt_tr_comment")
    WebElement commentWebElement;
    public void insertComment(String comment){
        commentWebElement.sendKeys(comment);
    }


    /**************************************************
     * dateOfCreatingMail Methods.
     ***************************************************/
    @FindBy (id = "cph_main_txt_tr_date")
    WebElement dateOfCreatingMail;                  /*تاريخ انشاء البريد و يأخذ تاريخ اليوم تلقائيا ولا يمكن تغيره */

    public String getTheValueOfCreatingDate() {
        return dateOfCreatingMail.getAttribute("value");
    }




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

    public void clearTheValueOfSubjectOfTheMail() {
        subjectOfTextBox.clear();
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
    @FindBy (id = "txt_tr_ref_letter")
    WebElement referredEmail;                      /*الخطاب المشار اليه */

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


    /**************************************************
     *Error Message Methods.
     ***************************************************/
    @FindBy(id = "spnA_txt_tr_subject")
    WebElement subjectValidator;

    @FindBy(css = "#spnA_txt_tr_subject p[class='span_error'] span")
    WebElement subjectErrorMsg;

    public String getSubjectValidatorState(){
        return getValidatorState(subjectValidator);
    }

    public String getSubjectErrorMsg(){
        subjectValidator.click();
        return subjectErrorMsg.getText();
    }

    @FindBy(id = "btn_EtEntrySend")
    WebElement sendBtnWebElement;

    @FindBy(css = "input[value='موافق']")
    WebElement confirmWebElement;

    @FindBy(id="divMasterAlert_close-lnk2")
    WebElement closeDivSave;

    @FindBy(css="input[value='غير موافق ']")
    WebElement declineBtnWebElement;
    public void clickSendConfirm(){
        sendBtnWebElement.click();
        confirmWebElement.click();
    }
    public void clickDeclineSend(){
        declineBtnWebElement.click();
    }
    public void clickSend(){
        sendBtnWebElement.click();
    }
    /*********************************************
     *                 Successful Adding
     *********************************************/
    @FindBy(css = ".txt_msg")
    WebElement successfulMsgWebElement;

    public boolean validateSuccessfulCreatingMail(){
        exWait.until(ExpectedConditions.visibilityOf(successfulMsgWebElement));
        boolean flag = successfulMsgWebElement.getText().equals("تم إرسال بريد خارجي بنجاح");
        closeDivSave.click();
        return flag;
    }
    public void clearAllField(){
        clearTheValueOfSubjectOfTheMail();
        clearDocTypeNum();
        clearReceiverNum();
        clearSenderNum();
        clearRecipient();
        clearTreatClassificationNum();
    }

}



