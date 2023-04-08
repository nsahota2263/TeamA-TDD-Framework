package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;

import javax.lang.model.element.Element;

import static utility.Webelements.EbayWebElements.*;
public class AuthenticateBag extends CommonAPI {
    @FindBy(how = How.XPATH, using = Websearchcoloum) public WebElement SearchColoum;
    @FindBy(how = How.XPATH, using = LVBagSelect) public WebElement LvBagSelectingbyUser;
    @FindBy(how = How.XPATH,using = Authonticatebag) public WebElement LVBagAuthentication;

    public void  Searchanenter(){typeAndEnter(SearchColoum, "Louis Vuitton LV Boston");}
    public void ScrolDown(){scrollUpDownByHeight();}

   public void waiting(){waitUntilVisible(LVBagAuthentication);}
    public void SelectLvBag(){LvBagSelectingbyUser.click();}
   public void AuthenticatonBg(){LVBagAuthentication.isDisplayed();}
    public void NextTab(){handleNewTab(driver);}
    public String AssertLV(){return LVBagAuthentication.getText();}

    public void Authenticationg_LV_BAg() throws InterruptedException {
        Searchanenter();
      //  ScrolDown();
        SelectLvBag();
        NextTab();
        waiting();
        Thread.sleep(3000);
        AuthenticatonBg();
    }
}
