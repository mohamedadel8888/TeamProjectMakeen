package AutomateMakeen.TestPages;

import AutomateMakeen.Base.BaseComp;
import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Archive_Search_Grid;
import AutomateMakeen.Pages.Mail_CreateExMail;
import AutomateMakeen.Pages.Mail_Inbox_Grid;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.LocalDate;
import java.util.List;

import static AutomateMakeen.Base.BaseComp.getHijriDate;
import static AutomateMakeen.TestPages.TC_Mail_CreateExMail.*;
import static AutomateMakeen.TestPages.TC_Mail_inbox_Grid.*;

public class TC_Archive_Search_Grid extends TestInit {
    private Archive_Search_Grid archiveSearchGrid ;
    private Mail_CreateExMail mail_CreateExMail;
    Mail_Inbox_Grid mailInboxGrid;

    private final String testEtSubject = "Chief Paradigm Planner";
    private final String testEtRedirect = "National Creative Specialist";
    private final String testetIncomeNumber = "271461";
    private final String testArchiveNumber = "204216";
    private String testDay;
    private final String testLetterNumber = "15135654";
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
        archiveSearchGrid.searchByIncomeMail( "رقم الوارد" , etIncomeNumber );
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"رقم الوارد");
        archiveSearchGrid.searchByIncomeMail("تاريخ الوارد", getHijriDate());
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"تاريخ الوارد");
        softAssert.assertAll();
    }



    @Test
    public void TC_searchByEtSubject(){
        archiveSearchGrid.searchTabNavigator("الموضوع");
        archiveSearchGrid.searchByEtSubject(etSubject);
//        archiveSearchGrid.searchByEtSubject(testEtSubject);
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtAttachment(){
        archiveSearchGrid.searchTabNavigator("المرفقات");
        archiveSearchGrid.searchByAttachment("file1","");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"اسم المرفق");
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(""));
        archiveSearchGrid.searchByAttachment("","أخري");
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"نوع المرفق");
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByEtArchive(){
        archiveSearchGrid.searchTabNavigator("الارشيف");
        //archiveSearchGrid.searchByArchive(etArchiveNum,"1446", "رجب","19");
        archiveSearchGrid.searchByArchive("" ,"1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"تايخ الانشاء");
        archiveSearchGrid.searchByArchive(etArchiveNum ,"", "","");
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"رقم الارشيف");
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByEtSender(){
        archiveSearchGrid.searchTabNavigator("المرسل");
        //archiveSearchGrid.searchByEtSender(etSenderName);
        archiveSearchGrid.searchByEtSenderName(etSender);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"المرسل");
        archiveSearchGrid.clearEtSenderName();
        //archiveSearchGrid.searchByLetterNumber(letterNum);
        archiveSearchGrid.searchByLetterNumber(letterNum);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"رقم الخطاب");
        archiveSearchGrid.clearLetterNumber();
        archiveSearchGrid.setLetterDateFrom("1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"التايخ من");
        archiveSearchGrid.clearLetterDateFrom();
        archiveSearchGrid.setLetterDateTo("1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"التاريخ الي");
        archiveSearchGrid.clearLetterDateTo();
        archiveSearchGrid.setEtSource(etSender); // خانة المرسل في انشاء بريد خارجي
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"المصدر");
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        softAssert.assertAll();
    }

    @Test
    public void TC_searchByEtReceiver(){
        archiveSearchGrid.searchTabNavigator("المستقبل");
        archiveSearchGrid.searchByEtReceiver("مروان خليل");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByEtLinkCivil(){
        archiveSearchGrid.searchTabNavigator("المواطن");
        archiveSearchGrid.searchByLinkEt("الرقم التعريفي",civilId);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"الرقم التعريفي");
        archiveSearchGrid.searchByLinkEt("الاسم الاول", "مروان");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"الاسمالاول");
        archiveSearchGrid.searchByLinkEt("الاسم الثاني", "خليل");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"الاسم الثاني");
        archiveSearchGrid.searchByLinkEt("الاسم الثالث","ربط");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"الاسم الثالث");
        archiveSearchGrid.searchByLinkEt("الاسم الاخير", "مواطن");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"الاسم الاخير");
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByExportData(){
        archiveSearchGrid.searchTabNavigator("الصادر");
        archiveSearchGrid.searchByEtExport("رقم الصادر",etIncomeNumber);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"رقم الصادر");
        archiveSearchGrid.searchByEtExport("تاريخ من","1446/رجب/"+"20");
        archiveSearchGrid.searchByEtExport("تاريخ الي","1446/رجب/"+"20");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("hghhh"),"التاريخ");  //تم تصدير المعاملة يوم 20
