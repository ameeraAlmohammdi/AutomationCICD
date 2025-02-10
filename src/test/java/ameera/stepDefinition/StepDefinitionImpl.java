package ameera.stepDefinition;

import java.io.IOException;
import org.testng.Assert;

import ameera.TestComponents.BaseTest;
import ameera.pageopject.CartPage;
import ameera.pageopject.CheckoutPage;
import ameera.pageopject.ConfiramtionPage;
import ameera.pageopject.LandingPage;
import ameera.pageopject.ProductCatalouge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPge;
	public ProductCatalouge productCatalouge;
	public ConfiramtionPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password) {
		productCatalouge = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException {
		// List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} messgae is displayed on confirmation")
	public void Messgae_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfiramtionMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void Messgae_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
