package swarajtripathy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swarajtripathy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "span[class='ng-star-inserted']")
	List<WebElement> options;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	public void selectCountry(String countryName) {
		selectCountry.sendKeys(countryName);
		clickIt(options.stream().filter(s -> s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null));
	}
	
	public ConfirmationPage placeOrder() {
		clickIt(placeOrder);
		return new ConfirmationPage(driver);
	}
}
