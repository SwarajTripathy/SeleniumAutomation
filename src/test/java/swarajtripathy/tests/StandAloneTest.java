package swarajtripathy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "LG Refrigerator";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("swaraj@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Swaraj@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		Actions a= new Actions(driver);
		WebElement addToCart = prod.findElement(By.cssSelector(".card button:last-of-type"));
		a.click(addToCart).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		a.click(driver.findElement(By.cssSelector("[routerLink*='cart']"))).build().perform();
		
		List<WebElement> cartProds= driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match= cartProds.stream().anyMatch(c->c.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		List<WebElement> options= driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
		options.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		WebElement submit= driver.findElement(By.cssSelector(".action__submit"));
		
		a.click(submit).build().perform();
		
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();


	}

}
