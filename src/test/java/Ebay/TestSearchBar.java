package Ebay;

import Ebay.EbayHomepage;
import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.ExcelReader;

import java.util.List;

public class TestSearchBar extends CommonAPI {

    InventoryPage obg = new InventoryPage(driver);
    EbayHomepage searchBar = new EbayHomepage(driver);

    @Test(priority = 1, testName = "regression")
    public void testSearchBar(){
        ExcelReader excelReader = new ExcelReader("C:/Users/Sharmin Zaman/IdeaProjects/FinalProject_GroupA/src/test/resources/TestData.xlsx");
        String searchTerm = excelReader.getDataFromCell("EbayData",0,0);
        //System.out.println(searchTerm);
        searchBar.typeAndSearch(searchTerm);
        List<String> actualSearchResults = obg.getMugSearchResultText();
        for (String s : actualSearchResults){
            if (s.contains(searchTerm)){
                Assert.assertTrue(true);
            } Assert.assertTrue(false);
        }
    }

    @Test
    public void testSearchForBooks() {
        ExcelReader excelReader = new ExcelReader("C:/Users/Sharmin Zaman/IdeaProjects/FinalProject_GroupA/src/test/resources/TestData.xlsx");
        String searchTerm = excelReader.getDataFromCell("EbayData",2,0);
        //System.out.println(searchTerm);

        searchBar.typeAndSearch(searchTerm);
        List<String> actualSearchResults = obg.getMugSearchResultText();
        for (String s : actualSearchResults){
            if (s.contains(searchTerm)){
                Assert.assertTrue(true);
            } Assert.assertTrue(false);
        }

    }
}
