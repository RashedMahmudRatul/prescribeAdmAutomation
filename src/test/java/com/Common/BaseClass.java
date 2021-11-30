package com.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Others.CopyPaste;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends CopyPaste {
	public static WebDriver driver;
	public static Properties prop;

	@BeforeSuite
	public void copy() throws IOException {
		copyFile();
	}
	
	@Parameters("browser")
	@BeforeClass
	public void broswerConfig(String bswr) {

		if (bswr.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (bswr.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// Headless
			ChromeOptions obj = new ChromeOptions();
			obj.setHeadless(false);
			driver = new ChromeDriver(obj);
			// driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}
	@BeforeMethod
	public void login() throws FileNotFoundException, InterruptedException {
		readConfig();
		  driver.get(prop.getProperty("loginUrl"));

		 driver.findElement(By.xpath(prop.getProperty("logEmail"))).sendKeys("fahim@aritsltd.com");;
		 driver.findElement(By.xpath(prop.getProperty("logPass"))).sendKeys("123456789");;
		 driver.findElement(By.xpath(prop.getProperty("logBtn"))).click();
		 Thread.sleep(1000);


	}
	
	@AfterMethod
		public void close() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.close();
		}

	public void readConfig() throws FileNotFoundException {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/Configuration/OR.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
