package Test_Scripts;

import static Test_Scripts.Global.driver;
import static Test_Scripts.Global.calculateObj;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MortgageCalculate {

	@Test
	public void thirdMethod() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");

		driver.findElement(By.xpath(calculateObj.getProperty("rates_widget")))
				.click();

		Thread.sleep(1000);
		WebElement Advanced_Rate = driver.findElement(By.xpath(calculateObj
				.getProperty("date_options")));
		Actions builder = new Actions(driver);
		builder.moveToElement(Advanced_Rate);
		builder.build().perform();

		driver.findElement(By.xpath(calculateObj.getProperty("custom_range")))
				.click();
		driver.findElement(By.xpath(calculateObj.getProperty("custom_month"))).sendKeys("10");

	}

}
