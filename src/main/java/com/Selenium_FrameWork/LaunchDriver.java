package com.Selenium_FrameWork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import com.Selenium_main.Baidu;
import com.Selenium_main.Hao123;

public class LaunchDriver {
	
	public static final long IMPLICIT_WAIT = 5;
	protected static WebDriver driver;
	
	
	// start Autotask
	public static Hao123 startHao123(String url) {
		launchDriverChrome(url);
		return PageFactory.initElements(driver, Hao123.class);
	}
	
	// start baidu
	public static Baidu baidu(String url) {
		launchDriverChrome(url);
		return PageFactory.initElements(driver, Baidu.class);
	}
	
	public static void launchDriverChrome(String url) {

		DesiredCapabilities capabilites = new DesiredCapabilities();
		capabilites.setBrowserName(DesiredCapabilities.chrome()
				.getBrowserName());
		
		//setup the location of chromedriver.exe
		
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new ChromeDriver();	
		SelGeneral.globalDriver = driver;
		//WaitTool.globalDriver = driver;
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//setup the output file
		SelGeneral.setDirect();
	}

}
