package com.Selenium_main;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

import com.Selenium_FrameWork.LaunchDriver;

public class Selenium_TestNg {
	
@Test
  public void f() throws UnsupportedEncodingException {
	SearchZhihu searchZhihu = LaunchDriver.zhihu("https://www.zhihu.com/search");
	//set the search keywords
	searchZhihu.searchQuestion("韩春雨");
	//expand all the contents
	searchZhihu.clickMore();
	//click one question and get in the link
	QuestionZhihu questionZhihu = searchZhihu.clickLink();
	//show and output the questions and answers
	questionZhihu.showMessage();
  }
}
