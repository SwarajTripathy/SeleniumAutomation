package swarajtripathy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import swarajtripathy.pageobjects.CartPage;
import swarajtripathy.pageobjects.CheckoutPage;
import swarajtripathy.pageobjects.ConfirmationPage;
import swarajtripathy.pageobjects.LoginPage;
import swarajtripathy.pageobjects.ProductCatalogue;
import swarajtripathy.testcomponents.BaseTest;
import swarajtripathy.testcomponents.Retry;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		loginPage.loginApplication("swaraj@gmail.com", "Swaraj@12");
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");

	}

	@Test(groups = { "ErrorValidation" })
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "LG Refrigerator";
		ProductCatalogue productCatalogue = loginPage.loginApplication("john.snow@gmail.com", "Welcome@123");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyCartProduct("Iphone");
		Assert.assertFalse(match);

	}

}
