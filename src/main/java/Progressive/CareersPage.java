package Progressive;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CareersPage extends CommonAPI {

    @FindBy(xpath = "//a[@href='/careers/']")
    public WebElement careersButton;
    @FindBy(xpath = "//div[@class='button']//a[contains(text(),'View job openings')]")
    public WebElement viewJobOpeningsButton;
    @FindBy(xpath = "//tbody/tr/td/div/label/div[@class='checkboxp checkbox-unchecked']")
    public WebElement option;
    @FindBy(xpath = "//a[text()='Add']")
    public WebElement addButton;
    /** STATE*/
    @FindBy(xpath = "a[@id='LOCATION-seeallolf']")
    public WebElement seeAllLocations;
    @FindBy(xpath = "//input[@type='search']")
    public WebElement filterLocations;
    @FindBy(xpath = "//span[text()='Georgia']")
    public WebElement optionGeorgia;
    @FindBy(xpath = "//tbody//tr//td[2]")
    public List<WebElement> stateLocation;
    /** Job Type */
    @FindBy(xpath = "//a[@id='JOB_FIELD-seeallolf']")
    public WebElement seeAllJobField;
    @FindBy(xpath = "//input[@type='search']")
    public WebElement inputJobField;

    @FindBy(xpath = "//tbody//tr//td[1]")
    public List<WebElement> jobCategoryPostings;
    /** Search using keyword*/
    @FindBy(xpath = "//input[@id='KEYWORD']")
    public WebElement inputKEYWORD;

    public CareersPage(WebDriver driver) {PageFactory.initElements(driver,this);}
    /**
     * Navigate to the careers pages
     * Use as background for all careers test cases
     */
    public void clickOnCareersPage() {
        // WebDriver driver = new WebDriver();
        /*WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(careersButton));*/
            click(careersButton);
        }
        // clickWithJS(careersButton, driver);
        //scrollToView(careersButton, driver);
    public void clickOnViewJobOpenings() {
        click(viewJobOpeningsButton);
    }
    /**
     * Use the side filters to search the job openings for a specific state: Georgia
     */
    public void clickSeeAllLocations() {click(seeAllLocations);}
    public void enterStateInFilterLocations(String state) {type(filterLocations,state);}
    public void selectGeorgiaStateFilter(String state) {
        clickOnCareersPage();
        clickOnViewJobOpenings();
        clickSeeAllLocations();
        enterStateInFilterLocations(state);
        clickAddButton();
    }
    public List<String> listOfGeorgiaLocations() {
        List<String> listGeorgia = new ArrayList<>();
        for (WebElement element: stateLocation){
            listGeorgia.add(element.getText());
        }
        return listGeorgia;
    }
    /**
     * Use the side filters to search the job openings for Job category: Sales
     */
    public void clickSeeAllJobField() {
        click(seeAllJobField);
    }
    public void enterValueInJobField(String job) {type(inputJobField,job);}
    //public void selectSales() {click(optionSales);}
    public void clickAddButton() {click(addButton);}
    public void selectSalesJobCategoryFilter(String job) {
        clickOnCareersPage();
        clickOnViewJobOpenings();
        clickSeeAllJobField();
        enterValueInJobField(job);
        clickAddButton();
    }
    public List<String> listOfSalesPositions() {
        List<String> listSale = new LinkedList();
        for (WebElement element: jobCategoryPostings){
            listSale.add(element.getText());
        }
        return listSale;
    }
    /**
     * Use the search KEYWORD input field to search a career of choice: engineer
     */
    public void enterCareerInKEYWORD(String career){
        typeAndEnter(inputKEYWORD,career);
    }
    public void searchUsingKEYWORD(String career) {
        clickOnCareersPage();
        clickOnViewJobOpenings();
        enterCareerInKEYWORD(career);
    }
    public List<String> listOfPositionsUsingKeyword() {
        List<String> listSale = new LinkedList();
        for (WebElement element: jobCategoryPostings){
            listSale.add(element.getText());
        }
        return listSale;
    }

}

