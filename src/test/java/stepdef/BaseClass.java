package stepdef;

import Utitlities.ReadConfig;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageobjects.AddNewCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;
import org.apache.logging.log4j.*;

import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public LoginPage loginPage;
    public AddNewCustomerPage addNewCustomerPage;
    public SearchCustomerPage searchCustomerPage;

    public static Logger logger;

    public ReadConfig readConfig;

    public String genarateEmailID(){
       return( RandomStringUtils.randomAlphabetic(5));
    }
}
