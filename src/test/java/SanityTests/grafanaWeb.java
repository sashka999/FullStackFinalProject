package SanityTests;

import Extensions.uiActions;
import Extensions.verifications;
import Utilities.commonOps;
import WorkFlows.webFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utilities.listeners.class)
public class grafanaWeb extends commonOps {

    @Test(description = "Test01: Login to Grafana")
    @Description("Test Description: Login to Grafana Web Application")
    public void test01_login(){

        webFlows.login(getData("user"), getData("password"));
        verifications.textInElement(grafanaMain.txt_mainHeading, "Welcome to Grafana");
    }

    @Test(description = "Test02: Verify Default Users")
    @Description("Test Description: Verifies Number of Users (Should be 1)")
    public void test02_verifyDefaultUsers(){

        uiActions.mouseHoverElements(grafanaLeftMenu.btn_ServerAdmin, grafanaServerAdminMenuPage.link_users);
        verifications.numberOfElements(grafanaServerAdminMainPage.rows, 1);
    }

    @Test(description = "Test03: Add and Verify Users")
    @Description("Test Description: Add a New User and Verify Users")
    public void test03_addAndVerifyUsers(){

        uiActions.mouseHoverElements(grafanaLeftMenu.btn_ServerAdmin, grafanaServerAdminMenuPage.link_users);
        webFlows.createUser("Alex", "1@1.com", "Alex1", "123456");
        verifications.numberOfElements(grafanaServerAdminMainPage.rows, 2);
    }

    @Test(description = "Test04: Delete Last User and Verify Users")
    @Description("Test Description: Delete Last Created User and Verify the Number of Users is 1")
    public void test04_deleteAndVerifyUsers(){

        uiActions.mouseHoverElements(grafanaLeftMenu.btn_ServerAdmin, grafanaServerAdminMenuPage.link_users);
        webFlows.deleteUser();
        verifications.numberOfElements(grafanaServerAdminMainPage.rows, 1);
    }

    @Test(description = "Test05: Verify Avatar")
    @Description("Test Description: Verify PageObjects.Grafana's default (admin) avatar")
    public void test05_verifyAvatar (){

        verifications.visualElement(grafanaLeftMenu.image_Avatar, "grafanaAvatar");
    }
}
