package tests;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import BasePage.BaseTest;
import pages.LoginPage;
import util.DataUtil;

public class TC_Customer2 extends BaseTest {

	
	
	@Test(dataProvider = "getData", priority = 1)
	public void CreateCustomer(Hashtable<String, String> data) throws Exception {
		startreport("Test case 2");
		
		browser();
		extentTest.log(LogStatus.PASS, "Logged in ");
		System.out.println(data.get("Username"));
		LoginPage lp = new LoginPage(driver, extentTest);
		lp.login(data.get("Username"), data.get("Password"));
		

	}

	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(datatable, "TC_Customer", "Customer");

	}  
}
