package swarajtripathy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import swarajtripathy.pageobjects.CartPage;
import swarajtripathy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerLink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerLink*=myorders]")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void clickIt(WebElement ele) {
		Actions a = new Actions(driver);
		a.click(ele).build().perform();
	}
	
	public CartPage goToCart() {
		clickIt(cartHeader);
		return new CartPage(driver);
	}
	
	public OrderPage goToOrders() {
		clickIt(orderHeader);
		return new OrderPage(driver);
	}


}
