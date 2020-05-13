import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportDemo1 {

    WebDriver driver1 = null ;
    ExtentReports extent = null;
    @BeforeTest
    public void setupWebdriver() {

        // ExtentSparkReporter , ExtentReports
        String path = System.getProperty("user.dir")+"\\extent-reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        extent  = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Rahula Suba");


        System.setProperty("webdriver.chrome.driver","C:\\Rahula Space\\Drivers\\chromedriver_win32_ver79\\chromedriver.exe");
        driver1 = new ChromeDriver();
    }

    @Test
    public void gotoAmazonIndiaHomePage() {
        ExtentTest test = extent.createTest("gotoAmazonIndiaHomePage");
        driver1.get("http://www.amazon.com");
        System.out.println(driver1.getTitle());

        //test.fail("Results do not match for the amazon page Title"); // line to purposefully fail the test in the reports
    }

    @AfterTest
    public void endSetup() {
        try {
            extent.flush();
            driver1.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
