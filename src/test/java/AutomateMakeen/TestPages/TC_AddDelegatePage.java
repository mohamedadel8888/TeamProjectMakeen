package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class TC_AddDelegatePage extends TestInit{

    DelegatePage delegatePage;
    UsersControl usersControl;
    AddDelegatePage addDelegatePage;
    PersonalAccountsPage personalAccountsPage;
    SoftAssert softAssert = new SoftAssert();
    //Today's Date in Hiijri
    HijrahDate dateHijri = HijrahDate.now();
    HijrahDate dateHijriPlus10Days = dateHijri.plus(10, ChronoUnit.DAYS);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String hijriDateToday = dateHijri.format(formatter);
    String hijri10DaysDate = dateHijriPlus10Days.format(formatter);


    @BeforeClass
   public void setupClass()  {
       lunchDriver();
       loginPage.goToLoginPage();
       HomePage homePage = loginPage.loginUserWithoutRemMe(userID,userPasswd);
       homePage.goToHomePage();
       usersControl = contentAside.goToUsersControl();
        }

   @Test
    public void addNewDelegateWithValidData(){
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.selectDepartmentNameFromDropDown("ادارة الارشيف");
        addDelegatePage.selectDelegatedEmployeeFromDropDown("عادل حسن");
        addDelegatePage.chooseNewPeriodRadioButton();
        addDelegatePage.inputDelegateDateFrom(hijriDateToday);
        addDelegatePage.inputDelegateDateTo(hijri10DaysDate);
        //addDelegatePage.inputTimePeriodFrom("11:59");
        //addDelegatePage.selectTimePeriodFromDropDown("صباحا");
        //addDelegatePage.inputTimePeriodTo("10:00");
        //addDelegatePage.selectTimePeriodToDropDown("مساءا");
        addDelegatePage.clickSaveButton();
        addDelegatePage.acceptPopUp();
        addDelegatePage.clickGoBackButton();
        boolean delegateAdded = delegatePage.getDelegateResult("عادل حسن");
        softAssert.assertTrue(delegateAdded,"Incorrect Addition of New Delegate");
        delegatePage.clickSignOut();
        personalAccountsPage = loginPage.loginUserWithDelegateAccounts("3569897","24602460");
        boolean delegateAccountPresent = personalAccountsPage.getDelegateEmployeeName("عادل حسن");
        softAssert.assertTrue(delegateAccountPresent,"Delegate Account Not Present");
        softAssert.assertAll();
    }

    @Test
    public void addNewDelegateWithDefaultAndEmptyData(){
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.clickSaveButton();
        String depNameErrorMessage = addDelegatePage.getDepartmentNameErrorMessage();
        //String delegateEmployeeErrorMessage = addDelegatePage.getDelegateEmployeeErrorMessage();
        String periodTypeErrorMessage = addDelegatePage.getPeriodTypeErrorMessage();
        //String delegateDateFromErrorMessage = addDelegatePage.getDelegateDateFromErrorMessage();
        //String delegateDateToErrorMessage = addDelegatePage.getDelegateDateToErrorMessage();
        softAssert.assertEquals(depNameErrorMessage ,
                "اختر الإدارة",
                "Incorrect Department Name Error Message.");
        /*
        softAssert.assertEquals(delegateEmployeeErrorMessage,
                " اختر الموظف المفوض",
                "Incorrect Delegate Employee Error Message.");

         */
        softAssert.assertEquals(periodTypeErrorMessage,
                "يرجى اختيار نوع الفترة",
                "Incorrect Period Type Error Message.");

        /*
        softAssert.assertEquals(delegateDateFromErrorMessage,
                "يرجى إدخال تاريخ بداية التفويض",
                "Incorrect Delegate Date From Error Message.");
        softAssert.assertEquals(delegateDateToErrorMessage,
                " يرجى إدخال تاريخ نهاية التفويض",
                "Incorrect Delegate Date To Error Message.");
         */

        softAssert.assertAll();


    }



}





