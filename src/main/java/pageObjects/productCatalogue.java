package pageObjects;

import java.util.List;

import org.openqa.selenium.By;

//PageObjects does not hold any data , it holds only elements and actions that's all.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class productCatalogue extends AbstractComponent  {
	WebDriver driver;
	
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".col-lg-4") private List<WebElement> products;
	
	By productsBy = By.cssSelector(".col-lg-4");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname) {

		WebElement element = getProductList().stream().filter(p->p.findElement(By.xpath("//b[text()='ZARA COAT 3']")).getText().equals(productname)).findFirst().orElse(null);
		return element ;
	}
	
	public  void addProductToCart(String productname) {
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForELementToDisappear(toastMessage);
	}
	
	
}
