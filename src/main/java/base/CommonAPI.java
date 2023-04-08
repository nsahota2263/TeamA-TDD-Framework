package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CommonAPI {
    // All the setup and Helper method will be here

    //ExtentReport
    public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws IOException {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }
        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            // captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    //Browser SetUp
    public static WebDriver driver = null;
    public String browserstack_username = "nicholassahota_gv49nl";
    public String browserstack_accesskey = "qnWwYivPx4A9CSc3xyPF";
    public String saucelabs_username = "monsurahmed1";
    public String saucelabs_accesskey = "48bd964a-506f-4ad1-a5f5-f37e060bbea5";


    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("false") String cloudEnvName,
                      @Optional("windows") String os, @Optional("10") String os_version, @Optional("chrome-options") String browserName, @Optional("81")
                      String browserVersion, @Optional("https://www.google.com") String url) throws IOException {

        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(os, browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.get(url);
        //driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver(@Optional("mac") String OS, String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "Driver/MacDriver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "driver/ChromeDriver/chromedriver.exe");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("chrome-options")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "Driver/MacDriver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "driver/ChromeDriver/chromedriver.exe");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", "Driver/MacDriver/MyGECKODRIVE");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "driver/GeckoDriver/geckodriver.exe");
            }
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/BrowserDriver/windows/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        return driver;
    }


    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if (envName.equalsIgnoreCase("Saucelabs")) {
            //resolution for Saucelabs
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        //driver.close();
        driver.quit();
    }

    /********************************** HELPER METHODS ***************************************/

    /**Click using a WebElement*/
    public void click(WebElement element){
        try {
            element.click();
        } catch(Exception ex){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    /**Type using a WebElement*/
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
    public void typeAndEnter(WebElement element, String text){
        element.sendKeys(text, Keys.ENTER);
    }
    //Armachen's methods to submit
    public void submit(WebElement element) { element.submit(); }
    public void typSubmit(WebElement element, String string) { element.sendKeys(string); element.submit(); }

    /**Clear entry using a WebElement*/
    public void clear(WebElement element){ element.clear();}

    /**Refresh page*/
    public void refreshPage(){
        driver.navigate().refresh();
    }

    /**Get URL or Title*/
    public void getTitle() {
        driver.getTitle();
    }
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }
    public String getUrlLink(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public void getLinks(String linkText){
        driver.findElement(By.linkText(linkText)).findElement(By.tagName("a")).getText();
    }

    /** Navigate*/
    public void navigateBack() {
        driver.navigate().back();
    }
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }
    public void navigateForward() {
        driver.navigate().forward();
    }
    public void navigateRefresh() {
        driver.navigate().refresh();
    }

    /** Window Handling*/
    public static void windowMaximize() {
        driver.manage().window().maximize();
    }
    public void goBackToHomeWindow() {
        driver.switchTo().defaultContent();
    }
    public static void windowSwitch() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
    public static void switchToPreviousWindow(String winHandleBefore) {
        driver.switchTo().window(winHandleBefore);
    }

    /** MouseHover*/
    public static void mouseHover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
    //Mehnaz's method for mouse hover over with xpath
    public void mouseHoverByXpath(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    /** wailing */
    public static void scrollUpDownByHeight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public void scrollToView(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /** Drag and Drop */
    public static void dragAndDrop(WebElement To, WebElement from) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(To, from).build().perform();
    }
    /**  DROPDOWNS  */
    public void selectFromDropdown(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        try {
            select.selectByVisibleText(option);
        } catch (Exception e) {
            select.selectByValue(option);
        }
    }

    /**CHECKBOXES*/
    private boolean flag;

    public List<WebElement> getDropDownOptions (WebElement dropdown) {
        Select select = new Select(dropdown);
        return select.getOptions();
    }
    public static void selectDropDownByIndex(WebElement element, int value) {
        Select select = new Select(element);
        select.selectByIndex(value);
    }
    public boolean checkSelected(WebElement element){
        if (element.isSelected()){
            flag = true;
        }
        return flag;
    }
    public boolean checkNotSelected(WebElement element){
        if (!element.isSelected()){
            flag = true;
        }
        return flag;
    }
    public boolean checkEnabled(WebElement element){
        flag = (element.isEnabled());
        return flag;
    }
    public boolean checkDisabled(WebElement element){
        flag = (!element.isEnabled());
        return flag;
    }
    public boolean checkDisplayed(WebElement element){
        flag = element.isDisplayed();
        return flag;
    }
    public boolean checkNotDisplayed(WebElement element){
        flag = (!element.isDisplayed());
        return flag;
    }

    /** Handle alerts */
    public String alertMessage() {
        String alertMessage = driver.switchTo().alert().getText();
        return alertMessage;
    }
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /** Are pop-ups displayed
     * Boolean
     */
    public static boolean isPopUpWindowDisplayed(WebElement element) {
        boolean value = element.isDisplayed();
        return value;
    }

    /** Handle iFrames  */
    public void iFrameHandle(WebElement element){
        driver.switchTo().frame(element);
    }

    /**Handling New Tabs */
    public static WebDriver handleNewTab(WebDriver driver1) {
        String oldTab = driver1.getWindowHandle();
        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
        newTabs.remove(oldTab);
        driver1.switchTo().window(newTabs.get(0));
        return driver1;
    }

    /**Get text using a WebElement*/
    public String getTextFromWebElement(WebElement element) {
        String text = element.getText();
        return text;
    }
    public String getText(WebElement element){
        String text = element.getText();
        return text;
    }
    //Shoumik's method to get text
    public String getElementTextWithElement(WebElement element) {
        String text = element.getText();
        return text;
    }
    public String getElementText(String locator){
        try {
            return driver.findElement(By.cssSelector(locator)).getText();
        }catch (Exception e){
            return driver.findElement(By.xpath(locator)).getText();
        }
    }

    /**Get List of text using List<WebElement>*/
    public List<String> getListOfText(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }
        return items;
    }
    public static List<String> getTextFromWebElements(String locator) {
        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for (WebElement web : element) {
            String st = web.getText();
            text.add(st);
        }
        return text;
    }
    public List<WebElement> getListOfWebElementsById(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));
        return list;
    }

    public static List<WebElement> getListOfWebElementsByCss(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.cssSelector(locator));
        return list;
    }


    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    /***************************    WAITS      *****************************/
    public static void implicitWait(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }
    public void waitUntilClickAble(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitUntilVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitUntilSelectable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    /** Take Screenshots */
    public void captureScreenshot() {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


