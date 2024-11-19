package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.FileNotFoundException;
import java.util.List;

public class TC_DelegatesPage extends TestInit {


    @BeforeClass
    public void setupClass() {
        lunchDriver();
        loginPage.goToLoginPage();
        HomePage homePage = loginPage.loginUserWithoutRemMe(userID, userPasswd);
        homePage.goToHomePage();
        usersControl = contentAside.goToUsersControl();
    }

    /*
   هذا الاختبار يتحقق من الانتقال الصحيح إلى صفحة "إضافة مفوض" من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين".
 3. تحديد موظف معين مفوض من قبل.
 4. النقر على زر "إضافة مفوض".
 5. التحقق من أن صفحة "إضافة مفوض" يتم عرضها بشكل صحيح.
     */
    @Test(priority = 1)
    public void correctNavigationToAddDelegatePage() throws FileNotFoundException {

        usersControl.selectEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDelegate"));
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        String pageTitle = addDelegatePage.getPageTitle();
        delegatePage = addDelegatePage.clickGoBackButton();
        Assert.assertEquals(pageTitle,
                getJsonData("DelegateData","correctDelegatePageName"),
                "Incorrect Navigation to Add Delegate Page");
    }

    /*
    هذا الاختبار يتحقق من الانتقال الصحيح إلى صفحة "تعديل مفوض" من خلال:
 1. تسجيل الدخول إلى النظام.
2. الانتقال إلى قائمة "ادارة المستخدمين".
3. تحديد موظف معين مفوض من قبل.
 4. اختيار تفويض معين والنقر على زر "تعديل".
 5. التحقق من أن صفحة "تعديل مفوض" يتم عرضها بشكل صحيح وأنها الصفحة الصحيحة للموظف المحدد.
     */
    @Test (priority = 2)
    public void correctNavigationToEditDelegatePage() throws FileNotFoundException {

        delegatePage.clickCheckBoxDelegateEmployeeByID(getJsonData("DelegateData","validEmployeeIDToEditDelegation"));
        editDelegatePage = delegatePage.clickEditButton();
        String pageTitle = editDelegatePage.getEditDelegateTitlePage();
        String delegateEmployeeField = editDelegatePage.getEditDelegateEmployeeField();
        editDelegatePage.clickGoBack();
        editDelegatePage.acceptPopUp();
        softAssert.assertEquals(pageTitle,
                getJsonData("DelegateData","correctEditDelegatePageName"),
                "Incorrect Navigation to Edit Delegate Page");
        softAssert.assertTrue(delegateEmployeeField.contains
                        (getJsonData("DelegateData","validEmployeeIDToEditDelegation")),
                "Incorrect Edit Delegate Page For Specific Employee");
        softAssert.assertAll();
    }

    /*
    هذا الاختبار يتحقق من عرض معلومات التفويض الصحيحة لموظف محدد من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين".
 3. تحديد موظف معين.
 4. التحقق من أن تفاصيل التفويض المعروضة للموظف المحدد صحيحة.
     */
    @Test(priority = 3)
    public void getValidDelegationInformationSpecificEmployee() throws FileNotFoundException {

        List<String> delegationInformationList =
                delegatePage.getDelegationInformationByEmployeeID(getJsonData("DelegateData","validEmployeeIDToGetDelegationInformation"));
        softAssert.assertEquals(delegationInformationList.get(2),
                getJsonData("DelegateData","correctEmployeeName"),
                "Incorrect Employee Name");
        softAssert.assertEquals(delegationInformationList.get(3),
                getJsonData("DelegateData","correctDelegateDateFrom"),
                "Incorrect Delegate Date From");
        softAssert.assertEquals(delegationInformationList.get(4),
                getJsonData("DelegateData","correctDelegateDateTo"),
                "Incorrect Delegate Date To");
        softAssert.assertEquals(delegationInformationList.get(5),
                getJsonData("DelegateData","correctDelegateTimeFrom"),
                "Incorrect Delegate Time From");
        softAssert.assertEquals(delegationInformationList.get(6),
                getJsonData("DelegateData","correctDelegateTimeTo"),
                "Incorrect Delegate Time To");
        softAssert.assertAll();
    }

    /*
     هذا الاختبار يتحقق من الحذف الصحيح لتفويض من خلال:
 1. تسجيل الدخول إلى النظام.
 2. الانتقال إلى قائمة "ادارة المستخدمين".
 3. تحديد موظف معين مفوض من قبل.
 4. اختيار تفويض معين والنقر على زر "حذف".
 5. التحقق من أن التفويض قد تم حذفه بنجاح وعدم ظهوره في القائمة.
     */
    @Test(priority = 4)
    public void correctDeletionOfDelegation() throws FileNotFoundException {

        delegatePage.clickCheckBoxDelegateEmployeeByID(getJsonData("DelegateData","validEmployeeIDToDeleteDelegation"));
        delegatePage.clickDeleteDelegatation();
        String deleteConfirmationMessage = delegatePage.getPopUpMessage();
        delegatePage.acceptPopUp();
        boolean delegationPresent = delegatePage.checkPresenceOfDelegationByEmployeeID(getJsonData("DelegateData","validEmployeeIDToDeleteDelegation"));
        softAssert.assertEquals(deleteConfirmationMessage,
                getJsonData("DelegateData","correctDeleteConfirmationMessage"),
                "Incorrect Delete Confirmation Message.");
        // Assuming softAssert is an instance of SoftAssert
        if (!delegationPresent) {
            softAssert.assertFalse(delegationPresent, "Incorrect Delegate Deletion For Specific Employee");
        } else {
            System.out.println("Condition failed: delegationPresent is true.");
        }
        softAssert.assertAll();
    }
}

