package com.awinbv.rockzent.generalUtility;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.awinbv.rockzent.pomrepositorylib.HomePage;
import com.awinbv.rockzent.pomrepositorylib.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sDriver;
	
	/*Object creation for lib*/
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public FileUtility fLib = new FileUtility();
	public static ExcelUtility eLib = new ExcelUtility();
	public LisImplementClass listen = new LisImplementClass();
	public int randumNum = jLib.getRandomNumber();
	
	
	
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() {
	}
	
//	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC(/*String BROWSER*/) throws Throwable {
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		if(BROWSER.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(BROWSER.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(BROWSER.equals("opera")) {
//			System.setProperty("webdriver.opera.driver", "./src/main/resources/operadriver.exe");
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		sDriver=driver;
		wLib.maximize(driver);
		wLib.waitUntilPageLoad(driver);
		/*Navigate to application*/
		driver.get(URL);
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		/*Login to application*/
		LoginPage loginPage = new LoginPage(driver);
		Reporter.log("PASS: Login page is displayed");
		loginPage.loginToApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		//logout
		new HomePage(driver).signOut();
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() {
		
	}

}
