package Ebay;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MensFashionWatchTest extends CommonAPI {
    static MEnsFashionWatches mEnsFashionWatches;
    public static void  getwatches(){mEnsFashionWatches = PageFactory.initElements(driver,MEnsFashionWatches.class);}

    @Test
    public void FinallySelectingMyFashion(){
       getwatches();
        mEnsFashionWatches.MenFashion_Scrool_Watches();
        String actual = mEnsFashionWatches.VerifuyToWatch();
        String expected = "Shop by Model";
        Assert.assertEquals(actual,expected);
    }
}

