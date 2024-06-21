package pageObjects;

import java.util.List;

//PageObjects does not hold any data , it holds only elements and actions that's all.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class Cartpage extends AbstractComponent {
	WebDriver driver;
	
	public Cartpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.cart h3") private List<WebElement> listOfCartProducts;
	@FindBy(xpath="//div[contains(@class,'subtotal')] //li //button") private WebElement checkoutEle;
	
	
	public Boolean verifyProductDisplay(String productname) {
		Boolean match = listOfCartProducts.stream().anyMatch(si->si.getText().contains(productname));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
	}
	
}
