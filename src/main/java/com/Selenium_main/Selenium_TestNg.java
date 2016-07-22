package com.Selenium_main;

import org.testng.annotations.Test;

import com.Selenium_FrameWork.LaunchDriver;

public class Selenium_TestNg {
	
@Test
  public void f() {
	//Hao123 hao123 = LaunchDriver.startHao123("https://www.hao123.com/");
	//hao123.slizeToolBar();
	Baidu bd = LaunchDriver.baidu("https://www.baidu.com/");
	bd.Search("Selenium");
	//Netease netease = hao123.neteaseLink();
	//hao123 = netease.returnHao123();
	//hao123.neteaseLink();
  }
}
