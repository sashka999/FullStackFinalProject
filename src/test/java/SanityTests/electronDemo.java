package SanityTests;

import Extensions.verifications;
import Utilities.commonOps;
import WorkFlows.electronFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(Utilities.listeners.class)
public class electronDemo extends commonOps {

    @Test(description = "Test01: Verify Screen Resolution")
    @Description("Test Description: Getting the Resolution from App and Verify it")
    public void test01_verifyResolution(){

        electronFlows.getScreenInfo();
        verifications.textInElement(electronMain.field_screen_info, "Your screen is: 1920px x 1080px");
    }

}
