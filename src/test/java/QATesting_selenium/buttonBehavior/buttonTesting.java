package QATesting_selenium.buttonBehavior;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class buttonTesting {
	public static final String URL = "https://the-internet.herokuapp.com/dynamic_controls";
	
	public static final String DISABLE_BUTTON_XPATH = "//button[contains(text(), 'Disable')]";
	public static final String ENABLE_BUTTON_XPATH = "//button[contains(text(), 'Enable')]";
	public static final String TEXT_FIELD_XPATH = "//form[@id = 'input-example']//input[@type = 'text']";
	
	WebDriver driver;
	WebDriverWait normalWait;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.get(URL);
		normalWait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@Test (priority = 1)
	public void enablingTextField() {
		WebElement enableButton = driver.findElement(By.xpath(ENABLE_BUTTON_XPATH));
		enableButton.click();
		
		try {
			WebElement disableButton = normalWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DISABLE_BUTTON_XPATH)));
			assertTrue(disableButton.isDisplayed()); // after 5 seconds the disable button will be displayed
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			WebElement textField = normalWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TEXT_FIELD_XPATH)));
			assertTrue(textField.isEnabled()); // the text field will be enabled
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test (priority = 2)
	public void disablingTextField () {
		WebElement disableButton = driver.findElement(By.xpath(DISABLE_BUTTON_XPATH));
		disableButton.click();
		
		try {
			WebElement enableButton = normalWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ENABLE_BUTTON_XPATH)));
			assertTrue(enableButton.isDisplayed()); // after 5 seconds the enable button will be displayed
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			WebElement textField = normalWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TEXT_FIELD_XPATH)));
			assertFalse(textField.isEnabled()); // the text field will be disabled
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();		
		}
	}
}

