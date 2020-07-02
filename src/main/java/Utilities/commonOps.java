package Utilities;

import Extensions.apiActions;
import WorkFlows.apiFlows;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import static WorkFlows.apiFlows.getTeamProperty;

public class commonOps extends base{

    public static String getData(String nodeName){

        File fXmlFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc = null;
        try{
            fXmlFile = new File ("./Configuration/DataConfig.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder  = dbFactory.newDocumentBuilder();
            doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        }
        catch(Exception e){
            System.out.println("Error Reading XML file: " + e);
        }
        finally{
            return doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
    }

    public static void initBrowser(String browserType) {

        if(browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if(browserType.equalsIgnoreCase("firefox"))
            driver = initFFDriver();
        else if(browserType.equalsIgnoreCase("ie"))
            driver = initIEDriver();
        else
            throw new RuntimeException(("Invalid platform name stated"));

        driver.manage().window().maximize();
        driver.get(getData("url"));
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("TimeOut")));
        action = new Actions(driver);
    }

    public static WebDriver initChromeDriver(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver initFFDriver(){

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver initIEDriver(){

        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

    public static void initMobile(){

        dc.setCapability(MobileCapabilityType.UDID, getData("UDID"));
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getData("APP_PACKAGE"));
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getData("APP_ACTIVITY"));
        try{
            driver = new RemoteWebDriver(new URL(getData("Appium_Server") + "/wd/hub"), dc);
        }
        catch(Exception e){
            System.out.println("Can not connect to Appium Server, See Details: " + e);
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
    }

    public static void initAPI(){

        RestAssured.baseURI = getData("url_api");
        httpRequest = RestAssured.given().auth().preemptive().basic(getData("user"), getData("password"));
        if(!getTeamProperty("totalCount").equals("0")){
            for(int i = Integer.parseInt(getTeamProperty("totalCount")); i > 0; i-- ){
                apiActions.delete(apiFlows.getTeamProperty("teams[0].id"));
            }
        }
    }

    public static void initElectron(){

        System.setProperty("webdriver.chrome.driver", getData("ElectronDriverPath"));
        ChromeOptions opt = new ChromeOptions();
        opt.setBinary(getData("ElectronAppPath"));
        dc.setCapability("chromeOptions", opt);
        dc.setBrowserName("chrome");
        driver = new ChromeDriver(dc);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
    }

    public static void initDesktop(){

        dc.setCapability("app", getData("Calculator_App"));
        try {
            driver = new WindowsDriver(new URL(getData("Appium_Server")), dc);
        }
        catch(Exception e){
            System.out.println("Can not connect to Windows Application, See Details: " + e);
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);


    }

        @BeforeClass
        @Parameters({"PlatformName"})
        public void startSession (String PlatformName) {

            Platform = PlatformName;

            if (Platform.equalsIgnoreCase("web"))
                initBrowser(getData("BrowserName"));
            else if(Platform.equalsIgnoreCase("mobile"))
                initMobile();
            else if(Platform.equalsIgnoreCase("api"))
                initAPI();
            else if(Platform.equalsIgnoreCase("electron"))
                initElectron();
            else if(Platform.equalsIgnoreCase("desktop"))
                initDesktop();
            else
                throw new RuntimeException(("Invalid platform name stated"));
            managePages.init();

            manageDB.initConnection(getData("dbURL"), getData("dbUser"), getData("dbPassword"));
        }

        @AfterMethod
        public void afterMethod(){
            if(Platform.equalsIgnoreCase("web"))
            driver.get(getData("url"));
        }

        @AfterClass
        public void closeSession () {

            manageDB.closeConnection();

            if(!Platform.equalsIgnoreCase("api"))
            driver.quit();
        }
}
