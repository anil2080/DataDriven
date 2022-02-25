package Test_Scripts;

import static Test_Scripts.Global.driver;
import static Test_Scripts.Global.calculateObj;
//import static Test_Scripts.Global.xls;

import static Test_Scripts.Global.prop;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test_Scripts.Xls_Reader;

public class MortgageValue {

	@DataProvider
	public Object[][] AddData() throws IOException {
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")
				+ "\\src\\TestData\\Data.xlsx");

		int rows = xls.getRowCount("Sheet1");
		int cols = xls.getColumnCount("Sheet1");
		Object data[][] = new Object[rows - 1][cols];

		for (int rNum = 2; rNum <= rows; rNum++) {
			for (int cNum = 0; cNum < cols; cNum++) {
				data[rNum - 2][cNum] = xls.getCellData("Sheet1", cNum, rNum);
			}
		}
		return data;
	}

	@Test(alwaysRun = true, dataProvider = "AddData")
	public void secondMethod(String Home, String LoanAmount, String Interest,
			String LoanTerm, String PropertyTax, String PMI)
			throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath(calculateObj.getProperty("Home"))).clear();
		Thread.sleep(500);

		Home = Home.split("\\.")[0];
		driver.findElement(By.xpath(calculateObj.getProperty("Home")))
				.sendKeys(Home);

		driver.findElement(By.xpath(calculateObj.getProperty("loanamount")))
				.clear();
		Thread.sleep(500);

		LoanAmount = LoanAmount.split("\\.")[0];

		driver.findElement(By.xpath(calculateObj.getProperty("loanamount")))
				.sendKeys(LoanAmount);

		driver.findElement(By.xpath(calculateObj.getProperty("term"))).clear();
		driver.findElement(By.xpath(calculateObj.getProperty("term")))
				.sendKeys(LoanTerm);

		driver.findElement(By.xpath(calculateObj.getProperty("calculate")))
				.click();
	}

}
