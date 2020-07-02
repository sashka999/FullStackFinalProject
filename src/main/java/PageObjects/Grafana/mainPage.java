package PageObjects.Grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class mainPage {

    @FindBy(how = How.XPATH, using = "//div[@class = 'dashboard-header']/span")
    public WebElement txt_mainHeading;

}
