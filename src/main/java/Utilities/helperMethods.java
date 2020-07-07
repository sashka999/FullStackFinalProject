package Utilities;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;

import javax.imageio.ImageIO;
import java.io.File;

// --------------------------------------------------- This is a helper method to take a screenshot. Used for visual testing ------------------------------------------------
public class helperMethods extends commonOps{

    public static void takeElementScreenShot(WebElement imageElement, String imageName){

        try{
            imageScreenShot = new AShot().takeScreenshot(driver, imageElement);
            ImageIO.write(imageScreenShot.getImage(), "png", new File(getData("ImageRepo") + imageName + ".png"));
        }
        catch (Exception e){
            System.out.println("Error writing image file, see details: " + e);
        }
    }

}
