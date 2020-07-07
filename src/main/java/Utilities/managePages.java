package Utilities;

import org.openqa.selenium.support.PageFactory;

public class managePages extends base{
// ------------------------------------------ This method initiates web/desktop elements on pages/apps. Used POM (Page Object Model) ---------------------------------
    public static void init(){
        grafanaLogin = PageFactory.initElements(driver, PageObjects.Grafana.loginPage.class);
        grafanaMain = PageFactory.initElements(driver, PageObjects.Grafana.mainPage.class);
        grafanaLeftMenu = PageFactory.initElements(driver, PageObjects.Grafana.leftMenuPage.class);
        grafanaServerAdminMenuPage = PageFactory.initElements(driver, PageObjects.Grafana.serverAdminMenuPage.class);
        grafanaServerAdminMainPage = PageFactory.initElements(driver, PageObjects.Grafana.serverAdminMainPage.class);
        grafanaUserListPage = PageFactory.initElements(driver, PageObjects.Grafana.userListPage.class);

        mortgageMain = PageFactory.initElements(driver, PageObjects.Mortgage.mainPage.class);
        electronMain = PageFactory.initElements(driver, PageObjects.ElectronDemo.mainPage.class);
        calcMain = PageFactory.initElements(driver, PageObjects.Calculator.mainPage.class);
    }
}
