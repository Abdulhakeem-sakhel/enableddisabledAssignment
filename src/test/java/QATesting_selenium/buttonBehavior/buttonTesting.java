package QATesting_selenium.buttonBehavior;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class buttonTesting {
	public static final String URL = "https://the-internet.herokuapp.com/dynamic_controls";
	
	public static final String DISABLE_BUTTON_XPATH = "//button[contains(text(), 'Disable')]";
	public static final String ENABLE_BUTTON_XPATH = "//button[contains(text(), 'Enable')]";
	public static final String TEXT_FIELD_XPATH = "//form[@id = 'input-example']//input[@type = 'text']";
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(URL);
	}

	@Test (priority = 1)
	public void enablingTextField() {
		WebElement enableButton = driver.findElement(By.xpath(ENABLE_BUTTON_XPATH));
		enableButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement disableButton = driver.findElement(By.xpath(DISABLE_BUTTON_XPATH));
		assertTrue(disableButton.isDisplayed()); // after 5 seconds the disable button will be displayed

		WebElement textField = driver.findElement(By.xpath(TEXT_FIELD_XPATH));
		assertTrue(textField.isEnabled()); // the text field will be enabled
	}

	@Test (priority = 2)
	public void disablingTextField () {
		WebElement disableButton = driver.findElement(By.xpath(DISABLE_BUTTON_XPATH));
		disableButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement enableButton = driver.findElement(By.xpath(ENABLE_BUTTON_XPATH));
		assertTrue(enableButton.isDisplayed()); // after 5 seconds the enable button will be displayed

		WebElement textField = driver.findElement(By.xpath(TEXT_FIELD_XPATH));
		assertFalse(textField.isEnabled()); // the text field will be disabled
	}
	
	@AfterClass
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();		
		}
	}
}

