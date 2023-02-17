package com.CMS.TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class BaseTest {
	public static WebDriver driver;
	public static Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws SecurityException, FileNotFoundException, IOException {

		String chromeDriverPath = System.getProperty("user.dir") + "\\Tools\\chromedriver.exe";
		String firefoxDriverPath = System.getProperty("user.dir") + "\\Tools\\geckodriver.exe";
		String msedgeDriverPath = System.getProperty("user.dir") + "\\Tools\\msedgedriver.exe";
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", msedgeDriverPath);
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser value!!");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger = Logger.getLogger("CMS");
		LogManager.getLogManager().readConfiguration(new FileInputStream("Log.properties"));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "testData")
	public Object[][] testData() throws IOException {

		DataFormatter formatter = new DataFormatter();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\CMS\\TestData\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount - 1][columnCount];
		for (int i = 0; i < rowCount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;

	}

	public void dataDriven() throws IOException {

		String filepath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\CMS\\TestData\\TestData.properties";
		FileInputStream fis = new FileInputStream(filepath);
		Properties prop = new Properties();
		prop.load(fis);

	}

}
