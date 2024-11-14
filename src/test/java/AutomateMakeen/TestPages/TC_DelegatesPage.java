package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


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

    @Test
    public void correctNavigationToAddDelegatePage() {
        usersControl.selectEmployeeByID("3569897");

        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        String pageTitle = addDelegatePage.getPageTitle();
        Assert.assertEquals(pageTitle,
                "اضافة تفويض",
                "Incorrect Navigation to Add Delegate Page");
    }

    @Test
    public void correctNavigationToEditDelegatePage() {
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID("5555444");
        editDelegatePage = delegatePage.clickEditButton();
        String pageTitle = editDelegatePage.getEditDelegateTitlePage();
        String delegateEmployeeField = editDelegatePage.getEditDelegateEmployeeField();
        softAssert.assertEquals(pageTitle,
                "تعديل تفويض",
                "Incorrect Navigation to Edit Delegate Page");
        softAssert.assertTrue(delegateEmployeeField.contains("5555444"),
                "Incorrect Edit Delegate Page For Specific Employee");
        softAssert.assertAll();
    }

    @Test
    public void getDelegationInformation() {
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        List<String> delegationInformationList = delegatePage.getDelegationInformationByEmployeeID("1601111");
        softAssert.assertEquals(delegationInformationList.get(2), "a a a a" ,"Incorrect Employee Name");
        softAssert.assertEquals(delegationInformationList.get(3), "1446/05/18", "Incorrect Delegate Date From");
        softAssert.assertEquals(delegationInformationList.get(4), "1446/05/24","Incorrect Delegate Date To");
        softAssert.assertEquals(delegationInformationList.get(5), "09:00 ص","Incorrect Delegate Time From");
        softAssert.assertEquals(delegationInformationList.get(6), "08:00 م","Incorrect Delegate Time To");
        softAssert.assertAll();
    }


    @Test
    public void correctDeletionOfDelegation() {
        usersControl.selectEmployeeByID("3569897");
        delegatePage = usersControl.delegationControl();
        delegatePage.clickCheckBoxDelegateEmployeeByID("1601111");
        delegatePage.clickDeleteDelegatation();
        String deleteConfirmationMessage = delegatePage.getPopUpMessage();
        delegatePage.acceptPopUp();
        //delegatePage.rejectPopUp();
        boolean delegationPresent = delegatePage.checkPresenceOfDelegationByEmployeeID("1601111");
        softAssert.assertEquals(deleteConfirmationMessage,
                "هل تريد اتمام عملية الإلغاء؟",
                "Incorrect Delete Confirmation Message.");
        softAssert.assertFalse(delegationPresent,
                "Incorrect Edit Delegate Page For Specific Employee");
        softAssert.assertAll();
    }

}

