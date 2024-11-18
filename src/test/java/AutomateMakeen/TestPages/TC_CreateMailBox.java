package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_CreateMailBox extends TestInit {
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

    @Test(description = "Test Create External Mail Usnig Valid data",priority = 11)
    public void tc_createValidExternalMail()  {
        createExternalMailPage.clearAllField();
        createExternalMailPage.enteringTheSubjectOfMail(subject);
        createExternalMailPage.setDocTypeUsingControl(docTypeName);
        createExternalMailPage.setReceiverUsingControl(receiverName);
        createExternalMailPage.setSenderUsingControl(senderName);
        createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
        createExternalMailPage.insertRecipient(recipient);
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirm();
        Assert.assertTrue(createExternalMailPage.validateSuccessfulCreatingMail());
    }

    static int Subjectflag = 0;
    @Test(dataProvider = "subjectDataProvider",description = "Test Create External Mail Usnig Invalid subject" +
            "and valid data in rest of fields",priority = 1)
    public void tc_testSubjectFieldWithInsertInvalidData(String subData)  {
        createExternalMailPage.clearTheValueOfSubjectOfTheMail();
        if(Subjectflag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.setDocTypeUsingControl(docTypeName);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
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
            Assert.assertEquals(createExternalMailPage.getTheValueOfSubjectOfTheMail().length(),
                    140);
        }else{
            createExternalMailPage.pressOnDeactivateReferralNumber();
            Assert.assertEquals(createExternalMailPage.getSubjectValidatorState(),"Red Circle");
            Assert.assertEquals(createExternalMailPage.getSubjectErrorMsg(),"برجاء إدخال الموضوع");
        }
    }

    @DataProvider(name = "subjectDataProvider")
    public Object[] subjectDataProvider() {
        return new Object[]{"","><{};'",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        };
    }
    static int docTypeFlag = 0;
    @Test(dataProvider = "controlDataProvider",description = "Test Create External Mail Usnig Invalid Doc type" +
            "and valid data in rest of fields",priority = 2)
    public void tc_testDocTypeFieldWithInsertInvalidData(String docTypeData) {
        createExternalMailPage.clearDocTypeNum();
        if(docTypeFlag == 0){
            createExternalMailPage.clearAllField();
            createExternalMailPage.enteringTheSubjectOfMail(subject);
            createExternalMailPage.setReceiverUsingControl(receiverName);
            createExternalMailPage.setSenderUsingControl(senderName);
            createExternalMailPage.setTreatClassificationUsingControl("تصنيف رئيسي",treatClassification);
            createExternalMailPage.insertRecipient(recipient);
            createExternalMailPage.pressOnDeactivateReferralNumber();
            docTypeFlag = 1;
        }
        createExternalMailPage.setDocTypeNum(docTypeData);
        createExternalMailPage.clickSend();
        createExternalMailPage.getTopOfThePage();
        createExternalMailPage.pressOnDeactivateReferralNumber();
        Assert.assertEquals(createExternalMailPage.getDocTypeValidatorState(),"Red Circle");
        if(docTypeData.equals("1111")){
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),
                    "نوع المستند غير صحيح أو غير موجود");
        }else{
            Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),"برجاء إدخال نوع المستند");
        }
    }
    @DataProvider(name = "controlDataProvider")
    public Object[] controlDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", "",
              "1111"
        };
    }

    static int docTypeCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider",description = "Test Create External Mail Usnig copy paste by insert Invalid subject" +
            "and valid data in rest of fields",priority = 3)
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
        Assert.assertEquals(createExternalMailPage.getDocTypeErrorMsg(),
                    "عفواً نوع المستند يقبل أرقام فقط");
    }
    @DataProvider(name = "controlCPDataProvider")
    public Object[] controlCPDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", "",
        };
    }
    /*********************************************************/

    static int receiverFlag = 0;
    @Test(dataProvider = "controlDataProvider",description = "Test Create External Mail Using Invalid Receiver" +
            "and valid data in rest of fields",priority = 4)
    public void tc_testReceiverFieldWithInsertInvalidData(String receiverData) {
        createExternalMailPage.clearReceiverNum();
        if(receiverFlag == 0){
//            Assert.assertEquals(createExternalMailPage.getReceiverValidatorState(),"Asterisk");
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
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),
                    "رقم المرسل إليه غير صحيح أو غير موجود");
        }else{
            Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),"برجاء إدخال المرسل إليه");
        }
    }

    static int receiverCPFlag = 0;
    @Test(dataProvider = "controlCPDataProvider",description = "Test Create External Mail by copy paste Using Invalid Receiver" +
            "and valid data in rest of fields",priority = 5)
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
        Assert.assertEquals(createExternalMailPage.getReceiverErrorMsg(),
                "عفوا, المرسل اليه يقبل ارقام فقط");
    }

    /********************************************/
    static int senderFlag = 0;
    @Test(dataProvider = "controlDataProvider",description = "Test Create External Mail Using Invalid Sender" +
            "and valid data in rest of fields",priority = 6)
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
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),
                    "رقم المرسل غير صحيح أو غير موجود");
        }else{
            Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),"برجاء إدخال المرسل");
        }
    }


    static int senderCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider",description = "Test Create External Mail Using copy paste in Invalid Receiver" +
            "and valid data in rest of fields",priority = 7)
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
        Assert.assertEquals(createExternalMailPage.getSenderErrorMsg(),
                "عفوا, المرسل يقبل ارقام فقط");
    }
    @DataProvider(name = "receiverCPDataProvider")
    public Object[] receiverCPDataProvider() {
        return new Object[]{"أبجد", "ABCD", "(@*%~^)", "١٣٠", "",
        };
    }

    /****************************************************/
    static int classFlag = 0;
    @Test(dataProvider = "controlDataProvider",description = "Test Create External Mail Using Invalid treat classification" +
            "and valid data in rest of fields",priority = 8)
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
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),
                    "نوع التصنيف غير صحيح أو غير موجود");
        }else{
            Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),"برجاء إدخال تصنيف المعامله");
        }
    }


    static int classCPFlag = 0;
    @Test(dataProvider = "receiverCPDataProvider",description = "Test Create External Mail Using copy paste Invalid treat classification" +
            "and valid data in rest of fields",priority = 9)
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
        Assert.assertEquals(createExternalMailPage.getTreatClassificationErrorMsg(),
                "عفواً نوع التصنيف يقبل أرقام فقط");
    }

    /*******************************************************************/
    @Test(description = "Test Create External Mail Using Invalid recipient" +
            "and valid data in rest of fields",priority = 10)
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
        Assert.assertEquals(createExternalMailPage.getRecipientErrorMsg(),
                    "برجاء إدخال جهة الإحالة");

    }
}

