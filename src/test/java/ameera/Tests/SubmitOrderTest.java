package ameera.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ameera.TestComponents.BaseTest;
import ameera.pageopject.CartPage;
import ameera.pageopject.CheckoutPage;
import ameera.pageopject.ConfiramtionPage;
import ameera.pageopject.OrderPage;
import ameera.pageopject.ProductCatalouge;

public class SubmitOrderTest extends BaseTest {
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalouge productCat = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCat.addProductToCart(input.get("productName"));
		CartPage cartPage = productCat.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		ConfiramtionPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfiramtionMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	
	@Test(dependsOnMethods = { "submitOrder" }) // Dependency
	public void OrderHistoryTest() {
		ProductCatalouge productCat = landingPage.loginApplication("ameera@gmail.com", "Amera1919@");
		OrderPage orderPage = productCat.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	// Extent Reports 

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/ameera/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
