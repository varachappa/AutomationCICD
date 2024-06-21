package MyAutomationLearning.FrameWorksSelenium;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import pageObjects.Cartpage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.productCatalogue;
import testComponents.BaseTest;


public class SubmitOrderTest extends BaseTest {
	public String productName = "ZARA COAT 3";
    @Test(dataProvider="getData",groups= {"purchase"})
    public void submitOrder(HashMap<String , String> input) throws IOException {
        
        productCatalogue productcatalague = landingPage.loginAppication(input.get("email"), input.get("password"));
        productcatalague.addProductToCart(input.get("productname"));
        Cartpage cartpage = productcatalague.goToCartPage();
        Boolean match = cartpage.verifyProductDisplay(input.get("productname"));
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
        checkoutpage.selectCountry("India");
        ConfirmationPage confirmationpage = checkoutpage.submitOrder();
        Assert.assertTrue(confirmationpage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
    }
    @Test(dependsOnMethods="submitOrder")
    public void orderHistoryTest() {
    	 productCatalogue productcatalague = landingPage.loginAppication("random@email.com", "Random@me0");
    	 OrderPage orderpage=productcatalague.goToOrdersPage();
    	 Assert.assertTrue(orderpage.verifyOderDisplay(productName));
    }
    
    
   
    @DataProvider
    public Object[][] getData() throws IOException {
//    	return new Object[][] {{"random@email.com","Random@me0","ZARA COAT 3"},{"random1@gmail.com","Random@me1","ZARA COAT 3"}};
    	
    	List<HashMap<String , String>> data =getJsonDataToMap("/Users/vara/eclipse-workspace/FrameWorksSelenium/src/test/java/data/PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}


