package com.CMS.TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.CMS.Utilities.ConfigParser;

public class BaseTest {
	public static WebDriver driver;
	public static Logger logger;
	public static ConfigParser configFilesobj;
	public static ConfigParser testDataobj;
	public static String testDataFilepath = "";
	public static String configDataFilepath = "";
	public String filepath = "";
	// public static String url = testDataobj.getPropertiesValue("url");
	Properties prop;

	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional String browser) throws SecurityException, FileNotFoundException, IOException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		if (browser == null) {
			browser = "chrome";
		}
		configDataFilepath = System.getProperty("user.dir") + "\\ConfigFiles\\config.properties";
		configFilesobj = new ConfigParser(configDataFilepath);
		testDataFilepath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\CMS\\TestData\\TestData.properties";
		testDataobj = new ConfigParser(testDataFilepath);
		
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
	
	@DataProvider(name = "testData_Password")
	public Object[][] testData_Password() throws IOException {

		DataFormatter formatter = new DataFormatter();
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\CMS\\TestData\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(1);
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

	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshot = ts.getScreenshotAs(OutputType.FILE);
			filepath = System.getProperty("user.dir") + "//Screenshots//" + testCaseName + ".png";
			File file = new File(filepath);
			FileUtils.copyFile(screenshot, file);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filepath;

	}

	public void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
