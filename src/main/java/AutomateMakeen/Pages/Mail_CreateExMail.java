package AutomateMakeen.Pages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.Base.ContentAside;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Mail_CreateExMail extends BaseComp {
    private WebDriver driver;
    private WebDriverWait exWait;

    // Constructor to initialize WebDriver and WebDriverWait
    public Mail_CreateExMail(WebDriver driver){
        super(driver);
        this.driver = driver;
        exWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        contentAside = new ContentAside(driver); // Initialize a component or subsection of the page
    }

    /******************************************
     * نوع المستند (Document Type)
     ******************************************/

    @FindBy(id ="txt_doc_type_num")
    WebElement docTypeWebElement;               // WebElement for Document Type input field

    @FindBy(id = "spnA_txt_doc_type_num")
    WebElement docTypeValidator;                // WebElement for Document Type validation icon

    @FindBy(css = "#spnA_txt_doc_type_num p[class='span_error'] span")
    WebElement errorMsgWebElement;              // WebElement for error message display

    @FindBy(css = "span[onclick='extInboxCreation.showPickupDocType()']")
    WebElement openDocTypeControl;              // WebElement to open Document Type control

    @FindBy(id = "txt_doc_type_name")
    WebElement typeNameWebElement;              // WebElement for Document Type name display

    // Method to insert Document Type number
    public void insertDocTypeNum(String docType){
        docTypeWebElement.sendKeys(docType);
    }

    // Method to get Document Type number from input field
    public String getDocTypeNum(){
        return docTypeWebElement.getAttribute("value");
    }

    // Method to clear Document Type number input field
    public void clearDocTypeNum(){
        docTypeWebElement.clear();
    }

    // Method to copy text to Document Type number field using cut-paste
    public void copyPasteToDocTypeNum(String docType){
        cutPastAction(commentWebElement, docTypeWebElement, docType);
    }

    // Method to get validation state of Document Type field
    public String getDocTypeValidatorState(){
        return getValidatorState(docTypeValidator);
    }

    // Method to get error message for Document Type field
    public String getDocTypeErrorMsg(){
        docTypeValidator.click();
        return errorMsgWebElement.getText();
    }

    // Unused method to set Document Type using control
    public void setDocTypeUsingControl(String docCode) {
        control(openDocTypeControl, docCode);
    }

    // Unused method to set Document Type number
    public void setDocTypeNum(String docCode) {
        docTypeWebElement.sendKeys(docCode);
    }

    // Unused method to get Document Type name
    public String getTypeName() {
        return typeNameWebElement.getAttribute("value");
    }

    /******************************************
     * المرسل اليه (Receiver)
     ******************************************/

    @FindBy(id ="txt_receiver_num")
    WebElement receiverNumWebElement;           // WebElement for Receiver number input field

    @FindBy(id = "spnA_txt_receiver_num")
    WebElement receiverValidator;               // WebElement for Receiver validation icon

    @FindBy(css = "b[id='spnA_txt_receiver_num'] span")
    WebElement receiverErrorMsgWebElement;      // WebElement for error message display for Receiver number field

    @FindBy(xpath = "//span[@onclick=\"pickPopUp.initialize('recivers','txt_receiver_num');\"]")
    WebElement openReceiverControl;             // WebElement to open Receiver control popup

    @FindBy(id = "txt_receiver_name")
    WebElement receiverNameWebElement;          // WebElement for Receiver name display

    // Method to insert Receiver number
    public void setReceiverNum(String receiverName){
        receiverNumWebElement.sendKeys(receiverName);
    }

    // Method to get Receiver number from input field
    public String getReceiverNum(){
        return receiverNumWebElement.getAttribute("value");
    }

    // Method to clear Receiver number input field
    public void clearReceiverNum(){
        receiverNumWebElement.clear();
    }

    // Method to copy text to Receiver number field using cut-paste
    public void copyPasteToReceiverNum(String receiverNumb){
        cutPastAction(commentWebElement, receiverNumWebElement, receiverNumb);
    }

    // Method to get validation state of Receiver field
    public String getReceiverValidatorState(){
        return getValidatorState(receiverValidator);
    }

    // Method to get error message for Receiver field
    public String getReceiverErrorMsg(){
        receiverValidator.click();
        return receiverErrorMsgWebElement.getText();
    }

    // Unused method to set Receiver using control
    public void setReceiverUsingControl(String receiverNum){
        exWait.until(ExpectedConditions.visibilityOf(openReceiverControl));
        control(openReceiverControl, receiverNum);
    }

    // Unused method to get Receiver name
    public String getReceiverName(){
        return receiverNameWebElement.getAttribute("value");
    }

    /******************************************
     * المرسل (Sender)
     ******************************************/

    @FindBy(id ="txt_sender_num")
    WebElement senderNumWebElement;             // WebElement for Sender number input field

    @FindBy(id = "spnA_txt_sender_num")
    WebElement senderValidator;                 // WebElement for Sender validation icon

    @FindBy(css = "b[id='spnA_txt_sender_num'] span")
    WebElement senderErrorMsgWebElement;        // WebElement for error message display for Sender number field

    @FindBy(xpath = "//span[@onclick=\"pickPopUp.initialize('senders','txt_sender_num');\"]")
    WebElement openSenderControl;               // WebElement to open Sender control popup

    @FindBy(id = "txt_sender_name")
    WebElement senderNameWebElement;            // WebElement for Sender name display

    // Method to insert Sender number
    public void insertSenderNum(String senderName){
        senderNumWebElement.sendKeys(senderName);
    }

    // Method to clear Sender number input field
    public void clearSenderNum(){
        senderNumWebElement.clear();
    }

    // Method to copy text to Sender number field using cut-paste
    public void copyPasteToSenderNum(String senderNumb){
        cutPastAction(commentWebElement, senderNumWebElement, senderNumb);
    }

    // Method to get validation state of Sender field
    public String getSenderValidatorState(){
        return getValidatorState(senderValidator);
    }

    // Method to get error message for Sender field
    public String getSenderErrorMsg(){
        senderValidator.click();
        return senderErrorMsgWebElement.getText();
    }

    // Unused method to set Sender using control
    public void setSenderUsingControl(String senderNum){
        control(openSenderControl, senderNum);
    }

    // Unused method to get Sender name
    public String getSenderName(){
        return senderNameWebElement.getAttribute("value");
    }

    /******************************************
     * تصنيف المعاملة (Transaction Classification)
     ******************************************/

    @FindBy(id ="txt_treat_classification_num")
    WebElement treatClassificationNumWebElement;// WebElement for Transaction Classification input field

    @FindBy(id = "spnA_txt_treat_classification_num")
    WebElement treatClassificationValidator;    // WebElement for Transaction Classification validation icon

    @FindBy(css = "b[id='spnA_txt_treat_classification_num'] span")
    WebElement treatClassificationErrorMsgWebElement; // WebElement for error message display for Transaction Classification field

    @FindBy(xpath = "//span[@onclick='extInboxCreation.initalizeClassificationPopUp()']")
    WebElement openTreatClassificationControl;  // WebElement to open Transaction Classification control

    @FindBy(id = "txt_treat_classification_name")
    WebElement treatClassificationNameWebElement; // WebElement for Transaction Classification name display

    // Method to insert Transaction Classification number
    public void setTreatClassificationNum(String treat_classificationName){
        treatClassificationNumWebElement.sendKeys(treat_classificationName);
    }

    // Method to get Transaction Classification number from input field
    public String getTreatClassificationNum(){
        return treatClassificationNumWebElement.getAttribute("value");
    }

    // Method to clear Transaction Classification number input field
    public void clearTreatClassificationNum(){
        treatClassificationNumWebElement.clear();
    }

    // Method to copy text to Transaction Classification number field using cut-paste
    public void copyPasteToTreatClassificationNum(String treat_classificationNumb){
        cutPastAction(commentWebElement, treatClassificationNumWebElement, treat_classificationNumb);
    }

    // Method to get validation state of Transaction Classification field
    public String getTreatClassificationValidatorState(){
        return getValidatorState(treatClassificationValidator);
    }

    // Method to get error message for Transaction Classification field
    public String getTreatClassificationErrorMsg(){
        treatClassificationValidator.click();
        return treatClassificationErrorMsgWebElement.getText();
    }

    // Unused method to set Transaction Classification using control
    public void setTreatClassificationUsingControl(String treat_classificationNum){
        control(openTreatClassificationControl, treat_classificationNum);
    }

    // Unused method to get Transaction Classification name
    public String getTreatClassificationName(){
        return treatClassificationNameWebElement.getAttribute("value");
    }

    @FindBy(css = "span[onclick='extInboxCreation.initalizeClassificationPopUp()']")
    WebElement openControlClassification;        // WebElement to open control for Classification

    @FindBy(id = "itemid_ddlSelectButtonTarget")
    WebElement controlDDLWebElement;             // Dropdown element for Classification

    @FindBy(css = "div[class='col-bx-5'] div[class=' '] input[type='text']")
    WebElement controlSearchWebElement;          // Search input field within control

    @FindBy(id = "itemid_txtSearch")
    WebElement ddlSearchWebElement;              // Input field for dropdown search

    @FindBy(css = "div[class='content_bx'] li")
    List<WebElement> listOfDdlItems;             // List of items in dropdown

    @FindBy(css = "input[value='بحث']")
    WebElement searchWebElement;                 // Search button element

    // Method to set Transaction Classification using control
    public String setTreatClassificationUsingControl(String mainClass, String subClass){
        openTreatClassificationControl.click();
        controlSearchWebElement.sendKeys(subClass);
        searchWebElement.click();
        String activePeriod = driver.findElement(By.xpath("//div[contains(text(),'" + subClass + "')]/../../td[5]")).getText();
        driver.findElement(By.xpath("//div[contains(text(),'" + subClass + "')]/../../td[1]")).click();
        return activePeriod; // Return active period
    }

    /******************************************
     * الاحالة (Referral)
     ******************************************/

    @FindBy(id = "ddl_tr_recipient_ddlSelectButtonTarget")
    WebElement recipientWebElement;              // Element for recipient dropdown

    @FindBy(id = "ddl_tr_recipient_txtSearch")
    WebElement recipientSearchWebElement;        // Search input field within recipient dropdown

    @FindBy(css = "div[id='div_maken_content'] li")
    List<WebElement> recipientListWebElement;    // List of recipients in dropdown

    @FindBy(id = "ddl_tr_recipient_collapsibleDiv")
    WebElement recipientDivWebElement;           // Collapsible div for recipient list

    @FindBy(css = "ul[id='ddl_tr_recipient_collapsibleDiv'] li[value='-1']")
    WebElement defaultChooseInRecipientList;     // Default option in recipient dropdown

    @FindBy(id = "spnA_ddl_tr_recipient")
    WebElement recipientValidatorWebElement;     // Validator for recipient field

    @FindBy(css = "b[id='spnA_ddl_tr_recipient'] span")
    WebElement recipientErrorMsgWebElement;      // Error message element for recipient field

    // Method to insert a recipient by name
    public void insertRecipient(String recipientName) throws InterruptedException {
        recipientWebElement.click();
        recipientSearchWebElement.sendKeys(recipientName);
        WebElement recipient = recipientListWebElement.stream().filter(s -> s.getText().contains(recipientName)).findFirst().orElse(null);
        if (recipient != null) recipient.click();
    }

    // Method to search for a recipient by name
    public void searchInRecipient(String recipientName){
        recipientWebElement.click();
        recipientSearchWebElement.sendKeys(recipientName);
    }

    // Method to clear recipient selection
    public void clearRecipient(){
        recipientWebElement.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = 0;", recipientDivWebElement);
        defaultChooseInRecipientList.click();
    }

    // Method to scroll to the top of the page
    public void getTopOfThePage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    // Method to get validation state of recipient field
    public String getRecipientValidatorState(){
        return getValidatorState(recipientValidatorWebElement);
    }

    // Method to get error message for recipient field
    public String getRecipientErrorMsg(){
        recipientValidatorWebElement.click();
        return recipientErrorMsgWebElement.getText();
    }

    @FindBy(id = "txt_tr_comment")
    WebElement commentWebElement;                // Element for comment input field

    // Method to insert a comment
    public void insertComment(String comment){
        commentWebElement.sendKeys(comment);
    }

    /**************************************************
     * dateOfCreatingMail Methods (تاريخ انشاء البريد)
     **************************************************/
    @FindBy(id = "cph_main_txt_tr_date")
    WebElement dateOfCreatingMail;               // WebElement for creation date, auto-filled and non-editable

    // Method to get the creation date value
    public String getTheValueOfCreatingDate() {
        return dateOfCreatingMail.getAttribute("value");
    }

    /**************************************************
     * Subject Name Methods (موضوع البريد)
     **************************************************/
    @FindBy(id = "txt_tr_subject")
    WebElement subjectOfTextBox;                 // WebElement for subject input field

    // Method to insert mail subject
    public void enteringTheSubjectOfMail(String mailSub) {
        subjectOfTextBox.sendKeys(mailSub);
    }

    // Method to get the mail subject value
    public String getTheValueOfSubjectOfTheMail() {
        return subjectOfTextBox.getAttribute("value");
    }

    // Method to clear the mail subject field
    public void clearTheValueOfSubjectOfTheMail() {
        subjectOfTextBox.clear();
    }

    /**************************************************
     * Number Of Document (رقم الخطاب)
     **************************************************/
    @FindBy(id = "txt_tr_letter_num")
    WebElement numberOfEmail;                   // WebElement for email number input field

    // Method to insert email number
    public void setEtNum(String numberOfMail) {
        numberOfEmail.sendKeys(numberOfMail);
    }

    // Method to get the email number value
    public String getNumMail() {
        return numberOfEmail.getAttribute("value");
    }

    /**************************************************
     * Date Of Document (تاريخ الخطاب)
     **************************************************/
    @FindBy(id = "txt_tr_letter_date")
    WebElement dateOfEmail;                     // WebElement for email date input field

    // Method to insert email date
    public void setEtDate(String DateOfMail) {
        dateOfEmail.sendKeys(DateOfMail);
    }

    /**************************************************
     * Number Of Referral Document (الخطاب المشار اليه)
     **************************************************/
    @FindBy(id = "txt_tr_ref_letter")
    WebElement referredEmail;                    // WebElement for referred email input field

    // Method to insert referred email number
    public void setReferralMailNumber(String ReferralMailNumber) {
        referredEmail.sendKeys(ReferralMailNumber);
    }

    // Method to get the referred email number value
    public String getTheValueOfReferralNumberOfTheMail() {
        return referredEmail.getAttribute("value");
    }

    /**************************************************
     * Click On CheckBox Methods
     **************************************************/
    @FindBy(id = "cph_main_chkLetter")
    WebElement referredEmailCheckBox;            // Checkbox for referred email

    @FindBy(id = "cph_main_chk_storage_num")
    WebElement storageNumberCheckBox;            // Checkbox for storage number

    @FindBy(id = "cph_main_chk_urgent_tr")
    WebElement urgentTransactionCheckBox;        // Checkbox for urgent transaction

    @FindBy(id = "cph_main_chk_secret_tr")
    WebElement secretEmailCheckBox;              // Checkbox for secret email

    @FindBy(id = "cph_main_chk_LinkDealToCitizen")
    WebElement linkTransactionCheckBox;          // Checkbox to link transaction

    @FindBy(id = "cph_main_chk_sendT3mem")
    WebElement generalSendingCheckBox;           // Checkbox for general sending

    // Method to deactivate referred email number checkbox
    public void pressOnDeactivateReferralNumber() {
        referredEmailCheckBox.click();
    }

    // Method to toggle storage number checkbox
    public void pressOnNumberOfStorage() {
        storageNumberCheckBox.click();
    }

    // Method to toggle urgent transaction checkbox
    public void pressOnUrgentTransaction() {
        urgentTransactionCheckBox.click();
    }

    // Method to toggle secret email checkbox
    public void pressOnSecretEmail() {
        secretEmailCheckBox.click();
    }

    // Method to toggle referral email number link checkbox
    public void clickLinkEt() {
        linkTransactionCheckBox.click();
    }

    private By civilIdLinkBy = By.id("dv_linkTreat_txt_Natno");
    public void setCivilId(String civilId) {
        driver.findElement(civilIdLinkBy).sendKeys(civilId);
    }
    // Method to toggle general sending checkbox
    public void pressOnGeneralSending() {
        generalSendingCheckBox.click();
    }

    /**************************************************
     * Error Message Methods
     **************************************************/
    @FindBy(id = "spnA_txt_tr_subject")
    WebElement subjectValidator;                // Validator for subject field

    @FindBy(css = "#spnA_txt_tr_subject p[class='span_error'] span")
    WebElement subjectErrorMsg;                 // Error message for subject field

    // Method to get validation state of subject field
    public String getSubjectValidatorState(){
        return getValidatorState(subjectValidator);
    }

    // Method to get error message of subject field
    public String getSubjectErrorMsg(){
        subjectValidator.click();
        return subjectErrorMsg.getText();
    }

    @FindBy(id = "btn_EtEntrySend")
    WebElement sendBtnWebElement;               // Button to send email
    // Button to send email
    @FindBy(css = "input[value='موافق']")
    WebElement confirmWebElement;               // Button to confirm action
    // Button to confirm send email


    // WebElement for the close button in the save confirmation dialog
    @FindBy(id="divMasterAlert_close-lnk2")
    WebElement closeDivSave;

    // WebElement for the decline button
    @FindBy(css="input[value='غير موافق ']")
    WebElement declineBtnWebElement;

    // Method to click the send button and confirm the action
    public void clickSendConfirm(){
        sendBtnWebElement.click();
        confirmWebElement.click();
    }

    // Method to click the decline button
    public void clickDeclineSend(){
        declineBtnWebElement.click();
    }

    // Method to only click the send button
    public void clickSend(){
        sendBtnWebElement.click();
    }

    /*********************************************
     *                 Successful Adding
     *********************************************/
// WebElement for the successful message display
    @FindBy(css = ".txt_msg")
    WebElement successfulMsgWebElement;

    // Method to validate that the mail was created successfully
    public boolean validateSuccessfulCreatingMail(){
        exWait.until(ExpectedConditions.visibilityOf(successfulMsgWebElement));
        boolean flag = successfulMsgWebElement.getText().equals("تم إرسال بريد خارجي بنجاح");
        // Close the success message dialog
        closeDivSave.click();
        return flag;
    }

    // Method to clear all fields in the form
    public void clearAllField(){
        clearTheValueOfSubjectOfTheMail();
        clearDocTypeNum();
        clearReceiverNum();
        clearSenderNum();
        clearRecipient();
        clearTreatClassificationNum();
    }

    private By generaliziationCkBoxBy = By.id("cph_main_chk_sendT3mem");
    private By sliderBarInOutGehaBy = By.cssSelector(".slider.round");
    private By forAllBy = By.id("cph_main_rdGenPurbAll");

    public void setTheEtGeneralized(){
        driver.findElement(generaliziationCkBoxBy).click();
        driver.findElement(sliderBarInOutGehaBy).click();
        try{
            driver.findElement(forAllBy).click();
        }catch (Exception e){
            driver.findElement(sliderBarInOutGehaBy).click();
            driver.findElement(forAllBy).click();
        }
    }
}



