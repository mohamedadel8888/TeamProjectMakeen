package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
    // Test data for creating an external mailbox
    private String subject = "انشاء بريد خارجي";
    private String docTypeNum = "123";
    private String docTypeName = "اعادة المياة";
    private String receiverNum = "5432";
    private String receiverName = "مرسل جديد";
    private String senderNum = "14912";
    private String senderName = "جديده سلمى";
    private String treatClassification = "تصريح بناء جديد";
    private String recipient = "مروان خليل";
    private String activeDays;
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
        Assert.assertTrue(createExternalMailPage.addFile("file name","src\\test\\resources\\Screenshot.png"));
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
            Assert.assertEquals(createExternalMailPage.getSubjectValidatorState(),"Asterisk");
            Assert.assertEquals(createExternalMailPage.getTheValueOfSubjectOfTheMail().length(), 140);
        } else {
            createExternalMailPage.pressOnDeactivateReferralNumber();
            Assert.assertEquals(createExternalMailPage.getSubjectValidatorState(),"Red Circle");
            Assert.assertEquals(createExternalMailPage.getSubjectErrorMsg(),"برجاء إدخال الموضوع");
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
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),"Red Circle");
        if(docTypeData.equals("1111")){
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),"نوع المستند غير صحيح أو غير موجود");
        } else {
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),"برجاء إدخال نوع المستند");
        }
    }

    // Data provider for control data tests
    @DataProvider(name = "controlDataProvider")
    public Object[] controlDataProvider() throws Exception {
        return getJsonArrayAsObjectArray("DataProviderCreateMailBox","docTypeData");
    }

    // Test method to validate the document type field using copy-paste invalid data
    static int docTypeCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider")
    public void tc_testDocTypeFieldWithCopyPasteInvalidData(String docTypeData) {
        createExternalMailPage.clearDocTypeNum();
        if(docTypeCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            docTypeCPFlag = 1;
        }
        createExternalMailPage.copyPasteToDocTypeNum(docTypeData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),"Red Circle");
        Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),"عفواً نوع المستند يقبل أرقام فقط");
    }

    // Data provider for control copy-paste data tests
    @DataProvider(name = "controlCPDataProvider")
    public Object[] controlCPDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", ""};
    }

    // Test method to validate the receiver field using invalid data
    static int receiverFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testReceiverFieldWithInsertInvalidData(String receiverData) {
        createExternalMailPage.clearReceiverNum();
        if(receiverFlag == 0) {
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeNum(docTypeNum);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            receiverFlag = 1;
        }
        createExternalMailPage.insertReceiverNum(receiverData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getReceiverValidatorState(),"Red Circle");
        if(receiverData.equals("1111")){
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),"رقم المرسل إليه غير صحيح أو غير موجود");
        } else {
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),"برجاء إدخال المرسل إليه");
        }
    }

    // Test method to validate the receiver field using copy-paste invalid data
    static int receiverCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider")
    public void tc_testReceiverFieldWithCopyPasteInvalidData(String receiverData) {
        createExternalMailPage.clearReceiverNum();
        if(receiverCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeUsingControl(docTypeName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            receiverCPFlag = 1;
        }
        createExternalMailPage.copyPasteToReceiverNum(receiverData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getReceiverValidatorState(),"Red Circle");
        Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),"عفوا, المرسل اليه يقبل ارقام فقط");
    }

    // Test method to validate the sender field using invalid data
    static int senderFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testSenderFieldWithInsertInvalidData(String senderData) {
        createExternalMailPage.clearSenderNum();
        if(senderFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeNum(docTypeNum);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            senderFlag = 1;
        }
        createExternalMailPage.insertSenderNum(senderData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getSenderValidatorState(),"Red Circle");
        if(senderData.equals("1111")){
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),"رقم المرسل غير صحيح أو غير موجود");
        } else {
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),"برجاء إدخال المرسل");
        }
    }

    // Test method to validate the sender field using copy-paste invalid data
    static int senderCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider")
    public void tc_testSenderFieldWithCopyPasteInvalidData(String senderData) {
        createExternalMailPage.clearSenderNum();
        if(senderCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeUsingControl(docTypeName);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            senderCPFlag = 1;
        }
        createExternalMailPage.copyPasteToSenderNum(senderData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getSenderValidatorState(),"Red Circle");
        Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),"عفوا, المرسل يقبل ارقام فقط");
    }

    // Data provider for receiver copy-paste data tests
    @DataProvider(name = "receiverCPDataProvider")
    public Object[] receiverCPDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", ""};
    }

    // Test method to validate the treatment classification field using invalid data
    static int classFlag = 0;
    @Test(dataProvider = "controlDataProvider")
    public void tc_testTreatClassFieldWithInsertInvalidData(String classData) {
        createExternalMailPage.clearTreatClassificationNum();
        if(classFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeNum(docTypeNum);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            classFlag = 1;
        }
        createExternalMailPage.insertTreatClassificationNum(classData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getTreatClassificationValidatorState(),"Red Circle");
        if(classData.equals("1111")){
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),"نوع التصنيف غير صحيح أو غير موجود");
        } else {
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),"برجاء إدخال تصنيف المعامله");
        }
    }

    // Test method to validate the treatment classification field using copy-paste invalid data
    static int classCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider")
    public void tc_testTreatClassFieldWithCopyPasteInvalidData(String classData) {
        createExternalMailPage.clearTreatClassificationNum();
        if(classCPFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setDocTypeUsingControl(docTypeName);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            classCPFlag = 1;
        }
        createExternalMailPage.copyPasteToTreatClassificationNum(classData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getTreatClassificationValidatorState(),"Red Circle");
        Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),"عفواً نوع التصنيف يقبل أرقام فقط");
    }

    // Test method to validate the recipient field using invalid data
    @Test
    public void tc_testRecipientFieldWithInsertInvalidData() {
        createExternalMailPage.clearAllField();
        createExternalMailPage.enteringTheSubjectOfMail(subject);
        createExternalMailPage.setDocTypeNum(docTypeNum);
        createExternalMailPage.setReceiverUsingControl(receiverName);
        createExternalMailPage.setSenderUsingControl(senderName);
        createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getRecipientValidatorState(),"Red Circle");
        Assert.assertEquals(createExternalMailPage.getRecipientErrorMsg(),"برجاء إدخال جهة الإحالة");
    }
}