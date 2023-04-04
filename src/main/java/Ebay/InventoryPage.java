package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends CommonAPI {

    @FindBy(xpath = "//span[@class='ebayui-ellipsis-2']") public List<WebElement> mugSearchResults;
    @FindBy(xpath = "//button[@aria-label='Sort selector. Best Match selected.']") public WebElement filterDropdown;
    @FindBy(xpath = "//span[text()='Price + Shipping: lowest first']") public WebElement priceLowToHigh;
    @FindBy(xpath = "//span[@class='s-item__price']") public List <WebElement> prices;
    public InventoryPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    /**
     * Search results and filter price low to high
     * */
    public List <String> getMugSearchResultText (){
        List <String> getMugSearchResultText= new ArrayList<>();
        for (WebElement element: mugSearchResults){
            getMugSearchResultText.add(getTextFromWebElement(element));
        }return getMugSearchResultText;
    }
    public void clickOnFilterDropdown(){
        click(filterDropdown);
    }
    public void clickOnLowToHighFilter(){
        click(priceLowToHigh);
    }
    public List <Float> listOfPrices(){
        List <Float> listPrices = new ArrayList();
        for(int i = 1;i < 70; i++){
           listPrices.add(Float.parseFloat(prices.get(i).getText().substring(1))) ;
        }
//        for(WebElement priceElements: prices){
//            if(!priceElements.getText().equals(" ")){
//                listPrices.add(getTextFromWebElement(priceElements));
//            }
         return listPrices;
    }

}
