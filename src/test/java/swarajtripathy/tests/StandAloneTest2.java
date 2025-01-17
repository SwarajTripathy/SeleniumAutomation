package swarajtripathy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import swarajtripathy.pageobjects.CartPage;
import swarajtripathy.pageobjects.CheckoutPage;
import swarajtripathy.pageobjects.ConfirmationPage;
import swarajtripathy.pageobjects.LoginPage;
import swarajtripathy.pageobjects.OrderPage;
import swarajtripathy.pageobjects.ProductCatalogue;
import swarajtripathy.testcomponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StandAloneTest2 extends BaseTest {

	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyCartProduct(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		Assert.assertTrue(confirmationPage.confirmationMessage().equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderVerfication() {
		ProductCatalogue productCatalogue = loginPage.loginApplication("swaraj@gmail.com", "Swaraj@123");
		OrderPage orderPage = productCatalogue.goToOrders();
		Assert.assertTrue(orderPage.verifyOrder(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\swarajtripathy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

// @DataProvider
//	HashMap<String,String> map= new HashMap<String,String>();
//	map.put("email", "swaraj@gmail.com");
//	map.put("password", "Swaraj@123");
//	map.put("productName", "IPHONE 13 PRO");
//	
//	HashMap<String,String> map1= new HashMap<String,String>();
//	map1.put("email", "john.snow@gmail.com");
//	map1.put("password", "Welcome@123");
//	map1.put("productName", "LG Refrigerator");

	// return new Object[][] {{"swaraj@gmail.com","Swaraj@123","IPHONE 13
	// PRO"},{"john.snow@gmail.com","Welcome@123","LG Refrigerator"}};

}
