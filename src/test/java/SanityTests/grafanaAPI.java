package SanityTests;

import Extensions.verifications;
import Utilities.commonOps;
import WorkFlows.apiFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class grafanaAPI extends commonOps {

    String groupID;

    @Test(description = "Test01: Add Team and Verify it")
    @Description("Test Description: Add Team to Grafana and Verify it")
    public void test01_AddTeamAndVerify(){

        apiFlows.postTeam("AlexTeam1", "1@1.com");
        verifications.text(apiFlows.getTeamProperty("teams[0].name"), "AlexTeam1");
    }

    @Test(description = "Test02: Update Team and Verify it")
    @Description("Test Description: Update Team to Grafana and Verify it")
    public void test02_UpdateTeamAndVerify(){

        apiFlows.updateTeam("AlexTeam2", "1@1.com", apiFlows.getTeamProperty("teams[0].id"));
        verifications.text(apiFlows.getTeamProperty("teams[0].name"), "AlexTeam2");
    }

    @Test(description = "Test03: Delete Team and Verify it")
    @Description("Test Description: Delete Team to Grafana and Verify it")
    public void test03_DeleteTeamAndVerify(){

        apiFlows.deleteTeam(apiFlows.getTeamProperty("teams[0].id"));
        verifications.text(apiFlows.getTeamProperty("totalCount"), "0");
    }
}
