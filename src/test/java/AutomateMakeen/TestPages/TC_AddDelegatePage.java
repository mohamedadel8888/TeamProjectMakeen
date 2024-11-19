package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import AutomateMakeen.Utilities.Data;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


   /* السماحية للدخول الي النظام .
الصلاحية للدخول الي لوحة التحكم .
الصلاحية للدخول الي ادارة المستخدمين .
الصلاحية لادارة تفويض موظف .
     */

public class TC_AddDelegatePage extends TestInit {

    //Today's Date in Hiijri
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String hijriDateToday = dateHijri.format(formatter);
    String hijri10DaysDate = dateHijriPlus10Days.format(formatter);

    //Time Now
    String currentTimeFormat = updatedTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    String currenttimePeriod = currentTime.isBefore(LocalTime.NOON) ? "صباحا" : "مساءا";


    @BeforeClass
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }



    @Test (priority = 1)
    public void addNewDelegateWithValidData() {
        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.selectDepartmentNameFromDropDown(Data.validDepartmentName);
        addDelegatePage.selectDelegatedEmployeeFromDropDown(Data.validDelegatedEmployeeName);
        addDelegatePage.chooseNewPeriodRadioButton();
        addDelegatePage.inputDelegateDateFrom(hijriDateToday);
        addDelegatePage.inputDelegateDateTo(hijri10DaysDate);
        addDelegatePage.clickSaveButton();
        addDelegatePage.acceptPopUp();
        addDelegatePage.clickGoBackButton();
        boolean delegateAdded = delegatePage.getDelegateResult(Data.validDelegatedEmployeeName);
        softAssert.assertTrue(delegateAdded, "Incorrect Addition of New Delegate");
        delegatePage.clickSignOut();
        personalAccountsPage = loginPage.loginUserWithDelegateAccounts(Data.validEmployeeIDToDelegate, Data.validPassword);
        boolean delegateAccountPresent = personalAccountsPage.getDelegateEmployeeName(Data.validDelegatedEmployeeName);
        homePage = personalAccountsPage.enterDelegateAccountByName(Data.validDelegatedEmployeeName);
        usersControl = contentAside.goToUsersControl();
        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID(Data.validDelegatedEmployeeID);
        delegatePage.clickDeleteDelegatation();
        delegatePage.acceptPopUp();
        delegatePage.signOut();
        loginPage.clearAllFeild();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        contentAside.goToUsersControl();
        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        usersControl.delegationControl();
        softAssert.assertTrue(delegateAccountPresent, "Delegate Account Not Present");
        softAssert.assertAll();
    }

    @Test (priority = 2)
    public void addNewDelegateWithDefaultAndEmptyData() {
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.clickSaveButton();
        String depNameErrorMessage = addDelegatePage.getDepartmentNameErrorMessage();
        //String delegateEmployeeErrorMessage = addDelegatePage.getDelegateEmployeeErrorMessage();
        String periodTypeErrorMessage = addDelegatePage.getPeriodTypeErrorMessage();
        addDelegatePage.clickGoBackButton();
        //String delegateDateFromErrorMessage = addDelegatePage.getDelegateDateFromErrorMessage();
        //String delegateDateToErrorMessage = addDelegatePage.getDelegateDateToErrorMessage();
        softAssert.assertEquals(depNameErrorMessage,
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


    /*
//اختبار Radio Button "اعتماد الفترة" لموظف معطل مؤقت في فترات اخري
// عند اختيار فترة معينة تظهر في الجزء التالي الخاص بتحديد فترة التكليف
    @Test
    public void addNewDelegateWithMoreThanOneDisablePeriod() {
        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.selectDepartmentNameFromDropDown(Data.validDepartmentNameMoreThanOnePeriod);
        addDelegatePage.selectDelegatedEmployeeFromDropDown(Data.validDelegatedEmployeeNameMoreThanOnePeriod);
        addDelegatePage.chooseAcceptedPeriodRadioButton();
        addDelegatePage.selectAcceptedPeriodFromDropDown(1);
        addDelegatePage.clickSaveButton();
        addDelegatePage.clickGoBackButton();
        addDelegatePage.acceptPopUp();
        delegatePage.clickGoBackButton();
        delegatePage.acceptPopUp();
        boolean popUpDisplayed= addDelegatePage.isPopUpDisplayed();
        Assert.assertTrue(popUpDisplayed,"PopUp Not Displayed");
    }

     */
}





