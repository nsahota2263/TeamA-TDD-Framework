package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static utility.Webelements.EbayWebElements.*;
public class OPenStore extends CommonAPI {
    @FindBy(how = How.XPATH,using = SelectStores)public WebElement StoreSelectButton;
    @FindBy(how = How.XPATH,using = OpenStoreButton)public WebElement StoreOpen;
    @FindBy(how = How.XPATH,using =VerifySelling )public WebElement Verify;

    public void Store(){StoreSelectButton.click();}
    public void Open(){StoreOpen.click();}
    public void scroll(){scrollUpDownByHeight();}
    public String Verifing(){return Verify.getText();}
    public void TestOpenStore(){
        scroll();
        Store();
        Open();
    }

}

