import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testing {
	public WebDriver driver =null;
	
	
	@BeforeMethod
	
	public void beforetest() {
		System.out.println("Before Test");
		System.out.println("Test Energy");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PREETI\\Documents\\Project12\\chromedriver.exe");
	    driver = new ChromeDriver();
	}
	
	@AfterMethod
	public void afterTest() {
		System.out.println("After Test");
	}
	@Test
	public void test() {
	
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//*[text()='Ich stimme zu']")).click();
		System.out.println(driver.getTitle());
		driver.close();
		
		
	}

	
	@Test
	public void test1() {
		driver.get("https://www.google.com");
	
		driver.findElement(By.xpath("//*[text()='Ich stimme zu']")).click();
		System.out.println(driver.getTitle());
		driver.close();
		
		
	}
}
