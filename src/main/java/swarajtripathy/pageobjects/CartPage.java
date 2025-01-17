package swarajtripathy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swarajtripathy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProds;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	public boolean verifyCartProduct(String productName) {
		boolean match= cartProds.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		clickIt(checkout);
		return new CheckoutPage(driver);
	}
}
