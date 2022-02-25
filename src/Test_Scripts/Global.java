package Test_Scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Global {

	public static WebDriver driver;
	public static Properties prop;
	public static Properties calculateObj;

	String browser;

	@BeforeSuite
	public void initiate() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")
						+ "\\src\\Repository\\Constants.properties");
		prop.load(fis);

		calculateObj = new Properties();
		FileInputStream fcalc = new FileInputStream(
				System.getProperty("user.dir")
						+ "\\src\\Repository\\Calculate.properties");
		calculateObj.load(fcalc);

		browser = prop.getProperty("Browser");
		
		if (browser.equalsIgnoreCase("Mozilla")) {
			driver = new FirefoxDriver();
			
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		

		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void tearDown(){
		driver.close();
	}

}
