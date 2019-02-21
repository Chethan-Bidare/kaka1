package com.pharmeasy.MercuryUI.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.pharmeasy.MercuryUI.Base.TestBase;

public class Listener extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("About to Start the Test "+result.getName(), true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		
		String res = result.getName();
		
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportDirectory = System.getProperty("user.dir")+"/src/main/java/com/pharmeasy/MercuryUI/Screenshots/SuccessScreenshots/";
			File destFile = new File( reportDirectory + res + "_" + formatter.format(calendar.getTime() + ".png"));
			FileHandler.copy(srcFile, destFile);
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		
		String res = result.getName();
		
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportDirectory = System.getProperty("user.dir")+"/src/main/java/com/pharmeasy/MercuryUI/Screenshots/FailureScreenshots/";
			File destFile = new File( reportDirectory + res + "_" + formatter.format(calendar.getTime()) + ".png");
			FileHandler.copy(srcFile, destFile);
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("About to Skip the Test "+result.getName(), true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log("About to Start the Suite "+context.getName(), true);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log("About to End the Suite "+context.getName(), true);
		
	}

}
