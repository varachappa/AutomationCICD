package MyAutomationLearning.FrameWorksSelenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		
		String productname= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("random@email.com");
		driver.findElement(By.id("userPassword")).sendKeys("Random@me0");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
//		System.out.println(products.get(0).getText().replaceAll("\n", " ").split(" ")[0].trim());
//		for(WebElement product : products) {
//			String s = product.getText().replaceAll("\n", " ");
//			if(s.contains("ZARA")) {
//			product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
////			//System.out.println(s.split(" ")[0].trim());
//			}
//		}
////		
		WebElement element = products.stream().filter(p->p.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(productname)).findFirst().orElse(null);
		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		System.out.println(element.getText());
		Thread.sleep(2000);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		String s = driver.findElement(By.cssSelector("#toast-container")).getText();
		Assert.assertEquals(s,"Product Added To Cart");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.cssSelector("button[routerlink*='car']")).click();
		
		List<WebElement> list = driver.findElements(By.cssSelector("div.cart h3"));
		Boolean match = list.stream().anyMatch(si->si.getText().contains(productname));
		System.out.println(match);
		
		Actions action = new Actions(driver);
		
		driver.findElement(By.xpath("//div[contains(@class,'subtotal')] //li //button")).click();
//		driver.findElement(By.cssSelector("input[placeholder*='Select']")).sendKeys("India");
		
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select']")), "India").build().perform();
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.xpath("//div/a[@class='btnn action__submit ng-star-inserted']")).click();
//		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText());
		
//		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().contains("THANKYOU "));
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().split(" ")[1].trim());
		
		
		
		

		
		Thread.sleep(3000);
		driver.close();
	}

}

