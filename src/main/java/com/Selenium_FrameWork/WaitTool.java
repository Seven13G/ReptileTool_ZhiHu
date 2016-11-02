package com.Selenium_FrameWork;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTool {
	// times to be used in longWait function
	public static final int LONGWAIT = 5000;

	// times to be used in a shortWait function
	public static final int SHORTWAIT = 2000;

	public static WebDriver globalDriver;

	// wait for "loading" element to be invisible
	public static void ajaxWait() {
		waitForElementToBeInvisibleByLocator(By.id("PopupUpdateProgress"));
		waitForElementToBeInvisibleByLocator(By.id("LoadingIndicator"));
		waitForElementToBeInvisibleByLocator(By.id("ctl00_PopupUpdateProgress"));
		waitForElementToBeInvisibleByLocator(By.cssSelector("#oDragPage_LockOutBody2 img"));
		waitForElementToBeInvisibleByLocator(By.id("hourglass"));
	}

	public static void waitForAutoComplete() {
		waitForElementByLocator(By.id("divAutocomplete"));
	}

	public static void waitForAutoCompleteMVC() {
		waitForElementByLocator(By.className("AutoCompleteResultGroup"));
	}

	// wait for element to be clickable
	public static void waitForElementToBeClickAble(WebElement element) {
		waitForElementToBeClickAble(element, LONGWAIT);
	}

	public static void waitForElementToBeClickableShort(WebElement element) {
		waitForElementToBeClickAble(element, SHORTWAIT);
	}

	public static void waitForElementToBeInvisibleByLocator(By locator) {
		waitForElementToBeInvisibleByLocator(locator, LONGWAIT);
	}

	public static void waitForElementToBeInvisibleByLocatorShort(By locator) {
		waitForElementToBeInvisibleByLocator(locator, SHORTWAIT);
	}

	// wait for element by WebElement name
	public static void waitForElement(WebElement element) {
		waitForElement(element, LONGWAIT);
	}

	public static void waitForElementShort(WebElement element) {
		waitForElement(element, SHORTWAIT);
	}

	// Wait for an element by any identifier.
	// Only used in constructor

	public static WebElement waitForElementByLocator(final By by) {
		return waitForElementByLocator(by, LONGWAIT);
	}

	public static WebElement waitForElementByLocatorShort(final By by) {
		return waitForElementByLocator(by, SHORTWAIT);
	}

	// Waits for an element to appear on the page, by id
	// Only used in constructor
	public static WebElement waitForElementById(final String id) {
		return waitForElementByLocator(By.id(id));
	}

	public static WebElement waitForElementByIdShort(final String id) {
		return waitForElementByLocator(By.id(id), SHORTWAIT);
	}

	// Attempts to wait for the frame to finish loading and checks that it is
	// there
	public static void waitForFrame(final String frameName) {
		waitForFrame(frameName, LONGWAIT);
	}

	// wait for page refresh by catching the StaleelementReferenceException -
	// usually caused by page refreshed
	public static boolean waitForPageRefresh(WebElement trigger) {
		boolean isRefresh = false;
		try {
			for (int i = 1; i < 5; i++) {
				trigger.getText();
				General.sleep(1);
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			return isRefresh;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return isRefresh;
	}

	// click a button/image/link and switch to the popup window
	// will click the element again if it didn't appear
	public static void waitForPopUpOpen(WebElement elementToClick) {
		globalDriver.switchTo().window(waitForPopUpOpen(elementToClick, LONGWAIT));
	}

	// click a WebElement and wait the popup window by url
	public static boolean waitAndSwitchForPopUpOpenByURL(WebElement elementToClick, String url) {
		waitForElement(elementToClick);
		elementToClick.click();
		SelGeneral.acceptAlert();
		int i = 0;
		boolean status = false;
		try {
			status = SelGeneral.switchToWindow(url);
		} catch (NoSuchWindowException e) {
			General.sleep(3);
			status = SelGeneral.switchToWindow(url);
		}
		while (!status && i < LONGWAIT) {
			General.sleep(1);
			status = SelGeneral.switchToWindow(url);
			i++;
		}
		return status;
	}

	// wait for select webelement reload, sometimes its value change based on
	// its parent select
	public static void waitForSelectLoad(WebElement selectToWaitFor) {
		Select select = new Select(selectToWaitFor);
		int timeout = 5;
		do {
			General.sleep(0.5);
			timeout--;
		} while (select.getOptions().size() <= 1 && timeout != 0);
	}

	private static String waitForPopUpOpen(WebElement elementToClick, int waitTime) {
		Set<String> afterPopUp;
		int timeOut = waitTime * 2;
		Set<String> beforePopUp = globalDriver.getWindowHandles();
		waitForElement(elementToClick);
		do {
			SelGeneral.acceptAlert();
			elementToClick.click();
			SelGeneral.acceptAlert();
			General.sleep(0.5);
			afterPopUp = globalDriver.getWindowHandles();
			afterPopUp.removeAll(beforePopUp);
			timeOut -= 1;
			System.out.println("size:"+afterPopUp.size());
		} while (afterPopUp.size() != 1 && timeOut != 0);

		if (afterPopUp.size() == 1) {
			return (String) afterPopUp.toArray()[0];
		} else
			return null;
	}

	private static void waitForElementToBeClickAble(WebElement element, int waitTime) {
		globalDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(globalDriver, waitTime)).until(ExpectedConditions.elementToBeClickable(element));
		globalDriver.manage().timeouts().implicitlyWait(LaunchDriver.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	private static void waitForElementToBeInvisibleByLocator(By locator, int waitTime) {
		globalDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(globalDriver, waitTime)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
		globalDriver.manage().timeouts().implicitlyWait(LaunchDriver.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	// wait for element by WebElement name
	private static void waitForElement(WebElement element, int waitTime) {
		globalDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		(new WebDriverWait(globalDriver, waitTime)).until(ExpectedConditions.visibilityOf(element));
		globalDriver.manage().timeouts().implicitlyWait(LaunchDriver.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	// Wait for an element by any identifier.
	private static WebElement waitForElementByLocator(final By by, int waitTime) {
		globalDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement toWaitFor = (new WebDriverWait(globalDriver, waitTime)).until(ExpectedConditions
				.visibilityOfElementLocated(by));
		globalDriver.manage().timeouts().implicitlyWait(LaunchDriver.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return toWaitFor;
	}

	private static void waitForFrame(final String frameName, int waitTime) {
		try {
			WebDriverWait wdw = new WebDriverWait(globalDriver, waitTime);
			wdw.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static String waitForPopUpOpenModal(WebElement elementToClick) {
	// return waitForPopUpOpenModal(elementToClick, LONGWAIT);
	// }

	// private static String waitForPopUpOpenModal(WebElement elementToClick,
	// int waitTime) {
	// Set<String> afterPopUp;
	// int timeOut = waitTime * 2;
	// Set<String> beforePopUp = globalDriver.getWindowHandles();
	// do {
	// JavascriptExecutor executor = (JavascriptExecutor) globalDriver;
	// executor.executeScript("var el=arguments[0]; setTimeout(function() { el.click(); }, 100);",
	// elementToClick);
	// General.sleep(0.5);
	// afterPopUp = globalDriver.getWindowHandles();
	// afterPopUp.removeAll(beforePopUp);
	// timeOut -= 1;
	// } while (afterPopUp.size() != 1 && timeOut != 0);
	//
	// if (afterPopUp.size() == 1) {
	// return (String) afterPopUp.toArray()[0];
	// } else
	// return null;
	// }
}
