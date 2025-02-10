package ameera.pageopject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.abstractsComponents.AbstractComponent;

public class ConfiramtionPage extends AbstractComponent {

	WebDriver driver;

	public ConfiramtionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	private WebElement confirmMessage;

	public String getConfiramtionMessage() {
		return confirmMessage.getText();

	}

}
