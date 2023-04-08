package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static utility.Webelements.EbayWebElements.*;
public class EbayHomepage2 extends CommonAPI {
    //calling the webelements from EbayWebElements
    @FindBy(how = How.XPATH, using = Websearchcoloum) public WebElement Websearcher;
    @FindBy(how = How.XPATH, using = Editdiameter) public WebElement DiameterSetter;
    @FindBy(how = How.XPATH, using = checkingtherresult) public WebElement resultedcount;
    @FindBy(how = How.XPATH,using = selectingdesiredproduct)public WebElement SelectedAlloys12;
    @FindBy(how = How.XPATH,using = addtocart)public WebElement added;
    @FindBy(how = How.XPATH,using = AssertAlloyWheels)public WebElement verify;
    @FindBy(how = How.XPATH,using = VerifyAlloyWheels)public WebElement verify3;
    @FindBy(how = How.XPATH,using = VerifyAddedtoMyCart)public WebElement verify4;



    // All the steps to add Webelements
    public void submitForSearchResult() {typeAndEnter(Websearcher,"Alloy Wheels");}
    public void maximunSize(){windowMaximize();}
    public void clickthediameter() {DiameterSetter.click();}
    public void Confirmation(){checkDisplayed(resultedcount);}
    public void clicktheOneIlike2 () {SelectedAlloys12.click();}
    public void scrooltoview () {scrollToView(SelectedAlloys12, driver);}
    public void CARTADDITION(){added.click();}
    public void waitDown_coolDown(){waitUntilClickAble(added);}
    public String VerifyAlooyewheelsDiameter(){return resultedcount.getText();}
    public String VerifyThatItISClickble(){return verify.getText();}
    public void NextTab(){handleNewTab(driver);}
    public String assertSelectingmyFavorate(){return verify3.getText();}
    public String assertaddedtoCart(){return verify4.getText();}
    // This is a test case for searching a product from the search bar of Ebay homepage and filtering it.
    public void checkOutMYResult() {
        submitForSearchResult();
        clickthediameter();
    }
    public void Actual_Result_Of_number(){
        submitForSearchResult();
        clickthediameter();
       Confirmation();
    }
    public void selecting_My_Favorate() throws InterruptedException {
        submitForSearchResult();
        clickthediameter();
        scrooltoview();
        clicktheOneIlike2();
        NextTab();
        Thread.sleep(300);
    }
    public void ADDING_TO_MY_EBAY_CART() throws InterruptedException {
        submitForSearchResult();
        clickthediameter();
        scrooltoview();
        clicktheOneIlike2();
        NextTab();
        Thread.sleep(3000);
        CARTADDITION();
    }
}
