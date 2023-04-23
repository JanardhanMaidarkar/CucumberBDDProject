package stepdef;


import Utitlities.ReadConfig;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageobjects.AddNewCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Step extends BaseClass {

    //@Before(order = 1)
    @Before("@Sanity")
    public void setup() {

        readConfig = new ReadConfig();
        logger = LogManager.getLogger("Step");
        String browser = readConfig.getBrowser();


        //launch browser
        switch(browser.toLowerCase())
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                break;

            case "msedge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                break;
            default:
                driver = null;
                break;
        }
        System.out.println("Setup methord executed... ");
        logger.info("Setup logger Executed ");
    }

//    //@Before(order = 0)
//    @Before("@Regression")
//    public void setup1(){
//        System.out.println("Setup1 methord executed... ");
//        logger.info("Setup1 logger Executed ");
//    }


    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        loginPage = new LoginPage(driver);
        addNewCustomerPage = new AddNewCustomerPage(driver);
        searchCustomerPage = new SearchCustomerPage(driver);
        logger.info("Browser launch ");

    }
    @When("User opens URL {string}")
    public void user_opens_url(String url) {
    driver.get(url);
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String emailAdd, String pwd) {
    loginPage.enterEmail(emailAdd);
    loginPage.enterPassword(pwd);
        logger.info("Enter Username and Password ");
    }
    @When("Click on Login")
    public void click_on_login() {
    loginPage.clickOnLoginButton();
    }
    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        if(actualTitle.equals(expectedTitle)){
            logger.warn("Test Passed : Login Page Title match ");
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
            logger.warn("Test Failed : Login Page Title not match ");
        }

    }
    @When("User click on Log out link")
    public void user_click_on_log_out_link() {
    loginPage.clickOnLogOutButton();
    logger.info("User Click on LogoutBtn");
    }
    // Add New Customer
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        String actualTitle = addNewCustomerPage.getPageTitle();
        String expectedTitle = "Dashboard / nopCommerce administration";
        if(actualTitle.equals(expectedTitle)){
            logger.warn("Test Passed : Dashboard Title match");
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
            logger.warn("Test Failed : Dashboard Title not match");
        }

    }
    @When("User click on Customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        addNewCustomerPage.clickOnCustomersMenu();
        logger.info("Click on Customer Menu ");
    }
    @When("Click on customer Menu Item")
    public void click_on_customer_menu_item() throws InterruptedException {
        Thread.sleep(3000);
        addNewCustomerPage.clickOnCustomersMenuItem();
        logger.info("Click on Customer Menu item ");
    }
    @When("Click on Add new Button")
    public void click_on_add_new_button() {
        addNewCustomerPage.clickOnAddnew();
        logger.info("Click on Add new Button ");
    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        String actualTitle = addNewCustomerPage.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";
        if(actualTitle.equals(expectedTitle)){
            logger.warn("Test Passed : Customer page Title match");
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
            logger.warn("Test Failed : Customer page Title not match");
        }

    }
    @When("User enter customer info")
    public void user_enter_customer_info() {
       // addNewCustomerPage.enterEmail("jana3@gmail.com");
        addNewCustomerPage.enterEmail(genarateEmailID()+"@gmail.com");
        addNewCustomerPage.enterPassword("test1");
        addNewCustomerPage.enterFirstName("jeevika");
        addNewCustomerPage.enterLastName("maidarkar");
        addNewCustomerPage.enterGender("Female");
        addNewCustomerPage.enterDob("11/15/2014");
        addNewCustomerPage.enterCompanyName("coforge");
        addNewCustomerPage.enterManagerOfVendor("Vendor 1");
        addNewCustomerPage.enterAdminContent("admin");
        logger.info("Enter Customer info Successfully");
    }
    @When("Click on Save button")
    public void click_on_save_button() throws InterruptedException{
        addNewCustomerPage.clickOnSave();
        Thread.sleep(3000);
        logger.info("Click on Save Button");
    }
    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String expectedMessage) {
        String bodyTagText = driver.findElement(By.tagName("Body")).getText();
        if(bodyTagText.contains(expectedMessage))
        {
            logger.warn("Test Passed : Confirmation message is match");
            Assert.assertTrue(true);
        }else{
            Assert.assertTrue(false);
            logger.warn("Test Failed : Confirmation message is not match");
        }
    }
    // search by Email

    @When("Enter customer Email")
    public void enter_customer_email() {
        searchCustomerPage.enterEmailAdd("admin@yourStore.com");
        logger.info("Enter the Email Address ");

    }
    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomerPage.clickOnSearchButton();
        Thread.sleep(5000);
        logger.info("Click on Search Button");
    }
    @Then("User should found Email in the search table")
    public void user_should_found_email_in_the_search_table() {
        String expectedEmail = "admin@yourStore.com";
        Assert.assertTrue((searchCustomerPage.searchCustomerByEmail(expectedEmail)));
    }
// search By Name

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
    searchCustomerPage.enterFirstName("John");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
    searchCustomerPage.enterLastName("Smith");
    }
    @Then("User should found Name in the search table")
    public void user_should_found_name_in_the_search_table() {
        String expectedName = "John Smith";
        if(searchCustomerPage.searchCustomerByName(expectedName)){
           Assert.assertTrue(true);
       }else {
           Assert.assertTrue(false);
       }
    }
   // @After
    public void teardown(Scenario sc){
        System.out.println("TearDown Executed");
        if(sc.isFailed()==true)
        {
            String fileWithPath = "C:\\Users\\Janardhan\\Automation_Project\\CucumberFramework\\ScreenShot\\failedScreenshot.png";
            TakesScreenshot scrShot =((TakesScreenshot)driver);

            //Call getScreenshotAs method to create image file
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
            File DestFile=new File(fileWithPath);

            //Copy file at destination

            try {
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        driver.quit();
    }

    @AfterStep
    public void addScreenshot(Scenario sc){
        if(sc.isFailed()){
         final byte[]  screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
         sc.attach(screenshot,"image/png",sc.getName());
        }
    }
    /*
   @After
   public void teardown1(){
        System.out.println("TearDown1 Executed");
       driver.quit();
    }
      @BeforeStep
  public void beforeStepMethordDemo(){
        System.out.println("This is Before step");
    }
    @AfterStep(order = 0)
    public void afterStepMethordDemo(){
        System.out.println("This is After step");
    }

    @AfterStep(order = 1)
    public void afterStepMethordDemo1(){
        System.out.println("This is After1 step");
    }*/

}
