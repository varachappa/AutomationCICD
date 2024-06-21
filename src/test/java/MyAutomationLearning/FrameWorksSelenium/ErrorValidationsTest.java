package MyAutomationLearning.FrameWorksSelenium;

import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.Assert;
import pageObjects.Cartpage;
import pageObjects.productCatalogue;
import testComponents.BaseTest;

import testComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() {
		String productname= "ZARA COAT 3";
		productCatalogue productcatalague =landingPage.loginAppication("raandom@email.com", "Random@me0");
		
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	@Test
	public void ProductErrorValidation() {
		String productname= "ZARA COAT 3";
		productCatalogue productcatalague =landingPage.loginAppication("random@email.com", "Random@me0");
		productcatalague.addProductToCart(productname);
		Cartpage cartpage=productcatalague.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}


