package runner;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;



//@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src//test//resources//features//",
        //features = "src//test//resources//features//AddCustomers.feature",
        //features = {"src//test//resources//features//AddCustomers.feature","src//test//resources//features//loginPage.feature"},
        glue = "stepdef",
        dryRun = false,
        monochrome = true,
        tags = "@Sanity",
        //tags = "@Sanity and @Regression",//scenarios under @sanity tag will be executed
//        plugin = {"pretty",
//                "html:target/cucumber-reports/reports_html.html",
//                "json:target/cucumber-reports/reports_json.json",
//                "junit:target/cucumber-reports/reports_xml.xml"}
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

//plugin = {"pretty","html:target/cucumber-reports/reports1.html"}
//		plugin = {"pretty","json:target/cucumber-reports/report_json.json"}

public class TestRunner
        extends AbstractTestNGCucumberTests{
    /*This class will be empty*/
}