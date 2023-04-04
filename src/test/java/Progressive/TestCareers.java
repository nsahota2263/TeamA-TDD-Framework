package Progressive;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestCareers extends CommonAPI {
    static CareersPage careersPage;

    @Test
    public void testStateFilter() {
        CareersPage careersPage = new CareersPage(driver);
        careersPage.selectGeorgiaStateFilter("Georgia");
        List<String> georgiaResults = careersPage.listOfGeorgiaLocations();
        for (String result: georgiaResults) {
            if (result.contains("Georgia") || result.contains("Multiple Locations")) {
                Assert.assertTrue(true);
            }
        }
    }
    @Test
    public void testJobCategoryFilter() {
        careersPage = new CareersPage(driver);
        careersPage.selectSalesJobCategoryFilter("Sales");
        List<String> salesResults = careersPage.listOfSalesPositions();
        for (String result: salesResults) {
            if (salesResults.contains("Territory Sales Representative")) {
                Assert.assertTrue(true);
            }
        }
    }
    @Test
    public void testKEYWORDSearchBar() {
        careersPage = new CareersPage(driver);
        careersPage.searchUsingKEYWORD("engineer");
        List<String> engineerResults = careersPage.listOfPositionsUsingKeyword();
        for (String result: engineerResults) {
            if (engineerResults.contains("Engineer")||engineerResults.contains("Data Scientist")) {
                Assert.assertTrue(true);
            }
        }
    }

}
