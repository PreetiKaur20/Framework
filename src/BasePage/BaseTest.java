package BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;
import util.DataUtil;
import util.FBConstants;
import util.ReadPropertyFile;
import util.Xls_Reader;

public class BaseTest {
	// public static final String run_env = System.getProperty("environment");
	public static final String run_env = "BT";
	public static Properties config = null;
	public static Properties OR = null;
	public WebDriver driver = null;
	public static Logger log = Logger.getLogger("");

	public static boolean loggedIn = false;
	public static Xls_Reader datatable = null;
	public ExtentReports extentReports = null;
	public ExtentTest extentTest = null;
	public static final String LOG_PATH = FBConstants.REPORTS_PATH + "WEB\\";
	private static final String FileUtils = null;

	@BeforeSuite
	public void initialize() throws IOException, InterruptedException {

		System.out.println(System.getProperty("user.dir"));
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\log\\log4j.properties");

		config = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\config.properties");
		config.load(fp);

		OR = new Properties();
		fp = new FileInputStream(System.getProperty("user.dir") + "\\src\\config\\OR.properties");
		OR.load(fp);
		datatable = new Xls_Reader(System.getProperty("user.dir") + "\\src\\data\\" + run_env + "_Data.xlsx");
		System.out.println(config.getProperty("browserType"));
		log.info("Initializing all elements");

	}

	// @AfterTest
	// public void afterTest() {
	// driver.quit();
	// }

	// call after every method
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {

		extentReports.endTest(extentTest);
		extentReports.flush();
		extentReports.close();

	}

	public void browser() throws IOException, InterruptedException {
		if ((config.getProperty("browserType")).equals("Firefox")) {

			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);

		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			System.out.println(config.getProperty("BO_BT"));
			driver.get(config.getProperty("BO_BT"));

		}

	}

	public void reportFailure(String failureMessage) {
		extentTest.log(LogStatus.FAIL, failureMessage);
		Assert.fail(failureMessage);
	}

	public void startreport(String s) {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
		String reportPath = LOG_PATH + fileName;
		extentReports = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);
		extentReports.loadConfig(new File(System.getProperty("user.dir") + "\\ReportsConfig.xml"));
		extentReports.addSystemInfo("Selenium Version", "3.53.0").addSystemInfo("Environment", run_env);

		extentTest = extentReports.startTest(s);
	}

	public void takeScreenShot() throws IOException {

		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String filePath = FBConstants.REPORTS_PATH + "\\screenshots\\" + screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scrFile, new File(filePath));
		extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(filePath));

	}

}