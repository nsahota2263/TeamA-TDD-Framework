package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends CommonAPI {
    static EbayHomepage2 ebayHomepage;

    public static void getInitElements() {ebayHomepage = PageFactory.initElements (driver, EbayHomepage2.class);}

    @Test
    public void theResultCheck(){
        getInitElements();
        ebayHomepage.checkOutMYResult();
        String Actual = ebayHomepage.VerifyAlooyewheelsDiameter();
        String Expected = "Alloy Wheels";
        Assert.assertEquals(Actual,Expected);

    }
    @Test
    public void theConfirmation(){
        getInitElements();
       ebayHomepage.Actual_Result_Of_number();
       String actual = ebayHomepage.VerifyAlooyewheelsDiameter();
       String expected = "Alloy Wheels";
       Assert.assertEquals(actual,expected);
    }
    @Test
    public void TheDodgeAlloy() throws InterruptedException {
        getInitElements();
        ebayHomepage.selecting_My_Favorate();
        String actual = ebayHomepage.assertSelectingmyFavorate();
        String expected = "New";
        Assert.assertEquals(actual,expected);

    }
    @Test
    public void FinallyAddingtoCrt() throws InterruptedException {
        getInitElements();
        ebayHomepage.ADDING_TO_MY_EBAY_CART();
        String actual = ebayHomepage.assertaddedtoCart();
        String expected = "1 item added to cart";
        Assert.assertEquals(actual,expected);
    }
}
