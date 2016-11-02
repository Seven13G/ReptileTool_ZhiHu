package com.Selenium_FrameWork;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotTestListener extends TestListenerAdapter {
	/*@Override
	public void onTestFailure(ITestResult tr) {
		// make it so screenshots show in reportNG
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<a href=\"" + SelGeneral.takeScreenshot() + "\">ScreenShot</a>");
		Reporter.setCurrentTestResult(null);
		String format = "* " + tr.getName() +" has Failed *";
		String printString="\n";
		int i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n"+format+"\n";
		i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n";
		System.out.println(printString);
	}*/

	@Override
	public void onTestSuccess(ITestResult tr){
		super.onTestSuccess(tr);
		String format = "* " + tr.getName() +" has Passed *";
		String printString="\n";
		int i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n"+format+"\n";
		i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n";
		System.out.println(printString);
	}
	
	@Override
	public void onTestSkipped(ITestResult tr){
		super.onTestSkipped(tr);
		String format = "* " + tr.getName() +" has been Skipped *";
		String printString="\n";
		int i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
			
		}
		printString+="\n"+format+"\n";
		i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n";
		System.out.println(printString);
	}
	@Override
	public void onStart(ITestContext testContext){
		super.onStart(testContext);
		String format = "* " + testContext.getName() +" has Started *";
		String printString="\n";
		int i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
			
		}
		printString+="\n"+format+"\n";
		i = 0;
		while(i < format.length()){
			printString+="*";
			i++;
		}
		printString+="\n";
		System.out.println(printString);
		
	}
}
