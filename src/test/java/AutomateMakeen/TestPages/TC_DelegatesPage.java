package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.*;
import AutomateMakeen.Utilities.Data;
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

    @Test(priority = 1)
    public void correctNavigationToAddDelegatePage() {

        usersControl.selectEmployeeByID(Data.validEmployeeIDToDelegate);
        delegatePage = usersControl.delegationControl();
        addDelegatePage = delegatePage.clickAddButton();
        String pageTitle = addDelegatePage.getPageTitle();
        delegatePage = addDelegatePage.clickGoBackButton();
        Assert.assertEquals(pageTitle,
                Data.correctDelegatePageName,
                "Incorrect Navigation to Add Delegate Page");
    }

    @Test (priority = 2)
    public void correctNavigationToEditDelegatePage() {

        delegatePage.clickCheckBoxDelegateEmployeeByID(Data.validEmployeeIDToEditDelegation);
        editDelegatePage = delegatePage.clickEditButton();
        String pageTitle = editDelegatePage.getEditDelegateTitlePage();
        String delegateEmployeeField = editDelegatePage.getEditDelegateEmployeeField();
        editDelegatePage.clickGoBack();
        editDelegatePage.acceptPopUp();
        softAssert.assertEquals(pageTitle,
                Data.correctEditDelegatePageName,
                "Incorrect Navigation to Edit Delegate Page");
        softAssert.assertTrue(delegateEmployeeField.contains(Data.validEmployeeIDToEditDelegation),
                "Incorrect Edit Delegate Page For Specific Employee");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void getDelegationInformation() {

        List<String> delegationInformationList = delegatePage.getDelegationInformationByEmployeeID(Data.validEmployeeIDToGetDelegationInformation);
        softAssert.assertEquals(delegationInformationList.get(2), Data.correctEmployeeName ,"Incorrect Employee Name");
        softAssert.assertEquals(delegationInformationList.get(3), Data.correctDelegateDateFrom, "Incorrect Delegate Date From");
        softAssert.assertEquals(delegationInformationList.get(4), Data.correctDelegateDateTo,"Incorrect Delegate Date To");
        softAssert.assertEquals(delegationInformationList.get(5), Data.correctDelegateTimeFrom,"Incorrect Delegate Time From");
        softAssert.assertEquals(delegationInformationList.get(6), Data.correctDelegateTimeTo,"Incorrect Delegate Time To");
        softAssert.assertAll();
    }


    @Test(priority = 4)
    public void correctDeletionOfDelegation() {

        delegatePage.clickCheckBoxDelegateEmployeeByID(Data.validEmployeeIDToDeleteDelegation);
        delegatePage.clickDeleteDelegatation();
        String deleteConfirmationMessage = delegatePage.getPopUpMessage();
        delegatePage.acceptPopUp();
        boolean delegationPresent = delegatePage.checkPresenceOfDelegationByEmployeeID(Data.validEmployeeIDToDeleteDelegation);
        softAssert.assertEquals(deleteConfirmationMessage,
                Data.correctDeleteConfirmationMessage,
                "Incorrect Delete Confirmation Message.");
        softAssert.assertFalse(delegationPresent,
                "Incorrect Edit Delegate Page For Specific Employee");
        softAssert.assertAll();
    }

}

