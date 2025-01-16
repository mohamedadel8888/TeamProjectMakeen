package AutomateMakeen.TestPages;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.HR_TreatmentJob_grid;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_TreatmentJob extends TestInit {
    static Faker faker = new Faker();
    private HR_TreatmentJob_grid treatmentJobGrid;
    private String treatmentManagement = "مروان خليل هيكل اداري";
    static String treatmentName = faker.name().title();

    @BeforeMethod
    public void setupClass() throws InterruptedException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe("0342169", "24602460");
        treatmentJobGrid = contentAside.goToTreatmentJob();
    }
    //Test case that test search functionality by name
    @Test(description = "test case that test adding new treatment jobs functionality")
    public void TC_addTreatmentJob() throws InterruptedException {
        treatmentJobGrid.addNewTreatmentJob(treatmentName,treatmentManagement);
    }
}
