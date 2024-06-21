package testComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		
		Properties property = new Properties();
		///Users/vara/eclipse-workspace/FrameWorksSelenium/src/main/java/resources/GlobalData.properties
		FileInputStream file = new FileInputStream("/Users/vara/eclipse-workspace/FrameWorksSelenium/src/main/java/resources/GlobalData.properties");
		property.load(file);
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") :property.getProperty("browser");
		
		if(browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
//			driver.manage().window().setSize(new Dimension(1440,900));
	//		driver.manage().window().maximize();
	//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}else if(browsername.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}else {
			System.out.println("Please! Enter a valid browser name correctly");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	
		
		}
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath));
		
//		String filepath = "/Users/vara/eclipse-workspace/FrameWorksSelenium/src/test/java/data/PurchaseOrder.json";
//		String jsonContent = FileUtils.readFileToString(Paths.get(filepath));
		ObjectMapper objmapper = new ObjectMapper(); //jackson DataBind dependency
		List<HashMap<String , String>> data = objmapper.readValue(jsonContent, new TypeReference<List<HashMap<String ,String>>>(){});
		return data ; 
		
	}
	
	 public String getScreenshot(String testCasename,WebDriver driver) throws IOException {
	    	TakesScreenshot shot = (TakesScreenshot)driver;
	    	File source =shot.getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(source,new File("/Users/vara/eclipse-workspace/FrameWorksSelenium/src/main/java/screenShots/"+testCasename+".png"));
	    	return "/Users/vara/eclipse-workspace/FrameWorksSelenium/src/main/java/screenShots/"+testCasename+".png";
	    }
	
		@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException {
			driver=initializeDriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
		
		
//		
		@AfterMethod(alwaysRun=true)
		public void tearDown() {

//			Thread.sleep(3000);
			driver.close();
		}
	}

