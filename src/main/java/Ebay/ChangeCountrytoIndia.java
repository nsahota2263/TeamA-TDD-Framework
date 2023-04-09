package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static utility.Webelements.EbayWebElements.*;

public class ChangeCountrytoIndia extends CommonAPI {
    //calling the webelements from EbayWebElements
    @FindBy(how = How.XPATH, using = Countryhover) public WebElement CNHover;
    @FindBy(how = How.XPATH, using = CountrySelect) public WebElement SelectIndian;
    @FindBy(how = How.XPATH, using = CountryConfirm) public WebElement ConfirmIndian;

    // All the steps to add Webelements
    public void HoverOver(){mouseHover(CNHover);}
    public void India(){SelectIndian.click();}
    public void SCROLLDOWN(){scrollUpDownByHeight();}
    public String ConfirmationOfIndia(){return ConfirmIndian.getText();}

    // This is a test case for changing the country to India

    public void ChangeMyCountryToIndia(){
        SCROLLDOWN();
        HoverOver();
        India();
    }
}
