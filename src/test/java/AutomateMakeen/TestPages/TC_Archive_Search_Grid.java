package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Archive_Search_Grid;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.chrono.HijrahDate;

import static AutomateMakeen.TestPages.TC_Mail_CreateExMail.*;
import static AutomateMakeen.TestPages.TC_Mail_inbox_Grid.etArchiveNum;
import static AutomateMakeen.TestPages.TC_Mail_inbox_Grid.etIncomeNumber;

public class TC_Archive_Search_Grid extends TestInit {
    private Archive_Search_Grid archiveSearchGrid ;

    private String testEtSubject = "Chief Paradigm Planner";
    private String testEtRedirect = "National Creative Specialist";
    private String testetIncomeNumber = "271461";
    private String testArchiveNumber = "204216";
    private String testDay;
    private String testLetterNumber = "15135654";
    @BeforeMethod
    public void setup() throws InterruptedException {
        testDay = HijrahDate.now().toString();
        testDay = testDay.substring(testDay.length()-2);
        lunchDriver();
        loginPage.goToLoginPage();
        qCMSHomePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        userName = qCMSHomePage.getUserName();
        userDept = qCMSHomePage.getUserDept();
        archiveSearchGrid = contentAside.goToArchiveSearch();
    }
    @Test/*(dependsOnMethods = "AutomateMakeen.TestPages.TC_Mail_inbox_Grid.tc_validateCreatedMailAddedToImportedMail")*/
    public void TC_searchByEtIncomeNum() {
        //click on toggle date
        archiveSearchGrid.searchTabNavigator("الوارد");
//        archiveSearchGrid.searchByIncomeMail( etIncomeNumber , "", "", "");
        archiveSearchGrid.searchByIncomeMail( testetIncomeNumber, "", "", "");
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByIncomeMail("","1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
    }

    @Test
    public void TC_searchByEtSubject(){
        archiveSearchGrid.searchTabNavigator("الموضوع");
        //archiveSearchGrid.searchByEtSubject(etSubject);
        archiveSearchGrid.searchByEtSubject(testEtSubject);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtAttachment(){
        archiveSearchGrid.searchTabNavigator("المرفقات");
        archiveSearchGrid.searchByAttachment("file1","");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(""));
        archiveSearchGrid.searchByAttachment("","أخري");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }
    @Test
    public void TC_searchByEtArchive(){
        archiveSearchGrid.searchTabNavigator("الارشيف");
        //archiveSearchGrid.searchByArchive(etArchiveNum,"1446", "رجب","19");
        archiveSearchGrid.searchByArchive("" ,"1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByArchive(testArchiveNumber ,"", "","");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }
    @Test
    public void TC_searchByEtSender(){
        archiveSearchGrid.searchTabNavigator("المرسل");
        //archiveSearchGrid.searchByEtSender(etSenderName);
        archiveSearchGrid.searchByEtSenderName(etSender);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.clearEtSenderName();
        //archiveSearchGrid.searchByLetterNumber(letterNum);
        archiveSearchGrid.searchByLetterNumber(testLetterNumber);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.clearLetterNumber();
        archiveSearchGrid.setLetterDateFrom("1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.clearLetterDateFrom();
        archiveSearchGrid.setLetterDateTo("1446", "رجب",testDay);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.clearLetterDateTo();
        archiveSearchGrid.setEtSource("مروان"); // خانة المرسل في انشاء بريد خارجي
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        //Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(etSubject));
    }

    @Test
    public void TC_searchByEtReceiver(){
        archiveSearchGrid.searchTabNavigator("المستقبل");
        archiveSearchGrid.searchByEtReceiver("مروان خليل");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
    }
    @Test
    public void TC_searchByEtLinkCivil(){
        archiveSearchGrid.searchTabNavigator("المواطن");
        archiveSearchGrid.searchByLinkEt("الرقم التعريفي",civilId);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByLinkEt("الاسم الاول", "مروان");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByLinkEt("الاسم الثاني", "خليل");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByLinkEt("الاسم الثالث","ربط");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByLinkEt("الاسم الاخير", "مواطن");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
    }
    @Test
    public void TC_searchByExportData(){
        archiveSearchGrid.searchTabNavigator("الصادر");
        archiveSearchGrid.searchByEtExport("رقم الصادر",testetIncomeNumber);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByEtExport("تاريخ من","1446/رجب/"+"20");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect));
//        archiveSearchGrid.searchByEtExport("تاريخ الي","1446/رجب/"+testDay);
//        archiveSearchGrid.clickSearch();
//        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.searchByEtExport("الموجه اليه","مروان خليل موجه اليه");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtRedirect));
    }
    @Test
    public void TC_searchByDocType(){
        archiveSearchGrid.searchTabNavigator("نوع المستند");
        archiveSearchGrid.searchByDocType(etDocType);
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
    }

    @Test
    public void TC_searchByEtCreator(){
        archiveSearchGrid.searchTabNavigator("منشئ المعاملة");
        archiveSearchGrid.selectEtCreator("الادارة","ادارة عامة آيه","");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
        archiveSearchGrid.selectEtCreator("الموظف","ادارة عامة آيه","مروان خليل موظف اول");
        archiveSearchGrid.clickSearch();
        Assert.assertTrue(archiveSearchGrid.checkIfTreatmentExists(testEtSubject));
    }
    @Test
    public void TC_etDetails(){
        archiveSearchGrid.searchTabNavigator("الوارد");
//        archiveSearchGrid.searchByIncomeMail( etIncomeNumber , "", "", "");
        archiveSearchGrid.searchByIncomeMail( etIncomeNumber, "", "", "");
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
        softAssert.assertNotNull(archiveSearchGrid.getTreatmentSpecificDetail("الرد علي المعاملة"));
        softAssert.assertAll();
    }

}
