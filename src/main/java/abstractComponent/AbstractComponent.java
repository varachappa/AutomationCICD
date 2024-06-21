package abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.Cartpage;
import pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='car']") WebElement cartHeader;
	@FindBy(css="button[routerlink='/dashboard/myorders']") WebElement orderHeader;
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public Cartpage goToCartPage() {
		cartHeader.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
	}
	
	public void waitForELementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
}
