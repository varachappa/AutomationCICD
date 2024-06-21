package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver ; 

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="input[placeholder*='Select']") private WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)") private WebElement selectCountry;
	@FindBy(xpath="//div/a[@class='btnn action__submit ng-star-inserted']") private WebElement submit;
	
	public void selectCountry(String countryname) {
		
		Actions action = new Actions(driver);
		action.sendKeys(country, countryname).build().perform();
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	
	
	
	

}
