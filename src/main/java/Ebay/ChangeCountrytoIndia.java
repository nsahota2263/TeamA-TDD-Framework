package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static utility.Webelements.EbayWebElements.*;
public class ChangeCountrytoIndia extends CommonAPI {
    @FindBy(how = How.XPATH, using = Countryhover) public WebElement CNHover;
    @FindBy(how = How.XPATH, using = CountrySelect) public WebElement SelectIndian;
    @FindBy(how = How.XPATH, using = CountryConfirm) public WebElement ConfirmIndian;
    public void HoverOver(){mouseHover(CNHover);}
    public void India(){SelectIndian.click();}
    public void SCROLLDOWN(){scrollUpDownByHeight();}
    public String ConfirmationOfIndia(){return ConfirmIndian.getText();}
    public void ChangeMyCountryToIndia(){
        SCROLLDOWN();
        HoverOver();
        India();
    }


}
