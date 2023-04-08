package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeCountryTEST extends CommonAPI {
    static ChangeCountrytoIndia changeCountry;
    public static void  getInit(){changeCountry = PageFactory.initElements(driver, ChangeCountrytoIndia.class);}
    @Test
    public void Testing_To_Change(){
        getInit();
        changeCountry.ChangeMyCountryToIndia();
        String actual = changeCountry.ConfirmationOfIndia();
        String expected = "Connecting India With The World";
        Assert.assertEquals(actual,expected);
    }
}
