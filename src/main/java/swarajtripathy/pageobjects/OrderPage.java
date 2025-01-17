package swarajtripathy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swarajtripathy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> orders;
	
	public boolean verifyOrder(String productName) {
		boolean match= orders.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		return match;
	}
}
