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
    @FindBy (how = How.XPATH, using = scrollupArrow) public WebElement ScrollUP;
    @FindBy (how = How.XPATH, using = EbayHomePAgeEBAY) public WebElement searchPAge;
    public void scrolling() {
        scrollUpDownByHeight();WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ScrollUP));}
    public void waitForthis(){waitUntilClickAble(ScrollUP);}
    public String verification(){return searchPAge.getText();}
 //  public void clickArrow(){ScrollUP.click();}
   public void waitfotit(){waitUntilVisible(searchPAge);}
    public void UPARROW() throws InterruptedException {
        scrolling();
        Thread.sleep(300);
        waitForthis();
        Thread.sleep(2000);
    }
}
