package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EbayUploadSellTest extends CommonAPI {
    static EbaySell ebaySell;
    public static void  getSELL(){ebaySell = PageFactory.initElements(driver, EbaySell.class);}
    @Test(testName = "Functional Testing", priority = 1)
    public void Testing_EbaySell(){
      getSELL();
      ebaySell.Sell_Macbookpro();
      String actual= ebaySell.getVerifymessage();
      String expected= "Please verify yourself to continue";
        Assert.assertEquals(actual,expected);
    }
}
