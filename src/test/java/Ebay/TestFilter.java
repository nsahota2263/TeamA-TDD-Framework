package Ebay;

import Ebay.EbayHomepage;
import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFilter extends CommonAPI {

    @Test(priority = 2, testName = "regression")
    public void testFilterDropdown(){
       InventoryPage inventoryPage = new InventoryPage(driver);
       EbayHomepage searchBar = new EbayHomepage(driver);
       searchBar.typeAndSearch("iphone");
        inventoryPage.clickOnFilterDropdown();
        inventoryPage.clickOnLowToHighFilter();
        List<Float> prices = inventoryPage.listOfPrices();
        //System.out.println(prices);
        List<Float> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertTrue(prices.equals(sortedPrices));
    }

}
