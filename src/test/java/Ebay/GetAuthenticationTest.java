package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAuthenticationTest extends CommonAPI {
    static AuthenticateBag authenticateBag;
    public static void  getAuthenticate(){authenticateBag = PageFactory.initElements(driver,AuthenticateBag.class);}
    @Test
    public void Testing_Authenticaton() throws InterruptedException {
        getAuthenticate();
        authenticateBag.Authenticationg_LV_BAg();
        String actual= authenticateBag.AssertLV();
        String expected = "Add to cart";
        Assert.assertEquals(actual,expected);
    }
}
