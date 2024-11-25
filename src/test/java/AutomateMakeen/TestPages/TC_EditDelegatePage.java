package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TC_EditDelegatePage extends TestInit {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String hijriDateYesterday= dateHijriMinus.format(formatter);


    @BeforeClass
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }

    /*
     هذا الاختبار يتحقق من تعديل بيانات مفوض باستخدام بيانات صحيحة من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين".
 3. اختيار تفويض معين والنقر على زر "تعديل".
 4. إدخال بيانات صحيحة (في المفوض من والمفوض الي) وحفظ التعديلات.
 5. التحقق من أن التعديلات تم حفظها بنجاح وعرض البيانات المحدثة.
     */
    @Test (priority = 1)
    public void editDelegateWithValidData() throws FileNotFoundException {

        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        delegatePage = usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID(getJsonData("DelegateData","validEmployeeIDToEditDelegation"));
        editDelegatePage = delegatePage.clickEditButton();
        editDelegatePage.clearEditDelegateDateFrom();
        editDelegatePage.clearEditDelegateDateTo();
        editDelegatePage.inputEditDelegateDateFrom(getJsonData("DelegateData","editDelegateDateFrom"));
        editDelegatePage.inputEditDelegateDateTo(getJsonData("DelegateData","editDelegateDateTo"));
        editDelegatePage.clickSaveButton();
        editDelegatePage.acceptPopUp();
        List<String> delegationInformationList =  delegatePage.getDelegationInformationByEmployeeID(
                getJsonData("DelegateData","validEmployeeIDToEditDelegation"));
        softAssert.assertEquals(delegationInformationList.get(3),
                getJsonData("DelegateData","correctEditDelegateDateFrom"),
                "Incorrect Delegate Date From");
        softAssert.assertEquals(delegationInformationList.get(4),
                getJsonData("DelegateData","correctEditDelegateDateTo"),
                "Incorrect Delegate Date To");
        softAssert.assertAll();

    }
/*
 هذا الاختبار يتحقق من التعامل مع تعديل بيانات مفوض باستخدام تواريخ غير صحيحة (تواريخ في الماضي) من خلال:
// 1. تسجيل الدخول إلى النظام.
// 2. الانتقال إلى قائمة "ادارة المستخدمين".
// 3. اختيار تفويض معين والنقر على زر "تعديل".
// 4. إدخال تواريخ غير صالحة (مثل تاريخ بدء في الماضي) وحفظ التعديلات.
// 5. التحقق من عرض رسالة خطأ توضح أن التواريخ غير صالحة.
 */
    @Test (priority = 2)
    public void editDelegateWithInvalidPastDates() throws FileNotFoundException {
        delegatePage.clickCheckBoxDelegateEmployeeByID(getJsonData("DelegateData","validEmployeeIDToEditDelegation"));
        editDelegatePage = delegatePage.clickEditButton();
        editDelegatePage.clearEditDelegateDateFrom();
        editDelegatePage.clearEditDelegateDateTo();
        editDelegatePage.inputEditDelegateDateFrom(getJsonData("DelegateData","editInvalidDelegateDateFrom"));
        editDelegatePage.inputEditDelegateDateTo(getJsonData("DelegateData","editInvalidDelegateDateFrom"));
        editDelegatePage.clickSaveButton();
        String editDateFromError = editDelegatePage.getEditDelegateDateFromErrorMessage();
        String editDateToError = editDelegatePage.getEditDelegateDateToErrorMessage();
        softAssert.assertEquals(editDateFromError,
                "يجب ان لا يكون التاريخ قبل تاريخ اليوم",
                "Incorrect Error Message");
        softAssert.assertEquals(editDateToError,
                "عفوا لا يمكن ادخال تاريخ نهاية قبل "+ hijriDateYesterday,
                "Incorrect Error Message");
        softAssert.assertAll();
        editDelegatePage.clickGoBack();
        editDelegatePage.acceptPopUp();
    }
}
