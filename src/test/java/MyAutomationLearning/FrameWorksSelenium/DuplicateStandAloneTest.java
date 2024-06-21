package MyAutomationLearning.FrameWorksSelenium;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import pageObjects.Cartpage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.productCatalogue;
import testComponents.BaseTest;

public class DuplicateStandAloneTest extends BaseTest{
	@Test(groups= {"reference"})
	public void submitOrder() throws IOException {
		
		//Added comments here just to test the Git and Github 
		String productname= "ZARA COAT 3";

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		
//		
//		//===========
//		
//		LandingPage landingPage = new LandingPage(driver);
//		
//		landingPage.goTo();
//		driver.get("https://rahulshettyacademy.com/client");
		
		
		
		productCatalogue productcatalague =landingPage.loginAppication("random@email.com", "Random@me0");
		
//		driver.findElement(By.id("userEmail")).sendKeys("random@email.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Random@me0");
//		driver.findElement(By.id("login")).click();
		
//		productCatalogue productcatalague = new productCatalogue(driver);//we return this class and written on above line ;
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
//		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
//		System.out.println(products.get(0).getText().replaceAll("\n", " ").split(" ")[0].trim());
//		for(WebElement product : products) {
//			String s = product.getText().replaceAll("\n", " ");
//			if(s.contains("ZARA")) {
//			product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
////			//System.out.println(s.split(" ")[0].trim());
//			}
//		}
//////		
		productcatalague.addProductToCart(productname);
//		WebElement element = products.stream().filter(p->p.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(productname)).findFirst().orElse(null);
//		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		System.out.println(element.getText());
//		Thread.sleep(2000);
	
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		String s = driver.findElement(By.cssSelector("#toast-container")).getText();
//		Assert.assertEquals(s,"Product Added To Cart");
		
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		Cartpage cartpage=productcatalague.goToCartPage();
//		driver.findElement(By.cssSelector("button[routerlink*='car']")).click();
		
		
		
		
//		List<WebElement> listofCartProducts = driver.findElements(By.cssSelector("div.cart h3"));
//		Boolean match = listofCartProducts.stream().anyMatch(si->si.getText().contains(productname));
		Boolean match = cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
//		System.out.println(match);
		
		CheckoutPage checkoutpage = cartpage.goToCheckout();
//		driver.findElement(By.xpath("//div[contains(@class,'subtotal')] //li //button")).click();
//		driver.findElement(By.cssSelector("input[placeholder*='Select']")).sendKeys("India");
		
		checkoutpage.selectCountry("India");
//		Actions action = new Actions(driver);
		
		
		
//		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select']")), "India").build().perform();
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
//		driver.findElement(By.xpath("//div/a[@class='btnn action__submit ng-star-inserted']")).click();
//		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText());
		
//		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().contains("THANKYOU "));
		
//		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		Assert.assertTrue(confirmationpage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
		
//		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().split(" ")[1].trim());
//		
		
		

//		Thread.sleep(3000);
//		driver.close();

		
	}

}

