package com.Selenium_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class Hao123 extends Page {

	@FindBy(id = "slidetoolbar-btn")
	private WebElement toolBar;
	
	@FindBy(css = "#box-site div div ul li:nth-child(5) a")
	private WebElement neteaseLink;
	
	@FindBy(css = "#box-site div div ul li:nth-child(1) a:nth-child(3)")
	private WebElement baiduLink;
	
	public Hao123(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("hao123");
	}
	
	public void slizeToolBar(){
		toolBar.click();
	}
	
	public Netease neteaseLink(){
		neteaseLink.click();
		return PageFactory.initElements(driver, Netease.class);
	}
	
	public Baidu baiduLink(){
		super.mouseOverElement(baiduLink);
		baiduLink.click();
		return PageFactory.initElements(driver, Baidu.class);
	}
}
