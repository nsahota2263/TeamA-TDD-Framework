package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static utility.Webelements.EbayWebElements.*;

public class EbaySell extends CommonAPI {
    //calling the webelements from EbayWebElements
    @FindBy (how = How.XPATH,using = SellButon) public WebElement EbaySellButton;
    @FindBy ( how = How.XPATH,using = ListingItemButton) public WebElement pressButton;
    @FindBy (how = How.XPATH, using = EnterTheSellingItem) public WebElement ProductEnter;
    @FindBy (how = How.XPATH, using = ContinuewithoutMatch) public WebElement Productcontinue;
    @FindBy (how = How.XPATH, using = NewButton) public WebElement ClickNew;
    @FindBy (how = How.XPATH, using = ClickButtonForNExt) public WebElement NextButton;
    @FindBy (how = How.XPATH, using = VerifySelling) public WebElement VerifyMacbookSell;
    @FindBy (how = How.XPATH, using = VerifySelling) public WebElement VerifySellingMac;

    // All the steps to add Webelements

    public void Sell(){EbaySellButton.click();}
    public void Press(){pressButton.click();}
    public void Enter(){typeAndEnter(ProductEnter,"Macbook Pro 14 ");}
    public void Continue(){Productcontinue.click();}
    public void ClickNewButton(){ClickNew.click();}
    public void ClickToSkipNEXT(){NextButton.click();}
    public String getVerifymessage(){return VerifyMacbookSell.getText();}

    // This is a test case for selling macbook on ebay.com
    public void Sell_Macbookpro(){
        Sell();
        Press();
        Enter();
        Continue();
        ClickNewButton();
        ClickToSkipNEXT();
    }
}
