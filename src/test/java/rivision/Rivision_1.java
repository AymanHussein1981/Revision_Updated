package rivision;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Rivision_1 {
	
	WebDriver driver;
	String Browser;
	String URL;
	String userName;
	String Password;

	By LOGIN_BUTTON=By.xpath("//*[@id=\"login_submit\"]");
	By USER_NAME_FIELD=By.xpath("//*[@id=\"user_name\"]");
	By PASSWORD_FIELD=By.xpath("//*[@id=\"password\"]");
	By CUSTOMERS_BUTTON=By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a");
	By ADD_CUSTOMER_BUTTON=By.xpath("//*[@id=\"customers\"]/li[2]/a");
	By FULL_NAME_FIELD=By.xpath("//*[@id=\"general_compnay\"]/div[1]/div/input");
	By EMAIL_FIELD=By.xpath("//*[@id=\"general_compnay\"]/div[3]/div/input");
	By PHONE_NUMBER_FIELD=By.xpath("//*[@id=\"phone\"]");
	By COMPANY_DROPDWON=By.xpath("//*[@id=\"general_compnay\"]/div[2]/div/select");
	By COUNTRY_DROPDOWN=By.xpath("//*[@id=\"general_compnay\"]/div[8]/div[1]/select");
	By SAVE_BUTTON=By.xpath("//*[@id=\"save_btn\"]");
	By DEMO_BUTTON=By.xpath("/html/body/div[1]/header/nav/div[2]/ul[2]/li/a/span");
	By LOGOUT_BUTTON=By.xpath("/html/body/div[1]/header/nav/div[2]/ul[2]/li/ul/li[2]/form/div/button");
	
	@BeforeClass
	public void config() {
	
		try {
			InputStream input=new FileInputStream("src\\main\\java\\rivision\\config.properties");
			Properties prop=new Properties();
			prop.load(input);
			Browser = prop.getProperty("browser");
			URL=prop.getProperty("url");
			userName=prop.getProperty("username");
			Password=prop.getProperty("password");
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod
	public void init() {
		
		
		if(Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("browser is not valid!!!");
		}
		
		driver.get(URL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test(priority=1)
	public void popup() throws InterruptedException {
		
		driver.findElement(LOGIN_BUTTON).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.switchTo().alert().getText(), "Please enter your user name", "Error Message!!!");
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
	}
	
	
	@Test(priority=2)
	public void popup1() throws InterruptedException {
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(LOGIN_BUTTON).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.switchTo().alert().getText(), "Please enter your password", "Error Message!!!");
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
	}
	
	
	@Test(priority=3)
	public void Assertion() throws InterruptedException {
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(Password);
		driver.findElement(LOGIN_BUTTON).click();
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not found!!!");
		Thread.sleep(2000);
	}
	
	
	@Test(priority=4)
	public void DropDown() throws InterruptedException {
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(Password);
		driver.findElement(LOGIN_BUTTON).click();
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not found!!!");
		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		Random rnd= new Random();
		int random=rnd.nextInt(999);
		driver.findElement(FULL_NAME_FIELD).sendKeys("Travel One"+random);
		Select sel = new Select(driver.findElement(COMPANY_DROPDWON));
		sel.selectByVisibleText("Dream Team");
		driver.findElement(EMAIL_FIELD).sendKeys("travelone"+random+"@gmail.com");
		driver.findElement(PHONE_NUMBER_FIELD).sendKeys("9647841"+random);
		Select sel1=new Select(driver.findElement(COUNTRY_DROPDOWN));
		sel1.selectByVisibleText("United States of America");
		driver.findElement(SAVE_BUTTON).click();
		Thread.sleep(2000);
		
	}
	
	
	
	@Test(priority=5)
	public void iFrame() throws InterruptedException {
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(Password);
		driver.findElement(LOGIN_BUTTON).click();
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not found!!!");
		driver.switchTo().frame("advertisement");
		driver.findElement(By.xpath("//*[@id=\"ad_three\"]/a/img")).click();
		driver.switchTo().frame("portals");
		driver.findElement(By.xpath("//*[@id=\"portal_three\"]/div")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("customer_groupaddframe");
		driver.findElement(By.xpath("//*[@id=\"customer_groupName\"]")).sendKeys("Ayman");
		Thread.sleep(5000);
	}
	
	
	@Test(priority=6)
	public void MouseHoverANDkeyboardEventAndscroll() throws InterruptedException {
		
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(Password);
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Assert.assertEquals(driver.getTitle(), "Codefios", "Page not found!!!");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scroll(0,500)");
		Thread.sleep(2000);
		js.executeScript("scroll(0,0)");
		action.moveToElement(driver.findElement(DEMO_BUTTON)).build().perform();
		driver.findElement(LOGOUT_BUTTON).click();
		Thread.sleep(2000);
		
	}

	

	@AfterMethod
	public void teardown() {
		
		driver.close();
		driver.quit();
	}

}
