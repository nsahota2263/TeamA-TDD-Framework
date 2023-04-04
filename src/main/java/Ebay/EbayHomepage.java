package Ebay;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayHomepage extends CommonAPI {

    @FindBy(xpath = "//input[@id='gh-ac']") public WebElement searchBar;
    @FindBy(xpath = "//input[@id='gh-btn']") public WebElement searchBarButton;


    public EbayHomepage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    /**
     * Type any item in the ebay search bar and search
     * */
    public void typeItem(String item) {
        type(searchBar, item);
    }
    public void searchAnItem(){
        click(searchBarButton);
    }
    public void typeAndSearch(String item){
        type(searchBar, item);
        click(searchBarButton);
    }

}
