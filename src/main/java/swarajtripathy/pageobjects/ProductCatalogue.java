package swarajtripathy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swarajtripathy.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProduct(String productName) {
		WebElement prod = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		clickIt(getProduct(productName).findElement(addToCart));
		waitForElementToAppear(toastMessage);
		Thread.sleep(3000);
	}
	
}
