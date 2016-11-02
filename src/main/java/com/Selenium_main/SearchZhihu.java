package com.Selenium_main;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Selenium_FrameWork.General;
import com.Selenium_FrameWork.Page;
import com.Selenium_FrameWork.SelGeneral;

public class SearchZhihu extends Page {


	
	@FindBy(id = "q")
	private WebElement questionInput;
	
	@FindBy(name = "password")
	private WebElement password;
	
	@FindBy(name = "remember_me")
	private WebElement remember;
	
	@FindBy(css = ".zu-top-search-button")
	private WebElement search;
	
	@FindBy(css = ".zg-btn-white.zu-button-more")
	private WebElement moreButton;
	
	@FindBy(css = ".js-title-link")
	private List<WebElement> questionLists;
	
	@FindBy(css = ".btn-backtotop.btn-action")
	private WebElement ups;
	
	public SearchZhihu(WebDriver driver) {
		super(driver);
		SelGeneral.switchToWindow("www.zhihu.com");
	}
	
	public void searchQuestion(String questionKey){
			questionInput.sendKeys(questionKey);
			search.click();
	}
	
	public void clickMore(){
		
		while(super.verifyElementExsitByLocator(By.cssSelector(".zg-btn-white.zu-button-more")))
		{	
			moreButton.click();
			General.sleep(2);
		}
		ups.click();
	}
	
	public QuestionZhihu clickLink(){
		int s= 0;
		for(WebElement x : questionLists)
		{
			s++;
			if(x.getAttribute("href").contains("question")){
			General.sleep(2);
			x.click();
			questionLink = x.getAttribute("href");
			questionName = x.getText();
			questionNumber = s;
			QuestionZhihu qz = enterLink();
			qz.showMessage();
			}
			//questionLists.get(i).click();
		}
		//if(super.verifyElementExsitByLocator(By.cssSelector(".btn-backtotop.btn-action")));
		//ups.click();
		//General.sleep(2);
		//questionLists.get(0).click();
		
		return PageFactory.initElements(driver, QuestionZhihu.class);
	}
	
	public QuestionZhihu enterLink(){
		//WaitTool.waitForPopUpOpen(e);
		return PageFactory.initElements(driver, QuestionZhihu.class);
	}
	
	
	
	
	//去除html的正则表达式	
		public static String delHTMLTag(String htmlStr){ 
	        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
	         
	        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	        Matcher m_script=p_script.matcher(htmlStr); 
	        htmlStr=m_script.replaceAll(""); //过滤script标签 
	         
	        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	        Matcher m_style=p_style.matcher(htmlStr); 
	        htmlStr=m_style.replaceAll(""); //过滤style标签 
	         
	        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	        Matcher m_html=p_html.matcher(htmlStr); 
	        htmlStr=m_html.replaceAll(""); //过滤html标签 

	        return htmlStr.trim(); //返回文本字符串 
	    } 

}