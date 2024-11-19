package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import AutomateMakeen.Utilities.Data;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
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

/*
 هذا الاختبار يتحقق من إضافة مفوض جديد باستخدام بيانات صحيحة من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين"
 3. تحديد موظف معين واختيار ادارة تفويض.
 4. الانتقال إلى صفحة "إضافة تفويض جديد".
 5. إدخال بيانات صحيحة وحفظها.
 6. التحقق من أن المفوض الجديد تمت إضافته بنجاح ويظهر في قائمة المفوضين.
7.التحقق من أنه عند تسجيل الخروج والدخول بالمستخدم المفوض بالأعمال يتم ظهور الحساب الخاص بالموظف الاخر.
 */
    @Test (priority = 1)
    public void addNewDelegateWithValidData() throws FileNotFoundException {
        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.selectDepartmentNameFromDropDown(getJsonData("DelegateData","validDepartmentName"));
        addDelegatePage.selectDelegatedEmployeeFromDropDown(getJsonData("DelegateData","validDelegatedEmployeeName"));
        addDelegatePage.chooseNewPeriodRadioButton();
        addDelegatePage.inputDelegateDateFrom(hijriDateToday);
        addDelegatePage.inputDelegateDateTo(hijri10DaysDate);
        addDelegatePage.clickSaveButton();
        addDelegatePage.acceptPopUp();
        addDelegatePage.clickGoBackButton();
        boolean delegateAdded = delegatePage.getDelegateResult(getJsonData("DelegateData","validDelegatedEmployeeName"));
        softAssert.assertTrue(delegateAdded, "Incorrect Addition of New Delegate");
        delegatePage.clickSignOut();
        personalAccountsPage = loginPage.loginUserWithDelegateAccounts(
                getJsonData("DelegateData","validEmployeeIDToDelegate"),
                getJsonData("DelegateData","validPassword"));
        boolean delegateAccountPresent = personalAccountsPage.getDelegateEmployeeName(getJsonData("DelegateData","validDelegatedEmployeeName"));
        homePage = personalAccountsPage.enterDelegateAccountByName(getJsonData("DelegateData","validDelegatedEmployeeName"));
        usersControl = contentAside.goToUsersControl();
        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID(getJsonData("DelegateData","validDelegatedEmployeeID"));
        delegatePage.clickDeleteDelegatation();
        delegatePage.acceptPopUp();
        delegatePage.signOut();
        loginPage.clearAllFeild();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        contentAside.goToUsersControl();
        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        usersControl.delegationControl();
        softAssert.assertTrue(delegateAccountPresent, "Delegate Account Not Present");
        softAssert.assertAll();
    }

    /*
     هذا الاختبار يتحقق من التعامل مع إدخال بيانات افتراضية أو ترك الحقول فارغة أثناء إضافة مفوض جديد من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين"
 3. تحديد موظف معين واختيار ادارة تفويض.
 4. الانتقال إلى صفحة "إضافة تفويض جديد".
 3. ترك الحقول فارغة أو إدخال بيانات افتراضية فقط.
 4. محاولة حفظ البيانات والتحقق من ظهور رسائل خطأ توضح كل الحقول المطلوبة.
     */
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


//اختبار Radio Button "اعتماد الفترة" لموظف معطل مؤقت في فترات اخري
// عند اختيار فترة معينة تظهر في الجزء التالي الخاص بتحديد فترة التكليف

/*
// هذا الاختبار يتحقق من إمكانية إضافة فترة تعطيل محددة لموظف معطل مؤقت في فترات متعددة أثناء إضافة مفوض جديد من خلال:
// 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين"
 3. تحديد موظف معين واختيار ادارة تفويض.
 4. الانتقال إلى صفحة "إضافة مفوض جديد".
  5. اختيار موظف له أكثر من فترات تعطيل مؤقت.
// 6. إدخال بيانات صحيحة.
7.التحقق من أن الفترة المختارة تظهر في الجزء الخاص بتحديد فترة التكليف.
// 8. حفظ البيانات والتحقق من أن المفوض تمت إضافته بنجاح.
     */
    /*
    @Test
    public void addNewDelegateWithMoreThanOneDisablePeriod() {
        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        addDelegatePage.selectDepartmentNameFromDropDown(getJsonData("DelegateData","validDepartmentNameMoreThanOnePeriod"));
        addDelegatePage.selectDelegatedEmployeeFromDropDown(getJsonData("DelegateData","validDelegatedEmployeeNameMoreThanOnePeriod"));
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





