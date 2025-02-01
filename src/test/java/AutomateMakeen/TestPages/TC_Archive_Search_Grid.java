package AutomateMakeen.TestPages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Archive_Search_Grid;
import AutomateMakeen.Pages.Mail_CreateExMail;
import AutomateMakeen.Pages.Mail_Inbox_Grid;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static AutomateMakeen.Base.BaseComp.getHijriDate;
import static AutomateMakeen.TestPages.TC_Mail_CreateExMail.*;
import static AutomateMakeen.TestPages.TC_Mail_inbox_Grid.*;

public class TC_Archive_Search_Grid extends TestInit {
    private Archive_Search_Grid archiveSearchGrid;
    private Mail_CreateExMail mail_CreateExMail;
    private Mail_Inbox_Grid mailInboxGrid;

    private final String testEtRedirect = "Human Markets Associate";


    @BeforeClass
    public void setup() throws InterruptedException {
        lunchDriver();
        loginPage.goToLoginPage();
        qCMSHomePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        userName = qCMSHomePage.getUserName();
        userDept = qCMSHomePage.getUserDept();
        mail_CreateExMail = contentAside.goToCreateExMail();
        mail_CreateExMail.enteringTheSubjectOfMail(etSubject);
        mail_CreateExMail.setEtNum(letterNum);
        mail_CreateExMail.setEtDate(letterDate);
        mail_CreateExMail.setReferralMailNumber(referalEtNum);
//        mail_CreateExMail.pressOnDeactivateReferralNumber();
        mail_CreateExMail.setDocTypeUsingControl(etDocType);
        mail_CreateExMail.setReceiverUsingControl(etReceivcer);
        mail_CreateExMail.setSenderUsingControl(etSender);
        mail_CreateExMail.setTreatClassificationUsingControl(etMainClass,etSubClass);
        mail_CreateExMail.clickLinkEt();
        mail_CreateExMail.setCivilId(civilId);
        Assert.assertTrue(mail_CreateExMail.addFile("file1","resourse/qr.pdf"));
        mail_CreateExMail.insertRecipient(etRecipient);
        mail_CreateExMail.clickSendConfirm();
        Assert.assertTrue(mail_CreateExMail.validateSuccessfulCreatingMail());
        mail_CreateExMail.goToHomePage();
        mailInboxGrid = contentAside.goToImportedMail();
        mailInboxGrid.getRecentlyAddedMail(etSubject);
        List<String> mailData = mailInboxGrid.getMailData();
        Assert.assertNotNull(mailData.get(4));
        Assert.assertNotNull(mailData.get(5));
        etIncomeNumber = mailData.get(4);
        etArchiveNum = mailData.get(5);
        System.out.println("Import : "+etIncomeNumber+ " Archive : "+etArchiveNum);
        mailInboxGrid.goToEtTabs("مذكرة داخلية");
        mailInboxGrid.createDecisionForInternalMemo(userDept,"27280","يبليل");
        etDecisionNumber = mailInboxGrid.getDecisionNum();
        mail_CreateExMail.goToHomePage();
        archiveSearchGrid = contentAside.goToArchiveSearch();
    }

