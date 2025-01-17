package swarajtripathy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import swarajtripathy.pageobjects.CartPage;
import swarajtripathy.pageobjects.CheckoutPage;
import swarajtripathy.pageobjects.ConfirmationPage;
import swarajtripathy.pageobjects.ProductCatalogue;
import swarajtripathy.testcomponents.BaseTest;

public class stepDefinitionImp extends BaseTest {
	
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	@Given("User lauches the application")
	public void user_launches_application() throws IOException {
		launchApplication();
	}
	
	@Then("^User logs in with (.+) and (.+)$")
	public void user_logs_in(String userName, String password) {
		productCatalogue = loginPage.loginApplication(userName, password);
	}
	
	@When("^User adds (.+) to cart and goes to cart page$")
	public void user_adds_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
		cartPage = productCatalogue.goToCart();
	}
	
	@Then("^User verifies the (.+) in cart and clicks on checkout and submits the order$")
	public void user_verifies_product_and_submits_order(String productName) {
		boolean match = cartPage.verifyCartProduct(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.placeOrder();
	}
	
	@And("^Verifies {string} message in confirmation page$")
	public void user_verfies_confirmation_message(String string) {
		Assert.assertTrue(confirmationPage.confirmationMessage().equalsIgnoreCase(string));
		driver.close();
	}

}
