package justATest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoExtentReport {
	
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir")+"/src/main/java"+"/reports/index.html";
		System.out.println(path);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Noob");
		
		
	}
	
	
	@Test
	public void initialDemo() throws InterruptedException {
		ExtentTest test =extent.createTest("initialDemo");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		test.fail("results do not match");
		extent.flush();
		
//		Thread.sleep(3000);
		driver.quit();
		
	}

}