    @Test/*(dependsOnMethods = "AutomateMakeen.TestPages.TC_Mail_inbox_Grid.tc_validateCreatedMailAddedToImportedMail")*/
    public void TC_searchByEtIncomeNum() {
        //click on toggle date
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail("رقم الوارد", etIncomeNumber);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "رقم الوارد");
        archiveSearchGrid.searchByIncomeMail("تاريخ الوارد", getHijriDate());
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "تاريخ الوارد");
        softAssert.assertAll();
    }


    @Test
    public void TC_searchByEtSubject() {
        archiveSearchGrid.searchTabNavigator("الموضوع");
        archiveSearchGrid.searchByEtSubject(etSubject);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtAttachment() {
        archiveSearchGrid.searchTabNavigator("المرفقات");
        archiveSearchGrid.searchByAttachment("file1", "");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "اسم المرفق");
        archiveSearchGrid.searchByAttachment("", "أخري");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "نوع المرفق");
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByEtArchive() {
        archiveSearchGrid.searchTabNavigator("الارشيف");

        archiveSearchGrid.searchByArchive("رقم الارشيف", etArchiveNum);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "رقم الارشيف");

        archiveSearchGrid.searchByArchive("تاريخ الانشاء", getHijriDate());
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "تايخ الانشاء");

        softAssert.assertAll();
    }

    @Test
    public void TC_searchByEtSender() {
        archiveSearchGrid.searchTabNavigator("المرسل");
        //archiveSearchGrid.searchByEtSender(etSenderName);
        archiveSearchGrid.searchByEtSenderName(etSender);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "المرسل");
        archiveSearchGrid.clearEtSenderName();
        archiveSearchGrid.searchByLetterNumber(letterNum);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "رقم الخطاب");
        archiveSearchGrid.clearLetterNumber();
        archiveSearchGrid.setLetterDateFrom(getHijriDate());
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "التايخ من");
        archiveSearchGrid.clearLetterDateFrom();
        archiveSearchGrid.setLetterDateTo(getHijriDate());
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "التاريخ الي");
        archiveSearchGrid.clearLetterDateTo();
        archiveSearchGrid.setEtSource(etSender); // خانة المرسل في انشاء بريد خارجي
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "المصدر");
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByEtReceiver() {
        archiveSearchGrid.searchTabNavigator("المستقبل");
        archiveSearchGrid.searchByEtReceiver("مروان خليل");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByEtLinkCivil() {
        archiveSearchGrid.searchTabNavigator("المواطن");
        archiveSearchGrid.searchByLinkEt("الرقم التعريفي", civilId);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "الرقم التعريفي");
        archiveSearchGrid.searchByLinkEt("الاسم الاول", "مروان");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "الاسمالاول");
        archiveSearchGrid.searchByLinkEt("الاسم الثاني", "خليل");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "الاسم الثاني");
        archiveSearchGrid.searchByLinkEt("الاسم الثالث", "ربط");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "الاسم الثالث");
        archiveSearchGrid.searchByLinkEt("الاسم الاخير", "مواطن");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "الاسم الاخير");
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByExportData() {
        archiveSearchGrid.searchTabNavigator("الصادر");
        archiveSearchGrid.searchByEtExport("رقم الصادر", etIncomeNumber);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "رقم الصادر");
        archiveSearchGrid.searchByEtExport("تاريخ من", "1446/7/29");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect), "التاريخ من");  //تم تصدير المعاملة يوم 20
        archiveSearchGrid.searchByEtExport("تاريخ الي", "1446/7/29");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect), "التاريخ الي");  //تم تصدير المعاملة يوم 20
        archiveSearchGrid.searchByEtExport("الموجه اليه", "مروان خليل");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect), "الموجه اليه");
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByDocType() {
        archiveSearchGrid.searchTabNavigator("نوع المستند");
        archiveSearchGrid.searchByDocType(etDocType);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtCreator() {
        archiveSearchGrid.searchTabNavigator("منشئ المعاملة");
        archiveSearchGrid.selectEtCreator("الادارة", userDept, "");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "خانة منشئ المعاملة");
        archiveSearchGrid.selectEtCreator("الموظف", userDept, userName);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject), "خانة الموظف");
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByDecisionNum() {
        archiveSearchGrid.searchTabNavigator("المذكرة الداخلية");
        archiveSearchGrid.searchByDecisionNum(etDecisionNumber);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_etDetails() {
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail("رقم الوارد", etIncomeNumber);
        archiveSearchGrid.clickSearch();
        archiveSearchGrid.openTreatmentDetails(etSubject);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("مصدر المعاملة"), etSender, "مصدر المعاملة");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("الموضوع"), etSubject + " وفقا للخطاب المشار إليه " + referalEtNum, "الموضوع");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("المستقبل"), etReceivcer, "المستقبل");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("منشئ الموضوع"), userName, "منشئ الموضوع");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("نوع المستند"), etDocType, "نوع المستند");
        softAssert.assertNotNull(archiveSearchGrid.getTreatmentSpecificDetail("رقم الخزنة"), "رقم الخزنة");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الوارد"), etIncomeNumber, "رقم الوارد");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الوارد"), getHijriDate(), "تاريخ الوارد");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الارشيف"), etArchiveNum, "رقم الارشيف");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الإنشاء"), getHijriDate(), "تاريخ الانشاء");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تصنيف المعاملة"), etSubClass, "تصنيف المعاملة");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("مدة الإنجاز"), etPeriod + " يوم", "مدة الانجاز");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الخطاب"), letterNum, "رقم الخطاب");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الخطاب"), getHijriDate(), "تاريخ الخطاب");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("الخطاب المشار إليه"), referalEtNum, "الخطاب المشار اليه");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("المعاملة لدى"), userDept + " - " + userName, "المعاملة لدي");
        archiveSearchGrid.closeEtDetails();
        softAssert.assertAll();
    }

    @Test
    public void TC_etGeneralization() {
        archiveSearchGrid.searchTabNavigator("التعميم");
        archiveSearchGrid.searchByGeneralization("تاريخ من", "1446/7/29");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة تاريخ من");

        archiveSearchGrid.searchByGeneralization("تاريخ الي", "1446/7/29");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة تاريخ الي");

        archiveSearchGrid.searchByGeneralization("الموضوع", "Senior Functionality Architect");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة الموضوع");

        archiveSearchGrid.searchByGeneralization("نوع المستند", "اعادة المياة");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة نوع المستند");

        archiveSearchGrid.searchByGeneralization("التصنيف الفرعي", "مولد");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة التصنيف الفرعي");

        archiveSearchGrid.searchByGeneralization("نوع التعميم", "الجميع");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة نوع التعميم");

        archiveSearchGrid.searchByGeneralization("نص التعميم", "با");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة نص التعميم");

        archiveSearchGrid.searchByGeneralization("تعميم ل", "الامين");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Senior Functionality Architect"), "خانة تعميم ل");
        softAssert.assertAll();
    }

    @Test
    public void TC_showAllBtn() {
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail("رقم الوارد", "123456");
        archiveSearchGrid.clickShowAllBtn();
        Assert.assertTrue(archiveSearchGrid.etIncomeNumIsEmpty());
    }

    @Test
    public void TC_ChangeDateBtn() {
        archiveSearchGrid.toggleDateType();
        softAssert.assertTrue(archiveSearchGrid.validateToggleDate("ميلادي"),"التاريخ الميلادي");
        archiveSearchGrid.toggleDateType();
        softAssert.assertTrue(archiveSearchGrid.validateToggleDate("هجري"),"التاريخ الهجري");
        softAssert.assertAll();
    }
}

//    @AfterMethod
//    public void refresh(){
//        driver.navigate().refresh();
//    }
