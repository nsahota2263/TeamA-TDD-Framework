package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utility.Webelements.EbayWebElements.*;

public class ScrollUpArrow extends CommonAPI {
    //calling the webelements from EbayWebElements
    @FindBy (how = How.XPATH, using = scrollupArrow) public WebElement ScrollUP;
    @FindBy (how = How.XPATH, using = EbayHomePAgeEBAY) public WebElement searchPAge;

    // All the steps to add Webelements
    public void scrolling() {
        scrollUpDownByHeight();WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ScrollUP));}
    public void waitForthis(){waitUntilClickAble(ScrollUP);}
    public String verification(){return searchPAge.getText();}
   public void waitfotit(){waitUntilVisible(searchPAge);}


    // This is a test case for scrolling all the wy up from bottom of the homepage using up aroow button .


    public void UPARROW() throws InterruptedException {
        scrolling();
        Thread.sleep(300);
        waitForthis();
        Thread.sleep(2000);
    }
}
