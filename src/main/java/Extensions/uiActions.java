package Extensions;

import Utilities.commonOps;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class uiActions extends commonOps {

    @Step("Click On Element")
    public static void click (WebElement elem){

        if(!Platform.equalsIgnoreCase("mobile") && !Platform.equalsIgnoreCase("electron") && !Platform.equalsIgnoreCase("desktop"))
           wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("Send Text To Text-Field")
    public static void updateText (WebElement elem, String value){

        if(!Platform.equalsIgnoreCase("mobile"))
             wait.until(ExpectedConditions.visibilityOf(elem));
        elem.sendKeys(value);
    }

    @Step("Update DropDown Field")
    public static void updateDropdown (WebElement elem, String value){

        Select myValue = new Select(elem);
        if(!Platform.equalsIgnoreCase("mobile"))
            wait.until(ExpectedConditions.visibilityOf(elem));
        myValue.selectByVisibleText(value);
    }

    @Step("Mouse Hover to Element")
    public static void mouseHoverElements (WebElement elem1, WebElement elem2){

        action.moveToElement(elem1).moveToElement(elem2).click().build().perform();
    }
}
