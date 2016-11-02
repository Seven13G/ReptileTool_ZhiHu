package com.Selenium_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Selenium_FrameWork.General;
import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class LoginZhihu extends Page {


	
	@FindBy(name = "account")
	private WebElement account;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "remember_me")
	private WebElement remember;
	
	@FindBy(css = ".sign-button.submit")
	private WebElement login;
	
	public LoginZhihu(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("www.zhihu.com");
	}
	
	public SearchZhihu clickLogin(String message){
		
		account.sendKeys("");
		password.sendKeys("");
		General.sleep(10);
		login.click();
		
		return PageFactory.initElements(driver, SearchZhihu.class);
	}

}