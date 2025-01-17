package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.Archive_Search_Grid;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_Archive_Search_Grid extends TestInit {
    private Archive_Search_Grid archiveSearchGrid ;
    @BeforeMethod
    public void setup() throws InterruptedException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe("0342169", "24602460");
        archiveSearchGrid = contentAside.goToArchiveSearch();
    }
    @Test
    public void TC_toggleDate() throws InterruptedException {
        //click on toggle date
        archiveSearchGrid.searchTabNavigator("الصادر");
        Thread.sleep(500);
        archiveSearchGrid.searchTabNavigator("الوارد");
        archiveSearchGrid.searchByIncomeMail("271335", "", "", "");
        archiveSearchGrid.clickSearch();
        Thread.sleep(3000);
        archiveSearchGrid.searchByIncomeMail("","1446", "رجب","16");
        archiveSearchGrid.clickSearch();
        Thread.sleep(3000);

        //validate the toggle date at each tab
    }
}
