package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static utility.Webelements.EbayWebElements.*;
public class MEnsFashionWatches extends CommonAPI {
        @FindBy(how = How.XPATH,using = hoveroverFashion)public WebElement Selecting;
        @FindBy(how = How.XPATH, using = watches)public WebElement SELECTINGwatches;
        @FindBy(how = How.XPATH, using = Verifywatches)public WebElement VERIFYwatches;

        public void SelectFAshion(){Selecting.click();}
        public void DEsiredWatches(){SELECTINGwatches.click();}
        public String VerifuyToWatch(){return VERIFYwatches.getText();}
        public void WatchWait(){waitUntilClickAble(SELECTINGwatches);}
        public void WaithWait(){implicitWait(5);}
    public void MenFashion_Scrool_Watches(){
        SelectFAshion();
        WaithWait();
        DEsiredWatches();
    }
}
