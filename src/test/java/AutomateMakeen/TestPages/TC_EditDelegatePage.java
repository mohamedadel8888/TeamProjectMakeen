package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HomePage;
import AutomateMakeen.Utilities.Data;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public void editDelegateWithValidData() {

        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        delegatePage = usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID(Data.validEmployeeIDToEditDelegation);
        editDelegatePage = delegatePage.clickEditButton();
        editDelegatePage.clearEditDelegateDateFrom();
        editDelegatePage.clearEditDelegateDateTo();
        editDelegatePage.inputEditDelegateDateFrom(Data.editDelegateDateFrom);
        editDelegatePage.inputEditDelegateDateTo(Data.editDelegateDateTo);
        editDelegatePage.clickSaveButton();
        editDelegatePage.acceptPopUp();
        List<String> delegationInformationList =  delegatePage.getDelegationInformationByEmployeeID(Data.validEmployeeIDToEditDelegation);
        softAssert.assertEquals(delegationInformationList.get(3), Data.correctEditDelegateDateFrom, "Incorrect Delegate Date From");
        softAssert.assertEquals(delegationInformationList.get(4), Data.correctEditDelegateDateTo,"Incorrect Delegate Date To");
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
    public void editDelegateWithInvalidPastDates() {
        delegatePage.clickCheckBoxDelegateEmployeeByID(Data.validEmployeeIDToEditDelegation);
        editDelegatePage = delegatePage.clickEditButton();
        editDelegatePage.clearEditDelegateDateFrom();
        editDelegatePage.clearEditDelegateDateTo();
        editDelegatePage.inputEditDelegateDateFrom(Data.editInvalidDelegateDateFrom);
        editDelegatePage.inputEditDelegateDateTo(Data.editInvalidDelegateDateFrom);
        editDelegatePage.clickSaveButton();
        String editDateFromError = editDelegatePage.getEditDelegateDateFromErrorMessage();
        String editDateToError = editDelegatePage.getEditDelegateDateToErrorMessage();
        softAssert.assertEquals(editDateFromError,"يجب ان لا يكون التاريخ قبل تاريخ اليوم");
        softAssert.assertEquals(editDateToError,"عفوا لا يمكن ادخال تاريخ نهاية قبل "+ hijriDateYesterday);
        softAssert.assertAll();
        editDelegatePage.clickGoBack();
        editDelegatePage.acceptPopUp();
    }
}
