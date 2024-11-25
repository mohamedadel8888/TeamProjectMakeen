package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
    HomePage homePage;
    CreateExternalMailPage createExternalMailPage;

    // Setup method to initialize preconditions for tests in this class
    @BeforeClass(description = "Preconditions for each test in the class :" +
            "السماحية للدخول الي النظام : الأمانة الإلكترونية" +
            "الصلاحية للدخول الى البرنامج الرئيسي البريد ." +
            "الصلاحية للدخول الى البرنامج الفرعي انشاء بريد خارجي ." +
            "من خلال الضغط علي إنشاء بريد خارجي من ال Tree View  . ")
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        createExternalMailPage = contentAside.goToCreateExternalMail();
    }

    // Test method to create a valid external mail
    @Test
    public void tc_createValidExternalMail() throws Exception {
        createExternalMailPage.clearAllField();
        createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
        createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeNum"));
        createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
        createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
        createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
        createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
        createExternalMailPage.pressOnDeactivateReferralNumber();
        //Assert.assertTrue(createExternalMailPage.addFile("file name","src\\test\\resources\\Screenshot.png"));
        createExternalMailPage.clickSendConfirm();
        Assert.assertTrue(createExternalMailPage.validateSuccessfulCreatingMail());
    }

    // Test method to validate the subject field using invalid data
    static int Subjectflag = 0;
    @Test(dataProvider = "subjectDataProvider")
    public void tc_testSubjectFieldWithInsertInvalidData(String subData) throws Exception {
        createExternalMailPage.clearTheValueOfSubjectOfTheMail();
        if(Subjectflag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeName"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            Subjectflag = 1;
        }
        createExternalMailPage.enteringTheSubjectOfMail(subData);
        createExternalMailPage.clickSend();
        if(subData.length() == 141){
            createExternalMailPage.clickDeclineSend();
            createExternalMailPage.getTopOfThePage();
            createExternalMailPage.pressOnDeactivateReferralNumber();
            Assert.assertEquals(createExternalMailPage.getSubjectValidatorState(),getJsonData("MailBoxExpectedData","Asterisk"));
            Assert.assertEquals(createExternalMailPage.getTheValueOfSubjectOfTheMail().length(), 140);
        } else {
            createExternalMailPage.pressOnDeactivateReferralNumber();
            Assert.assertEquals(createExternalMailPage.getSubjectValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
            Assert.assertEquals(createExternalMailPage.getSubjectErrorMsg(),getJsonData("MailBoxExpectedData","SubjectErrorMessage"));
        }
    }

    // Data provider for subject field tests
    @DataProvider(name = "subjectDataProvider")
    public Object[] subjectDataProvider() throws Exception {
        return getJsonArrayAsObjectArray("DataProviderCreateMailBox","subjects");
    }

    // Test method to validate the document type field using invalid data
    static int docTypeFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testDocTypeFieldWithInsertInvalidData(String docTypeData) throws Exception {
        createExternalMailPage.clearDocTypeNum();
        if(docTypeFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            docTypeFlag = 1;
        }
        createExternalMailPage.setDocTypeNum(docTypeData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        if(docTypeData.equals("11111111111111111")){
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),getJsonData("MailBoxExpectedData","DocTypeErrorMessage"));
        } else {
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),getJsonData("MailBoxExpectedData","DocTypeNotFound"));
        }
    }

    // Data provider for control data tests
    @DataProvider(name = "controlDataProvider")
    public Object[] controlDataProvider() throws Exception {
        return getJsonArrayAsObjectArray("DataProviderCreateMailBox","control");
    }

    // Test method to validate the document type field using copy-paste invalid data
    static int docTypeCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider")
    public void tc_testDocTypeFieldWithCopyPasteInvalidData(String docTypeData) throws Exception{
        createExternalMailPage.clearDocTypeNum();
        if(docTypeCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            docTypeCPFlag = 1;
        }
        createExternalMailPage.copyPasteToDocTypeNum(docTypeData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),getJsonData("MailBoxExpectedData","DocTypeNumberOnly"));
    }

    // Data provider for control copy-paste data tests
    @DataProvider(name = "controlCPDataProvider")
    public Object[] controlCPDataProvider() throws Exception{
        return getJsonArrayAsObjectArray("DataProviderCreateMailBox","CP");
    }

    // Test method to validate the receiver field using invalid data
    static int receiverFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testReceiverFieldWithInsertInvalidData(String receiverData) throws Exception {
        createExternalMailPage.clearReceiverNum();
        if(receiverFlag == 0) {
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeNum(getJsonData("ValidExternalMailData","docTypeNum"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            receiverFlag = 1;
        }
        createExternalMailPage.insertReceiverNum(receiverData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getReceiverValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        if(receiverData.equals("11111111111111111")){
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),getJsonData("MailBoxExpectedData","ReceiverNumberNotFound"));
        } else {
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),getJsonData("MailBoxExpectedData","ReceiverErrorMessage"));
        }
    }

    // Test method to validate the receiver field using copy-paste invalid data
    static int receiverCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider")
    public void tc_testReceiverFieldWithCopyPasteInvalidData(String receiverData) throws Exception{
        createExternalMailPage.clearReceiverNum();
        if(receiverCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            receiverCPFlag = 1;
        }
        createExternalMailPage.copyPasteToReceiverNum(receiverData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getReceiverValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),getJsonData("MailBoxExpectedData","ReceiverNumberOnly"));
    }

    // Test method to validate the sender field using invalid data
    static int senderFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testSenderFieldWithInsertInvalidData(String senderData) throws Exception{
        createExternalMailPage.clearSenderNum();
        if(senderFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeNum(getJsonData("ValidExternalMailData","docTypeNum"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            senderFlag = 1;
        }
        createExternalMailPage.insertSenderNum(senderData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getSenderValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        if(senderData.equals("11111111111111111")){
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),getJsonData("MailBoxExpectedData","ReceiverNumberError"));
        } else {
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),getJsonData("MailBoxExpectedData","SenderError"));
        }
    }

    // Test method to validate the sender field using copy-paste invalid data
    static int senderCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider")
    public void tc_testSenderFieldWithCopyPasteInvalidData(String senderData) throws Exception{
        createExternalMailPage.clearSenderNum();
        if(senderCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeName"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            senderCPFlag = 1;
        }
        createExternalMailPage.copyPasteToSenderNum(senderData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getSenderValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),getJsonData("MailBoxExpectedData","SenderNumberErrorMessage"));
    }

    // Data provider for receiver copy-paste data tests
    @DataProvider(name = "receiverCPDataProvider")
    public Object[] receiverCPDataProvider() throws Exception{
        return getJsonArrayAsObjectArray("DataProviderCreateMailBox","CP");
    }

    // Test method to validate the treatment classification field using invalid data
    static int classFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testTreatClassFieldWithInsertInvalidData(String classData) throws Exception{
        createExternalMailPage.clearTreatClassificationNum();
        if(classFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeNum(getJsonData("ValidExternalMailData","docTypeNum"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            classFlag = 1;
        }
        createExternalMailPage.insertTreatClassificationNum(classData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getTreatClassificationValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        if(classData.equals("11111111111111111")){
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),getJsonData("MailBoxExpectedData","TreatClassificationErrorMessage"));
        } else {
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),getJsonData("MailBoxExpectedData","TreatClassificationError"));
        }
    }

    // Test method to validate the treatment classification field using copy-paste invalid data
    static int classCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider")
    public void tc_testTreatClassFieldWithCopyPasteInvalidData(String classData) throws Exception{
        createExternalMailPage.clearTreatClassificationNum();
        if(classCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
            createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeName"));
            createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
            createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
            createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient"));
            createExternalMailPage.pressOnDeactivateReferralNumber();
            classCPFlag = 1;
        }
        createExternalMailPage.copyPasteToTreatClassificationNum(classData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getTreatClassificationValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),getJsonData("MailBoxExpectedData","TreatClassificationNumberErrorMessage"));
    }

    // Test method to validate the recipient field using invalid data
    @Test
    public void tc_testRecipientFieldWithInsertInvalidData() throws Exception{
        createExternalMailPage.clearAllField();
        createExternalMailPage.enteringTheSubjectOfMail(getJsonData("ValidExternalMailData","subject"));
        createExternalMailPage.setDocTypeNum(getJsonData("ValidExternalMailData","docTypeNum"));
        createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
        createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
        createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getRecipientValidatorState(),getJsonData("MailBoxExpectedData","RedCircle"));
        Assert.assertEquals(createExternalMailPage.getRecipientErrorMsg(),getJsonData("MailBoxExpectedData","PlaceToBeSendErrorMessage"));
    }
}