//        archiveSearchGrid.searchByEtExport("تاريخ الي","1446/رجب/"+testDay);
//        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByEtExport("الموجه اليه","مروان خليل موجه اليه");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect),"الموجه اليه");
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByDocType(){
        archiveSearchGrid.searchTabNavigator("نوع المستند");
        archiveSearchGrid.searchByDocType(etDocType);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtCreator(){
        archiveSearchGrid.searchTabNavigator("منشئ المعاملة");
        archiveSearchGrid.selectEtCreator("الادارة",userDept,"");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"خانة منشئ المعاملة");
        archiveSearchGrid.selectEtCreator("الموظف",userDept,userName);
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject),"خانة الموظف");
        softAssert.assertAll();
    }
    @Test
    public void TC_searchByDecisionNum(){
        archiveSearchGrid.searchTabNavigator("المذكرة الداخلية");
        archiveSearchGrid.searchByDecisionNum(etDecisionNumber);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }
    @Test
    public void TC_etDetails(){
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail( "رقم الوارد" , etIncomeNumber );
        archiveSearchGrid.clickSearch();
        archiveSearchGrid.openTreatmentDetails(etSubject);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("مصدر المعاملة"),etSender);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("الموضوع"),etSubject+" وفقا للخطاب المشار إليه "+referalEtNum);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("المستقبل"),etReceivcer);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("منشئ الموضوع"),userName);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("نوع المستند"),etDocType);
        softAssert.assertNotNull(archiveSearchGrid.getTreatmentSpecificDetail("رقم الخزنة"));
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الوارد"),etIncomeNumber);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الوارد"),"1446/07/"+testDay);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الارشيف"),etArchiveNum);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الإنشاء"),"1446/07/"+testDay);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تصنيف المعاملة"),etMainClass+" - "+etSubClass);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("مدة الإنجاز"),etPeriod+ " يوم");
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("رقم الخطاب"),letterNum);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("تاريخ الخطاب"),"1446/07/"+testDay);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("الخطاب المشار إليه"),referalEtNum);
        softAssert.assertEquals(archiveSearchGrid.getTreatmentSpecificDetail("المعاملة لدى"),userDept+" - "+userName);
//        softAssert.assertNotNull(archiveSearchGrid.getTreatmentSpecificDetail("الرد علي المعاملة"));
        softAssert.assertAll();
    }

    @Test
    public void TC_etGeneralization(){
        archiveSearchGrid.searchTabNavigator("التعميم");
        archiveSearchGrid.searchByGeneralization("تاريخ من","1446/رجب/23");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة تاريخ من");

        archiveSearchGrid.searchByGeneralization("تاريخ الي","1446/رجب/23");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة تاريخ الي");

        archiveSearchGrid.searchByGeneralization("الموضوع","Chief Infrastructure Technician");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة الموضوع");

        archiveSearchGrid.searchByGeneralization("نوع المستند","اعادة المياة");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة نوع المستند");

        archiveSearchGrid.searchByGeneralization("التصنيف الفرعي","مولد");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة التصنيف الفرعي");

        archiveSearchGrid.searchByGeneralization("نوع التعميم","الجميع");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة نوع التعميم");

        archiveSearchGrid.searchByGeneralization("نص التعميم","مروان تعميم");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة نص التعميم");

        archiveSearchGrid.searchByGeneralization("تعميم ل","الامين");
        archiveSearchGrid.clickSearch();
        softAssert.assertTrue(archiveSearchGrid.checkIfTreatmentExists("Chief Infrastructure Technician"),"خانة تعميم ل");
        softAssert.assertAll();
    }
    @Test
    public void TC_showAllBtn() {
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail( "رقم الوارد" , "123456" );
        archiveSearchGrid.clickShowAllBtn();
        Assert.assertTrue(archiveSearchGrid.etIncomeNumIsEmpty());
    }
//    @AfterMethod
//    public void refresh(){
//        driver.navigate().refresh();
//    }
}
