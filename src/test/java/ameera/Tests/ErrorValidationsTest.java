package ameera.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ameera.TestComponents.BaseTest;
import ameera.TestComponents.Retry;
import ameera.pageopject.CartPage;
import ameera.pageopject.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest {
// new comments
	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		landingPage.loginApplication("amra@gmail.com", "Amera9@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		String productName = "IPHONE 13 PRO";
		ProductCatalouge productCat = landingPage.loginApplication("ameera@gmail.com", "Amera1919@");
		productCat.addProductToCart(productName);
		CartPage cartPage = productCat.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("IPHONE 13 PRO");
		Assert.assertTrue(match);
	}
}
