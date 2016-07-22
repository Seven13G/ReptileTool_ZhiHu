package com.Selenium_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class Baidu extends Page {

	@FindBy(id = "kw")
	private WebElement inputMessage;
	
	@FindBy(id = "su")
	private WebElement searchButton;
	
	public Baidu(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("baidu");
	}
	
	public void Search(String message){
		
		inputMessage.sendKeys(message);
		searchButton.click();
	}

}
