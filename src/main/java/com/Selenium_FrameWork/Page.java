package com.Selenium_FrameWork;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
		// Driver that exist for all pages

		public WebDriver driver;

		// WebDriverWait that exist for all pages

		public WebDriverWait wait;
		
		// WebDriverWait long wait
		public static final int LONGWAIT = 20;

		// Default constructor for all Page classes

		public Page(WebDriver driver) {
			this.driver = driver;
			wait = new WebDriverWait(driver, 10);
		}

		// Constructor to change the timeout for the {@link WebDriverWait}

		public Page(WebDriver driver, int timeOut) {
			this.driver = driver;
			wait = new WebDriverWait(driver, timeOut);
		}
/*
 * Here is the parts of Mouse and Keyboard events
 */
		// common function for mouseOver to element
		public void mouseOverElement(WebElement target) {
			Actions action = new Actions(driver);
			action.moveToElement(target).perform();
		}
		
		// common function for right click
		public void rightClick(WebElement target){
			Actions action = new Actions(driver);
			action.contextClick(target);
		}
		
		// common function for double click
		public void doubleClick(WebElement target){
			Actions action = new Actions(driver);
			action.doubleClick(target);
		}
		
		// common function for click and hold
		public void clickAndHold(WebElement target){
			Actions action = new Actions(driver); 
			 action.clickAndHold(target);
		}
		
		// common function for drag and drop
		public void dragAndDrop(WebElement source, WebElement target){
			Actions action = new Actions(driver); 
			 action.dragAndDrop(source,target);
		}
		
		// common function for specific drag and drop
		public void dragAndDropSpecific(WebElement source, int xOffset, int yOffset){
			Actions action = new Actions(driver); 
			action.dragAndDropBy(source,xOffset,yOffset);
		}
		
}
