package SanityTests;

import Extensions.verifications;
import Utilities.commonOps;
import WorkFlows.webFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utilities.listeners.class)
public class grafanaWebDB extends commonOps {

    @Test(description = "Test01: Login to Grafana with DB credentials")
    @Description("Test Description: Login to Grafana Web Application with DB credentials")
    public void test01_login(){

        webFlows.loginDB();
        verifications.textInElement(grafanaMain.txt_mainHeading, "Welcome to Grafana");

    }

}
