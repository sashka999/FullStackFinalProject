package WorkFlows;

import Extensions.dbActions;
import Utilities.commonOps;
import Extensions.uiActions;
import io.qameta.allure.Step;

import java.util.List;

public class webFlows extends commonOps {

    @Step("Login Grafana Flow")
    public static void login(String user, String pass) {

        uiActions.updateText(grafanaLogin.txt_username, user);
        uiActions.updateText(grafanaLogin.txt_password, pass);
        uiActions.click(grafanaLogin.btn_login);
        uiActions.click(grafanaLogin.btn_skip);
    }

    @Step("Login Grafana Flow with DB Credentials")
    public static void loginDB() {

//        dbActions.getCredentials("SELECT user_name, password FROM Users WHERE id = '3'").get(0);
        List<String> cred = dbActions.getCredentials("SELECT user_name, password FROM Users WHERE id = '3'");
        uiActions.updateText(grafanaLogin.txt_username, cred.get(0));
        uiActions.updateText(grafanaLogin.txt_password, cred.get(1));
        uiActions.click(grafanaLogin.btn_login);
        uiActions.click(grafanaLogin.btn_skip);
    }

    @Step("Create a New User")
    public static void createUser(String name, String email, String user, String password){

        uiActions.click(grafanaServerAdminMainPage.btn_newUser);
        uiActions.updateText(grafanaUserListPage.txt_name, name);
        uiActions.updateText(grafanaUserListPage.txt_email, email);
        uiActions.updateText(grafanaUserListPage.txt_login, user);
        uiActions.updateText(grafanaUserListPage.txt_password, password);
        uiActions.click(grafanaUserListPage.btn_create);
    }

    @Step("Delete Last User From Users Table")
    public static void deleteUser(){

        uiActions.click(grafanaServerAdminMainPage.rows.get(grafanaServerAdminMainPage.rows.size()-1));
        uiActions.click(grafanaUserListPage.btn_delete);
        uiActions.click(grafanaUserListPage.btn_confirmDelete);
    }
}
