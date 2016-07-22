package com.Selenium_FrameWork;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class SelGeneral {
	public static WebDriver globalDriver;
	public static String suiteName;
	private static String direct;

	static int screenshotNumber = 1;

	// accept alert
	public static void acceptAlert() {
		if (SelGeneral.hasAlert()) {
			try {
				globalDriver.switchTo().alert().accept();
				// globalDriver.switchTo().window(globalDriver.getWindowHandle());
			} catch (NoSuchWindowException e) {
				globalDriver.switchTo().window(
						globalDriver.getWindowHandles().iterator().next());
			}
		}
	}

	// Changes to a different window by part of it's URL
	public static boolean switchToWindow(String url) {
		// String winHandle = null;
//		acceptAlert();
//		try {
//			// globalDriver.navigate().refresh();
//			globalDriver.switchTo().window(globalDriver.getWindowHandle());
//			if (globalDriver.getCurrentUrl().contains(url)) {
//
//				return true;
//			}
//		} catch (NoSuchWindowException e) {
//			globalDriver.switchTo().window(
//					globalDriver.getWindowHandles().iterator().next());
//		}
		Set<String> handles = globalDriver.getWindowHandles();
		// System.out.println("Going into the loop.");

		for (String s : handles) {
			globalDriver.switchTo().window(s);
			if (globalDriver.getCurrentUrl().contains(url)) {
				return true;
				// winHandle = s;
				// return s;
			}
		}
		// return winHandle;
		return false;
	}

	// public static int getWindows() {
	// Set<String> handles = globalDriver.getWindowHandles();
	// return handles.size();
	// }

	// Close a browser and switch to another
	public static void closeAWindowAndSwitchToAnotherByURL(String urlToClose,
			String urlToSwitch) {
		Set<String> handles = globalDriver.getWindowHandles();
		for (String s : handles) {
			globalDriver.switchTo().window(s);
			if (globalDriver.getCurrentUrl().contains(urlToClose)) {
				globalDriver.close();
				SelGeneral.switchToWindow(urlToSwitch);
			}
		}

	}

	// Close all browser except Landing and switch to another
	public static void closeAllWindowExceptLandingAndSwitchToAnotherByURL(
			String urlToSwitch) {
		Set<String> handles = globalDriver.getWindowHandles();
		for (String s : handles) {
			globalDriver.switchTo().window(s);
			if (!globalDriver.getCurrentUrl().contains(urlToSwitch)
					|| !globalDriver.getCurrentUrl().contains("Landing")) {
				globalDriver.close();

			}
		}
		SelGeneral.switchToWindow(urlToSwitch);

	}

	// check whether there is an alert
	public static boolean hasAlert() {
		// Get a handle to the open alert, prompt or confirmation
		try {
			globalDriver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		} catch (NoSuchWindowException e2) {
			globalDriver.switchTo().window(
					globalDriver.getWindowHandles().iterator().next());
			return false;
		}
	}

	// dismiss alert
	public static void dismissAlert() {
		if (hasAlert()) {
			globalDriver.switchTo().alert().dismiss();
		}
	}

	// used to restart a page
	public static Page reInitiatePage(Page pageToRestart) {
		return PageFactory.initElements(globalDriver, pageToRestart.getClass());
	}

	public static void click(WebElement elementToClick) {
		WaitTool.waitForElement(elementToClick);
		elementToClick.click();
	}

	public static String takeScreenshot() {
		try {
			if (direct.isEmpty()) {
				setDirect();
			}
			File dir = new File(direct);
			dir.mkdirs();
			// Create screen shot of the page
			File source = ((TakesScreenshot) globalDriver)
					.getScreenshotAs(OutputType.FILE);
			File path = new File(dir, suiteName + General.timeStamp()
					+ "Screenshot.png");
			FileUtils.copyFile(source, path);
			screenshotNumber++;
			return path.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setDirect() {
		// direct = "c:/Results/" + SuiteName + "/" + timeStamp();
		//direct = System.getProperty("user.dir") + "/test-output/html";
			direct = "../Level2-output/Screenshots/";
	}

	public static void clickWebElementWithClickingContral(WebElement webElement) {
		Actions action = new Actions(globalDriver);
		action.keyDown(Keys.CONTROL).perform();
		webElement.click();
		action.keyUp(Keys.CONTROL).perform();
	}

	public static void dragAndDropWebElement(WebElement source,
			WebElement target) {
		Actions action = new Actions(globalDriver);
		// action.clickAndHold(source).moveByOffset(0,
		// target.getLocation().getY()).perform();
		// action.release();
		// action.moveToElement(source).perform();
		action.dragAndDrop(source, target).perform();
		// action.dragAndDropBy(source, target.getLocation().getX(),
		// target.getLocation().getY()).perform();
	}

	public static int getWindowWidth(String url) {
		if (switchToWindow(url)) {
			return globalDriver.manage().window().getSize().getWidth();
		} else
			return 0;
	}
	
	public static Page refreshPage(Page pageToRefresh){
		globalDriver.navigate().refresh();
		return PageFactory.initElements(globalDriver, pageToRefresh.getClass());
	}
}
