package SanityTests;

import Extensions.verifications;
import Utilities.commonOps;
import WorkFlows.calcFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Utilities.listeners.class)
public class calculatorDesktop extends commonOps {

    @Test(description = "Test01: Verify Addition Command")
    @Description("Test Description: Verify Addition Command in Calculator")
    public void test01_addition(){

        calcFlows.calculateAddition();
        verifications.textInElement(calcMain.field_result, "8");
    }

}
