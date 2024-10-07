package rivision;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Rivision_2 {

	WebDriver driver;
	String Browser;
	String URL;
	
	
	@BeforeClass
	public void config() {
	
		try {
			InputStream input=new FileInputStream("src\\main\\java\\rivision\\config.properties");
			Properties prop=new Properties();
			prop.load(input);
			Browser = prop.getProperty("browser");
			URL=prop.getProperty("url1");
			
			
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
	
	
	@Test
	public void WindowHandel() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"ybar-sbq\"]")).sendKeys("selenium");
		driver.findElement(By.xpath("//*[@id=\"sa-item0\"]")).click();
		Thread.sleep(2000);
		System.out.println(driver.getWindowHandle());
		Set<String>handles=driver.getWindowHandles();
		for(String str:handles) {
			driver.switchTo().window(str);
			System.out.println(str);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"web\"]/ol/li[1]/div/div[1]/h3/a")).click();
		
		
	}
}
