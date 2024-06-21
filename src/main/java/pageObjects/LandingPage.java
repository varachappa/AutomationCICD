package pageObjects;

//PageObjects does not hold any data , it holds only elements and actions that's all.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail") private WebElement useremail;
	@FindBy(id="userPassword") private WebElement userpassword;
	@FindBy(id="login") private WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']") private WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public productCatalogue loginAppication(String email, String password ) {
		useremail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		productCatalogue pc = new productCatalogue(driver);
		return pc;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
