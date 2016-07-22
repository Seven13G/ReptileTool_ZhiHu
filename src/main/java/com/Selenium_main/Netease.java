package com.Selenium_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class Netease extends Page {

	public Netease(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("163");
	}
	
	public Hao123 returnHao123(){
		SelGeneral.switchToWindow("hao123");
		return PageFactory.initElements(driver, Hao123.class);
	}

}
