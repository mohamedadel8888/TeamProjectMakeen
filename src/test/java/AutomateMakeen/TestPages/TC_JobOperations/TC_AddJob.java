package AutomateMakeen.TestPages.TC_JobOperations;

import AutomateMakeen.BaseTest.TestInit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TC_AddJob extends TestInit {
    @BeforeClass
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);
        addJob = contentAside.goToJobOperations();
        addJob.goToAddJob();
    }
    @Test
    public void verifyAddJob (){
        addJob.selectClassCode("00102");
        addJob.selectJobName("001022001");
        addJob.selectJobGradient("21520404");
        addJob.selectJobType("مهندس");
        addJob.selectJobDegree("2");
        addJob.insertDepartment("ادارة المهندسين");
        addJob.insertJobStatus("غير مستثناة");
        addJob.startDateSelect("1","2","2025");

    }


}
