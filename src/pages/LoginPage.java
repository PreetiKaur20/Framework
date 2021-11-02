package pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import BasePage.BaseTest;

public class LoginPage extends BaseTest {

	@FindBy(xpath = "//*[@id='userid']")
	private WebElement email;

	@FindBy(xpath = "//a[text()='Sign In']")
	private WebElement SignIN;

	@FindBy(xpath = "(//*[@data-test-idp='0'])[1]")
	private WebElement EmailSignIn;

	@FindBy(xpath = "//*[@name='passwd']")
	private WebElement Password;

	@FindBy(xpath = "//*[@id='idSIButton9']")
	private WebElement PasswordSign;

	@FindBy(xpath = "//*[@id='idSIButton9']")
	private WebElement YesButton;

	@FindBy(xpath = "//*[@id='home']")
	private WebElement Home;

	public LoginPage(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		this.extentTest = extentTest;
		PageFactory.initElements(driver, this);

	}

	public void login(String Username, String Pwd) throws InterruptedException, IOException {

		email.sendKeys(Username);
		SignIN.click();
		EmailSignIn.click();
		Password.sendKeys(Pwd);
		PasswordSign.click();
		YesButton.click();
		takeScreenShot();
		System.out.println(Home.getText());
		Assert.assertEquals(Home.getText(), "Home");

	}

}
