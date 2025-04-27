package AutomateMakeen.TestPages.TC_JobOperations;

import AutomateMakeen.BaseTest.TestInit;
import AutomateMakeen.Pages.CreateExternalMailPage;
import AutomateMakeen.Pages.OutboxMails;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TC_AddJob extends TestInit {
    String jobNumber;
    String decNumber;
    String decDirection;
    String subject;
    Faker faker = new Faker();


    @BeforeClass
    public void setupClass() throws FileNotFoundException {
        lunchDriver();
        loginPage.goToLoginPage();
        loginPage.loginUserWithoutRemMe(userID, userPasswd);

        jobNumber = faker.number().digits(4);
        decNumber = faker.number().digits(3);
        decDirection = getJsonData("EmployeeOperations","decDirection"); /*جهه القرار*/

        subject = faker.lorem().sentence(2);

        CreateExternalMailPage createExternalMailPage = contentAside.goToCreateExternalMail();
        createExternalMailPage.clearAllField();
        createExternalMailPage.pressOnNumberOfStorage();
        createExternalMailPage.enteringTheSubjectOfMail(subject);
        createExternalMailPage.setDocTypeUsingControl(getJsonData("ValidExternalMailData","docTypeNum"));
        createExternalMailPage.setReceiverUsingControl(getJsonData("ValidExternalMailData", "receiverName"));
        createExternalMailPage.setSenderUsingControl(getJsonData("ValidExternalMailData","senderName"));
        createExternalMailPage.setTreatClassificationUsingControl(getJsonData("ValidExternalMailData","mainClass"),getJsonData("ValidExternalMailData","treatClassification"));
        createExternalMailPage.insertRecipient(getJsonData("ValidExternalMailData","recipient3"));
        createExternalMailPage.pressOnDeactivateReferralNumber();
        createExternalMailPage.clickSendConfirm();
        createExternalMailPage.validateSuccessfulCreatingMail();
        OutboxMails outboxMails = contentAside.goToExportedMail();
        outboxMails.getRecentlyAddedMail(subject);
        archiveNum = outboxMails.getMailData().get(5);
        addJob = contentAside.goToJobOperations();
        addJob.goToAddJob();
    }
    @Test
    public void verifyAddJob (){
        addJob.selectClassCode("00102");
        String jobName = addJob.selectJobName("001022001");
        addJob.selectJobGradient("21520404");
        addJob.selectJobType("مهندس");
        addJob.selectJobDegree("2");
        addJob.addJobNumber(jobNumber);
        addJob.insertDepartment("ادارة المهندسين");
        addJob.insertJobStatus("غير مستثناة");
        addJob.startDateSelect("1","2","2025");
        addJob.setRecNumberTextField(archiveNum);
        addJob.decNumber(decNumber);
        addJob.decDateSelect("2023/2/2");
        addJob.decSelectDirection(decDirection);
        addJob.saveJob();
        addJob.confirmJob();
        addJob.returnToMain();
        addJob.searchJob(jobNumber,"00102");
        Assert.assertEquals(jobName,addJob.getJobName());
    }

}
