package Progressive;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolsAndCalculatorPage extends CommonAPI {

    @FindBy(xpath = "//a[@id='resources']")
    public WebElement resourcesButton;

    @FindBy(xpath = "//body[1]/div[1]/div[4]/nav[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[4]/a[1]/span[1]")
    public WebElement toolsAndCalculatorSelection;

    public ToolsAndCalculatorPage(WebDriver driver){PageFactory.initElements(driver, this);}


    public void clickResourcesButton(){
        click(resourcesButton);

    }

    public void clickToolsAndCalculator(){
        click(toolsAndCalculatorSelection);
    }
}
